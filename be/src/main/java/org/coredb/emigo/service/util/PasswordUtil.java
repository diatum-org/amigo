package org.coredb.emigo.service.util;

import java.io.*;
import java.security.*;
import java.security.spec.*;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.*;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import java.nio.charset.StandardCharsets;

public class PasswordUtil {

  private static final String SEMI_SECRET = "92a41a31a209ce961e279e54592199be";

  public static String token() throws Exception {
    byte[] bytes = new byte[32];
    SecureRandom rand = new SecureRandom();
    rand.nextBytes(bytes);
    return Hex.encodeHexString(bytes);
  }

  public static String salt() throws Exception {
    byte[] bytes = new byte[32];
    SecureRandom rand = new SecureRandom();
    rand.nextBytes(bytes);
    return Hex.encodeHexString(bytes);
  }

  public static String apppw(String password) throws Exception {
    String prefix = "dikota:" + password;
    byte[] pass = prefix.getBytes(StandardCharsets.UTF_8);
    MessageDigest sha = MessageDigest.getInstance("SHA-256");
    return Hex.encodeHexString(sha.digest(pass));
  }

  public static String prepare(String password, String salt) throws Exception {
    byte[] pass = password.getBytes(StandardCharsets.UTF_8);
    MessageDigest sha = MessageDigest.getInstance("SHA-256");
    sha.update(Hex.decodeHex(salt));
    return Hex.encodeHexString(sha.digest(pass));
  }

  public static String authToken(Long timestamp) throws Exception {
    String auth = SEMI_SECRET + ":" + timestamp.toString();
    byte[] hash = auth.getBytes(StandardCharsets.UTF_8);
    MessageDigest sha = MessageDigest.getInstance("SHA-256");
    return Hex.encodeHexString(sha.digest(hash));
  }
}
