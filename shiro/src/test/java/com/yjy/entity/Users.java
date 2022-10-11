package com.yjy.entity;

public class Users {
    private int id;
    private String username;
    private String password;
    private String password_salt;

    public Users(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Users(String username, String password, String password_salt) {
        this.username = username;
        this.password = password;
        this.password_salt = password_salt;
    }

    public Users(String password, String password_salt) {
        this.password = password;
        this.password_salt = password_salt;
    }

    public Users(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_salt() {
        return password_salt;
    }

    public void setPassword_salt(String password_salt) {
        this.password_salt = password_salt;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", password_salt='" + password_salt + '\'' +
                '}';
    }
}
