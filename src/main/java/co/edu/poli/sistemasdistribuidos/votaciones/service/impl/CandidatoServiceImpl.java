package co.edu.poli.sistemasdistribuidos.votaciones.service.impl;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.CandidatoEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.entities.EleccionEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.repositories.CandidatoRepository;
import co.edu.poli.sistemasdistribuidos.votaciones.service.CandidatoService;
import co.edu.poli.sistemasdistribuidos.votaciones.service.EleccionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CandidatoServiceImpl implements CandidatoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CandidatoServiceImpl.class);

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Autowired
    private EleccionService eleccionService;

    @Override
    public CandidatoEntity buscarPorId(long idCandidato) {
        CandidatoEntity candidato = null;
        try {
            candidato = candidatoRepository.findOne(idCandidato);
        } catch (Exception e) {
            LOGGER.error("Ocurrió un error buscando al candidato con id=" + idCandidato, e);
        }
        return candidato;
    }

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

    @Override
    public List<CandidatoEntity> consultarPorEleccion(long idEleccion) {
        List<CandidatoEntity> candidatos = null;
        try {
            EleccionEntity eleccion = eleccionService.buscarPorId(idEleccion);
            candidatos = candidatoRepository.findByEleccion(eleccion);
        } catch (Exception e) {
            LOGGER.error("Ocurrió un error consultando los candidatos para la elección=" + idEleccion, e);
        }
        return candidatos;
    }
}
