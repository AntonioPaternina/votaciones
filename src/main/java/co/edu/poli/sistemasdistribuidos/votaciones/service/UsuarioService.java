package co.edu.poli.sistemasdistribuidos.votaciones.service;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.UsuarioEntity;

public interface UsuarioService {
    UsuarioEntity buscarPorId(long id);
    UsuarioEntity buscarPorUsername(String username);
    UsuarioEntity guardar(UsuarioEntity usuario);
    void eliminar(UsuarioEntity usuario);
    boolean existeUsuario(String username);
}
