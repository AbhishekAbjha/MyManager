package com.coronate.mymanager.domain;

/**
 * Created by abhishekjha on 26/01/16.
 */
public class PasswordAccount {

    private String accountId;
    private String accountName;
    private String userName;
    private String password;
    private String notes;

    public PasswordAccount(){

    }

    public PasswordAccount(String accountId, String accountName, String userName, String password, String notes) {
        super();
        this.accountId = accountId;
        this.accountName = accountName;
        this.userName = userName;
        this.password = password;
        this.notes = notes;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "PasswordAccount{" +
                "accountId='" + accountId + '\'' +
                ", accountName='" + accountName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
