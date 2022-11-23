package com.group03.desafio_integrador.bean;

import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTBean {
    private DecodedJWT decodedJWT;
    public void setDecodedJWT(DecodedJWT decodedJWT) {
        this.decodedJWT = decodedJWT;
    }
    public DecodedJWT getDecodedJWT() {
        return decodedJWT;
    }
}


