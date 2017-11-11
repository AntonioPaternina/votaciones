package co.edu.poli.sistemasdistribuidos.votaciones.controller;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.EleccionEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.service.EleccionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eleccion")
public class EleccionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EleccionController.class);

    @Autowired
    private EleccionService eleccionService;

    @RequestMapping("/consultar")
    public List<EleccionEntity> consultarElecciones() {
        List<EleccionEntity> elecciones = null;
        try {
            elecciones = eleccionService.consultar();
        } catch (Exception e) {
            LOGGER.error("Error consultando las elecciones", e);
        }
        return elecciones;
    }
}
