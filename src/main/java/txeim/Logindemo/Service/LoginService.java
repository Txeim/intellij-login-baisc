package txeim.Logindemo.Service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import txeim.Logindemo.Entity.LoginEntity;
import txeim.Logindemo.Repository.LoginRepo;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private LoginRepo loginrepo;
    @Autowired
    private LoginEntity Loginentity;



    public List<LoginEntity> getLogin(){
        return loginrepo.findAll();
    }

    public LoginEntity Savelogin(LoginEntity loginEntity){
        return loginrepo.save(loginEntity);
    }

    public void Deletelogin(ObjectId id){
        loginrepo.deleteById(id);
    }

    public LoginEntity PutloginData(LoginEntity loginEntity,ObjectId id){
        Optional<LoginEntity> user=loginrepo.findById(id);
        if(user.isPresent()) {
            LoginEntity getNewUser = user.get();
            getNewUser.setName(loginEntity.getName());
            getNewUser.setPassword(loginEntity.getPassword());
            loginrepo.save(getNewUser);
            return getNewUser;

        }
        return null;
    }
}

