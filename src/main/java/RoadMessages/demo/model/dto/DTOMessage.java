package RoadMessages.demo.model.dto;

import RoadMessages.demo.model.entity.Message;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Roberta on 09/2022
 */
public class DTOMessage {

    private String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public DTOMessage convertMessageToDTO(Message message) {


        DTOMessage messageDTO = new DTOMessage();
        messageDTO.setMessage(message.getMessage());

        return messageDTO;
    }
}
