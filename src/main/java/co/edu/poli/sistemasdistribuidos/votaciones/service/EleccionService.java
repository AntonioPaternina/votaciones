package co.edu.poli.sistemasdistribuidos.votaciones.service;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.EleccionEntity;

import java.util.List;

public interface EleccionService {

    EleccionEntity guardar(EleccionEntity eleccionEntity);

    List<EleccionEntity> consultar();

}
