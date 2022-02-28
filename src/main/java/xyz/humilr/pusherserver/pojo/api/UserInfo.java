package xyz.humilr.pusherserver.pojo.api;

public class UserInfo {
    private Integer id;
    private String username;

    public UserInfo(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    public UserInfo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
