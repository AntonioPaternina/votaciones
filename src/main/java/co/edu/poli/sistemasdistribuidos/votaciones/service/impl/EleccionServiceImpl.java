package co.edu.poli.sistemasdistribuidos.votaciones.service.impl;

import co.edu.poli.sistemasdistribuidos.votaciones.beans.EleccionBean;
import co.edu.poli.sistemasdistribuidos.votaciones.entities.EleccionEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.entities.UsuarioEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.repositories.EleccionRepository;
import co.edu.poli.sistemasdistribuidos.votaciones.service.EleccionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EleccionServiceImpl implements EleccionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EleccionServiceImpl.class);

    @Autowired
    private EleccionRepository eleccionRepository;

    @Override
    public EleccionEntity buscarPorId(long idEleccion) {
        EleccionEntity eleccion = null;
        try {
            eleccion = eleccionRepository.findOne(idEleccion);
        } catch (Exception e) {
            LOGGER.error("Ocurrió un error buscando la elección con id=" + idEleccion, e);
        }
        return eleccion;
    }

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
    public List<EleccionBean> consultarEleccionesDelUsuario(UsuarioEntity usuario) {
        List<EleccionBean> elecciones = new ArrayList<>();
        try {
            List<EleccionEntity> eleccionesEntities = eleccionRepository.findAll();
            for (EleccionEntity eleccion : eleccionesEntities) {
                EleccionBean eleccionBean = new EleccionBean();
                eleccionBean.setEleccion(eleccion);
                boolean esActiva = !eleccionRepository.usuarioHaVotadoEnEleccion(usuario, eleccion);
                eleccionBean.setActiva(esActiva);
                elecciones.add(eleccionBean);
            }
        } catch (Exception e) {
            LOGGER.error("Error consultando las elecciones", e);
        }
        return elecciones;
    }

    @Override
    public boolean usuarioHaVotadoEnEleccion(EleccionEntity eleccion, UsuarioEntity usuario) {
        boolean haVotado = false;
        try {
            haVotado = eleccionRepository.usuarioHaVotadoEnEleccion(usuario, eleccion);
        } catch (Exception e) {
            LOGGER.error("Ocurrió un error consultando si el usuario ha votado en la elección", e);
        }
        return haVotado;
    }
}
