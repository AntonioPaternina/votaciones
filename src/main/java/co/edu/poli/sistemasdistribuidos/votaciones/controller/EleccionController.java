package co.edu.poli.sistemasdistribuidos.votaciones.controller;

import co.edu.poli.sistemasdistribuidos.votaciones.beans.EleccionBean;
import co.edu.poli.sistemasdistribuidos.votaciones.entities.EleccionEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.entities.UsuarioEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.service.EleccionService;
import co.edu.poli.sistemasdistribuidos.votaciones.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;
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
            Collections.sort(elecciones, new Comparator<EleccionBean>() {
                @Override
                public int compare(EleccionBean o1, EleccionBean o2) {
                    return o1.getEleccion().getInicioEleccion().compareTo(o2.getEleccion().getFinEleccion());
                }
            });
        } catch (Exception e) {
            LOGGER.error("Error consultando las elecciones", e);
        }
        return elecciones;
    }

    @RequestMapping("/consultar-por-id")
    public EleccionEntity consultarEleccion(@RequestParam("idEleccion") long idEleccion) {
        EleccionEntity eleccion = null;
        try {
            eleccion = eleccionService.buscarPorId(idEleccion);
        } catch (Exception e) {
            LOGGER.error("Error consultando la elección con id=" + idEleccion);
        }
        return eleccion;
    }
}
