package com.qa.user.userapi.persistence.domain;

public class SentUser {

    private Long userId;

    private String firstName;

    private String lastName;

    private String userName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getUserName() {
        return userName;
    }

    public void setAccountNumber(String userName) {
        this.userName = userName;
    }

    public SentEmail getSentEmail() {
        return sentEmail;
    }

    public void setSentEmail(SentEmail sentEmail) {
        this.sentEmail = sentEmail;
    }

    private SentEmail sentEmail;

    public SentUser(){}

    public SentUser(User user){
        this.userId = user.getUserId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.userName = user.getUserName();
        this.sentEmail = new SentEmail(user.getEmail());
    }


}
