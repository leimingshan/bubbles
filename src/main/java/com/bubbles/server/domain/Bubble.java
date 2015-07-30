package com.bubbles.server.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity Bubble, mapped to table bubble.
 *
 * @author Mingshan Lei
 * @since 2015/5/21
 */
@Entity
@Cacheable
@JsonIgnoreProperties({"parentBubble"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Bubble {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * The direct parent id of this bubble or reply.
     * The value may be null; null represents main bubble, else represents reply.
     */
    private Long parentId;

    /**
     * The bubble which contains this object.
     */
    private Long parentBubbleId;

    /**
     * The author object.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id")
    private User user;

    private String content;

    private Integer replyNum;
    private Integer score;

    private Date timestamp;
    private Date lastReplyTime;

    private Double latitude;
    private Double longitude;

    private Integer distance;

    public Bubble() {
        super();
    }

    public Bubble(long id, Double latitude, Double longitude) {
        super();
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getParentBubbleId() {
        return parentBubbleId;
    }

    public void setParentBubbleId(Long parentBubbleId) {
        this.parentBubbleId = parentBubbleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getLastReplyTime() {
        return lastReplyTime;
    }

    public void setLastReplyTime(Date lastReplyTime) {
        this.lastReplyTime = lastReplyTime;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Bubble{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", parentBubbleId=" + parentBubbleId +
                ", user=" + user +
                ", content='" + content + '\'' +
                ", replyNum=" + replyNum +
                ", score=" + score +
                ", timestamp=" + timestamp +
                ", lastReplyTime=" + lastReplyTime +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", distance=" + distance +
                '}';
    }
}
