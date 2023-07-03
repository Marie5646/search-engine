package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


import static org.springframework.security.config.Customizer.withDefaults;


// informs spring that this class is to configure the spring application
@Configuration
// will allow us to edit the MVC security for our application
@EnableWebSecurity
public class SecurityConfiguration {



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests

                        /* Pages that do not require authentication
                         * anyone can visit the home page, register, login, and view ads */
                        // /event/reviews/create is a public page and can be visited by anyone
                        .requestMatchers("/").permitAll()
                        // allow loading of static resources
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                )
                /* Login configuration */
                .formLogin((login) -> login.loginPage("/LoginPage").defaultSuccessUrl("/profile"))
                /* Logout configuration */
                .logout((logout) -> logout.logoutSuccessUrl("/LoginPage?logout"))
                .httpBasic(withDefaults());
        return http.build();
    }
}
