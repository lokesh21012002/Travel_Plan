package com.example.travelplan.config;

import com.example.travelplan.models.Role;
import com.example.travelplan.services.UserService;
import com.example.travelplan.utils.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.context.annotation.Bean;
import
        org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SuppressWarnings("removal")
@Configuration
@EnableWebSecurity

@RequiredArgsConstructor
public class WebSecurityConfig {

    @Autowired
    private JwtRequestFilter requestFilter;


    @Autowired
    private  UserService userService;
    @Bean
    public RestTemplate restTesmplate() {
        return new RestTemplate();
    }






    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider userDetailsService() {

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userService.userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;


    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
          corsConfiguration.setAllowedOriginPatterns(Arrays.asList(
//                "http://travel-plan-client.s3-website.ap-south-1.amazonaws.com/**",
//                "http://localhost:3000",

                "*"
                )


        );
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

//    @Bean
//    public FilterRegistrationBean corsFilter(){
//        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource=new UrlBasedCorsConfigurationSource();
//        CorsConfiguration corsConfiguration=new CorsConfiguration();
//        corsConfiguration.setAllowCredentials(true);
//        corsConfiguration.addAllowedOriginPattern("*");
//        corsConfiguration.addAllowedHeader("Authorization");
//        corsConfiguration.addAllowedHeader("Accept");
//        corsConfiguration.addAllowedMethod("GET");
//        corsConfiguration.addAllowedMethod("POST");
//        corsConfiguration.addAllowedMethod("PUT");
//        corsConfiguration.addAllowedMethod("DELETE");
//        corsConfiguration.addAllowedMethod("OPTIONS");
//        corsConfiguration.setMaxAge(3600L);
//
//
//
//
//
//
//
//        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",corsConfiguration);
//        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean(new CorsFilter(urlBasedCorsConfigurationSource));
//
//        return filterRegistrationBean;
//
//    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // http.cors().and().csrf().disable();
        
http.csrf()
        .disable()
        .cors()
        .disable();
http.headers().frameOptions().disable().and().cors().and().csrf().disable();

        http

                .authorizeHttpRequests((request) -> request.anyRequest().permitAll()
                        .requestMatchers("/api/v1/admin/**").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers("/api/v1/user/**").hasAnyAuthority(Role.USER.name())
                        .anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(userDetailsService()).addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class


                );
        return http.build();

//                .authorizeRequests().requestMatchers("/**","/authenticate**", "/login/**","/admin/home/**","/user/**","home/**").permitAll()
//                .anyRequest().authenticated/()
//                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class)
//                 .httpBasic();
//        http.headers().frameOptions().disable();
//        return http.build();
//    }
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//
//
//
//
//
//        http.authorizeHttpRequests()
//                .requestMatchers("/login/*").permitAll()
//                .anyRequest().authenticated()
//                .and().httpBasic();
//        return http.build();


//        http.csrf().disable();
//                .authorizeHttpRequests().requestMatchers("/login","/signup").permitAll()
//                http.authorizeHttpRequests((requests)->requests
//
//                                .requestMatchers( "/login","/signup").permitAll()
////                                .requestMatchers("/*").authenticated()
//                        );
//                .authorizeRequests().requestMatchers( "/login","/signup").permitAll()
//                .requestMatchers("*").authenticated();
//        http.formLogin(Customizer.withDefaults());
//        http.httpBasic(Customizer.withDefaults());


//        http.headers().frameOptions().disable();
//        return http.build();
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .requestMatchers("/signup", "/login").permitAll()
//                .anyRequest().authenticated();
//    }

//    @Bean
//    public SecurityFilterChain filterChain(HttinpSecurity http) throws Exception
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
}
