package txeim.Logindemo.Service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import txeim.Logindemo.Entity.SignupEntity;
import txeim.Logindemo.Repository.SignupRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SignupService {
    @Autowired
    SignupRepo signuprepo;
    @Autowired
    SignupEntity signupEntity;

    public SignupEntity SaveSignup(SignupEntity signupentity){
        signupentity.setLocaldatetime(LocalDateTime.now());
        return signuprepo.save(signupentity);
    }

    public List<SignupEntity> GetAllSignup(){
        return signuprepo.findAll();
    }

    public void Deletesignup(ObjectId id){
        signuprepo.deleteById(id);
    }

    public SignupEntity Putsignup(SignupEntity signupdata,ObjectId id){
        Optional<SignupEntity> user=signuprepo.findById(id);
        if(user.isPresent()){
            SignupEntity newuser=user.get();
            newuser.setLocaldatetime(LocalDateTime.now());
            newuser.setFirstName(signupdata.getFirstName());
            newuser.setPassword(signupdata.getFirstName());
            return newuser;
        }
        return null;

    }
}
