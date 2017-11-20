package co.edu.poli.sistemasdistribuidos.votaciones.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class LoginListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

    private static Logger LOGGER = LoggerFactory.getLogger(LoginListener.class);

    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
        UserDetails userDetails = (UserDetails) event.getAuthentication().getPrincipal();
        LOGGER.info("Se ha autenticado el usuario=" + userDetails);
    }
}