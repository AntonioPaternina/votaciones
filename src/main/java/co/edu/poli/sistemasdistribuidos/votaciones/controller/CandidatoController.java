package co.edu.poli.sistemasdistribuidos.votaciones.controller;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.CandidatoEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.service.CandidatoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/candidato")
public class CandidatoController extends BaseController {

    private static Logger LOGGER = LoggerFactory.getLogger(CandidatoController.class);

    @Autowired
    private CandidatoService candidatoService;

    @RequestMapping("/consultar")
    public List<CandidatoEntity> consultarCandidatos(@RequestParam(name = "idEleccion") long idEleccion) {
        List<CandidatoEntity> candidatos = null;
        try {
            candidatos = candidatoService.consultarPorEleccion(idEleccion);
        } catch (Exception e) {
            LOGGER.error("Ocurrió un error consultando los candidatos", e);
        }
        return candidatos;
    }
}
