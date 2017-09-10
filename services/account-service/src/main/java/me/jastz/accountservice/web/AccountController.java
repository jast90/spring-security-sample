package me.jastz.accountservice.web;

import me.jastz.accountserviceapi.entity.Account;
import me.jastz.accountserviceapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhiwen on 2017/9/8.
 */
@Controller
@RequestMapping("account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("create")
    public ResponseEntity<String> save(Account account) {
        accountService.save(account);
        return new ResponseEntity<>("创建用户成功", HttpStatus.OK);
    }

    @GetMapping("isComplete")
    public ResponseEntity<Boolean> isComplete(long accountId) {
        accountService.isComplete(accountId);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
