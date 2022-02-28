package xyz.humilr.pusherserver.pojo.module;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Id;
import javax.persistence.Transient;

public class MatterFan {
    @Id
    @JsonIgnore
    Integer id;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Integer matterId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Integer fanId;

    Integer sourceType;
    @Transient
    Matter matter;

    public MatterFan() {
    }

    public MatterFan(Integer matterId, Integer fanId) {
        this.matterId = matterId;
        this.fanId = fanId;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMatterId() {
        return matterId;
    }

    public void setMatterId(Integer matterId) {
        this.matterId = matterId;
    }

    public Integer getFanId() {
        return fanId;
    }

    public void setFanId(Integer fanId) {
        this.fanId = fanId;
    }

    public Matter getMatter() {
        return matter;
    }

    public void setMatter(Matter matter) {
        this.matter = matter;
    }
}
