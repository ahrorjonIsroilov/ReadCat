package ent.readcat.config.security;

import ent.readcat.config.jwt.JwtUtils;
import ent.readcat.config.security.filter.MyAuthorizationFilter;
import ent.readcat.service.user.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    protected String[] WHITE_LIST = {
            "/home/welcome",
            "/api/v1/auth/login",
            "/api/v1/auth/register",
            "/api/v1/auth/sendConfirmationCode",
            "/api/v1/auth/resetPassword",
            "/api/v1/auth/forgotPassword",
            "/api/v1/auth/confirmAccount",
            "/api/v1/auth/resendResetPassword"
    };

    public SecurityConfig(AuthService authService, PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable();
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers(WHITE_LIST)
                .permitAll()
                .anyRequest()
                .authenticated();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(new MyAuthorizationFilter(new JwtUtils(), authService), UsernamePasswordAuthenticationFilter.class);
    }
}
