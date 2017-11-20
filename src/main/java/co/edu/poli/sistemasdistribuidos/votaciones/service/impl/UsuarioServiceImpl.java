package co.edu.poli.sistemasdistribuidos.votaciones.service.impl;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.UsuarioEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.repositories.UsuarioRepository;
import co.edu.poli.sistemasdistribuidos.votaciones.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UsuarioEntity buscarPorId(long id) {
        UsuarioEntity usuario = null;
        try {
            usuario = usuarioRepository.findOne(id);
        } catch (Exception e) {
            LOGGER.error("Error buscando al usuario con id=" + id, e);
        }
        return usuario;
    }

    @Override
    public UsuarioEntity buscarPorUsername(String username) {
        UsuarioEntity usuario = null;
        try {
            usuario = usuarioRepository.findByUsername(username);
        } catch (Exception e) {
            LOGGER.error("Error buscando al usuario con username=" + username, e);
        }
        return usuario;
    }

    @Override
    public UsuarioEntity guardar(UsuarioEntity usuario) {
        UsuarioEntity usuarioGuardado = null;
        try {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            usuarioGuardado = usuarioRepository.saveAndFlush(usuario);
        } catch (Exception e) {
            LOGGER.error("Error guardando el usuario.", e);
        }
        return usuarioGuardado;
    }

    @Override
    public void eliminar(UsuarioEntity usuario) {
        try {
            usuarioRepository.delete(usuario);
        } catch (Exception e) {
            LOGGER.error("Error eliminando el usuario", e);
        }
    }

    @Override
    public boolean existeUsuario(String username) {
        boolean existe = false;
        try {
            long conteo = usuarioRepository.countByUsername(username);
            existe = conteo > 0;
        } catch (Exception e) {
            LOGGER.error("Error consultando el conteo de usuarios para el usuario=" + username, e);
        }
        return existe;
    }

    @Override
    public List<UsuarioEntity> buscarTodos() {
        List<UsuarioEntity> usuarios = null;
        try {
            usuarios = usuarioRepository.findAll();
        } catch (Exception e) {
            LOGGER.error("Error buscando todos los usuarios", e);
        }
        return usuarios;
    }

    @Override
    public long conteoDeUsuarios() {
        return usuarioRepository.count();
    }
}
