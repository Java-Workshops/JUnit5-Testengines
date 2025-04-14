package junit.com.svenruppert.junit.basics;

import com.svenruppert.dependencies.core.logger.HasLogger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;

//Context Store
public class Basic04Test {

  public static class MyExtension
      implements
      BeforeEachCallback,
      AfterEachCallback,
      HasLogger {

    public static final String KEY = "KEY";
    public static final Namespace MY_EXTENSION_NAMESPACE
        = Namespace.create(MyExtension.class);

    private ExtensionContext.Store store(ExtensionContext ctx) {
      return ctx.getStore(MY_EXTENSION_NAMESPACE);
    }

    @Override
    public void beforeEach(ExtensionContext ctx)
        throws Exception {
      final ExtensionContext.Store store = store(ctx);
      logger().info("beforeEach");
      String value = "something";
      store.put(KEY, value);
      logger().info("stored inside the context Store value={}", value);
    }

    @Override
    public void afterEach(ExtensionContext ctx)
        throws Exception {
      final ExtensionContext.Store store = store(ctx);
      logger().info("afterEach");
      final String value = store.get(KEY, String.class);
      logger().info("value is {}", value);
    }

    @Test
    @ExtendWith(MyExtension.class)
    void test001() {
      //possible to annotate only a method
    }
  }


}
