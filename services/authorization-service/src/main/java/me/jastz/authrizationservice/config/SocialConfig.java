package me.jastz.authrizationservice.config;

import com.alibaba.druid.pool.DruidDataSource;
import me.jastz.accountserviceapi.service.AccountService;
import me.jastz.authrizationservice.socail.AccountConnectionSignUp;
import me.jastz.authrizationservice.strategy.CurrentAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.SessionUserIdSource;
import org.springframework.social.weibo.connect.WeiboConnectionFactory;

import javax.sql.DataSource;

/**
 * Created by zhiwen on 2017/9/8.
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;


    @Autowired
    private AccountService accountService;

    @Autowired
    private Environment environment;

    @Autowired
    private CurrentAccount currentAccount;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        connectionFactoryConfigurer.addConnectionFactory(
                new WeiboConnectionFactory(environment.getProperty("weibo.clientId")
                        , environment.getProperty("weibo.clientSecret")));
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new SessionUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource
                , connectionFactoryLocator, Encryptors.noOpText());
        repository.setConnectionSignUp(new AccountConnectionSignUp(accountService));
        return repository;
    }

    /*@Bean
    public ProviderSignInController providerSignInController(ConnectionFactoryLocator connectionFactoryLocator
            , UsersConnectionRepository usersConnectionRepository) {
        ProviderSignInController providerSignInController = new ProviderSignInController(connectionFactoryLocator, usersConnectionRepository
                , new SimpleSignInAdapter(accountDAO, currentAccount));
        return providerSignInController;
    }

    @Bean
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
        ConnectController controller = new ConnectController(
                connectionFactoryLocator, connectionRepository);
        return controller;
    }*/

}
