package co.edu.poli.sistemasdistribuidos.votaciones.controller;

import co.edu.poli.sistemasdistribuidos.votaciones.beans.EleccionBean;
import co.edu.poli.sistemasdistribuidos.votaciones.entities.UsuarioEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.service.EleccionService;
import co.edu.poli.sistemasdistribuidos.votaciones.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eleccion")
public class EleccionController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EleccionController.class);

    @Autowired
    private EleccionService eleccionService;

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping("/consultar")
    public List<EleccionBean> consultarElecciones() {
        List<EleccionBean> elecciones = null;
        try {
            UsuarioEntity usuarioEnSesion = usuarioService.buscarPorUsername(getUsernameEnSesion());
            elecciones = eleccionService.consultarEleccionesDelUsuario(usuarioEnSesion);
        } catch (Exception e) {
            LOGGER.error("Error consultando las elecciones", e);
        }
        return elecciones;
    }
}
