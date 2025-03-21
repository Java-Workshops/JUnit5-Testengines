package junit.com.svenruppert.junit.engine.micro;

import jakarta.inject.Inject;
import junit.com.svenruppert.junit.engine.micro.mocks.MyMockedService;
import org.junit.jupiter.api.Assertions;
import com.svenruppert.junit.engine.micro.MicroTest;
import com.svenruppert.junit.engine.micro.MicroTestClass;

//import javax.inject.Inject;


@MicroTestClass(useCDI = false)
public class SecondMicroTest {

  @Inject
  private MyMockedService service;

  @MicroTest
  void test001_A() {
    Assertions.assertNull(service);
  }
}
