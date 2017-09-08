package me.jastz.accountservice.dao;

import me.jastz.accountserviceapi.entity.Account;
import org.springframework.stereotype.Repository;

/**
 * Created by zhiwen on 2017/8/25.
 */
@Repository
public interface AccountDAO {

    void save(Account account);

    boolean isComplete(long accountId);

}
