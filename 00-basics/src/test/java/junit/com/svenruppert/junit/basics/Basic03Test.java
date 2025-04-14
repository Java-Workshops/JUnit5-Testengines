package junit.com.svenruppert.junit.basics;

import com.svenruppert.dependencies.core.logger.HasLogger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.Extensions;

public class Basic03Test {

  public static class MyExtensionA
      implements BeforeEachCallback , HasLogger {
    @Override
    public void beforeEach(ExtensionContext extensionContext)
        throws Exception { logger().info("beforeEach - A");  }
  }

  public static class MyExtensionB
      implements BeforeEachCallback , HasLogger {
    @Override
    public void beforeEach(ExtensionContext extensionContext)
        throws Exception { logger().info("beforeEach - B");  }
  }

  @ExtendWith(Basic03Test.MyExtensionB.class)
  @ExtendWith(Basic03Test.MyExtensionA.class)
  public static class ExtendedTestClassA {
    @Test
    void test001() { }
  }

  @ExtendWith(Basic03Test.MyExtensionA.class)
  @ExtendWith(Basic03Test.MyExtensionB.class)
  public static class ExtendedTestClassB {
    @Test
    void test001() { }
  }


  @Extensions({
   @ExtendWith({Basic03Test.MyExtensionB.class}),
   @ExtendWith({Basic03Test.MyExtensionA.class})
  })
  public @interface BothExtensions { }

  @BothExtensions
  public static class ExtendedTestClassC {
    @Test
    void test001() { }
  }



}


