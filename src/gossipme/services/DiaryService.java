package gossipme.services;

import gossipme.data.models.Diary;
import gossipme.data.models.Entry;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DiaryService {
    void register(String username,String password);
    void login(String username,String password);
    Diary findDiaryBelongingTo(String username);
    void writeOn(String username,String title,String body);
    List<Entry> findEntriesBelongingTo(String username);
}
