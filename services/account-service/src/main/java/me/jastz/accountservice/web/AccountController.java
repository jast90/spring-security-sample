package me.jastz.accountservice.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

/**
 * Created by zhiwen on 2017/9/8.
 */
@Controller
public class AccountController {
    @GetMapping("/")
    public ResponseEntity<Principal> currentUser(Principal principal) {
        return new ResponseEntity(principal, HttpStatus.OK);
    }
}
