package co.edu.poli.sistemasdistribuidos.votaciones.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserDetailsImpl implements UserDetails {
    private Set<GrantedAuthority> authorities;
    private String username;
    private String password;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private boolean accountNonLocked;

    public void agregarRoles(Collection<String> roles) {
        for (String rol : roles) {
            agregarRol(rol);
        }
    }

    public void agregarRol(String rol) {
        if (this.authorities == null) {
            this.authorities = new HashSet<>();
        }

        GrantedAuthority grantedAuthority = new GrantedAuthorityImpl(rol);
        this.authorities.add(grantedAuthority);
    }

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public String toString() {
        return "{username='" + username + '\'' +
                ",accountNonExpired=" + accountNonExpired +
                ",credentialsNonExpired=" + credentialsNonExpired +
                ",enabled=" + enabled +
                ",accountNonLocked=" + accountNonLocked +
                ",authorities=" + authorities +
                '}';
    }
}
