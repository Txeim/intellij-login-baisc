package txeim.Logindemo.Service;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;
import txeim.Logindemo.Entity.UserEntity;
import txeim.Logindemo.Repository.UserRepo;

import static org.mockito.Mockito.when;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceMockitoTest {
    @Mock
    public UserRepo userRepo;
    @InjectMocks
    public UserService userService;
    @Test
    public void DeleteSignupTest(){
        UserEntity user=UserEntity.builder()
                .username("ram")
                .id(new ObjectId())
                .build();
        when(userRepo.findByUsername("ram")).thenReturn(Optional.of(user));

    boolean check=userService.Deletesignup("ram");
    Assertions.assertTrue(check);

    }
    @Test
    public void deleteSignup_whenUserNotExists_returnsFalse(){
        when(userRepo.findByUsername("ram")).thenReturn(Optional.empty());
        boolean check=userService.Deletesignup("ram");
        Assertions.assertFalse(check);
    }
}
