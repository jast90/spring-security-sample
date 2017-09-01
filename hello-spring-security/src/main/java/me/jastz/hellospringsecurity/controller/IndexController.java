package me.jastz.hellospringsecurity.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhiwen on 2017/9/1.
 */
@Controller
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("")
    public String index() {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        logger.info(principal.toString());
        logger.info(username);
        return "index";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/isAnonymous")
    @ResponseBody
    public String isAnonymous() {
        return "isAnonymous";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/isAuthenticated")
    @ResponseBody
    public String isAuthenticated() {
        return "isAuthenticated";
    }

    @PreAuthorize("hasRole('VIP')")
    @GetMapping("/vip")
    @ResponseBody
    public String hasVipRole() {
        return "hasVipRole";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/hasUserRole")
    @ResponseBody
    public String hasUserRole() {
        return "hasUserRole";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/hasAdminRole")
    @ResponseBody
    public String hasAdminRole() {
        return "hasAdminRole";
    }
}
