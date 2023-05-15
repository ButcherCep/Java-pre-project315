package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@ComponentScan("ru.kata.spring.boot_security.demo")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler;
    private UserDetailsService userService;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public WebSecurityConfig(SuccessUserHandler successUserHandler, UserDetailsService userService, PasswordEncoder passwordEncoder) {
        this.successUserHandler = successUserHandler;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        System.out.println(passwordEncoder.encode("user"));
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .successHandler(successUserHandler)
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll();
        http
                .authorizeRequests()
                .antMatchers("/login").anonymous()
                .antMatchers("/").access("hasAnyRole('ROLE_USER','ROLE_ADMIN')").anyRequest().authenticated();

        http.logout()
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .and().csrf().disable();
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/admin/**").permitAll()
//                .anyRequest().permitAll() // изменил на permit и все заработало
//                .and()
//                .formLogin().loginPage("/login")
//                .successHandler(successUserHandler)
//                .permitAll()
//                .and()
//                .logout().logoutUrl("/logout").logoutSuccessUrl("/login")
//                .permitAll();
    }
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }
}
