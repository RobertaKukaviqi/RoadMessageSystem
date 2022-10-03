package RoadMessages.demo.model.dto;

import RoadMessages.demo.model.entity.Message;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Roberta on 09/2022
 */
public class MessageDTO {

    private String message;

    private int minutes;

    private Integer importance;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        this.importance = importance;
    }

    public Message convertDtoToMessage(MessageDTO messageDTO) {


        Calendar date = Calendar.getInstance();

        long timeInSecs = date.getTimeInMillis();

        Date afterAddingMinutes = new Date(timeInSecs + (messageDTO.getMinutes() * 60 * 1000));
        Timestamp t=new Timestamp(afterAddingMinutes.getTime());

        Message message = new Message();
        message.setMessage(messageDTO.getMessage());
        message.setImportance(messageDTO.getImportance());
        message.setDateModified(t);

        return message;
    }

}
