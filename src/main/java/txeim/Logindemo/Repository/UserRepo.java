package txeim.Logindemo.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import txeim.Logindemo.Entity.JournalEntity;
import txeim.Logindemo.Entity.UserEntity;

import java.util.Optional;

public interface
UserRepo extends MongoRepository<UserEntity, ObjectId> {
    Optional<UserEntity> findByUsername(String username);
}
