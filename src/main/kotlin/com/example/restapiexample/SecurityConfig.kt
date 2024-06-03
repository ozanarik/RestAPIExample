package com.example.restapiexample

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { csrf -> csrf.disable() }  // CSRF korumasını devre dışı bırak
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/users/**").authenticated()  // /users/** endpoint'lerine kimlik doğrulama gerekli
                    .anyRequest().permitAll()  // Diğer tüm istekler izinli
            }
            .httpBasic { basic -> }  // Temel kimlik doğrulama kullan
        return http.build()
    }
}