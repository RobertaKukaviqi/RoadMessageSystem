package RoadMessages.demo.service.impl;


import RoadMessages.demo.model.dto.MessageDTO;
import RoadMessages.demo.model.entity.Message;
import RoadMessages.demo.repository.MessageRepository;
import RoadMessages.demo.service.MessageService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;


    @Scheduled(cron = "*/10 * * * * * ")
    public void cronJobSch() {

        Message message=findFirst();

        ObjectId idOfFirstMessage=message.get_id();
        message.set_id(null);

        messageRepository.save(message);

        messageRepository.deleteById(idOfFirstMessage);

    }

    @Override
    public Message findFirst() {
            return messageRepository.findFirst().get(0);
    }
    @Override
    public List<Message> findAllMessages() {
        return messageRepository.findFirst();
    }

    @Override
    public Message showMessage() {
        Message importantMessage=messageRepository.findByImportance(1);
        Date now=new Date();
        Timestamp tsn=new Timestamp(now.getTime());
        Timestamp tsm=null;

        if(importantMessage!=null){
            tsm=new Timestamp(importantMessage.getDateModified().getTime());
        }

        if(importantMessage==null){
            return messageRepository.findFirst().get(0);
        }
        else if(importantMessage!=null&& tsm.after(tsn)){

            return messageRepository.findByImportance(1);
        }
        else if(importantMessage!=null&& tsm.before(tsn)) {
            messageRepository.delete(importantMessage);

        }
        return messageRepository.findFirst().get(0);

    }
    @Override
    public Optional<Message> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Message> findAllItems() {
        return null;
    }

    @Override
    public void saveItem(MessageDTO messageDTO) {

        Message message=new Message();
        messageRepository.save(messageDTO.convertDtoToMessage(messageDTO));
    }

    @Override
    public void deleteItem(Message message) {

    }

}
