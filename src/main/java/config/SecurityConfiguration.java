package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers("/**")
                .permitAll()
                .anyRequest()
                .authenticated();


    }


}
