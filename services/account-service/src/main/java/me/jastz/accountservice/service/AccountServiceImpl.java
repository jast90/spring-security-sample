package me.jastz.accountservice.service;

import me.jastz.accountservice.dao.AccountDAO;
import me.jastz.accountserviceapi.entity.Account;
import me.jastz.accountserviceapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhiwen on 2017/9/8.
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDAO accountDAO;

    @Override
    public void save(Account account) {
        accountDAO.save(account);
    }

    @Override
    public boolean isComplete(long accountId) {
        return accountDAO.isComplete(accountId);
    }
}
