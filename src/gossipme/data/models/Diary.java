package gossipme.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Diary {
    @Id
    private String id;
    private String password;
    private String username;
    private boolean isLocked = true;
}
