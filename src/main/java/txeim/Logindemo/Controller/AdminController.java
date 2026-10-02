package txeim.Logindemo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import txeim.Logindemo.Entity.UserEntity;
import txeim.Logindemo.Service.UserService;

import java.util.List;

@Controller
@RequestMapping("/Admin")
public class AdminController {
    @Autowired
    UserEntity userEntity;
    @Autowired
    private UserService userService;
    @GetMapping("Get-allUsers")
    public ResponseEntity<?> getAllUsers(){
        List<UserEntity> data=userService.GetAll();
        if(data!=null && !data.isEmpty()){
            return new ResponseEntity<>(data, HttpStatus.OK);
        }return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @PutMapping("Add-Admin")
    public ResponseEntity<?> AddAdmin(@RequestBody UserEntity userEntity){
        UserEntity useradd=userService.save(userEntity);
        if(useradd!=null) return new ResponseEntity<>(useradd,HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
}
