package co.edu.poli.sistemasdistribuidos.votaciones.service.impl;

import co.edu.poli.sistemasdistribuidos.votaciones.beans.EstadisticaCandidatoBean;
import co.edu.poli.sistemasdistribuidos.votaciones.beans.InfoGraficaBean;
import co.edu.poli.sistemasdistribuidos.votaciones.entities.CandidatoEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.entities.EleccionEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.entities.EstadisticaEleccionEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.repositories.EstadisticaEleccionRepository;
import co.edu.poli.sistemasdistribuidos.votaciones.service.CandidatoService;
import co.edu.poli.sistemasdistribuidos.votaciones.service.EleccionService;
import co.edu.poli.sistemasdistribuidos.votaciones.service.EstadisticaEleccionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EstadisticaEleccionServiceImpl implements EstadisticaEleccionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EstadisticaEleccionServiceImpl.class.getName());

    @Autowired
    private EstadisticaEleccionRepository estadisticaEleccionRepository;

    @Autowired
    private EleccionService eleccionService;

    @Autowired
    private CandidatoService candidatoService;

    @Override
    public InfoGraficaBean generarEstadistica(EleccionEntity eleccion) {
        InfoGraficaBean infoGraficaBean = null;
        try {
            EstadisticaEleccionEntity nuevaEstadistica = new EstadisticaEleccionEntity();

            CandidatoEntity ganador = eleccionService.calcularGanadorEleccion(eleccion);
            long conteoVotos = eleccionService.obtenerConteoDeVotosEleccion(eleccion);

            nuevaEstadistica.setCandidatoGanador(ganador);
            nuevaEstadistica.setConteoVotos(conteoVotos);
            nuevaEstadistica.setEleccion(eleccion);

            estadisticaEleccionRepository.saveAndFlush(nuevaEstadistica);

            infoGraficaBean = new InfoGraficaBean();
            for (CandidatoEntity candidato : eleccion.getCandidatos()) {
                EstadisticaCandidatoBean estadisticaCandidatoBean = new EstadisticaCandidatoBean();
                estadisticaCandidatoBean.setCandidato(candidato);
                estadisticaCandidatoBean
                        .setConteoVotos(candidatoService.getConteoVotosDelCandidatoParaEleccion(eleccion, candidato));
                infoGraficaBean.agregarCandidato(estadisticaCandidatoBean);
            }

            infoGraficaBean.setEleccion(eleccion);
            infoGraficaBean.setEstadisticaEleccion(nuevaEstadistica);
        } catch (Exception e) {
            LOGGER.error("Error creando las estadisticas de la elección", e);
        }

        return infoGraficaBean;
    }
}
