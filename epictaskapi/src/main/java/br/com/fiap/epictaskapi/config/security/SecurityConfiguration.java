package br.com.fiap.epictaskapi.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http    
            .httpBasic()
            .and()
            .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/api/task/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/task").authenticated()

                .antMatchers(HttpMethod.POST, "/api/auth").permitAll()
                
                .antMatchers(HttpMethod.GET, "/api/user/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/user").authenticated()
                
                
                .antMatchers(HttpMethod.GET, "/task").authenticated()
                .antMatchers(HttpMethod.POST, "/task").permitAll()
                .antMatchers(HttpMethod.GET, "/task/delete/**").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET, "/user").authenticated()
                .antMatchers(HttpMethod.POST, "/task").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/task/delete/**").hasRole("ADMIN")

                
                .anyRequest().permitAll()
            .and()
                .formLogin()

            .and() 
                .csrf().disable()

        ;        
        
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }
    
}
