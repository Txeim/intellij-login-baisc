package txeim.Logindemo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import txeim.Logindemo.Entity.UserEntity;
import txeim.Logindemo.Repository.UserRepo;

@Component
public class UserDetailsServiceIMPL implements UserDetailsService{
    @Autowired
    public UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user=userRepo.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("This user is not fountd"));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getUserrole().toArray(new String[0]))
                .build();
    }
}
