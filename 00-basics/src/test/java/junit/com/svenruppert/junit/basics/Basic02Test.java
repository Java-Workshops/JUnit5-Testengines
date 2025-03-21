package junit.com.svenruppert.junit.basics;

import com.svenruppert.dependencies.core.logger.HasLogger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

public class Basic02Test {

  public static class MyExtension
      implements BeforeEachCallback, AfterEachCallback,
      HasLogger {

    @Override
    public void beforeEach(ExtensionContext extensionContext)
        throws Exception { logger().info("beforeEach"); }

    @Override
    public void afterEach(ExtensionContext extensionContext)
        throws Exception { logger().info("afterEach"); }
  }

  @ExtendWith(MyExtension.class)
  public static class ExtendedTestClass {
    @Test
    void test001() { }
  }
}
