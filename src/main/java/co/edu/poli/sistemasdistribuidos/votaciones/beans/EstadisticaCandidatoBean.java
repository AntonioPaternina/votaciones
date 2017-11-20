package co.edu.poli.sistemasdistribuidos.votaciones.beans;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.CandidatoEntity;

public class EstadisticaCandidatoBean {
    private CandidatoEntity candidato;
    private long conteoVotos;


    public long getConteoVotos() {
        return conteoVotos;
    }

    public void setConteoVotos(long conteoVotos) {
        this.conteoVotos = conteoVotos;
    }

    public CandidatoEntity getCandidato() {

        return candidato;
    }

    public void setCandidato(CandidatoEntity candidato) {
        this.candidato = candidato;
    }
}
