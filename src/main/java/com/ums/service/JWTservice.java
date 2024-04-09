package com.ums.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ums.entity.Appuser;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTservice {
    public  final static String USER_NAME = "name";
    @Value("${jwt.algorithmkey}")
    private String algorithmkey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiration}")
    private int expiration;
    
    
    
    private Algorithm algorithm;
    @PostConstruct
    public void setAlgorithm(){
      algorithm = Algorithm.HMAC256(algorithmkey);
    }

    public String generateToken(Appuser user){
        return JWT.create()
                .withClaim(USER_NAME,user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .withIssuer(issuer)
                .sign(algorithm);




    }
    
}
