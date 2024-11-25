package dci.j24e01.TravelBlog.security;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/submit", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login") // Specifies the custom login page URL
                        .defaultSuccessUrl("/admin", true) // Redirect after successful login
                        .permitAll()
                )
                .logout(logout -> logout.logoutUrl("/logout").permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public org.springframework.security.core.userdetails.UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        return username -> {
            if ("admin".equals(username)) {
                return org.springframework.security.core.userdetails.User
                        .withUsername("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles("ADMIN")
                        .build();
            } else {
                throw new RuntimeException("User not found");
            }
        };
    }
}
