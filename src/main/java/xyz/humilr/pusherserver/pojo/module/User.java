package xyz.humilr.pusherserver.pojo.module;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Id;

public class User {
    @JsonIgnore
    @Id
    private Integer id;
    private String uname;
    private String nickname;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String upassword;
    private String utel;
    private String umail;
    @JsonIgnore
    private String salt;
    private Integer avatarid;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String uname, String nickname) {
        this.id = id;
        this.uname = uname;
        this.nickname = nickname;
    }

    public Integer getAvatarId() {
        return avatarid;
    }

    public void setAvatarId(Integer avatarId) {
        this.avatarid = avatarId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }


    public String getUtel() {
        return utel;
    }

    public void setUtel(String utel) {
        this.utel = utel;
    }


    public String getUmail() {
        return umail;
    }

    public void setUmail(String umail) {
        this.umail = umail;
    }


    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

}
