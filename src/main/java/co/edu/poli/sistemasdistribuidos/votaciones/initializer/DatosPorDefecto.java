package co.edu.poli.sistemasdistribuidos.votaciones.initializer;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.*;
import co.edu.poli.sistemasdistribuidos.votaciones.service.*;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Long.parseLong;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Component
@Transactional
public class DatosPorDefecto implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatosPorDefecto.class);
    public static final String FORMATO_TIMESTAMP = "yyyy-MM-dd'T'HHmm";
    public static final String FORMATO_DATE = "yyyy-MM-dd";

    @Value("${votaciones.inicializar-datos-por-defecto}")
    private boolean inicializarDatosPorDefecto;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @Autowired
    private PartidoService partidoService;

    @Autowired
    private CandidatoService candidatoService;

    @Autowired
    private EleccionService eleccionService;

    @Autowired
    private ObjectParser objectParser;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            if (inicializarDatosPorDefecto && !isBaseDeDatosInicializada()) {
                crearRoles();
                crearPartidos();
                crearUsuarios();
                crearCandidatos();
                crearElecciones();
            }
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un error en la inicialización de datos por defecto", e);
        }
    }

    private void crearCandidatos() {
        List<String[]> candidatos = objectParser.obtenerListaCadenas("data/candidatos.csv");
        for (String[] candidato : candidatos) {
            crearCandidato(candidato);
        }
    }

    private void crearCandidato(String[] rawCandidato) {
        CandidatoEntity candidato = new CandidatoEntity();
        candidato.setId(parseLong(rawCandidato[0]));
        candidato.setBiografia(rawCandidato[1]);
        candidato.setFoto(rawCandidato[2]);

        String idPartidoString = rawCandidato[3];
        PartidoEntity partido = partidoService.buscarPorId(parseLong(idPartidoString));
        candidato.setPartido(partido);

        String idUsuarioString = rawCandidato[4];
        UsuarioEntity usuario = usuarioService.buscarPorId(parseLong(idUsuarioString));
        candidato.setUsuario(usuario);

        candidatoService.guardar(candidato);
    }

    private void crearElecciones() throws ParseException {
        List<String[]> eleccionesRaw = objectParser.obtenerListaCadenas("data/elecciones.csv");
        for (String[] eleccionRaw : eleccionesRaw) {
            crearEleccion(eleccionRaw);
        }
    }

    private void crearEleccion(String[] eleccionRaw) throws ParseException {
        EleccionEntity eleccion = new EleccionEntity();
        eleccion.setId(parseLong(eleccionRaw[0]));
        eleccion.setDescripcion(eleccionRaw[1]);
        eleccion.setInicioInscripcion(parseTimestamp(eleccionRaw[2]));
        eleccion.setFinInscripcion(parseTimestamp(eleccionRaw[3]));
        eleccion.setInicioEleccion(parseTimestamp(eleccionRaw[4]));
        eleccion.setFinEleccion(parseTimestamp(eleccionRaw[5]));
        eleccion.setNombre(eleccionRaw[6]);

        String candidatosRaw = eleccionRaw[7];
        if (isNotBlank(candidatosRaw)) {
            String[] idCandidatosArray = candidatosRaw.split(",");
            for (String idCandidatoRaw : idCandidatosArray) {
                CandidatoEntity candidato = candidatoService.buscarPorId(parseLong(idCandidatoRaw));
                eleccion.agregarCandidato(candidato);
            }
        }

        eleccionService.guardar(eleccion);
    }

    private Date parseTimestamp(String str) throws ParseException {
        return DateUtils.parseDate(str, FORMATO_TIMESTAMP);
    }

    private Date parseDate(String s) throws ParseException {
        return DateUtils.parseDate(s, FORMATO_DATE);
    }

    private void crearPartidos() {
        List<String[]> partidos = objectParser.obtenerListaCadenas("data/partidos.csv");
        for (String[] partido : partidos) {
            crearPartido(partido);
        }
    }

    private void crearPartido(String[] partidoRaw) {
        PartidoEntity partido = new PartidoEntity();
        partido.setId(parseLong(partidoRaw[0]));
        partido.setNombre(partidoRaw[1]);
        partidoService.guardar(partido);
    }

    private void crearRoles() {
        List<String[]> roles = objectParser.obtenerListaCadenas("data/roles.csv");
        for (String[] rol : roles) {
            crearRol(rol);
        }
    }

    private void crearRol(String[] rawRol) {
        if (!rolService.existe(rawRol[1])) {
            RolEntity rol = new RolEntity();
            rol.setId(parseLong(rawRol[0]));
            rol.setNombre(rawRol[1]);

            rolService.guardar(rol);
        }
    }

    private void crearUsuarios() throws ParseException {
        List<String[]> usuarios = objectParser.obtenerListaCadenas("data/usuarios.csv");
        for (String[] usuario : usuarios) {
            crearUsuario(usuario);
        }
    }

    private void crearUsuario(String[] rawUsuario) throws ParseException {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(parseLong(rawUsuario[0]));
        usuario.setUsername(rawUsuario[1]);
        usuario.setPassword(rawUsuario[2]);
        usuario.setPrimerNombre(rawUsuario[3]);
        usuario.setSegundoNombre(rawUsuario[4]);
        usuario.setPrimerApellido(rawUsuario[5]);
        usuario.setSegundoApellido(rawUsuario[6]);
        usuario.setCorreo(rawUsuario[7]);
        usuario.setFechaDeNacimiento(parseDate(rawUsuario[8]));
        usuario.setActivo(parseBoolean(rawUsuario[9]));

        String rawIdRoles = rawUsuario[10];
        if (isNotBlank(rawIdRoles)) {
            String[] idsRolStringArray = rawIdRoles.split(",");
            for (String idRolString : idsRolStringArray) {
                RolEntity rol = rolService.buscarPorId(parseLong(idRolString));
                usuario.agregarRol(rol);
            }
        }

        usuarioService.guardar(usuario);
    }

    private boolean isBaseDeDatosInicializada() {
        return usuarioService.conteoDeUsuarios() > 0;
    }
}
