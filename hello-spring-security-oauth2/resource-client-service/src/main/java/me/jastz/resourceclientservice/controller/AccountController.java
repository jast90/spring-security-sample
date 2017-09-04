package me.jastz.resourceclientservice.controller;

import me.jastz.resourceclientservice.form.AccountForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * Created by zhiwen on 2017/9/4.
 */
@RestController
public class AccountController {
    @Autowired
    @Qualifier("authServiceRestTemplate")
    private OAuth2RestTemplate restTemplate;

    @GetMapping("createAccount")
    public ResponseEntity<String> createAccount(AccountForm accountForm) {
        return restTemplate.postForEntity(URI.create("http://localhost:8080/user/createUser"), accountForm, String.class);
    }
}
