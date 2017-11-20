package co.edu.poli.sistemasdistribuidos.votaciones.service.impl;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.RolEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.repositories.RolRepository;
import co.edu.poli.sistemasdistribuidos.votaciones.service.RolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl implements RolService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RolServiceImpl.class);

    @Autowired
    private RolRepository rolRepository;

    @Override
    public RolEntity guardar(RolEntity rol) {
        RolEntity rolGuardado = null;

        try {
            rolGuardado = rolRepository.saveAndFlush(rol);
        } catch (Exception e) {
            LOGGER.error("Ocurrió un error guardando el rol", e);
        }

        return rolGuardado;
    }

    @Override
    public RolEntity buscarPorNombre(String nombre) {
        RolEntity rol = null;
        try {
            rol = rolRepository.findByNombre(nombre);
        } catch (Exception e) {
            LOGGER.error("Error consultando el rol con nombre=" + nombre, e);
        }
        return rol;
    }

    @Override
    public boolean existe(String nombre) {
        boolean existe = false;
        try {
            existe = rolRepository.countByNombre(nombre) > 0;
        } catch (Exception e) {
            LOGGER.error("Error consultando si existe el rol con nombre=" + nombre);
        }
        return existe;
    }
}
