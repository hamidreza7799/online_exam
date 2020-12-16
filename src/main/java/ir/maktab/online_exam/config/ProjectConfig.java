package ir.maktab.online_exam.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public ModelMapper createModelMapperForDTO(){
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider);
        http.httpBasic();
        http.formLogin().loginPage("/login_page.html").permitAll();
        //TODO fix csrf attack
        http.csrf().disable();
        http.authorizeRequests()
                .mvcMatchers("/new-register-requests/**", "/courses/**").hasRole("MANAGER")
                .mvcMatchers("/courses/*/students/*", "/courses/*/teachers/*").hasRole("MANAGER")
                .mvcMatchers("/users**").authenticated();
    }

}
