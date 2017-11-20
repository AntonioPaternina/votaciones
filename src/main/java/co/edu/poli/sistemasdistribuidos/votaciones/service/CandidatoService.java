package co.edu.poli.sistemasdistribuidos.votaciones.service;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.CandidatoEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.entities.EleccionEntity;

import java.util.List;

public interface CandidatoService {

    CandidatoEntity buscarPorId(long idCandidato);

    CandidatoEntity guardar(CandidatoEntity candidato);

    List<CandidatoEntity> consultarPorEleccion(long idEleccion);

    long getConteoVotosDelCandidatoParaEleccion(EleccionEntity eleccion, CandidatoEntity candidato);

}
