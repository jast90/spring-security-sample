package me.jastz.ssoclient.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhiwen on 2017/9/13.
 */
@RestController
public class ResourceController {

    @GetMapping("hello")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }
}
