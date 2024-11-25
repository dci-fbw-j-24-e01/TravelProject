package dci.j24e01.TravelBlog;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/css/**", "/images/**").permitAll();
            auth.anyRequest().authenticated();
        });

        httpSecurity.formLogin(form -> form
                .loginPage("/admin")
                .defaultSuccessUrl("/admin_panel", true)
                .permitAll()
        );
//        httpSecurity.formLogin(Customizer.withDefaults());

        httpSecurity.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll()
        );

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        Dotenv dotenv = Dotenv.load();

        String username = dotenv.get("ADMIN_LOGIN");
        String password = dotenv.get("ADMIN_PASSWORD");

        UserDetails admin = User
                .withDefaultPasswordEncoder()
                .username(username)
                .password(password)
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }
}
