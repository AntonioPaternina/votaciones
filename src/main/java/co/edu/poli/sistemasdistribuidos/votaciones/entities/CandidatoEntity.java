package co.edu.poli.sistemasdistribuidos.votaciones.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "CANDIDATO")
public class CandidatoEntity extends BaseEntity implements Serializable {
    @OneToOne
    @JoinColumn(name = "ID_USUARIO")
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "ID_PARTIDO")
    private PartidoEntity partido;

    @ManyToMany(mappedBy = "candidatos")
    @JsonIgnore
    private Set<EleccionEntity> elecciones;

    @Column(name = "BIOGRAFIA")
    @Lob
    private String biografia;

    @Column(name = "FOTO")
    private String foto;

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public PartidoEntity getPartido() {
        return partido;
    }

    public void setPartido(PartidoEntity partido) {
        this.partido = partido;
    }

    public Set<EleccionEntity> getElecciones() {
        return elecciones;
    }

    public void setElecciones(Set<EleccionEntity> elecciones) {
        this.elecciones = elecciones;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
