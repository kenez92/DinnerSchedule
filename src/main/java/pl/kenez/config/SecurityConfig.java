package pl.kenez.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final String USER_ROLE = "USER";
    private static final String ADMIN_ROLE = "ADMIN";
    @Value("${app.username}")
    private String username;

    @Value("${app.password}")
    private String password;

    @Value("${app.simple.user}")
    private String simpleUser;

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername(username)
                    .password(passwordencoder().encode(password))
                    .roles(USER_ROLE, ADMIN_ROLE)
                    .build(),
                User.withUsername(simpleUser)
                    .password(passwordencoder().encode(simpleUser))
                    .roles(USER_ROLE)
                    .build());
    }

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                        e -> e.requestMatchers(HttpMethod.POST, "/admin/update/from/excel").hasRole(ADMIN_ROLE)
                              .requestMatchers(HttpMethod.GET, "/admin").hasRole(ADMIN_ROLE)
                              .requestMatchers(HttpMethod.POST, "/api/recipe").hasRole(USER_ROLE)
                              .requestMatchers(HttpMethod.GET, "/api/recipe").hasRole(USER_ROLE)
                              .requestMatchers(HttpMethod.POST, "/api/schedule").hasRole(USER_ROLE)
                              .requestMatchers(HttpMethod.GET, "/api/schedule").hasRole(USER_ROLE))
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .formLogin(customizer -> customizer.defaultSuccessUrl("/api/recipe"))
                .build();
    }

    @Bean
    public PasswordEncoder passwordencoder() {
        return new BCryptPasswordEncoder();
    }
}
