package junit.com.svenruppert.junit.basics;

import com.svenruppert.dependencies.core.logger.HasLogger;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Basic01Test
    implements HasLogger {

  static {
    System.out.println("static block B");
  }

  static {
    System.out.println("static block A");
  }


  @BeforeAll
  void beforeAll() {
    //logger().info( "beforeAll" );
    System.out.println("before all");
  }

  @AfterAll
  void afterAll() {
    //logger().info( "afterAll" );
    System.out.println("after all");
  }


  @BeforeEach
  void setUp() {
    logger().info("Before each test");
  }

  @AfterEach
  void tearDown() {
    logger().info("After each test");
  }

  @Test
  void test001() {
    logger().info("test001");
  }

  @Test
  void test002() {
    logger().info("test002");
  }
}
