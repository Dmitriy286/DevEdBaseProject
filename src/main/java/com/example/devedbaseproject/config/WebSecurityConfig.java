package com.example.devedbaseproject.config;

import com.example.devedbaseproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //ниже строки из работающего кода!!!
//    @Autowired
//    private DataSource dataSource;
    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


//    @Bean
//    public PasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
    ////      в configure:          .passwordEncoder(encoder())
//    }
//
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
                //ниже строки из работающего кода!!!
//                jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select username, password, active from Employees where username=?")
//                .authoritiesByUsernameQuery("select e.username, r.name from employees as e inner join employee_role as er on e.id = er.employee_id inner join roles as r on r.id = er.role_id where e.username=?");
    }

//                .passwordEncoder(NoOpPasswordEncoder.getInstance())
//                .usersByUsernameQuery("select username, password, active from Employees where username=?")
////                .authoritiesByUsernameQuery("select e.login, r.roles from Employees e " +
////                "inner join role r on r.id = er.role_id " +
////                        "inner join employee_role er on e.id = er.employee_id " +
////                        "where e.username=?");
//                .authoritiesByUsernameQuery("select e.username, r.name" +
//                        "from employees as e" +
//                        "inner join employee_role as er on e.id = er.employee_id" +
//                        "inner join roles as r on r.id = er.role_id" +
//                        "where e.username=?");
//    }
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //и добавляем его сюда
//        auth.authenticationProvider(customAuthencationProvider);
//    }



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
                .antMatchers("/", "/registration").permitAll()
                .anyRequest().authenticated()
//                .antMatchers("/admin/**").hasRole("Admin")
//                .antMatchers("/user/**").hasAnyRole("Manager", "Admin")
//                .antMatchers("/hello/**").permitAll()
            .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
            .and()
                .logout()
                .permitAll();
    }


}
