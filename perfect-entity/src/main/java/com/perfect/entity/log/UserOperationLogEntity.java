package com.perfect.entity.log;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by yousheng on 2015/12/13.
 */
@Document(collection = "system_log")
public class UserOperationLogEntity {

    @Id
    private String id;

    private Long oid;

    private long userId;

    private String userName;

    private long campgainId;

    private String campaignName;

    private long adgroupdId;

    private String adgroupName;

    private String name;

    private int type;

    private String property;

    private Object before;

    private Object after;

    private long time;

    private boolean uploaded;

    private String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getOid() {
        return oid;
    }

    public UserOperationLogEntity setOid(Long oid) {
        this.oid = oid;
        return this;
    }

    public long getCampgainId() {
        return campgainId;
    }

    public UserOperationLogEntity setCampgainId(long campgainId) {
        this.campgainId = campgainId;
        return this;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public UserOperationLogEntity setCampaignName(String campaignName) {
        this.campaignName = campaignName;
        return this;
    }

    public long getAdgroupdId() {
        return adgroupdId;
    }

    public UserOperationLogEntity setAdgroupdId(long adgroupdId) {
        this.adgroupdId = adgroupdId;
        return this;
    }

    public String getAdgroupName() {
        return adgroupName;
    }

    public UserOperationLogEntity setAdgroupName(String adgroupName) {
        this.adgroupName = adgroupName;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserOperationLogEntity setName(String name) {
        this.name = name;
        return this;
    }

    public int getType() {
        return type;
    }

    public UserOperationLogEntity setType(int type) {
        this.type = type;
        return this;
    }

    public String getProperty() {
        return property;
    }

    public UserOperationLogEntity setProperty(String property) {
        this.property = property;
        return this;
    }

    public Object getBefore() {
        return before;
    }

    public UserOperationLogEntity setBefore(Object before) {
        this.before = before;
        return this;
    }

    public Object getAfter() {
        return after;
    }

    public UserOperationLogEntity setAfter(Object after) {
        this.after = after;
        return this;
    }

    public long getTime() {
        return time;
    }

    public UserOperationLogEntity setTime(long time) {
        this.time = time;
        return this;
    }

    public boolean isUploaded() {
        return uploaded;
    }

    public UserOperationLogEntity setUploaded(boolean uploaded) {
        this.uploaded = uploaded;
        return this;
    }

    public long getUserId() {
        return userId;
    }

    public UserOperationLogEntity setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}