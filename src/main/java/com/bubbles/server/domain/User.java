package com.bubbles.server.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Entity User, mapped to table user
 *
 * @author Mingshan Lei
 * @since 2015/5/11
 */
@Entity
@Cacheable
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 64)
    private String deviceId;

    private String cellphone;

    @NotNull
    @Size(max = 32)
    private String nickname;

    @NotNull
    @Size(max = 1)
    private String gender;

    private String avatarUrl;

    private int score;

    private int getUpNum;
    private int giveUpNum;

    private Date createdDate;

    private Date modifiedDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getGetUpNum() {
        return getUpNum;
    }

    public void setGetUpNum(int getUpNum) {
        this.getUpNum = getUpNum;
    }

    public int getGiveUpNum() {
        return giveUpNum;
    }

    public void setGiveUpNum(int giveUpNum) {
        this.giveUpNum = giveUpNum;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", deviceId='" + deviceId + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", nickname='" + nickname + '\'' +
                ", gender='" + gender + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", score=" + score +
                ", getUpNum=" + getUpNum +
                ", giveUpNum=" + giveUpNum +
                '}';
    }
}
