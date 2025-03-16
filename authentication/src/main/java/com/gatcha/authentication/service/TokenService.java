package com.gatcha.authentication.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

  @Value("${application.secretKey}")
  private String secretKey;

  private static final String ALGORITHM = "AES";
  private static final String dataTemplate = "%s-%s";
  String formatPattern = "yyyy/MM/dd-HH:mm:ss";

  public String createToken(String userId, LocalDateTime creationTokenDate) throws Exception {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatPattern);
    var issuedAt = creationTokenDate.format(dateTimeFormatter);
    String data = dataTemplate.formatted(userId, issuedAt);

    Cipher cipher = getEncryptor();
    byte[] encryptedData = cipher.doFinal(data.getBytes());
    return Base64.getEncoder().encodeToString(encryptedData);
  }

  public String validateTokenAndGetData(String token) throws Exception {
    Cipher cipher = getDecryptor();
    byte[] decodedData = Base64.getDecoder().decode(token);
    byte[] decryptedData = cipher.doFinal(decodedData);
    return new String(decryptedData);
  }

  private SecretKey getKey() throws RuntimeException {
    byte[] keyBytes = secretKey.getBytes();
    return new SecretKeySpec(keyBytes, 0, 16, ALGORITHM);
  }

  private Cipher getEncryptor() throws Exception {
    SecretKey key = getKey();
    Cipher cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(Cipher.ENCRYPT_MODE, key);
    return cipher;
  }

  private Cipher getDecryptor() throws Exception {
    SecretKey key = getKey();
    Cipher cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(Cipher.DECRYPT_MODE, key);
    return cipher;
  }
}
