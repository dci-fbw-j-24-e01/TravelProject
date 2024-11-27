package dci.j24e01.TravelBlog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(auth -> {
            auth.anyRequest().permitAll();
        });

        httpSecurity.formLogin(form -> form
                .loginPage("/admin")
                .permitAll()
        );

        return httpSecurity.build();
    }

}