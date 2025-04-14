package junit.com.svenruppert.junit.basics;

import com.svenruppert.dependencies.core.logger.HasLogger;
import com.svenruppert.junit.example.DataSource;
import com.svenruppert.junit.example.LoginService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

import static org.junit.Assert.assertTrue;

public class Basic06Test {

  // Simuliere javax.inject.Inject (ohne externe Dependency)
  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.FIELD)
  public @interface Inject { }


  // Testklasse
  @LifeCycle
  public static class DemoTestKlasse
      implements HasLogger {
    @Inject
    private LoginService loginService; // soll gesetzt werden, wenn noch null

    @Test
    void test001() {
      logger().info("beginne den Service zu verwenden..");
      var adminLoggedIn = loginService.checkLogin("admin", "admin");
      assertTrue(adminLoggedIn);
      logger().info("bende den Service zu verwenden..");
    }
  }

  @Target(ElementType.TYPE)
  @Retention(RetentionPolicy.RUNTIME)
  @ExtendWith(DemoTestInstancePostProcessor.class)
  public @interface LifeCycle {}


  public static class DemoTestInstancePostProcessor
      implements TestInstancePostProcessor, HasLogger {
    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context)
        throws Exception {

      if (testInstance == null) return;

      Class<?> clazz = testInstance.getClass();

      for (Field field : clazz.getDeclaredFields()) {
        if (field.getType() == LoginService.class && field.isAnnotationPresent(Inject.class)) {
          field.setAccessible(true);
          try {
            Object value = field.get(testInstance);
            if (value == null) {
              field.set(testInstance, new LoginService(new DataSource()));
              logger().info("LoginService wurde injiziert.");
            } else {
              logger().info("LoginService ist bereits gesetzt.");
            }
          } catch (IllegalAccessException e) {
            throw new RuntimeException("Zugriff auf das Feld fehlgeschlagen: " + field.getName(), e);
          }
        }
      }
    }
  }

}
