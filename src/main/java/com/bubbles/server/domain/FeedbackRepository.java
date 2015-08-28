package com.bubbles.server.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * Interface using spring-data-jpa for Feedback CRUD operation.
 *
 * @author Mingshan Lei
 * @since 2015/8/27
 */
public interface FeedbackRepository extends CrudRepository<Feedback, Long> {

    /**
     * Get feedback info order by time in pages.
     *
     * @param pageable page and size
     * @return feedback list
     */
    @Query("from Feedback f order by f.createdDate desc")
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Feedback> findByPage(Pageable pageable);
}
