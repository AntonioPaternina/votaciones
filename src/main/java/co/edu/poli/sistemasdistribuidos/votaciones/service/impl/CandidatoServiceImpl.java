package co.edu.poli.sistemasdistribuidos.votaciones.service.impl;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.CandidatoEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.repositories.CandidatoRepository;
import co.edu.poli.sistemasdistribuidos.votaciones.service.CandidatoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CandidatoServiceImpl implements CandidatoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CandidatoServiceImpl.class);

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Override
    public CandidatoEntity guardar(CandidatoEntity candidato) {
        CandidatoEntity candidatoGuardado = null;
        try {
            candidatoGuardado = candidatoRepository.saveAndFlush(candidato);
        } catch (Exception e) {
            LOGGER.error("Error guardando el candidato", e);
        }
        return candidatoGuardado;
    }
}
