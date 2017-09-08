package me.jastz.accountserviceapi.service;

import me.jastz.accountserviceapi.entity.Account;
import org.springframework.stereotype.Service;

/**
 * Created by zhiwen on 2017/9/8.
 */
@Service
public interface AccountService {

    void save(Account account);

    boolean isComplete(long l);
}
