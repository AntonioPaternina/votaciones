package co.edu.poli.sistemasdistribuidos.votaciones.service.impl;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.EleccionEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.repositories.EleccionRepository;
import co.edu.poli.sistemasdistribuidos.votaciones.service.EleccionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EleccionServiceImpl implements EleccionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EleccionServiceImpl.class);

    @Autowired
    private EleccionRepository eleccionRepository;

    @Override
    public EleccionEntity guardar(EleccionEntity eleccion) {
        EleccionEntity eleccionGuardada = null;
        try {
            eleccionGuardada = eleccionRepository.saveAndFlush(eleccion);
        } catch (Exception e) {
            LOGGER.error("Ocurrió un error durante el guardado de la elección", e);
        }

        return eleccionGuardada;
    }

    @Override
    public List<EleccionEntity> consultar() {
        List<EleccionEntity> elecciones = null;
        try {
            elecciones = eleccionRepository.findAll();
        } catch (Exception e) {
            LOGGER.error("Error consultando las elecciones", e);
        }
        return elecciones;
    }
}
