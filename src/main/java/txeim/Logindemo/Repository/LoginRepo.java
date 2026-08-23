package txeim.Logindemo.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import txeim.Logindemo.Entity.LoginEntity;

@Repository
public interface LoginRepo extends MongoRepository<LoginEntity, ObjectId> {
}
