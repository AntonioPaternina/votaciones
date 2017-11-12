package co.edu.poli.sistemasdistribuidos.votaciones.service;

import co.edu.poli.sistemasdistribuidos.votaciones.beans.EleccionBean;
import co.edu.poli.sistemasdistribuidos.votaciones.entities.EleccionEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.entities.UsuarioEntity;

import java.util.List;

public interface EleccionService {

    EleccionEntity buscarPorId(long idEleccion);

    EleccionEntity guardar(EleccionEntity eleccionEntity);

    List<EleccionBean> consultarEleccionesDelUsuario(UsuarioEntity usuarioEntity);

    boolean usuarioHaVotadoEnEleccion(EleccionEntity eleccion, UsuarioEntity usuario);

}
