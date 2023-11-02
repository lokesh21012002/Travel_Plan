package com.example.travelplan.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import
        org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@SuppressWarnings("removal")
@Configuration
public class WebSecurityConfig  {
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().requestMatchers("/**", "/login/**","/admin/home/**","/user/**","home/**").permitAll()
                .anyRequest().authenticated();
        http.headers().frameOptions().disable();
        return http.build();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/signup", "/login").permitAll()
//                .anyRequest().authenticated();
//    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
//    {


//        http.authorizeHttpRequests((authz)->authz.anyRequest().permitAll());
//        http.csrf().disable();

//        http
//                .authorizeHttpRequests(requests -> requests
//                        .requestMatchers("/", "/signup**").anonymous()
//
////                        .hasAnyRole("SCHUELER", "LEHRER", "VERWALTUNG")
////                        .anyRequest().authenticated()
//                );

//                .formLogin(form -> form
//                        .loginPage("/signup")
//                        .permitAll()
//                )
//                .logout(LogoutConfigurer::permitAll);







//        http.authorizeHttpRequests((authz)->authz.anyRequest().permitAll());
//        return http.build();
//    }
}