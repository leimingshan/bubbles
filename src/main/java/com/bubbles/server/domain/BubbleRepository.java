package com.bubbles.server.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * Created by LMSH on 2015/5/21.
 */
@RepositoryRestResource(collectionResourceRel = "bubble", path = "bubbles-rest")
public interface BubbleRepository extends CrudRepository<Bubble, Long> {

    List<Bubble> findByUserId(long userId);

    @Query("from Bubble b where b.user.id=?1 order by b.timestamp")
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    List<Bubble> findByUserIdOrderByTimestamp(long userId);

    List<Bubble> findByParentId(Long parentId);
}
