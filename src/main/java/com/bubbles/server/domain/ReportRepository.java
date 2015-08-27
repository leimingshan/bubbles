package com.bubbles.server.domain;

import org.springframework.data.repository.CrudRepository;

/**
 * Interface using spring-data-jpa for Report CRUD operation.
 *
 * @author Mingshan Lei
 * @since 2015/8/27
 */
public interface ReportRepository extends CrudRepository<Report, Long> {

}
