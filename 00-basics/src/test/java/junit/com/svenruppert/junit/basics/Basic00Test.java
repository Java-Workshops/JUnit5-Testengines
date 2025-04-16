package junit.com.svenruppert.junit.basics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

//Test and Assertions
class Basic00Test {

  @Test
  void test001() {

    Assertions.assertTrue(true);
    Assertions.assertThrows(
        MyException.class,
        () -> {
          throw new MyException();
        });

    Assertions.assertThrowsExactly(MyException.class, () -> {
      throw new MyException();
    });

    Assertions.assertInstanceOf(Map.class, new HashMap<>());


  }

  public static class MyException
      extends RuntimeException { }
}
