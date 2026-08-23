package txeim.Logindemo.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import txeim.Logindemo.Entity.SignupEntity;

public interface SignupRepo  extends MongoRepository<SignupEntity, ObjectId> {
}
