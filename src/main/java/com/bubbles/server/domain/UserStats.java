package com.bubbles.server.domain;

/**
 * User stats info.
 *
 * @author Mingshan Lei
 * @since 2015/7/27
 */
public class UserStats {

    private Long id;

    private Integer score;

    private Integer postBubblesCount;

    private Integer postRepliesCount;
    private Integer getRepliesCount;

    private Integer approvalCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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

    public Integer getApprovalCount() {
        return approvalCount;
    }

    public void setApprovalCount(Integer approvalCount) {
        this.approvalCount = approvalCount;
    }

    @Override
    public String toString() {
        return "UserStats{" +
                "id=" + id +
                ", score=" + score +
                ", postBubblesCount=" + postBubblesCount +
                ", postRepliesCount=" + postRepliesCount +
                ", getRepliesCount=" + getRepliesCount +
                ", approvalCount=" + approvalCount +
                '}';
    }
}
