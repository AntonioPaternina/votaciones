package co.edu.poli.sistemasdistribuidos.votaciones.service;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.PartidoEntity;

import java.util.List;

public interface PartidoService {
    PartidoEntity guardar(PartidoEntity partido);

    List<PartidoEntity> consultar();

    PartidoEntity buscarPorNombre(String nombre);
}
