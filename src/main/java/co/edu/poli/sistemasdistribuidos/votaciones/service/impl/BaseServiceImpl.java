package co.edu.poli.sistemasdistribuidos.votaciones.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseServiceImpl {
    protected String getUsernameEnSesion() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
