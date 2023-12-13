package gossipme.data.repositories;

import gossipme.data.models.Diary;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DiaryRepository extends MongoRepository<Diary,String> {
    Diary findByUsername(String username);
}
