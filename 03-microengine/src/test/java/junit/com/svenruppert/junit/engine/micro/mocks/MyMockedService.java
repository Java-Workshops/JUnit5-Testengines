package junit.com.svenruppert.junit.engine.micro.mocks;

import com.svenruppert.dependencies.core.logger.HasLogger;

import jakarta.enterprise.context.Dependent;


@Dependent
public class MyMockedService implements HasLogger {

  public MyMockedService() {
    logger().info("{} will be created now", this.getClass().getSimpleName());
  }

  public String doSomeWork(){
    return "someWork";
  }
}
