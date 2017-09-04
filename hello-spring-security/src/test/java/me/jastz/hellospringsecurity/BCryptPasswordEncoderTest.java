package me.jastz.hellospringsecurity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by zhiwen on 2017/9/4.
 */
public class BCryptPasswordEncoderTest {
    private BCryptPasswordEncoder encoder;

    @Before
    public void init() {
        encoder = new BCryptPasswordEncoder();
    }

    @Test
    public void encode() {
        String rawPassword = "123456", encodedPassword;
        encodedPassword = encoder.encode(rawPassword);
        System.out.println(encodedPassword);
        Assert.assertTrue(encoder.matches(rawPassword, encodedPassword));
    }
}
