package co.edu.poli.sistemasdistribuidos.votaciones.beans;

import java.util.ArrayList;
import java.util.List;

public class InfoGraficaBean {
    List<EstadisticaCandidatoBean> candidatos;
    long totalVotos;

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

    public long getTotalVotos() {
        return totalVotos;
    }

    public void setTotalVotos(long totalVotos) {
        this.totalVotos = totalVotos;
    }
}
