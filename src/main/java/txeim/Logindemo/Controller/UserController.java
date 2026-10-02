package txeim.Logindemo.Controller;

import com.sun.net.httpserver.HttpsServer;
import org.bson.types.ObjectId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import txeim.Logindemo.Entity.UserEntity;
import txeim.Logindemo.Repository.UserRepo;
import txeim.Logindemo.Service.JournalEntityService;
import txeim.Logindemo.Service.UserService;


//private static final Logger logger = LoggerFactory.getLogger(JournalEntityService.class);

import java.security.Principal;
import java.util.List;
import java.util.Optional;
@RequestMapping("/User")
@RestController
public class UserController {
    @Autowired
    public UserService userService;
    @Autowired
    public UserRepo userRepo;

//    @GetMapping("/GetAllUser")
//    public ResponseEntity<List<UserEntity>>  getAllUser(){
//        List<UserEntity> SignupUserdata=userService.GetAllSignup();
//        if(SignupUserdata!=null) return new ResponseEntity<>(SignupUserdata,HttpStatus.FOUND);
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @GetMapping
    public ResponseEntity<?> getuserdata(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String user=authentication.getName();
        UserEntity data=userService.findwithusername(user);
        return new ResponseEntity<>(data,HttpStatus.OK);
    }

    @DeleteMapping("DeleteUser")
    public ResponseEntity DeleteUser(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String user=authentication.getName();
        boolean check=userService.Deletesignup(user);
        if(check){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } return new ResponseEntity(HttpStatus.NOT_FOUND);
    }


    @PutMapping("UpdateUserDetails")
    public ResponseEntity<UserEntity> PutUser(@RequestBody UserEntity signupdata){
        UserEntity SignupPut=userService.Putsignup(signupdata);
        return new ResponseEntity<>(SignupPut,HttpStatus.CREATED);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity DeleteUser(@PathVariable ObjectId id){
//        Optional<UserEntity> FindById=userRepo.findById(id);
//        if(FindById.isPresent()){
//            userService.Deletesignup(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }return new ResponseEntity(HttpStatus.NOT_FOUND);
//    }
//    @PutMapping("/{id}")
//    public ResponseEntity<UserEntity> PutUser(@RequestBody UserEntity signupdata, @PathVariable ObjectId id){
//        UserEntity SignupPut=userService.Putsignup(signupdata,id);
//        if(SignupPut!=null) return new ResponseEntity<>(signupdata,HttpStatus.CREATED);
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
}
