package edu.miu.cs425.eCarRental.config;
import edu.miu.cs425.eCarRental.service.serviceimplementation.CarRentalUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    public WebSecurityConfig(CarRentalUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                .frameOptions().sameOrigin()
                .and()
                .authorizeRequests()
                .antMatchers("/resources/static/**", "/images/**", "/css/**", "/ecarrental/public/**").permitAll()
                .antMatchers("/", "/ecarrental").permitAll()
                .antMatchers("/ecarrental/secured/admin/**", "/resources/secured/admin/**", "/ecarrental/admin/**", "/admin/**").hasRole("ADMIN")
                .antMatchers("/ecarrental/secured/staff/**", "/resources/secured/staff/**","/staff/**").hasRole("STAFF")
                .antMatchers("/ecarrental/secured/customer/**", "/resources/secured/staff/**").hasRole("CUSTOMER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/ecarrental/public/home/login")
                .defaultSuccessUrl("/ecarrental/secured/home")
                .failureUrl("/ecarrental/public/home/login?error")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/ecarrental/public/logout"))
                .logoutSuccessUrl("/ecarrental/public/login?logout")
                .permitAll()
                .and()
                .exceptionHandling();
    }
}
