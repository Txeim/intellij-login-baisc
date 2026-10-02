package txeim.Logindemo.Service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import txeim.Logindemo.Entity.UserEntity;
import txeim.Logindemo.Repository.UserRepo;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserEntity userEntity;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public UserEntity SaveSignup (UserEntity userEntity){
        return userRepo.save(userEntity);
    }

    public boolean SaveNewSignup(UserEntity userEntity){
        try{
        userEntity.setLocaldatetime(LocalDateTime.now());
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setUserrole(new ArrayList<>(List.of("User")));
        userRepo.save(userEntity);
        return true;

        } catch (Exception e) {
            log.info("there is no info there ");
            log.error("this is not a good code for:{}",userEntity.getUsername() );
            log.warn("this is the warn");
            log.trace("this is the trace");
            log.debug("this is the debug");
            return false;
        }
    }

    public List<UserEntity> GetAll(){
        return userRepo.findAll();
    }

    public boolean Deletesignup(String user ){
        Optional<UserEntity> username=userRepo.findByUsername(user);
        if(username.isPresent()){
            ObjectId id=username.get().getId();
            userRepo.deleteById(id);
            return true;
        }return false;
    }


    public UserEntity Putsignup(UserEntity signupdata){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        Optional<UserEntity> user=userRepo.findByUsername(username);
        UserEntity newuser=user.get();
        newuser.setLocaldatetime(LocalDateTime.now());
        newuser.setUsername(signupdata.getUsername());
        newuser.setPassword(passwordEncoder.encode(signupdata.getPassword()));
        userRepo.save(newuser);
        return newuser;
    }
    public UserEntity save(UserEntity userEntity){
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setLocaldatetime(LocalDateTime.now());
        userEntity.setUserrole(Arrays.asList("USER","ADMIN"));
        userRepo.save(userEntity);
        return userEntity;
    }

    public UserEntity findwithusername(String username){
        Optional<UserEntity> user= userRepo.findByUsername(username);
        if(user.isPresent()){
            return user.get();
        }
        return null;
    }
//

//    public void Deletesignup(ObjectId id){
//        userRepo.deleteById(id);
//    }

//    public UserEntity Putsignup(UserEntity signupdata, String id){
//        Optional<UserEntity> user=userRepo.findById(id);
//        if(user.isPresent()){
//            UserEntity newuser=user.get();
//            newuser.setLocaldatetime(LocalDateTime.now());
//            newuser.setUsername(signupdata.getUsername());
//            newuser.setPassword(passwordEncoder.encode(signupdata.getPassword()));
//            userRepo.save(newuser);
//            return newuser;
//        }
//        return null;
//    }

    public void GetJournalEnteries(){

    }
}
