package gossipme.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Document
@Data
public class Entry {
    @Id
    private String id;
    private String title;
    private String body;
    private LocalDateTime localDateTime = now();
    private String diaryId;

}
