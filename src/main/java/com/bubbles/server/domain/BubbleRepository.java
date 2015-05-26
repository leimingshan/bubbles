package com.bubbles.server.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * Interface using spring-data-jpa for bubbles CRUD operation.
 *
 * @author Mingshan Lei
 * @since 2015/5/21
 */
@RepositoryRestResource(collectionResourceRel = "bubble", path = "bubbles-rest")
public interface BubbleRepository extends CrudRepository<Bubble, Long> {

    /**
     * Find user's all bubbles by user's id.
     *
     * @param userId user's id
     * @return bubble list
     */
    List<Bubble> findByUserId(long userId);

    /**
     * Find user's bubbles by user's id and order by timestamp.
     *
     * @param userId user's id
     * @return bubble list
     */
    @Query("from Bubble b where b.user.id=?1 order by b.timestamp desc")
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Bubble> findByUserIdOrderByTime(long userId);

    /**
     * Find the bubble's all replies by bubble's id.
     *
     * @param bubbleId the bubble's id
     * @return bubble list
     */
    @Query("from Bubble b where b.parentBubbleId=?1 order by b.timestamp desc")
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Bubble> findRepliesByBubbleIdOrderByTime(Long bubbleId);
}
