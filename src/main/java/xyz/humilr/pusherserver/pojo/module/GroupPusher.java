package xyz.humilr.pusherserver.pojo.module;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

public class GroupPusher {
    @Id
    @JsonProperty("groupId")
    Integer id;
    @JsonIgnore
    Integer creatorUserId;
    String groupname;
    @Transient

    User creator;

    @Transient
    List<GroupFan> fans;

    public GroupPusher() {
    }

    public GroupPusher(Integer id) {
        this.id = id;
    }

    public GroupPusher(Integer id, Integer creatorUserId) {
        this.id = id;
        this.creatorUserId = creatorUserId;
    }

    public List<GroupFan> getFans() {
        return fans;
    }

    public void setFans(List<GroupFan> fans) {
        this.fans = fans;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(Integer creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}
