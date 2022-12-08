package com.demo.usermanagement.tokens;

public class TokenRes {
    private String token;

    private String username;
    private String name;
    private Long uid;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public TokenRes(String accessToken, Long uid, String username, String name) {
        this.token = accessToken;
        this.username = username;
        this.name = name;
        this.uid = uid;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
