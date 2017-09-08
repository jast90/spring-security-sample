package me.jastz.accountservice.strategy;

import javax.servlet.http.HttpSession;

/**
 * Created by zhiwen on 2017/8/28.
 */
public interface CurrentAccount {

    Long getAccountId();

    void setAccountId(Long accountId);

    void setHttpSession(HttpSession httpSession);

}
