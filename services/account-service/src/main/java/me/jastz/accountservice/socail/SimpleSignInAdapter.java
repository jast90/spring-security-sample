package me.jastz.accountservice.socail;

import me.jastz.accountservice.dao.AccountDAO;
import me.jastz.accountservice.strategy.CurrentAccount;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhiwen on 2017/8/25.
 */
public class SimpleSignInAdapter implements SignInAdapter {

    private AccountDAO accountDAO;

    private CurrentAccount currentAccount;

    public SimpleSignInAdapter(AccountDAO accountDAO, CurrentAccount currentAccount) {
        this.accountDAO = accountDAO;
        this.currentAccount = currentAccount;
    }

    @Override
    public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
        // 根据userId 判断用户是否绑定手机及设置密码，没有：跳转到设置手机、密码页面，有：跳转到首页
        if (!accountDAO.isComplete(Long.parseLong(userId))) {
            return "/account/viewComplete";
        }
        HttpServletRequest httpServletRequest = request.getNativeRequest(HttpServletRequest.class);
        if (httpServletRequest == null) {
            throw new RuntimeException("NativeWebRequest to HttpServletRequest error");
        }
        currentAccount.setHttpSession(httpServletRequest.getSession());
        currentAccount.setAccountId(Long.parseLong(userId));
        return null;
    }
}
