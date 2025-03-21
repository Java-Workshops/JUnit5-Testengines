package junit.com.svenruppert.junit.basics;

import com.svenruppert.dependencies.core.logger.HasLogger;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Basic01Test implements HasLogger {

  @BeforeEach
  void setUp() {
    logger().info("beforeEach");
  }

  @AfterEach
  void tearDown() {
    logger().info("afterEach");
  }


  @BeforeAll
  void beforeAll() {
    logger().info("beforeAll");
  }

  @AfterAll
  void afterAll() {
    logger().info("afterAll");
  }

  @Test
  void test001() {
  }
}
