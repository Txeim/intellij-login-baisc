package txeim.Logindemo.Controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import txeim.Logindemo.Entity.LoginEntity;
import txeim.Logindemo.Repository.LoginRepo;
import txeim.Logindemo.Service.LoginService;
import java.util.List;
import java.util.Optional;


@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private LoginRepo loginrepo;

    @PostMapping("/PostLogin")
    public ResponseEntity<LoginEntity> SaveLogin(@RequestBody LoginEntity logindata){
        LoginEntity userLogin=loginService.Savelogin(logindata);
        if(userLogin!=null) return new ResponseEntity<>(userLogin, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/GetAll")
    public ResponseEntity<List<LoginEntity>> GetLogin(){
        List<LoginEntity> AllUsers=loginService.getLogin();
        if(AllUsers!=null)  return new ResponseEntity<>(AllUsers,HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("/Login/{id}")
    public ResponseEntity DeleteLogin(@PathVariable ObjectId id){
        Optional<LoginEntity> userById=loginrepo.findById(id);
        if(userById.isPresent()){
            loginService.Deletelogin(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/Data/{id}")
    public ResponseEntity<LoginEntity> PutLoginData (@RequestBody LoginEntity logindata,@PathVariable ObjectId id){
        LoginEntity PutLogindata=loginService.PutloginData(logindata,id);
        if(PutLogindata!=null) return new ResponseEntity<>(logindata,HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
