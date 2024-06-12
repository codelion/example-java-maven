package com.srcclr;

import org.apache.commons.fileupload.MultipartStream;
import org.apache.xml.security.signature.XMLSignatureInput;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.util.UriUtils;

import java.io.ByteArrayInputStream;

public class Main {

  /**
   * Main method that takes a String argument, hashes it using BCrypt, checks the hashed value,
   * filters XML signatures, and encodes a fragment using UriUtils.
   * 
   * @param args Command line arguments
   * @throws Exception if there are any errors during the execution
   */
  public static void main(String[] args) throws Exception {
      String candidate = args[0];
      String hashed = BCrypt.hashpw(candidate, BCrypt.gensalt(12));
  
      BCrypt.checkpw(candidate, hashed);
  
      filterXMLSignature();
  
      // Update Advisor: changed in the upgrade from Spring Web 3.1.1.RELEASE to 3.2.15.RELEASE
      UriUtils.encodeFragment("", "");
  }

  /**
   * Filters the XML signature.
   */
  private static void filterXMLSignature() {
      byte[] bytes = new byte[256];
  
      new MultipartStream(new ByteArrayInputStream(bytes), bytes);
  
      new XMLSignatureInput(bytes).addNodeFilter(null);
  }
}
