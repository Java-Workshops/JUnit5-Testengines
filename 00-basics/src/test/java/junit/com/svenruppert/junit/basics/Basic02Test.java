package junit.com.svenruppert.junit.basics;

import com.svenruppert.dependencies.core.logger.HasLogger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

public class Basic02Test
    implements HasLogger {

  @ExtendWith(MyExtensionA.class)
  public static class ExtendedTestClass
      implements HasLogger {
    @Test
    void test001() {
      logger().info("test001");
    }
  }

  public static class MyExtensionA
      implements BeforeEachCallback, AfterEachCallback,
      HasLogger {

    @Override
    public void afterEach(ExtensionContext context)
        throws Exception {
      logger().info("afterEach");
    }

    @Override
    public void beforeEach(ExtensionContext context)
        throws Exception {
      logger().info("beforeEach");
    }
  }

}
