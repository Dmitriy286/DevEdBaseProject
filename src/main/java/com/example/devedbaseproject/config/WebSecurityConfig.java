package com.example.devedbaseproject.config;

import com.example.devedbaseproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
   }


//@Override
//public void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.inMemoryAuthentication()
//                .withUser("user")
//                .password("user")
//                .authorities("ROLE_USER")
//            .and()
//                .withUser("admin")
//                .password("admin")
//                .authorities("ROLE_ADMIN")
//            .and()
//                .withUser("q")
//                .password("q")
//                .authorities("ROLE_ADMIN")
//            .and()
//                .withUser("Bob1")
//                .password("asdf")
//                .authorities("ROLE_USER");
//
//}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/registration", "/order-data", "/purchase-data").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .permitAll()
                .defaultSuccessUrl("/index").permitAll()
            .and()
                .logout()
                .permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/style.css", "/image/1.jpg", "/image/1_1.jpg",
                "/image/2.jpg", "/image/2_2.png", "/image/3.jpg", "/image/3_3.jpg", "/image/4.jpg",
                "/image/4_4.jpg", "/image/5.jpg", "/image/5_5.jpg", "/image/6.jpg", "/image/6_6.jpg",
                "/image/Dane_png1.png",
                "https://fonts.googleapis.com/", "https://fonts.gstatic.com/",
                "https://fonts.googleapis.com/css2?family=Roboto+Slab&display=swap%22");
    }


}
