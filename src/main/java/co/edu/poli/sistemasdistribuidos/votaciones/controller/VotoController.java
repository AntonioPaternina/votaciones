package co.edu.poli.sistemasdistribuidos.votaciones.controller;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.VotoEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.service.VotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/voto")
public class VotoController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VotoController.class);

    @Autowired
    private VotoService votoService;

    @PostMapping("/votar")
    public VotoEntity votar(@RequestParam("idEleccion") long idEleccion,
                            @RequestParam("idCandidato") long idCandidato) {
        VotoEntity voto = null;
        try {
            voto = votoService.votar(idEleccion, idCandidato);
        } catch (Exception e) {
            LOGGER.error("Ha ocurrido un error realizando el voto", e);
        }
        return voto;
    }
}
