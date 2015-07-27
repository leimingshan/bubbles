package com.bubbles.server.domain;

/**
 * User stats info.
 *
 * @author Mingshan Lei
 * @since 2015/7/27
 */
public class UserStats {

    private Long id;

    private Integer postBubblesCount;

    private Integer postRepliesCount;
    private Integer getRepliesCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPostBubblesCount() {
        return postBubblesCount;
    }

    public void setPostBubblesCount(Integer postBubblesCount) {
        this.postBubblesCount = postBubblesCount;
    }

    public Integer getPostRepliesCount() {
        return postRepliesCount;
    }

    public void setPostRepliesCount(Integer postRepliesCount) {
        this.postRepliesCount = postRepliesCount;
    }

    public Integer getGetRepliesCount() {
        return getRepliesCount;
    }

    public void setGetRepliesCount(Integer getRepliesCount) {
        this.getRepliesCount = getRepliesCount;
    }

    @Override
    public String toString() {
        return "UserStats{" +
                "id=" + id +
                ", postBubblesCount=" + postBubblesCount +
                ", postRepliesCount=" + postRepliesCount +
                ", getRepliesCount=" + getRepliesCount +
                '}';
    }
}
