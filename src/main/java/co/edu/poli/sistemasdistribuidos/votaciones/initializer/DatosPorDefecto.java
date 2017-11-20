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
import java.util.HashSet;
import java.util.Set;

@Component
@Transactional
public class DatosPorDefecto implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatosPorDefecto.class);

    public static final String PARTIDO_LIBERAL = "Liberal";
    public static final String PARTIDO_CONSERVADOR = "Conservador";
    public static final String PARTIDO_POLO_DEMOCRATICO = "Polo Democrático";
    public static final String PARTIDO_VERDE = "Partido Verde";
    public static final String FORMATO_TIMESTAMP = "yyyy.MM.dd.HH.mm.ss";

    @Value("${votaciones.inicializar-datos-por-defecto}")
    private boolean inicializarDatosPorDefecto;

    @Value("${security.user.name}")
    private String usuarioPorDefectoUsername;

    @Value("${security.user.password}")
    private String usuarioPorDefectoPassword;

    @Value("${votaciones.usuario-por-defecto.primer-nombre}")
    private String usuarioPorDefectoPrimerNombre;

    @Value("${votaciones.usuario-por-defecto.segundo-nombre}")
    private String usuarioPorDefectoSegundoNombre;

    @Value("${votaciones.usuario-por-defecto.primer-apellido}")
    private String usuarioPorDefectoPrimerApellido;

    @Value("${votaciones.usuario-por-defecto.segundo-apellido}")
    private String usuarioPorDefectoSegundoApellido;

    @Value("#{new java.text.SimpleDateFormat(\"yyyyMMdd\").parse(\"${votaciones.usuario-por-defecto" +
            ".fecha-nacimiento}\")}")
    private Date usuarioPorDefectoFechaNacimiento;

    @Value("${votaciones.usuario-por-defecto.correo}")
    private String usuarioPorDefectoCorreo;

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

    private Set<CandidatoEntity> candidatosEleccion1 = new HashSet<>();
    private Set<CandidatoEntity> candidatosEleccion2 = new HashSet<>();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            if (inicializarDatosPorDefecto && !isBaseDeDatosInicializada()) {
                crearRoles();
                crearPartidos();
                crearElecciones();
                crearUsuarios();
            }
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un error en la inicialización de datos por defecto", e);
        }
    }

    private void crearElecciones() throws ParseException {
        EleccionEntity eleccionPresidencial = new EleccionEntity();
        eleccionPresidencial.setCandidatos(candidatosEleccion1);
        eleccionPresidencial.setNombre("Elecciones Presidenciales 2018");
        eleccionPresidencial.setDescripcion("Elecciones Presidenciales 2018");
        eleccionPresidencial.setInicioEleccion(parseDate("2018.05.24.08.00.00"));
        eleccionPresidencial.setFinEleccion(parseDate("2018.05.24.16.00.00"));
        eleccionPresidencial.setInicioInscripcion(parseDate("2017.10.01.08.00.00"));
        eleccionPresidencial.setFinInscripcion(parseDate("2017.12.15.08.00.00"));

        eleccionService.guardar(eleccionPresidencial);

        EleccionEntity consultaLiberal = new EleccionEntity();
        consultaLiberal.setCandidatos(candidatosEleccion1);
        consultaLiberal.setNombre("Consulta Liberal");
        consultaLiberal.setDescripcion("Consulta Liberal");
        consultaLiberal.setInicioEleccion(parseDate("2017.11.19.08.00.00"));
        consultaLiberal.setFinEleccion(parseDate("2017.11.19.16.00.00"));
        consultaLiberal.setInicioInscripcion(parseDate("2017.10.01.08.00.00"));
        consultaLiberal.setFinInscripcion(parseDate("2017.11.11.08.00.00"));

        eleccionService.guardar(consultaLiberal);
    }

    private Date parseDate(String str) throws ParseException {
        return DateUtils.parseDate(str, FORMATO_TIMESTAMP);
    }

    private void crearPartidos() {
        crearPartido(PARTIDO_LIBERAL);
        crearPartido(PARTIDO_CONSERVADOR);
        crearPartido(PARTIDO_POLO_DEMOCRATICO);
        crearPartido(PARTIDO_VERDE);
    }

    private void crearPartido(String nombre) {
        PartidoEntity partido = new PartidoEntity();
        partido.setNombre(nombre);
        partidoService.guardar(partido);
    }

    private void crearRoles() {
        crearRol(RolEntity.ADMIN);
        crearRol(RolEntity.ELECTOR);
        crearRol(RolEntity.CANDIDATO);
    }

    private void crearRol(String nombreRol) {
        if (!rolService.existe(nombreRol)) {
            RolEntity rol = new RolEntity();
            rol.setNombre(nombreRol);

            rolService.guardar(rol);
        }
    }

    private void crearUsuarios() {
        crearUsuarioAdmin();
        crearUsuariosElectores();
        crearUsuariosCandidatosParaEleccion(0);
        crearUsuariosCandidatosParaEleccion(1);
    }

    private void crearUsuarioAdmin() {
        UsuarioEntity usuario = new UsuarioEntity();

        usuario.setUsername(usuarioPorDefectoUsername);
        usuario.setPassword(usuarioPorDefectoPassword);
        usuario.setPrimerNombre(usuarioPorDefectoPrimerNombre);
        usuario.setSegundoNombre(usuarioPorDefectoSegundoNombre);
        usuario.setPrimerApellido(usuarioPorDefectoPrimerApellido);
        usuario.setSegundoApellido(usuarioPorDefectoSegundoApellido);
        usuario.setFechaDeNacimiento(usuarioPorDefectoFechaNacimiento);
        usuario.setCorreo(usuarioPorDefectoCorreo);
        usuario.setActivo(true);

        crearUsuario(usuario, new String[]{RolEntity.ADMIN});
    }

    private void crearUsuariosElectores() {
        for (int i = 0; i < 10; i++) {
            UsuarioEntity usuario = new UsuarioEntity();

            usuario.setUsername("elector" + i);
            usuario.setPassword(usuarioPorDefectoPassword);
            usuario.setPrimerNombre("elector" + i);
            usuario.setSegundoNombre("elector" + i);
            usuario.setPrimerApellido("elector" + i);
            usuario.setSegundoApellido("elector" + i);
            usuario.setFechaDeNacimiento(usuarioPorDefectoFechaNacimiento);
            usuario.setCorreo("elector" + i + "@foo.com");
            usuario.setActivo(true);

            crearUsuario(usuario, new String[]{RolEntity.ELECTOR});
        }
    }

    private void crearUsuariosCandidatosParaEleccion(int numeroEleccion) {
        String[] partidos = new String[]{PARTIDO_LIBERAL, PARTIDO_CONSERVADOR, PARTIDO_POLO_DEMOCRATICO, PARTIDO_VERDE};
        for (int i = 0; i < 4; i++) {
            UsuarioEntity usuario = new UsuarioEntity();

            usuario.setUsername("candidato" + i);
            usuario.setPassword(usuarioPorDefectoPassword);
            usuario.setPrimerNombre("candidato" + i);
            usuario.setSegundoNombre("candidato" + i);
            usuario.setPrimerApellido("candidato" + i);
            usuario.setSegundoApellido("candidato" + i);
            usuario.setFechaDeNacimiento(usuarioPorDefectoFechaNacimiento);
            usuario.setCorreo("candidato" + i + "@foo.com");
            usuario.setActivo(true);

            usuario = crearUsuario(usuario, new String[]{RolEntity.ELECTOR, RolEntity.CANDIDATO});
            CandidatoEntity candidato = crearCandidato(usuario,
                                                       "https://image.freepik" +
                                                               ".com/vector-gratis/eleccion-de-candidato_23-2147503010.jpg",
                                                       "Soy un buen candidato, voten por mi, soy el " + i, partidos[i]);

            switch (numeroEleccion) {
                case 0:
                    candidatosEleccion1.add(candidato);
                    break;
                case 1:
                    candidatosEleccion2.add(candidato);
                    break;
                default:
                    break;
            }

        }
    }

    private CandidatoEntity crearCandidato(UsuarioEntity usuario, String urlFoto, String biografia,
                                           String nombrePartido) {
        CandidatoEntity candidato = new CandidatoEntity();
        candidato.setUsuario(usuario);
        candidato.setBiografia(biografia);
        candidato.setFoto(urlFoto);
        candidato.setPartido(partidoService.buscarPorNombre(nombrePartido));

        return candidatoService.guardar(candidato);
    }

    private UsuarioEntity crearUsuario(UsuarioEntity usuario, String[] rolesString) {
        UsuarioEntity usuarioGuardado = null;
        if (!usuarioService.existeUsuario(usuario.getUsername())) {
            RolEntity rol;
            Set<RolEntity> roles = new HashSet<>();
            for (String rolString : rolesString) {
                rol = rolService.buscarPorNombre(rolString);
                roles.add(rol);
            }
            usuario.setRoles(roles);
            usuarioGuardado = usuarioService.guardar(usuario);
        }
        return usuarioGuardado;
    }

    private boolean isBaseDeDatosInicializada() {
        return usuarioService.conteoDeUsuarios() > 0;
    }
}
