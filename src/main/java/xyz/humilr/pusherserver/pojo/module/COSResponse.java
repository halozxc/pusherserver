package xyz.humilr.pusherserver.pojo.module;

import java.util.Date;

public class COSResponse {
    public COSResponse(String secertId,String secertKey,String token,Long lifeTime) {
        this.secertId = secertId;
        this.secertKey = secertKey;
        this.sessionToken = token;
        this.startTime = new Date().getTime();
        this.expireTime = this.startTime + lifeTime;
    }

    public String getSecertId() {
        return secertId;
    }

    public void setSecertId(String secertId) {
        this.secertId = secertId;
    }

    String secertId;

    public String getSecertKey() {
        return secertKey;
    }

    public void setSecertKey(String secertKey) {
        this.secertKey = secertKey;
    }

    String secertKey;

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    String sessionToken;

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    Long startTime;


    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    Long expireTime;
}
