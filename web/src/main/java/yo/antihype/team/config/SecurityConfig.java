package yo.antihype.team.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import yo.antihype.team.filter.JwtAuthenticationFilter;
import yo.antihype.team.filter.JwtAuthorizationFilter;
import yo.antihype.team.handler.JwtLogoutHandler;
import yo.antihype.team.service.JwtBlacklistService;

import static yo.antihype.team.util.SecurityConstants.DELETE_ALL_USERS;
import static yo.antihype.team.util.SecurityConstants.SIGN_UP_URL;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private JwtBlacklistService jwtBlacklistService;

    public SecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder,
                       JwtBlacklistService jwtBlacklistService) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtBlacklistService = jwtBlacklistService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.PUT, SIGN_UP_URL).permitAll()
                .antMatchers(HttpMethod.DELETE, DELETE_ALL_USERS).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), LogoutFilter.class)
                .addFilterBefore(new JwtAuthorizationFilter(authenticationManager(), jwtBlacklistService), LogoutFilter.class)
                .logout()
                .addLogoutHandler(new JwtLogoutHandler(jwtBlacklistService))
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

}
