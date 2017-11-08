package co.edu.poli.sistemasdistribuidos.votaciones.security;

import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityImpl implements GrantedAuthority {

    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return null;
    }

    @Override
    public String toString() {
        return "{authority=" + authority + "}";
    }
}
