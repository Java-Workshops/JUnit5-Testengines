package junit.com.svenruppert.junit.basics;

import com.svenruppert.dependencies.core.logger.HasLogger;
import com.svenruppert.junit.example.DataSource;
import com.svenruppert.junit.example.LoginService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

//inject - TestInstancePostProcessor
public class Basic06Test {

  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.FIELD)
  public @interface Inject{}

  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.TYPE)
  @ExtendWith(DemoTestInstancePostProcessor.class)
  public @interface LifeCycle {}


  public static class DemoTestInstancePostProcessor
  implements TestInstancePostProcessor , HasLogger {

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context)
        throws Exception {

      if(testInstance == null) return;

      Class<?> clazz = testInstance.getClass();
      for(Field field : clazz.getDeclaredFields()){
        if(field.getType() == LoginService.class && field.isAnnotationPresent(Inject.class)){
          field.setAccessible(true);
          try{
            Object value = field.get(testInstance);
            if(value == null) {
              var loginService = new LoginService(new DataSource());
              field.set(testInstance, loginService);
              logger().info("LoginService was set..");
            } else{
              logger().info("LoginService was already set..");
            }
          } catch(IllegalAccessException e){
            throw new RuntimeException(e);
          }
          field.setAccessible(false);
        }
      }
    }
  }



  @LifeCycle
  public static class DemoTestClass
      implements HasLogger {

    @Inject
    private LoginService loginService;

    @Test
    void test001() {
      logger().info("Start using the service...");
      var checked = loginService.checkLogin("admin", "admin");
      Assertions.assertTrue(checked);
      logger().info("End using the service.");
    }
  }
}
