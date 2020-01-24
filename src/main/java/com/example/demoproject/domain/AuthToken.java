package com.example.demoproject.domain;

import java.io.Serializable;

public class AuthToken implements Serializable {
    private String token;
    private String username;

    public AuthToken(){

    }

    public AuthToken(String token, String username){
        this.token = token;
        this.username = username;
    }

    public AuthToken(String token){
        this.token = token;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String toString(){
        return "authString";
    }
}
