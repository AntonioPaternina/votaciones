package co.edu.poli.sistemasdistribuidos.votaciones.entities;

import javax.persistence.*;

@Entity
@Table(name = "ESTADISTICA_ELECCION")
public class EstadisticaEleccionEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "ID_CANDIDATO_GANADOR")
    private CandidatoEntity candidatoGanador;
    @Column(name = "CONTEO_VOTOS")
    private long conteoVotos;
    @OneToOne
    private EleccionEntity eleccion;

    public CandidatoEntity getCandidatoGanador() {
        return candidatoGanador;
    }

    public void setCandidatoGanador(CandidatoEntity candidatoGanador) {
        this.candidatoGanador = candidatoGanador;
    }

    public long getConteoVotos() {
        return conteoVotos;
    }

    public void setConteoVotos(long conteoVotos) {
        this.conteoVotos = conteoVotos;
    }

    public EleccionEntity getEleccion() {
        return eleccion;
    }

    public void setEleccion(EleccionEntity eleccion) {
        this.eleccion = eleccion;
    }
}
