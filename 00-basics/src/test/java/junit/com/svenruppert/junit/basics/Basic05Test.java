package junit.com.svenruppert.junit.basics;

import com.svenruppert.dependencies.core.logger.HasLogger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//ParameterResolver
public class Basic05Test {

  @Test
  @ExtendWith(DemoParameterResolver.class)
  void test001(Demo demo) {
    Assertions.assertEquals("Hello World", demo.value());
  }

  @Test
  void test002(@DemoInjector Demo demo) {
    Assertions.assertEquals("Hello World", demo.value());
  }




  public record Demo(Integer id, String value){}

  @ExtendWith(DemoParameterResolver.class)
  @Retention(RetentionPolicy.RUNTIME)
  public @interface DemoInjector{}


  public static class DemoParameterResolver
  implements ParameterResolver , HasLogger {

    @Override
    public boolean supportsParameter(
        ParameterContext parameterContext,
        ExtensionContext extensionContext)
        throws ParameterResolutionException {
      Class<?> type = parameterContext.getParameter().getType();
      return Demo.class.isAssignableFrom(type);
    }

    @Override
    public Object resolveParameter(
        ParameterContext parameterContext,
        ExtensionContext extensionContext)
        throws ParameterResolutionException {
      return new Demo(1, "Hello World");
    }
  }

}
