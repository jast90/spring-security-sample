package me.jastz.authrizationservice.client;

import me.jastz.accountserviceapi.entity.Account;
import me.jastz.accountserviceapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by zhiwen on 2017/9/8.
 */
@Service("accountService")
public class AccountClient implements AccountService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void save(Account account) {
        
    }

    @Override
    public boolean isComplete(long l) {
        return false;
    }
}
