package junit.com.svenruppert.junit.engine.nano;

import com.svenruppert.junit.engine.nano.NanoTest;
import com.svenruppert.junit.engine.nano.NanoTestClass;

@NanoTestClass
public class FirstNanoTest {
  @NanoTest
  void test001_A() { }

  @NanoTest
  void test001_B() { }

  //@NanoTest
  void test002() { }
}
