package junit.com.svenruppert.junit.example;

import com.svenruppert.dependencies.core.logger.HasLogger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;
import com.svenruppert.junit.example.DataSource;
import com.svenruppert.junit.example.LoginService;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

class LoginServiceTestResult {

  @Test
  void test01(@Service LoginService service) {
    boolean condition = service.checkLogin("admin", "admin");
    Assertions.assertTrue(condition);
  }

  @Target({ElementType.METHOD, ElementType.PARAMETER})
  @Retention(RetentionPolicy.RUNTIME)
  @ExtendWith(LoginServiceParameterResolver.class)
  public @interface Service {
  }


  public static class LoginServiceParameterResolver
      implements ParameterResolver, HasLogger {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext)
        throws ParameterResolutionException {
      return parameterContext.getParameter()
          .getType() == LoginService.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext)
        throws ParameterResolutionException {
      return new LoginService(new DataSource());
    }
  }

}
