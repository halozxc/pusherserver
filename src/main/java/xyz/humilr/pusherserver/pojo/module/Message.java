package xyz.humilr.pusherserver.pojo.module;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Id;
import java.util.Date;

public class Message {


    @Id
    //@JsonIgnore
    Integer id;
    String sender;
    String  body;
    String destinationUser;



    Integer destinationMatterId;

    Date publishDate;
    public String getDestinationUser() {
        return destinationUser;
    }

    public void setDestinationUser(String destinationUser) {
        this.destinationUser = destinationUser;
    }


    public Integer getDestinationMatterId() {
        return destinationMatterId;
    }

    public void setDestinationMatterId(Integer destinationMatterId) {
        this.destinationMatterId = destinationMatterId;
    }





    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }







    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", sender='" + sender + '\'' +
                ", body='" + body + '\'' +
                ", destinationUser='" + destinationUser + '\'' +
                ", destinationMatteId=" + destinationMatterId +
                ", publishDate=" + publishDate +
                '}';
    }
}
