package co.edu.poli.sistemasdistribuidos.votaciones.service;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.VotoEntity;

public interface VotoService {

    VotoEntity guardarVoto(long idUsuario, long idEleccion, long idCandidato);

    VotoEntity votar(long idEleccion, long idCandidato);
}
