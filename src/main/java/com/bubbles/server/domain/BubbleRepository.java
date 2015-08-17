package com.bubbles.server.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

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

    @Query(value = "SELECT * FROM bubble\n" +
            "WHERE bubble.id IN\n" +
            "(\n" +
            "SELECT DISTINCT(parent_bubble_id) FROM bubble \n" +
            "WHERE bubble.author_id = ?1 AND !ISNULL(parent_bubble_id)\n" +
            ")\n" +
            "ORDER BY bubble.timestamp DESC", nativeQuery = true)
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Bubble> findParticipateBubblesByUserId(long userId);

    /**
     * Find the bubble's all replies by bubble's id.
     *
     * @param bubbleId the bubble's id
     * @return bubble list
     */
    @Query("from Bubble b where b.parentBubbleId=?1 order by b.timestamp desc")
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Bubble> findRepliesByBubbleIdOrderByTime(Long bubbleId);

    List<Bubble> findTop5ByParentBubbleId(Long parentBubbleId, Pageable pageable);

    @Query("from Bubble b where b.latitude between ?1 and ?2 and b.longitude between ?3 and ?4 order by b.score desc")
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Bubble> findHotBubbles(double minLat, double maxLat, double minLon, double maxLon, Pageable pageable);

    @Query("from Bubble b where b.latitude between ?1 and ?2 and b.longitude between ?3 and ?4 order by b.timestamp desc")
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Bubble> findNewBubbles(double minLat, double maxLat, double minLon, double maxLon, Pageable pageable);

    @Query("select new Bubble(id, latitude, longitude) from Bubble b where b.latitude != null and b.longitude != null")
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Bubble> findBriefBubbles();

    /**
     * Add specific num to bubble's score.
     *
     * @param bubbleId the bubble's id
     * @param addNum socre added, could be negative
     * @return
     */
    @Modifying
    @Transactional
    @Query("update Bubble b set b.score = b.score + ?2 where b.id = ?1")
    int addScoreById(long bubbleId, int addNum);
}
