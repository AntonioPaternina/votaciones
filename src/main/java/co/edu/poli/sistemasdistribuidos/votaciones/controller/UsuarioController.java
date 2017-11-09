package co.edu.poli.sistemasdistribuidos.votaciones.controller;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.UsuarioEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private static Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping("/consultar-datos-usuario-en-sesion")
    public UsuarioEntity consultarDatosUsuarioEnSesion() {
        UsuarioEntity usuario = null;
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                String username = authentication.getName();
                usuario = usuarioService.buscarPorUsername(username);
            }
        } catch (Exception e) {
            LOGGER.error("Error consultando los datos del usuario en sesión.", e);
        }
        return usuario;
    }

}
