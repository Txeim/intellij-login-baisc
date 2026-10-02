package txeim.Logindemo.Service;


import org.bson.types.ObjectId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import txeim.Logindemo.Entity.JournalEntity;
import txeim.Logindemo.Entity.UserEntity;
import txeim.Logindemo.Repository.JournalRepo;
import txeim.Logindemo.Repository.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntityService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    JournalRepo journalRepo;


    public List<JournalEntity> JournalEntityGet(String username){
        Optional<UserEntity> getJournalEnteries=userRepo.findByUsername(username);
        if(getJournalEnteries!=null){
            UserEntity data=getJournalEnteries.get();
            List<JournalEntity> allJournalEnteries=data.getJournalData();
            return allJournalEnteries;
        }
        return null;
    }

    public JournalEntity JournalEntitySave(JournalEntity journalEntity){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        Optional<UserEntity> getfromname=userRepo.findByUsername(username);
        if(getfromname.isPresent()){
            UserEntity user=getfromname.get();
            JournalEntity savedJournelEntity=journalRepo.save(journalEntity);
            user.getJournalData().add(savedJournelEntity);
            userRepo.save(user);
            return savedJournelEntity;
        }
        return null;

    }
    public JournalEntity GetByIdJournalEntity(String username, ObjectId id){
        Optional<UserEntity> data=userRepo.findByUsername(username);
        if(data.isPresent()){
            UserEntity getAlldata=data.get();
            for(JournalEntity je:getAlldata.getJournalData()){
                if(je.getId().equals(id)){
                    return je;
                }

            }
        }
        return null;
    }
    @Transactional
    public boolean deleteJournalEntery(String username,ObjectId id){
        Optional<UserEntity> GetAllData=userRepo.findByUsername((username));
        UserEntity user=GetAllData.get();
        boolean delete=user.getJournalData().removeIf(x->x.getId().equals(id));
        if(delete){
            userRepo.save(user);
            journalRepo.deleteById(id);
            return true;
        }
        return false;
    }
    public JournalEntity editJournalEntry(String username,ObjectId id,JournalEntity journalentity){
        UserEntity user =userRepo.findByUsername(username).get();
        for(JournalEntity je:user.getJournalData()){
            if(je.getId().equals(id)) {
                je.setStory(journalentity.getStory());
                je.setTitle(journalentity.getTitle());
                journalRepo.save(je);
                return je;
            }
        }

        return null;
    }
}

