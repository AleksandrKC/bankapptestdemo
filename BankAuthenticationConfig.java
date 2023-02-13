package com.example.bank.configs;

import com.example.bank.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@Slf4j
@EnableWebSecurity(debug = true)
public class BankAuthenticationConfig {

    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> {
                    try {
                        authorize
                                .requestMatchers("/","/login","logout").permitAll()
                                .requestMatchers("/user/**").hasRole("USER")
                                .requestMatchers("/manager/**").hasRole("MANAGER")
                                .requestMatchers("/authenticated/**").authenticated()
                                .requestMatchers("/only_for_admins/**").hasRole("ADMIN")
                                .and()
                                .formLogin()
                                .and()
                                .logout().logoutSuccessUrl("/");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                );
        http.httpBasic();
        http.csrf().disable();
        String s = passwordEncoder().encode("100");
        System.out.println("100======" + s);
        System.out.println("OK?"+passwordEncoder().matches("100",s));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        log.info("MYDEBUG!___________________BCryptPasswordEncoder() call");

        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("MYDEBUG!!!!!------------------------------new encoded 100=" + encoder.encode("100"));

        log.info("MYDEBUG: daoAuthenticationProvider bean init");
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }
}