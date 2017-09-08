package me.jastz.authrizationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@SpringBootApplication
@EnableAuthorizationServer
public class AuthorizationServiceApplication extends AuthorizationServerConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServiceApplication.class, args);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("foo").secret("bar").scopes("foo").authorizedGrantTypes("client_credentials", "refresh_token");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }
}
