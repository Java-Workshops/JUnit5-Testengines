package junit.com.svenruppert.junit.engine.micro.mocks;

import com.svenruppert.dependencies.core.logger.HasLogger;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.Instant;

@ApplicationScoped
public class MyStaticMockedService
    implements HasLogger {

  private final Instant created = Instant.now();

  public MyStaticMockedService() {
    logger().info("{}.. will be created now ..", this.getClass().getSimpleName());


  }

  public Instant getCreated() {
    return created;
  }
}
