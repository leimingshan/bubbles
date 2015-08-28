package com.bubbles.server.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * Interface using spring-data-jpa for Report CRUD operation.
 *
 * @author Mingshan Lei
 * @since 2015/8/27
 */
public interface ReportRepository extends CrudRepository<Report, Long> {

    /**
     * Get report info order by time in pages.
     *
     * @param pageable page and size
     * @return report list
     */
    @Query("from Report r order by r.createdDate desc")
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Report> findByPage(Pageable pageable);
}
