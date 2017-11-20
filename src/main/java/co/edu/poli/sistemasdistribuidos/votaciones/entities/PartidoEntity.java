package co.edu.poli.sistemasdistribuidos.votaciones.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PARTIDO")
public class PartidoEntity extends BaseEntity {
    @Column(name = "NOMBRE")
    String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
