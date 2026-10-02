package txeim.Logindemo.Controller;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import txeim.Logindemo.Entity.JournalEntity;
import txeim.Logindemo.Service.JournalEntityService;

import java.util.List;

@RestController
@RequestMapping("/Entity")
public class JournalEntityController {
    @Autowired
    JournalEntityService journalEntityService;

    @PostMapping("/SaveEntry")
    public ResponseEntity<?> journalEntitySave(@RequestBody JournalEntity journalEntity) {

        JournalEntity result = journalEntityService.JournalEntitySave(journalEntity);
        if (result != null) return new ResponseEntity<>(result, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("GetJournal")
    public ResponseEntity<?> GetAllJournalEnteries(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        List<JournalEntity> getJournalEnteries=journalEntityService.JournalEntityGet(username);
        if(getJournalEnteries!=null){
            return new ResponseEntity<>(getJournalEnteries,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> GetJournalEnteriesById( @PathVariable ObjectId id){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        JournalEntity getJournalEnteryById=journalEntityService.GetByIdJournalEntity(username,id);
        if(getJournalEnteryById!=null){
            return new ResponseEntity<>(getJournalEnteryById,HttpStatus.OK);
        }
        System.out.println(id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> DeleteJournalEntery(@PathVariable ObjectId id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        boolean DeleteOrNot=journalEntityService.deleteJournalEntery(username,id);
        if(DeleteOrNot){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> EditJournalEntry(
            @PathVariable ObjectId id,
            @RequestBody JournalEntity journalEntity){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        JournalEntity updateddata=journalEntityService.editJournalEntry(username,id,journalEntity);
        if(updateddata!=null){
            return new ResponseEntity<>(updateddata,HttpStatus.CREATED);
        }return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }


}
