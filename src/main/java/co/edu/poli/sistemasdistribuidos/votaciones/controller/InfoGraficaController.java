package co.edu.poli.sistemasdistribuidos.votaciones.controller;

import co.edu.poli.sistemasdistribuidos.votaciones.beans.InfoGraficaBean;
import co.edu.poli.sistemasdistribuidos.votaciones.entities.EleccionEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.service.EleccionService;
import co.edu.poli.sistemasdistribuidos.votaciones.service.EstadisticaEleccionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estadisticas")
public class InfoGraficaController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InfoGraficaController.class.getName());

    @Autowired
    private EstadisticaEleccionService estadisticaEleccionService;

    @Autowired
    private EleccionService eleccionService;

    @RequestMapping("/grafica-resumen-eleccion")
    public InfoGraficaBean obtenerInfoEstadistica(@RequestParam("idEleccion") long idEleccion) {
        InfoGraficaBean grafica = null;
        try {
            EleccionEntity eleccion = eleccionService.buscarPorId(idEleccion);
            grafica = estadisticaEleccionService.generarEstadistica(eleccion);
        } catch (Exception e) {
            LOGGER.error("Error consultando la información para la gráfica", e);
        }
        return grafica;
    }
}
