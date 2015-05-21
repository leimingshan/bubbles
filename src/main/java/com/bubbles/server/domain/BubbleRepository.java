package com.bubbles.server.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by LMSH on 2015/5/21.
 */
@Repository
public interface BubbleRepository extends CrudRepository<Bubble, Long> {
}
