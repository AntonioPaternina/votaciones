package co.edu.poli.sistemasdistribuidos.votaciones.service;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.VotoEntity;

public interface VotoService {
    VotoEntity votar(long idEleccion, long idCandidato);
}
