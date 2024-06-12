package com.srcclr;

import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

public class MainTest {

  /**
   * Method to print "1" to the console.
   */
  void m1() {
      System.out.println("1");
  }

  /**
   * Prints the number 2 to the console.
   */
  void m2() {
    System.out.println("2");
  }

  /**
   * Calls the method m1() in a loop 10 times with a delay of 1000 milliseconds between each call.
   */
  void callM1() {
    for (int i=0; i<10; i++) {
      m1();
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        System.err.println("oops1");
      }
    }
  }

  /**
   * Calls the method m2() in a loop 10 times with a 1-second delay between each call.
   */
  void callM2() {
      for (int i=0; i<10; i++) {
        m2();
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          System.err.println("oops2");
        }
      }
  }

  /**
   * Executes two methods callM1() and callM2() concurrently using separate threads t1 and t2.
   * Waits for both threads to finish before returning.
   * @throws InterruptedException if any thread interrupts the current thread before or while the current thread is waiting for t1 or t2 to finish.
   */
  void concurrency() throws InterruptedException {
      Thread t1 = new Thread(new Runnable() {
        @Override
        public void run() {
          callM1();
        }
      });
      t1.setName("t1");
      t1.start();
  
      Thread t2 = new Thread(new Runnable() {
        @Override
        public void run() {
          callM2();
        }
  
      });
      t2.setName("t2");
      t2.start();
  
      t1.join();
      t2.join();
  }

  /**
   * This method calls the methods m1() and m2(), and prints "lol" to the standard output.
   */
  private void stdlib() {
      System.out.println("lol");
      m1();
      m2();
  }

  /**
   * Makes an HTTP connection to a specified URL and prints the request method.
   *
   * @throws Exception if an error occurs during the HTTP connection
   */
  private void http() throws Exception {
      HttpURLConnection urlConnection = (HttpURLConnection) new URL("http://www.google.com").openConnection();
      System.out.println(urlConnection.getRequestMethod());
  }

  static class Animal {
    /**
     * Prints a question mark to the console.
     */
    public void call() {
      System.out.println("?");
    }
  }

  /**
   * Uses reflection to create an instance of the Animal class and invokes all its methods.
   *
   * @throws Exception if any error occurs during the reflection process.
   */
  private void reflection() throws Exception {
    Class<?> aClass = Class.forName(Animal.class.getName());
    Object t = aClass.newInstance();
  
    Method[] allMethods = aClass.getDeclaredMethods();
    for (Method m : allMethods) {
      m.invoke(t);
    }
  }

  static class Cat extends Animal {
    /**
     * Method to make a "meow" sound.
     */
    public void call() {
      System.out.println("meow");
    }
  }

  /**
   * Method to demonstrate dynamic dispatch in Java.
   * Creates a new Cat object and calls the 'call' method on an Animal reference.
   */
  private void dynamicDispatch() {
      Animal a = new Cat();
      a.call();
  }

  /**
   * This method is a test method that invokes various functions including stdlib, http, concurrency, reflection, and dynamicDispatch.
   * It also mentions that for a test using a vulnerable method, one should refer to BCryptTest.
   * 
   * @throws Exception if an error occurs during the test.
   */
  @Test
  public void test() throws Exception {
    stdlib();
    http();
    concurrency();
    reflection();
    dynamicDispatch();
    // For a test that uses a vulnerable method, see BCryptTest
  }
}
