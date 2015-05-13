package com.bubbles.server.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by LMSH on 2015/5/12.
 */
@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface UserRepository extends CrudRepository<User, Long> {
    User findByDeviceId(String devicdId);
}
