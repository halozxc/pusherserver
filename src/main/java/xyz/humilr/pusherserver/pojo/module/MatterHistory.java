package xyz.humilr.pusherserver.pojo.module;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Id;
import java.util.Date;

public class MatterHistory {

    @Id
    @JsonProperty("matterHistoryId")
    private Integer id;
    private Date publishDate;



    private Date endDate;
    private Integer hostId;
    private String body;
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public Integer getHostId() {
        return hostId;
    }

    public void setHostId(Integer hostId) {
        this.hostId = hostId;
    }



    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }



    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
