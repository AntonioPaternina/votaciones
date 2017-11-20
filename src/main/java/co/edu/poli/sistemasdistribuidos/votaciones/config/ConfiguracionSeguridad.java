package co.edu.poli.sistemasdistribuidos.votaciones.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter implements AuthenticationEntryPoint {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login.html").anonymous()
                .antMatchers("/js/**", "/css/**").anonymous()
                .antMatchers("/**").anonymous()
                //.anyRequest().authenticated()

                .and()

                .httpBasic().authenticationEntryPoint(this)

                .and()

                .exceptionHandling().authenticationEntryPoint(this)

                .and()

                .logout().permitAll()

                .and()

                .headers().frameOptions().disable() // para poder ver h2-console

                .and()

                .csrf().disable();
    }

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setHeader("WWW-Authenticate", "FormBased");
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.html");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                    userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
}
