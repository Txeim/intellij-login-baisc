package txeim.Logindemo.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import txeim.Logindemo.Entity.UserEntity;
import txeim.Logindemo.Repository.UserRepo;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceIMPLTest {
    @Mock
    public UserRepo userRepo;
    @InjectMocks
    public UserDetailsServiceIMPL userDetailsServiceIMPL;
    @Test
    void loadUserByUsernameTest(){
        when(userRepo.findByUsername("ram")).thenReturn(Optional.of(UserEntity.builder().username("ram").password("sgrfggf").userrole(new ArrayList<>()).build()));
        UserDetails user=userDetailsServiceIMPL.loadUserByUsername("ram");
        Assertions.assertNotNull(user);
    }
    @Test
    void donotloadUserByUsernameTest(){
        when(userRepo.findByUsername("rohan")).thenReturn(Optional.empty());
        Assertions.assertThrows(
                UsernameNotFoundException.class,()->userDetailsServiceIMPL.loadUserByUsername("rohan")
        );
    }
}

