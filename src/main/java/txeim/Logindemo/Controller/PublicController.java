package txeim.Logindemo.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import txeim.Logindemo.Entity.UserEntity;
import txeim.Logindemo.Service.UserService;


@RestController
@RequestMapping("/Public")
public class PublicController {

    @Autowired
    public UserService userService;

    @GetMapping("/signup-health")
    public ResponseEntity<String> healthcheck(){
        System.out.println("Health is good");
        return ResponseEntity.ok("Health of the server is good");
    }
    @PostMapping("/PostSignup")
    public ResponseEntity<UserEntity> SaveUser(@RequestBody UserEntity signupdata){
        boolean SaveSignupData=userService.SaveNewSignup(signupdata);
        if(SaveSignupData==true){
            return new ResponseEntity<>(signupdata, HttpStatus.OK);
        }return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
