package junit.com.svenruppert.junit.basics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;


public class Basic05Test {

  @Test
  @ExtendWith(Basic05Test.DemoParameterResolver.class)
  void test001(Demo demo) {
    Assertions.assertEquals("Hello World", demo.value());
  }

  public record Demo(Integer id, String value) {
  }

  public static class DemoParameterResolver
      implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext)
        throws ParameterResolutionException {
      final Class<?> type = parameterContext.getParameter()
          .getType();
      return Demo.class.isAssignableFrom(type);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext)
        throws ParameterResolutionException {
      return new Demo(1, "Hello World");
    }
  }

}
