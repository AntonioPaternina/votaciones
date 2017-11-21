package co.edu.poli.sistemasdistribuidos.votaciones.beans;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.EleccionEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.entities.EstadisticaEleccionEntity;

import java.util.ArrayList;
import java.util.List;

public class InfoGraficaBean {
    private List<EstadisticaCandidatoBean> candidatos;
    private EleccionEntity eleccion;
    private EstadisticaEleccionEntity estadisticaEleccion;

    public void agregarCandidato(EstadisticaCandidatoBean candidato) {
        if (this.candidatos == null) {
            this.candidatos = new ArrayList<>();
        }
        this.candidatos.add(candidato);
    }

    public List<EstadisticaCandidatoBean> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(
            List<EstadisticaCandidatoBean> candidatos) {
        this.candidatos = candidatos;
    }

    public EleccionEntity getEleccion() {
        return eleccion;
    }

    public void setEleccion(EleccionEntity eleccion) {
        this.eleccion = eleccion;
    }

    public EstadisticaEleccionEntity getEstadisticaEleccion() {
        return estadisticaEleccion;
    }

    public void setEstadisticaEleccion(
            EstadisticaEleccionEntity estadisticaEleccion) {
        this.estadisticaEleccion = estadisticaEleccion;
    }
}
