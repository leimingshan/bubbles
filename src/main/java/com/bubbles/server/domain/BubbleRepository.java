package com.bubbles.server.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by LMSH on 2015/5/21.
 */
@RepositoryRestResource(collectionResourceRel = "bubble", path="bubbles-rest")
public interface BubbleRepository extends CrudRepository<Bubble, Long> {

    List<Bubble> findByUserId(long userId);
}
