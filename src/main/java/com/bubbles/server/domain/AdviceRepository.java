package com.bubbles.server.domain;

import org.springframework.data.repository.CrudRepository;

/**
 * Interface using spring-data-jpa for Advice CRUD operation.
 *
 * @author Mingshan Lei
 * @since 2015/8/27
 */
public interface AdviceRepository extends CrudRepository<Advice, Long> {

}
