package ua.varus.stoplist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;

@Configuration
@Order(2)
public class OAuth2ServerConfig {
    @Configuration
    @EnableResourceServer
    @Order(3)
    protected static class Oauth2ServerConfig extends ResourceServerConfigurerAdapter{
        @Autowired
        private ResourceServerTokenServices tokenServices;

        @Value("${security.jwt.resource-ids}")
        private String resourceIds;

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.resourceId(resourceIds).tokenServices(tokenServices);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .requestMatchers()
                    .and()
                    .authorizeRequests()
//                        .antMatchers("/login","login?logout","login?error").permitAll()
                    .antMatchers("/actuator/**", "/api-docs/**").permitAll()
                        .antMatchers("/admin/**" ).authenticated()
                    .and()
                    .csrf().disable()

            ;
        }
    }


    @Configuration
    @EnableAuthorizationServer
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    protected static class AuthorizationServer extends AuthorizationServerConfigurerAdapter{
        @Value("${security.jwt.client-id}")
        private String clientId;

        @Value("${security.jwt.client-secret}")
        private String clientSecret;

        @Value("${security.jwt.grant-type}")
        private String grantType;

        @Value("${security.jwt.scope-read}")
        private String scopeRead;

        @Value("${security.jwt.scope-write}")
        private String scopeWrite = "write";

        @Value("${security.jwt.resource-ids}")
        private String resourceIds;

        @Autowired
        private TokenStore tokenStore;

        @Autowired
        private JwtAccessTokenConverter accessTokenConverter;

        @Autowired
        private AuthenticationManager authenticationManager;

        @Override
        public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
            configurer
                    .inMemory()
                    .withClient(clientId)
                    .secret(clientSecret)
                    .authorizedGrantTypes(grantType)
                    .scopes(scopeRead, scopeWrite)
                    .resourceIds(resourceIds).accessTokenValiditySeconds(4000);
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
            enhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
            endpoints.tokenStore(tokenStore)
                    .accessTokenConverter(accessTokenConverter)
                    .tokenEnhancer(enhancerChain)
                    .authenticationManager(authenticationManager);
        }
    }
}
