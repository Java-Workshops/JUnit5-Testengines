package org.rapidpm.junit.engine.distributed.demo;

import com.svenruppert.dependencies.core.logger.HasLogger;

public class DemoClassB implements HasLogger {

  public DemoClassB() {
    logger().info(this.getClass().getSimpleName() + " - " + this.getClass().getClassLoader().getClass().getSimpleName());
    logger().info(this.getClass().getSimpleName() + " - " + "Hello Mr.");
  }
}
