package com.portfolio.mutex.srms.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService {

  @Value("${security.jwt.secret-key}")
  private String secretKey;

  @Value("${security.jwt.expiration-time}")
  private long jwtExpiration;

  /**
   * {@inheritDoc}
   */
  @Override
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public long getExpirationTime() {
    return jwtExpiration;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
  }

  /**
   * Extracts a claim from a given token
   *
   * @param token          the token
   * @param claimsResolver the function to resolve what claim is needed
   * @param <T>            the type of claim
   * @return the value of the claim
   */
  private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  /**
   * Extract all claims from a given token
   *
   * @param token the token
   * @return all claims present in the given token
   */
  private Claims extractAllClaims(String token) {
    return Jwts.parser()
        .verifyWith(getSecretKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  /**
   * Retrieves the secret key
   *
   * @return the secret key
   */
  private SecretKey getSecretKey() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
  }

  /**
   * Generates a JWT for the given user
   *
   * @param extraClaims the extra claims
   * @param userDetails the user details
   * @return the generated JWT
   */
  private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
    return buildToken(extraClaims, userDetails, jwtExpiration);
  }

  /**
   * Builds a JWT
   *
   * @param extraClaims   the extra claims
   * @param userDetails   the user details
   * @param jwtExpiration the jwt expiration
   * @return the JWT
   */
  private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails,
      long jwtExpiration) {
    return Jwts.builder()
        .claims(extraClaims)
        .subject(userDetails.getUsername())
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
        .signWith(getSecretKey(), SIG.HS256)
        .compact();
  }

  /**
   * Checks if the given token is expired
   *
   * @param token the token to be checked
   * @return <b>true</b> if the token is already expired, otherwise, <b>false</b>
   */
  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  /**
   * Extracts expiration time from the given token
   *
   * @param token the token
   * @return the expiration time
   */
  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }
}
