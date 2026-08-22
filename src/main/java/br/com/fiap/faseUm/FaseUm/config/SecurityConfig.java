package br.com.fiap.faseUm.FaseUm.config;

import java.net.URI;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.fiap.faseUm.FaseUm.controllers.handlers.ControllerExceptionHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsManager userDetailsManager(){
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery("SELECT nome, senha, enabled FROM usuarios WHERE nome = ?");
        manager.setAuthoritiesByUsernameQuery("SELECT nome, CONCAT('ROLE_', role) AS role FROM usuarios WHERE nome = ?");
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/login").permitAll()
            .requestMatchers(HttpMethod.POST, "/v2/usuarios").permitAll()
            .anyRequest().authenticated()
        )
        .csrf(csrf -> csrf.disable())
        .formLogin(form -> form
            .successHandler((request, response, authentication) -> {
                response.setStatus(HttpServletResponse.SC_OK);
            })
            .failureHandler((request, response, exception) -> {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            })
        )
        .logout(Customizer.withDefaults());
        return http.build();
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handlerGenericException(Exception e, HttpServletRequest request) {
        logger.error("Erro interno inesperado", e); // <- linha nova, imprime o stack trace completo
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno inesperado");
        pd.setTitle("Erro interno");
        pd.setType(URI.create("https://faseum.fiap.com.br/erros/erro-interno"));
        pd.setInstance(URI.create(request.getRequestURI()));
        return pd;
    }

  
}
