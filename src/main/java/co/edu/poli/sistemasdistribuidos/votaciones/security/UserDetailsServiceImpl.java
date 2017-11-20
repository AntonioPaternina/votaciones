package co.edu.poli.sistemasdistribuidos.votaciones.security;

import co.edu.poli.sistemasdistribuidos.votaciones.entities.RolEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.entities.UsuarioEntity;
import co.edu.poli.sistemasdistribuidos.votaciones.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuarioEntity = usuarioService.buscarPorUsername(username);

        UserDetailsImpl userDetails = new UserDetailsImpl();
        if (usuarioEntity != null && usuarioEntity.getId() > 0) {
            userDetails.setUsername(usuarioEntity.getUsername());
            userDetails.setPassword(usuarioEntity.getPassword());
            userDetails.agregarRoles(obtenerListaDeRolesString(usuarioEntity.getRoles()));
            userDetails.setAccountNonExpired(true);
            userDetails.setAccountNonLocked(true);
            userDetails.setCredentialsNonExpired(true);
            userDetails.setEnabled(true);
        } else {
            throw new UsernameNotFoundException("No se ha encontrado el usuario=" + username);
        }

        return userDetails;
    }

    private Collection<String> obtenerListaDeRolesString(Set<RolEntity> roles) {
        Set<String> rolesString = new HashSet<>();
        for (RolEntity rol : roles) {
            rolesString.add(rol.getNombre());
        }
        return rolesString;
    }
}
