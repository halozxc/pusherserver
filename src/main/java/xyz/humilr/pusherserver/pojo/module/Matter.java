package xyz.humilr.pusherserver.pojo.module;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.security.Timestamp;
import java.util.Date;

public class Matter {
    @Id
    @JsonProperty("matterId")
    private Integer id;
    private String headline;
    private Date startdate;
    private Date enddate;
    private String location;
    private Boolean isergency;
    private String body;
    @JsonIgnore
    private Boolean hasFile;
    private Integer messageId;
    @JsonIgnore
    private Integer managerUserId;
    @Transient
    private User managerUser;
    @JsonProperty("repeat")
    private Integer repeatfreq;
    private Date publishDate;
    @Transient
    private GroupPusher group;
    @Transient
    private User user;
    @Transient
    private Integer groupId;
    @Transient
    private Integer userId;


    public Matter(Integer managerUserId, Date publishDate, Date startdate, Date enddate) {
        this.startdate = startdate;
        this.enddate = enddate;
        this.managerUserId = managerUserId;
        this.publishDate = publishDate;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Matter(Integer id) {
        this.id = id;
    }

    public Matter() {
    }

    public User getManagerUser() {
        return managerUser;
    }

    public void setManagerUser(User managerUser) {
        this.managerUser = managerUser;
    }

    public Integer getRepeatfreq() {
        return repeatfreq;
    }

    public void setRepeat(Integer repeatfreq) {
        this.repeatfreq = repeatfreq;
    }

    public GroupPusher getGroup() {
        return group;
    }

    public void setGroup(GroupPusher group) {
        this.group = group;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getIsergency() {
        return isergency;
    }

    public void setIsergency(Boolean isergency) {
        this.isergency = isergency;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Boolean getHasFile() {
        return hasFile;
    }

    public void setHasFile(Boolean hasFile) {
        this.hasFile = hasFile;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getManagerUserId() {
        return managerUserId;
    }

    public void setManagerUserId(Integer managerUserId) {
        this.managerUserId = managerUserId;
    }



    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
