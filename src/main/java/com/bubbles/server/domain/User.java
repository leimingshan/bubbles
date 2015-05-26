package com.bubbles.server.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    private int pubNum;
    private int giveReplyNum;
    private int getReplyNum;
    private int getUpNum;
    private int giveUpNum;

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

    public int getPubNum() {
        return pubNum;
    }

    public void setPubNum(int pubNum) {
        this.pubNum = pubNum;
    }

    public int getGiveReplyNum() {
        return giveReplyNum;
    }

    public void setGiveReplyNum(int giveReplyNum) {
        this.giveReplyNum = giveReplyNum;
    }

    public int getGetReplyNum() {
        return getReplyNum;
    }

    public void setGetReplyNum(int getReplyNum) {
        this.getReplyNum = getReplyNum;
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

    @Override
    public String toString() {
        return String.format(
                "User[id=%d deviceid=%s, phonenum=%s, score=%d",
                id, deviceId, cellphone, score);
    }

}
