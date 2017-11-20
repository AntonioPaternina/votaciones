package co.edu.poli.sistemasdistribuidos.votaciones.service;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.RolEntity;

public interface RolService {
    RolEntity guardar(RolEntity rol);

    RolEntity buscarPorNombre(String nombre);

    boolean existe(String nombre);
}
