package junit.com.svenruppert.junit.basics;

import com.svenruppert.dependencies.core.logger.HasLogger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

//Context Store
public class Basic04Test {


  @Test
  @ExtendWith(ExtensionA.class)
  void test001() {

  }

  public static class ExtensionA
  implements
      BeforeEachCallback,
      AfterEachCallback,
      HasLogger {

    public static final ExtensionContext.Namespace MY_EXTENSION_NAMESPACE
        = ExtensionContext.Namespace.create(ExtensionA.class);
    private static final String KEY = "KEY";

    private ExtensionContext.Store store(ExtensionContext ctx){
      return ctx.getStore(MY_EXTENSION_NAMESPACE);
    }

    @Override
    public void afterEach(ExtensionContext context)
        throws Exception {
      logger().info("After Each");
      var store = store(context);
      var value = store.get(KEY, String.class);
      logger().info("Key: " + KEY + " value: {}", value);
      store.remove(KEY);
    }

    @Override
    public void beforeEach(ExtensionContext context)
        throws Exception {
      logger().info("Before Each");
      String value = "something";
      ExtensionContext.Store store = store(context);
      store.put(KEY, value);
      logger().info("stored inside the context Store value={}", value);
    }
  }



}
