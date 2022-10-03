package RoadMessages.demo.service;


import RoadMessages.demo.model.dto.MessageDTO;
import RoadMessages.demo.model.entity.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface MessageService {


    Message findFirst();

    List<Message> findAllMessages();

    Message showMessage();

    Optional<Message> findById(Integer id);

    List<Message> findAllItems();

    void saveItem(MessageDTO messageDTO);

    void deleteItem(Message message);
}
