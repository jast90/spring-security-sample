package me.jastz.authrizationservice.socail;

import me.jastz.accountserviceapi.entity.Account;
import me.jastz.accountserviceapi.service.AccountService;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;

/**
 * Created by zhiwen on 2017/8/25.
 */
public class AccountConnectionSignUp implements ConnectionSignUp {

    private AccountService accountService;

    public AccountConnectionSignUp(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public String execute(Connection<?> connection) {
        UserProfile userProfile = connection.fetchUserProfile();
        Account account = new Account(userProfile.getUsername(), userProfile.getFirstName(), userProfile.getLastName());
        accountService.save(account);
        if (account.getAccountId() != null) {
            return account.getAccountId().toString();
        }
        return null;//返回null代表用户名注册失败
    }
}
