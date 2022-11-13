package sample.model;

import java.util.Date;

public class MessageModel {
    public Long clientSender;
    public Long clientRecipient;

    public String message;
    public Date date;


    public MessageModel(Long clientSender, Long clientRecipient, String message, Date date) {
        this.clientSender = clientSender;
        this.clientRecipient = clientRecipient;
        this.message = message;
        this.date = date;
    }
}
