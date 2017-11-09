package co.edu.poli.sistemasdistribuidos.votaciones.initializer;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.CandidatoEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.entities.PartidoEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.entities.RolEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.entities.UsuarioEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.service.CandidatoService;
import co.edu.poli.sistemasdistribuidos.votaciones.service.PartidoService;
import co.edu.poli.sistemasdistribuidos.votaciones.service.RolService;
import co.edu.poli.sistemasdistribuidos.votaciones.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
@Transactional
public class DatosPorDefecto implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatosPorDefecto.class);

    public static final String PARTIDO_LIBERAL = "Liberal";
    public static final String PARTIDO_CONSERVADOR = "Conservador";
    public static final String PARTIDO_POLO_DEMOCRATICO = "Polo Democrático";
    public static final String PARTIDO_VERDE = "Partido Verde";

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

    private List<CandidatoEntity> candidatosEleccion1 = new ArrayList<>();
    private List<CandidatoEntity> candidatosEleccion2 = new ArrayList<>();

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

    private void crearElecciones() {

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
        crearUsuariosCandidatos();
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

    private void crearUsuariosCandidatos() {
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
            crearCandidato(usuario, "https://image.freepik.com/vector-gratis/eleccion-de-candidato_23-2147503010.jpg",
                           "Soy un buen candidato, voten por mi, soy el " + i, partidos[i]);
        }
    }

    private void crearCandidato(UsuarioEntity usuario, String urlFoto, String biografia, String nombrePartido) {
        CandidatoEntity candidato = new CandidatoEntity();
        candidato.setUsuario(usuario);
        candidato.setBiografia(biografia);
        candidato.setFoto(urlFoto);
        candidato.setPartido(partidoService.buscarPorNombre(nombrePartido));

        candidatoService.guardar(candidato);
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
