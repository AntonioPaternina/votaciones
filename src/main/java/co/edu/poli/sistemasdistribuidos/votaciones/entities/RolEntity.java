package co.edu.poli.sistemasdistribuidos.votaciones.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "ROL")
public class RolEntity extends BaseEntity implements Serializable {

    public static final String ADMIN = "ROL_ADMIN";
    public static final String ELECTOR = "ROL_ELECTOR";
    public static final String CANDIDATO = "ROL_CANDIDATO";

    @Column(name = "NOMBRE")
    private String nombre;
    @ManyToMany
    @JoinTable(name = "ROL_PERMISO",
            joinColumns = @JoinColumn(name = "ID_ROL", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ID_PERMISO", referencedColumnName = "ID")
    )
    private Set<PermisoEntity> permisos;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<PermisoEntity> getPermisos() {
        return permisos;
    }

    public void setPermisos(Set<PermisoEntity> permisos) {
        this.permisos = permisos;
    }

    @Override
    public String toString() {
        return "RolEntity{" +
                "nombre='" + nombre + '\'' +
                ", permisos=" + permisos +
                '}';
    }
}
