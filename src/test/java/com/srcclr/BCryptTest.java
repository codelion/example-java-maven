/*
 * © Copyright 2018 -  SourceClear Inc
 */
package com.srcclr;

import static junit.framework.TestCase.assertFalse;

import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;

public class BCryptTest {

  /**
   * Test for correct hashing of non-US-ASCII passwords.
   */
  @Test
  public void testInternationalChars() {
      System.out.print("BCrypt.hashpw w/ international chars: ");
  
      // Non-US-ASCII passwords for testing
      String pw1 = "ππππππππ";
      String pw2 = "????????";
  
      // Hashing the first password
      String h1 = BCrypt.hashpw(pw1, BCrypt.gensalt());
      assertFalse(BCrypt.checkpw(pw2, h1));
      System.out.print(".");
  
      // Hashing the second password
      String h2 = BCrypt.hashpw(pw2, BCrypt.gensalt());
      assertFalse(BCrypt.checkpw(pw1, h2));
      System.out.print(".");
      System.out.println("");
  }
}
