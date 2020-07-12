package com.ldce.Main;

public class AuthenticationResponce {
    private final String jwt;
    private final String domain;

    public AuthenticationResponce(String jwt, String domain) {
        this.jwt = jwt;
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }

    public String getJwt() {
        return jwt;
    }



}
