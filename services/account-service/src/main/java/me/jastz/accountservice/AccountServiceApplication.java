package me.jastz.accountservice;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import me.jastz.accountservice.socail.SimpleSocialUserDetailsService;
import me.jastz.accountservice.strategy.CurrentAccount;
import me.jastz.accountservice.strategy.SessionCurrentAccount;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

@SpringBootApplication
@MapperScan("me.jastz.*.dao")
@ServletComponentScan
public class AccountServiceApplication {

    @Bean
    public CurrentAccount CurrentAccount() {
        return new SessionCurrentAccount();
    }

    @Bean
    public DruidDataSource druidDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @Configuration
    @EnableWebSecurity
    protected static class WebSecurity extends WebSecurityConfigurerAdapter {
        @Autowired
        private DruidDataSource druidDataSource;

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public SocialUserDetailsService socialUserDetailsService() {
            return new SimpleSocialUserDetailsService(userDetailsService());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login").permitAll()
                    .and()
                    .apply(new SpringSocialConfigurer());


        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.jdbcAuthentication()
                    .dataSource(druidDataSource)
                    .passwordEncoder(passwordEncoder());
        }
    }
}
