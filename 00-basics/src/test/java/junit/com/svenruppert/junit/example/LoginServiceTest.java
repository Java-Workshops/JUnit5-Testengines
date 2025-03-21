package junit.com.svenruppert.junit.example;

import org.junit.jupiter.api.Assertions;
import com.svenruppert.junit.example.LoginService;

public class LoginServiceTest {


  // make it happen :-)
  //@Test
  void test01(LoginService service){
    Assertions.assertTrue(service.checkLogin("admin", "admin"));
  }

}
