package RoadMessages.demo.controller;

import RoadMessages.demo.Scheduler;
import RoadMessages.demo.model.dto.DTOMessage;
import RoadMessages.demo.model.dto.MessageDTO;
import RoadMessages.demo.model.entity.Message;
import RoadMessages.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("")
public class MessageController {
    @Autowired
    MessageService messageService;
    @GetMapping("message")
    public DTOMessage saveItem() {
        Message message=new Message();
        DTOMessage messageDTO=new DTOMessage();
        messageDTO=messageDTO.convertMessageToDTO(messageService.showMessage());

        return messageDTO;
    }
    @GetMapping("messages/all")
    public List<DTOMessage> getMessages() {
        DTOMessage messageDTO=new DTOMessage();
        List<Message> messages=new ArrayList<>();
        List<DTOMessage>messageDTOS=new ArrayList<>();
        messageService.findAllMessages().stream().skip(1).forEach(message -> messageDTOS.add(messageDTO.convertMessageToDTO(message)));

        return messageDTOS;
    }
    @PostMapping("newmessage")
    public ResponseEntity<?> saveItem(@RequestBody MessageDTO messageDTO) {
        messageService.saveItem(messageDTO);
        return ResponseEntity.ok(new MessageResponse("New message is added successfully and will be deleted after "+messageDTO.getMinutes()+" minutes!"));
    }
}


