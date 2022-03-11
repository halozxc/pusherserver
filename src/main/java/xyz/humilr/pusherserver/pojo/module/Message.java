package xyz.humilr.pusherserver.pojo.module;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;
import java.util.Date;

public class Message {


    @Id
    @JsonIgnore
    Integer id;
    String sender;
    String   body;
    String destination_user;
    Integer destination_matter_id;

    public Date getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(Date publish_date) {
        this.publish_date = publish_date;
    }

    Date publish_date;
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



    public String getDestination_user() {
        return destination_user;
    }

    public void setDestination_user_id(String destination_user_id) {
        this.destination_user = destination_user_id;
    }



    public Integer getDestination_matter_id() {
        return destination_matter_id;
    }

    public void setDestination_matter_id(Integer destination_matter_id) {
        this.destination_matter_id = destination_matter_id;
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
                ", sender=" + sender +
                ", body='" + body + '\'' +
                ", destination_user_id=" + destination_user +
                ", destination_matter_id=" + destination_matter_id +
                '}';
    }
}
