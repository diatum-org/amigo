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
import java.nio.charset.StandardCharsets;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.ws.rs.NotAcceptableException;

import org.coredb.emigo.model.Amigo;
import org.coredb.emigo.model.AmigoMessage;

public class EmigoUtil {

  public static Amigo getObject(AmigoMessage msg) throws IllegalArgumentException, Exception {

    if(msg.getKey() == null || msg.getKeyType() == null || msg.getData() == null || msg.getSignature() == null) {
      throw new IllegalArgumentException("incomplete emigo message");
    }

    // load the public key object
    PublicKey key = readPubkey(msg.getKey(), msg.getKeyType());

    //validate signature
    if(!verify(msg.getData(), msg.getSignature(), key)) {
      throw new IllegalArgumentException("emigo signature error");
    }

    //populate data entry
    Amigo emigo = decode(msg.getData());

    //validate key and id
    if(!emigo.getAmigoId().equals(id(key))) {
      throw new IllegalArgumentException("emigo key id mismatch");
    }

    return emigo;
  }

  private static Amigo decode(String base) throws IllegalArgumentException {
    try {
      byte[] bytes = Base64.getDecoder().decode(base);
      String serial = new String(bytes);
      ObjectMapper mapper = new ObjectMapper();
      return mapper.readValue(serial, Amigo.class);
    }
    catch(Exception e) {
      throw new IllegalArgumentException("invalid emigo message");
    }
  }

  public static PublicKey readPubkey(String key, String type) throws Exception {
    byte[] bytes = Hex.decodeHex(key);
    KeyFactory publicKeyFactory = KeyFactory.getInstance(type);
    X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(bytes);
    return publicKeyFactory.generatePublic(publicKeySpec);
  }

  public static Boolean verify(String data, String signature, PublicKey publicKey) throws Exception {
    Signature publicSignature = Signature.getInstance("SHA256withRSA");
    publicSignature.initVerify(publicKey);
    publicSignature.update(data.getBytes(StandardCharsets.UTF_8));
    byte[] signatureBytes = Hex.decodeHex(signature);
    return publicSignature.verify(signatureBytes);
  }

  public static String id(PublicKey publicKey) throws Exception {
    MessageDigest sha = MessageDigest.getInstance("SHA-256");
    return Hex.encodeHexString(sha.digest(publicKey.getEncoded()));
  }

}

