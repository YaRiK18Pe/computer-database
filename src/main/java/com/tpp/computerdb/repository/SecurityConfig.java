package com.tpp.computerdb.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests(authz -> authz
                // Дозволити доступ до головних сторінок без аутентифікації
                .requestMatchers("/", "/home", "/login", "/register", "/css/**", "/error", "/access-denied").permitAll()
                
                // Обмежити доступ до дій з продуктами, компонентами та продукт-компонентами для ADMIN
                .requestMatchers("/products/add", "/products/edit/**", "/products/delete/**").hasRole("ADMIN")
                .requestMatchers("/components/add", "/components/edit/**", "/components/delete/**").hasRole("ADMIN")
                .requestMatchers("/productcomponents/add", "/productcomponents/edit/**", "/productcomponents/delete/**").hasRole("ADMIN")
                
                // Доступ до сторінок продуктів, компонентів та продукт-компонентів лише для USER та ADMIN
                .requestMatchers("/products", "/components", "/productcomponents").hasAnyRole("USER", "ADMIN")
                
                // Доступ до всіх сторінок для ADMIN в адмін панелі
                .requestMatchers("/admin/**").hasRole("ADMIN")
                
                // Заборонити доступ до всіх інших сторінок, якщо не авторизований
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/error")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login")
                .permitAll()
            )
            .exceptionHandling(ex -> ex
                .accessDeniedPage("/access-denied") // Перенаправлення на сторінку доступу заборонено
            );
        
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
