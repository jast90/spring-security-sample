package me.jastz.accountservice.entity;

/**
 * Created by zhiwen on 2017/8/25.
 */
public class Account {
    private Long accountId;
    private String username;
    private String firstName;
    private String lastName;

    public Account(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
