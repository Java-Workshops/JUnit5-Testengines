package junit.com.svenruppert.junit.basics;

import com.svenruppert.dependencies.core.logger.HasLogger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Basic03Test
    implements HasLogger {

  @Test
  void test001() {
    logger().info("test001");
  }



  @ExtendWith(MyExtensionB.class)
  @ExtendWith(MyExtensionA.class)
  public static class ExtendedClassA
      implements HasLogger {
    @Test
    void test001() {
      logger().info("ExtendedClassA - test001");
    }
  }

  @ExtendWith(MyExtensionA.class)
  @ExtendWith(MyExtensionB.class)
  public static class ExtendedClassB
      implements HasLogger {
    @Test
    void test001() {
      logger().info("ExtendedClassB - test001");
    }
  }


  @Extensions({
      @ExtendWith(MyExtensionA.class),
      @ExtendWith(MyExtensionB.class)
  })
  @Retention(RetentionPolicy.RUNTIME)
  public @interface BothExtensions { }

  @BothExtensions
  public static class Demo
      implements HasLogger {
    @Test
    void test001() {
      logger().info("Demo - test001");
    }
  }

  public static class MyExtensionA
      implements BeforeEachCallback, AfterEachCallback, HasLogger {

    @Override
    public void afterEach(ExtensionContext context)
        throws Exception {
      logger().info("After Each - A");
    }

    @Override
    public void beforeEach(ExtensionContext context)
        throws Exception {
      logger().info("Before Each - A");
    }
  }

  public static class MyExtensionB
      implements BeforeEachCallback, AfterEachCallback, HasLogger {

    @Override
    public void afterEach(ExtensionContext context)
        throws Exception {
      logger().info("After Each - B");
    }

    @Override
    public void beforeEach(ExtensionContext context)
        throws Exception {
      logger().info("Before Each - B");
    }
  }
}


