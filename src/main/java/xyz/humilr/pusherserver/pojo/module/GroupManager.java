package xyz.humilr.pusherserver.pojo.module;

public class GroupManager {

    Integer groupId;
    Integer managerUserId;

    public GroupManager(Integer groupId, Integer managerUserId) {
    this.groupId = groupId;
    this.managerUserId = managerUserId;
    }

    public Integer getManagerUserId() {
        return managerUserId;
    }

    public void setManagerUserId(Integer managerUserId) {
        this.managerUserId = managerUserId;
    }


    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }







}
