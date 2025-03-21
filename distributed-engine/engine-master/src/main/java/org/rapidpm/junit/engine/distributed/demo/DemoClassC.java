package org.rapidpm.junit.engine.distributed.demo;

import com.svenruppert.dependencies.core.logger.HasLogger;

public class DemoClassC implements HasLogger {


  public DemoClassC() {
    logger().info(this.getClass().getSimpleName() + " - " + this.getClass().getClassLoader().getClass().getSimpleName());
  }
}
