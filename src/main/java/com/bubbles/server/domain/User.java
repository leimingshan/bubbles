package com.bubbles.server.domain;

import javax.persistence.*;

/**
 * Created by LMSH on 2015/5/11.
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String deviceId;

    private String cellphone;
    private String nickname;
    private String gender;
    private byte[] avatar;

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

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
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
                "User[deviceid=%s, phonenum=%s, score=%d",
                deviceId, cellphone, score);
    }
}
