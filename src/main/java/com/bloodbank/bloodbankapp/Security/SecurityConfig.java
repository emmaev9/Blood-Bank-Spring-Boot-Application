package com.bloodbank.bloodbankapp.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private LoginHandler loginHandler;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.csrf().disable().authorizeRequests()
                .antMatchers( "/registerDonor","/login","/AdminHome/**","/DonorHome/**","/DoctorHome/**","/EditDonorProfile", "/registerDonor", "/registerDoctor").permitAll()
                //.antMatchers("/AdminHome").access("hasRole('ROLE_ADMIM')")
                //.antMatchers("/DonorHome").access("hasRole('ROLE_USER')")
                //.antMatchers("/DoctorHome").access("hasRole('ROLE_DOCTOR')")
                .and()
                .formLogin().loginPage("/login")
                .successHandler(loginHandler)
                .failureUrl("/login?error")
              //  .usernameParameter("username")
                .and()
                .logout().logoutSuccessUrl("/login?logout");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
