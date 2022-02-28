package xyz.humilr.pusherserver.pojo.module;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class GroupMatter {
    @JsonIgnore
    private Integer id;
    private Integer groupId;
    private Integer matterId;

    public GroupMatter() {
    }

    public GroupMatter(Integer groupId, Integer matterId) {
        this.groupId = groupId;
        this.matterId = matterId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getMatterId() {
        return matterId;
    }

    public void setMatterId(Integer matterId) {
        this.matterId = matterId;
    }
}
