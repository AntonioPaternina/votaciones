package co.edu.poli.sistemasdistribuidos.votaciones.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "USUARIO")
public class UsuarioEntity extends BaseEntity implements Serializable {
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    @JsonIgnore
    private String password;
    @Column(name = "PRIMER_NOMBRE")
    private String primerNombre;
    @Column(name = "SEGUNDO_NOMBRE")
    private String segundoNombre;
    @Column(name = "PRIMER_APELLIDO")
    private String primerApellido;
    @Column(name = "SEGUNDO_APELLIDO")
    private String segundoApellido;
    @Column(name = "CORREO")
    private String correo;
    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_NACIMIENTO")
    private Date fechaDeNacimiento;
    @Column(name = "ACTIVO")
    private boolean activo;
    @ManyToMany
    @JoinTable(name = "USUARIO_ROL",
            joinColumns = @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ID_ROL", referencedColumnName = "ID"))
    private Set<RolEntity> roles;
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<VotoEntity> votos;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Set<RolEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RolEntity> roles) {
        this.roles = roles;
    }

    public Set<VotoEntity> getVotos() {
        return votos;
    }

    public void setVotos(Set<VotoEntity> votos) {
        this.votos = votos;
    }

    @Override
    public String toString() {
        return "UsuarioEntity{" +
                "username='" + username + '\'' +
                ", primerNombre='" + primerNombre + '\'' +
                ", segundoNombre='" + segundoNombre + '\'' +
                ", primerApellido='" + primerApellido + '\'' +
                ", segundoApellido='" + segundoApellido + '\'' +
                ", correo='" + correo + '\'' +
                ", fechaDeNacimiento=" + fechaDeNacimiento +
                ", activo=" + activo +
                ", roles=" + roles +
                '}';
    }
}
