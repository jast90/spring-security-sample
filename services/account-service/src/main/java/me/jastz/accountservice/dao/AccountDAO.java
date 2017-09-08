package me.jastz.accountservice.dao;

import me.jastz.accountservice.entity.Account;
import org.springframework.stereotype.Repository;

/**
 * Created by zhiwen on 2017/8/25.
 */
@Repository
public interface AccountDAO {

    void save(Account account);

    boolean isComplete(long accountId);

    boolean isPhoneNumberExist(String phoneNumber);

    int updateComplete(Long accountId, boolean complete);

}
