package txeim.Logindemo.Controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import txeim.Logindemo.Entity.SignupEntity;
import txeim.Logindemo.Repository.SignupRepo;
import txeim.Logindemo.Service.SignupService;
import java.util.List;
import java.util.Optional;

@RestController
public class SignUpController {
    @Autowired
    public SignupService signupService;
    @Autowired
    public SignupRepo signupRepo;

    @PostMapping("/PostSignup")
    public ResponseEntity<SignupEntity> Savesignup(@RequestBody SignupEntity signupdata){
        SignupEntity SaveSignupData=signupService.SaveSignup(signupdata);
        if(SaveSignupData!=null){
                return new ResponseEntity<>(SaveSignupData, HttpStatus.OK);
        }return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/GetAllSignup")
    public ResponseEntity<List<SignupEntity>> getAllSignup(){
        List<SignupEntity> SignupUserdata=signupService.GetAllSignup();
        if(SignupUserdata!=null) return new ResponseEntity<>(SignupUserdata,HttpStatus.FOUND);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/Signup/{id}")
    public ResponseEntity DeleteSignup(@PathVariable ObjectId id){
        Optional<SignupEntity> FindById=signupRepo.findById(id);
        if(FindById.isPresent()){
            signupService.Deletesignup(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/Signup/{id}")
    public ResponseEntity<SignupEntity> PutSignup(@RequestBody SignupEntity signupdata,@PathVariable ObjectId id){
        SignupEntity SignupPut=signupService.Putsignup(signupdata,id);
        if(SignupPut!=null) return new ResponseEntity<>(signupdata,HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
