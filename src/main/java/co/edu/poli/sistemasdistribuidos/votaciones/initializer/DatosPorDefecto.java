package co.edu.poli.sistemasdistribuidos.votaciones.initializer;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.RolEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.entities.UsuarioEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.service.RolService;
import co.edu.poli.sistemasdistribuidos.votaciones.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class DatosPorDefecto implements ApplicationListener<ContextRefreshedEvent> {

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

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        crearRoles();
        crearUsuario();
    }

    private void crearRoles() {
        crearRol(RolEntity.ADMIN);
    }

    private void crearRol(String nombreRol) {
        if (!rolService.existe(nombreRol)) {
            RolEntity rol = new RolEntity();
            rol.setNombre(RolEntity.ADMIN);

            rolService.guardar(rol);
        }
    }

    private void crearUsuario() {
        if (!usuarioService.existeUsuario(usuarioPorDefectoUsername)) {
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

            RolEntity rolAdmin = rolService.buscarPorNombre(RolEntity.ADMIN);
            Set<RolEntity> roles = new HashSet<>();
            roles.add(rolAdmin);
            usuario.setRoles(roles);

            usuarioService.guardar(usuario);
        }
    }
}
