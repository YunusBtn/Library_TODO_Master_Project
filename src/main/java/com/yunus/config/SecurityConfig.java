package com.yunus.config;

import com.yunus.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserRepository userRepository;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(UserRepository userRepository, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userRepository = userRepository;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, ex) -> {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.setHeader("WWW-Authenticate", "");
            response.getWriter().write("{\"error\": \"Unauthorized access\"}");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers("/api/auth/**","/swagger-ui/**", "/v3/api-docs/**",
                                "/swagger-resources/**", "/webjars/**", "/docs").permitAll()
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                        );

        http.csrf(csrf -> csrf.disable());

        http.exceptionHandling(exceptionHandling ->
                exceptionHandling
                        .authenticationEntryPoint(authenticationEntryPoint()));

        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}










