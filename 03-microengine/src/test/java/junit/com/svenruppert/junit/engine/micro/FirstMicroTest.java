package junit.com.svenruppert.junit.engine.micro;

import jakarta.inject.Inject;
import junit.com.svenruppert.junit.engine.micro.mocks.MyMockedService;
import org.junit.jupiter.api.Assertions;
import com.svenruppert.junit.engine.micro.MicroTest;
import com.svenruppert.junit.engine.micro.MicroTestClass;


@MicroTestClass(useCDI = true)
public class FirstMicroTest {

  @Inject
  private MyMockedService service;

  @MicroTest
  void test001_A() {
    Assertions.assertEquals("someWork", service.doSomeWork());
  }
}
