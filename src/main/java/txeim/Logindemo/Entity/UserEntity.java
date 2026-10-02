package txeim.Logindemo.Entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "Users")
public class UserEntity {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String username;
    private String password;
    private LocalDateTime localdatetime;
    @DBRef
    private List<JournalEntity> JournalData=new ArrayList<>();
    private List<String> userrole=new ArrayList<>();


}
