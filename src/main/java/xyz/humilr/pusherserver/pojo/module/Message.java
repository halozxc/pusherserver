package xyz.humilr.pusherserver.pojo.module;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;

public class Message {


    @Id
    @JsonIgnore
    Integer id;
    Integer sender;
    String   body;
    Integer destination_user_id;
    Integer destination_matter_id;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getSender() {
        return sender;
    }

    public void setSender(Integer sender) {
        this.sender = sender;
    }



    public Integer getDestination_user_id() {
        return destination_user_id;
    }

    public void setDestination_user_id(Integer destination_user_id) {
        this.destination_user_id = destination_user_id;
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


}
