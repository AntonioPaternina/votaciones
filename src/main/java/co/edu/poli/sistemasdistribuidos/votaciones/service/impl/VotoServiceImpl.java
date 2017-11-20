package co.edu.poli.sistemasdistribuidos.votaciones.service.impl;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.CandidatoEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.entities.EleccionEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.entities.UsuarioEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.entities.VotoEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.repositories.VotoRepository;
import co.edu.poli.sistemasdistribuidos.votaciones.service.CandidatoService;
import co.edu.poli.sistemasdistribuidos.votaciones.service.EleccionService;
import co.edu.poli.sistemasdistribuidos.votaciones.service.UsuarioService;
import co.edu.poli.sistemasdistribuidos.votaciones.service.VotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VotoServiceImpl extends BaseServiceImpl implements VotoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VotoServiceImpl.class);

    @Autowired
    private CandidatoService candidatoService;

    @Autowired
    private EleccionService eleccionService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private VotoRepository votoRepository;

    @Override
    public VotoEntity votar(long idEleccion, long idCandidato) {
        VotoEntity voto = null;
        try {
            EleccionEntity eleccion = eleccionService.buscarPorId(idEleccion);
            CandidatoEntity candidato = candidatoService.buscarPorId(idCandidato);
            UsuarioEntity elector = usuarioService.buscarPorUsername(getUsernameEnSesion());

            if (!eleccionService.usuarioHaVotadoEnEleccion(eleccion, elector)) {
                voto = new VotoEntity();
                voto.setCandidato(candidato);
                voto.setEleccion(eleccion);
                voto.setUsuario(elector);

                voto = votoRepository.saveAndFlush(voto);
            } else {
                LOGGER.error(
                        "El usuario ya ha votado anteriormente para esta elección. No se hace nada., idEleccion=" +
                                idEleccion + ", idCandidato=" + idCandidato + ", username=" + getUsernameEnSesion());
            }
        } catch (Exception e) {
            LOGGER.error("Ocurrió un error votando", e);
        }
        return voto;
    }
}
