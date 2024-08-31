//package com.exampleView.authentication;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//import io.jsonwebtoken.*;
//
//import java.util.Date;
//
//
//@Component
//public class JwtCore {
//    private String jwtSecret;
//    private Integer timeLive;
//
//    @Autowired
//    public JwtCore(String jwtSecret, Integer timeLive) {
//        this.timeLive = timeLive;
//        this.jwtSecret = jwtSecret;
//    }
//
//    public String generateToken(Authentication authentication) {
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        return Jwts.builder().setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(new Date().getTime() + timeLive))
//                .signWith(SignatureAlgorithm.ES256, jwtSecret)
//                .compact();//?username??????
//    }
//}
