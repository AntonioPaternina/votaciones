package co.edu.poli.sistemasdistribuidos.votaciones.service.impl;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.PartidoEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.repositories.PartidoRepository;
import co.edu.poli.sistemasdistribuidos.votaciones.service.PartidoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PartidoServiceImpl implements PartidoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartidoServiceImpl.class);

    @Autowired
    private PartidoRepository partidoRepository;

    @Override
    public PartidoEntity guardar(PartidoEntity partido) {
        PartidoEntity partidoGuardado = null;
        try {
            partidoGuardado = partidoRepository.saveAndFlush(partido);
        } catch (Exception e) {
            LOGGER.error("Error guardando el partido", e);
        }
        return partidoGuardado;
    }

    @Override
    public List<PartidoEntity> consultar() {
        List<PartidoEntity> partidos = null;
        try {
            partidos = partidoRepository.findAll();
        } catch (Exception e) {
            LOGGER.error("Error consultando los partidos", e);
        }
        return partidos;
    }

    @Override
    public PartidoEntity buscarPorNombre(String nombre) {
        PartidoEntity partido = null;
        try {
            partido = partidoRepository.findByNombre(nombre);
        } catch (Exception e) {
            LOGGER.error("Ocurrió un error buscando el partido con nombre=" + nombre, e);
        }
        return partido;
    }
}
