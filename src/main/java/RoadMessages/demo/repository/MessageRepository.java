package RoadMessages.demo.repository;

import RoadMessages.demo.model.entity.Message;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends MongoRepository<Message,Integer> {
    @Transactional
    @Query("db.message.find({}).sort({'dateModified':1});")
    List<Message> findFirst();

//    @Transactional
//    @Query("db.message limit 1.find({\n\n},{\n   \"E\": 1\n}\n);")
//    Message deleteFirst();

    @Transactional
//    @Query("SELECT * FROM messages where importance=1;")
    Message findByImportance(Integer importance);

    @Query(value="{'_id' : ?0}", delete = true)
    public void deleteById(ObjectId id);

}
