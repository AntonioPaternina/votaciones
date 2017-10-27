package co.edu.poli.sistemasdistribuidos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter implements AuthenticationEntryPoint {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login.html").anonymous()
                .antMatchers("/js/**", "/css/**").anonymous()
                .anyRequest().authenticated()

                .and()

                .httpBasic().authenticationEntryPoint(this)

                .and()

                .exceptionHandling().authenticationEntryPoint(this)

                .and()

                .logout().permitAll()

                .and()

                .csrf().disable();
    }

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setHeader("WWW-Authenticate", "FormBased");
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.html");
    }
}
