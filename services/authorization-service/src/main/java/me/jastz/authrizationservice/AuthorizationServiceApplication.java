package me.jastz.authrizationservice;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import me.jastz.authrizationservice.strategy.CurrentAccount;
import me.jastz.authrizationservice.strategy.SessionCurrentAccount;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@SpringBootApplication
@EnableAuthorizationServer
@ServletComponentScan
public class AuthorizationServiceApplication extends AuthorizationServerConfigurerAdapter {
    @Bean
    public CurrentAccount CurrentAccount() {
        return new SessionCurrentAccount();
    }

    @Bean
    public DataSource dataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

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
