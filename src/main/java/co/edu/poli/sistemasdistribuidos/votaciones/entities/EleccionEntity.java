package co.edu.poli.sistemasdistribuidos.votaciones.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "ELECCION")
public class EleccionEntity extends BaseEntity {
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "INICIO_INSCRIPCION")
    private Date inicioInscripcion;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FIN_INSCRIPCION")
    private Date finInscripcion;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "INICIO_ELECCION")
    private Date inicioEleccion;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FIN_ELECCION")
    private Date finEleccion;
    @ManyToMany
    @JoinTable(name = "ELECCION_CANDIDATO",
            joinColumns = @JoinColumn(name = "ID_ELECCION", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ID_CANDIDATO", referencedColumnName = "ID"))
    private Set<CandidatoEntity> candidatos;
    @OneToMany(mappedBy = "eleccion", fetch = FetchType.LAZY)
    private Set<VotoEntity> votos;
    @OneToOne
    private EstadisticaEleccionEntity estadistica;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getInicioInscripcion() {
        return inicioInscripcion;
    }

    public void setInicioInscripcion(Date inicioInscripcion) {
        this.inicioInscripcion = inicioInscripcion;
    }

    public Date getFinInscripcion() {
        return finInscripcion;
    }

    public void setFinInscripcion(Date finInscripcion) {
        this.finInscripcion = finInscripcion;
    }

    public Date getInicioEleccion() {
        return inicioEleccion;
    }

    public void setInicioEleccion(Date inicioEleccion) {
        this.inicioEleccion = inicioEleccion;
    }

    public Date getFinEleccion() {
        return finEleccion;
    }

    public void setFinEleccion(Date finEleccion) {
        this.finEleccion = finEleccion;
    }

    public Set<CandidatoEntity> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(Set<CandidatoEntity> candidatos) {
        this.candidatos = candidatos;
    }

    public Set<VotoEntity> getVotos() {
        return votos;
    }

    public void setVotos(Set<VotoEntity> votos) {
        this.votos = votos;
    }

    public EstadisticaEleccionEntity getEstadistica() {
        return estadistica;
    }

    public void setEstadistica(EstadisticaEleccionEntity estadistica) {
        this.estadistica = estadistica;
    }
}
