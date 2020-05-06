package com.setu.biller.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class JwtTokenProvider {

  /**
   * Ideally these should come from either config-server or aws secret manager.
   */
  @Value("${jwt.secret}")
  private String secretKey;

  @Value(("${jwt.schemeId}"))
  private String schemeId;

  @Autowired
  UserDetailsService userDetailsService;

  public Authentication getAuthentication(String token) {
    UserDetails userDetails = userDetailsService.loadUserByUsername("rahul");
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  public String resolveToken(HttpServletRequest req) {
    String bearerToken = req.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }

  public boolean validateToken(String token) {
    try {
      verifyBearerToken(token);
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      throw new RuntimeException("Expired or invalid JWT token" + HttpStatus.INTERNAL_SERVER_ERROR.toString());
    }
  }

  public boolean verifyBearerToken(String bearerToken) throws JWTVerificationException {
    bearerToken = bearerToken.replace("Bearer ", "");
    Algorithm algorithm = Algorithm.HMAC256(this.secretKey);
    JWTVerifier verifier = JWT.require(algorithm)
        .acceptLeeway(10)
        .withAudience(this.schemeId)
        .build(); //Reusable verifier instance
    DecodedJWT jwt = verifier.verify(bearerToken);
    return true;
  }
}
