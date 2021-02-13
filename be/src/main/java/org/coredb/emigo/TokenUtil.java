package org.coredb.emigo;

import java.security.*;
import org.apache.commons.codec.binary.Hex;

public class TokenUtil {
  public static String getToken() {
    byte[] bytes = new byte[32];
    SecureRandom rand = new SecureRandom();
    rand.nextBytes(bytes);
    return Hex.encodeHexString(bytes);
  }
}

