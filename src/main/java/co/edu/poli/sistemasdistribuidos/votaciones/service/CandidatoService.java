package co.edu.poli.sistemasdistribuidos.votaciones.service;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.CandidatoEntity;

import java.util.List;

public interface CandidatoService {

    CandidatoEntity buscarPorId(long idCandidato);

    CandidatoEntity guardar(CandidatoEntity candidato);

    List<CandidatoEntity> consultarPorEleccion(long idEleccion);

}
