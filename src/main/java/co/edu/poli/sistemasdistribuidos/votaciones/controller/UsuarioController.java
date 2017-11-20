package co.edu.poli.sistemasdistribuidos.votaciones.controller;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.UsuarioEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController extends BaseController {
    private static Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping("/consultar-datos-usuario-en-sesion")
    public UsuarioEntity consultarDatosUsuarioEnSesion() {
        UsuarioEntity usuario = null;
        try {
            String username = getUsernameEnSesion();
            usuario = usuarioService.buscarPorUsername(username);
        } catch (Exception e) {
            LOGGER.error("Error consultando los datos del usuario en sesión.", e);
        }
        return usuario;
    }

}
