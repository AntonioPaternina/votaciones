package co.edu.poli.sistemasdistribuidos.votaciones.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VOTO")
public class VotoEntity extends BaseEntity {
    @ManyToOne
    private UsuarioEntity usuario;
    @ManyToOne
    private EleccionEntity eleccion;
    @ManyToOne
    private CandidatoEntity candidato;

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public EleccionEntity getEleccion() {
        return eleccion;
    }

    public void setEleccion(EleccionEntity eleccion) {
        this.eleccion = eleccion;
    }

    public CandidatoEntity getCandidato() {
        return candidato;
    }

    public void setCandidato(CandidatoEntity candidato) {
        this.candidato = candidato;
    }
}
