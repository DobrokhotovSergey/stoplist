package ua.varus.stoplist.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static ua.varus.stoplist.domain.RoleName.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Value("${security.signing-key}")
    private String signingKey;

    @Value("${security.encoding-strength}")
    private Integer encodingStrength;

    @Value("${security.security-realm}")
    private String securityRealm;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/stoplist/**")
                .authenticated()
//                .access("hasAnyRole('"+ ROLE_RADM+"','"+ROLE_SADM+"', '"+ROLE_SEC+"', '"+ROLE_OPER+"')")
//                .antMatchers("/oauth/token").permitAll()
                .and()
                .formLogin().loginPage("/login").failureUrl("/login?error")
//                .permitAll()
                .usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/stoplist", true)
//                .permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
//                .clearAuthentication(true)
//                .deleteCookies("JSESSIONID")
//                .invalidateHttpSession(true)
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                .and()
                .csrf()
                .and()
//                .headers().frameOptions().disable()
//                .and()
                .sessionManagement()
                .maximumSessions(100)
                .maxSessionsPreventsLogin(true)
                .expiredUrl("/login").sessionRegistry(sessionRegistry())

        ;
    }
    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
    }



    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(signingKey);
        return converter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    @Primary
    //Making this primary to avoid any accidental duplication with another token service instance of the same name
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }
}
