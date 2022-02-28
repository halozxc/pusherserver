package xyz.humilr.pusherserver.pojo.module;

import javax.persistence.Id;

public class Contact {
    @Id
    Integer id;
    Integer user_2;
    Integer user_1;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public Integer getUser_1() {
        return user_1;
    }

    public void setUser_1(Integer user_1) {
        this.user_1 = user_1;
    }



    public Integer getUser_2() {
        return user_2;
    }

    public void setUser_2(Integer user_2) {
        this.user_2 = user_2;
    }



}
