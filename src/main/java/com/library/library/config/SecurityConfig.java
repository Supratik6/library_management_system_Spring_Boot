package com.library.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.library.library.security.CustomUserDetailsService;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService uds;

    public SecurityConfig(CustomUserDetailsService uds) {
        this.uds = uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider p = new DaoAuthenticationProvider();
        p.setUserDetailsService(uds);
        p.setPasswordEncoder(passwordEncoder());
        return p;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .authenticationProvider(authProvider())
            .authorizeHttpRequests(auth -> auth
            	    .requestMatchers("/login", "/signup").permitAll()

            	    // ADMIN only
            	    .requestMatchers(
            	        "/addBookForm",
            	        "/saveBook",
            	        "/deleteBook/**",
            	        "/members",
            	        "/addMember",
            	        "/saveMember",
            	        "/issue",
            	        "/return"
            	    ).hasRole("ADMIN")

            	    // Any logged-in user
            	    .anyRequest().authenticated()
            	)

            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
            )
            .logout(logout -> logout.logoutSuccessUrl("/login?logout"));

        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
