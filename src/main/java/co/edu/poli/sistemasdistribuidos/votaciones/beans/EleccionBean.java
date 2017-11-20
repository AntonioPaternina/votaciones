package co.edu.poli.sistemasdistribuidos.votaciones.beans;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.EleccionEntity;

public class EleccionBean {
    private EleccionEntity eleccion;
    private boolean activa;

    public EleccionEntity getEleccion() {
        return eleccion;
    }

    public void setEleccion(EleccionEntity eleccion) {
        this.eleccion = eleccion;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
}
