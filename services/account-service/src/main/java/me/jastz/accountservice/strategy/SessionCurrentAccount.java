package me.jastz.accountservice.strategy;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

/**
 * Created by zhiwen on 2017/8/28.
 */
public class SessionCurrentAccount implements CurrentAccount {

    @Autowired
    private HttpSession httpSession;

    @Override
    public Long getAccountId() {
        if (httpSession == null) {
            throw new RuntimeException("Should define a HttpSession bean");
        }
        return (Long) httpSession.getAttribute("currentUser");
    }

    @Override
    public void setAccountId(Long accountId) {
        if (httpSession == null) {
            throw new RuntimeException("Should define a HttpSession bean");
        }
        httpSession.setAttribute("currentUser", accountId);
    }

    @Override
    public void setHttpSession(HttpSession session) {
        this.httpSession = session;
    }
}
