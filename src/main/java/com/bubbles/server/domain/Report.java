package com.bubbles.server.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Entity Report, mapped to table report.
 *
 * @author Mingshan Lei
 * @since 2015/8/27
 */
@Entity
@Cacheable
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private Long bubbleId;

    @NotNull
    private Integer category;

    private String content;

    private Date createdDate;
    private Date modifiedDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getBubbleId() {
        return bubbleId;
    }

    public void setBubbleId(Long bubbleId) {
        this.bubbleId = bubbleId;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        return "Report{" +
                "id=" + id +
                ", bubbleId=" + bubbleId +
                ", category=" + category +
                ", content='" + content + '\'' +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
