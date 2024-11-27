package dci.j24e01.TravelBlog.security;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

         String key = dotenv.get("rememberMeKey");

        http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/submit", "/css/**", "/js/**", "/images/**", "/fonts/**", "/icons/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/admin").permitAll()
                        .requestMatchers("admin_panel").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/admin")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/admin_panel", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                )
                .rememberMe(rememberMe -> rememberMe
                        .key(key)
                        .tokenValiditySeconds(7 * 24 * 60 * 60)
                        .tokenRepository(tokenRepository()));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        return username -> {
            if ("admin".equals(username)) {
                return User
                        .withUsername("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles("ADMIN")
                        .build();
            } else {
                throw new RuntimeException("User not found");
            }
        };
    }
    @Bean
    public InMemoryTokenRepositoryImpl tokenRepository() {
        return new InMemoryTokenRepositoryImpl();
    }
}
