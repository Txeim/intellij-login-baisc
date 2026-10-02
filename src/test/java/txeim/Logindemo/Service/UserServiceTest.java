package txeim.Logindemo.Service;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import txeim.Logindemo.Entity.UserEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;
    @Test
    public void testfindbyuserName(){
        assertNotNull(userService.findwithusername("fgfe1234"));
    }

    @ParameterizedTest
    @ArgumentsSource(UserArgumentProvider.class)
    public void testSaveNewUser(UserEntity user){
        assertTrue(userService.SaveNewSignup(user));
    }


    @Test
    public void testDeletesignup(){

        assertTrue(userService.Deletesignup("fgfe1234"));
    }


























//    @ParameterizedTest
//    @CsvSource({
//            "1,1,2",
//            "4,4,8",
//            "1,1,6"
//    })
//    public void Test(int x,int y,int expected){
//        assertEquals(expected,x+y);
//    }
}
