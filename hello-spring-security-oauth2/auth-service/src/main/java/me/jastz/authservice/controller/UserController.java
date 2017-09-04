package me.jastz.authservice.controller;

import me.jastz.authservice.form.UserForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhiwen on 2017/9/4.
 */
@Controller
@RequestMapping("user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("createUser")
    @PreAuthorize("#oauth2.hasScope('server')")
    public ResponseEntity<String> createUser(UserForm userForm) {
        logger.info(userForm.toString());
        ResponseEntity<String> responseEntity = new ResponseEntity<String>("创建用户成功", HttpStatus.OK);
        return responseEntity;
    }
}
