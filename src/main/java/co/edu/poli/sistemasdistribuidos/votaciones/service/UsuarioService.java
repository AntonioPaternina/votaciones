package co.edu.poli.sistemasdistribuidos.votaciones.service;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.UsuarioEntity;

import java.util.List;

public interface UsuarioService {
    UsuarioEntity buscarPorId(long id);

    UsuarioEntity buscarPorUsername(String username);

    UsuarioEntity guardar(UsuarioEntity usuario);

    void eliminar(UsuarioEntity usuario);

    boolean existeUsuario(String username);

    List<UsuarioEntity> buscarTodos();

    long conteoDeUsuarios();
}
