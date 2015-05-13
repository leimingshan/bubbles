package com.bubbles.server.domain;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by LMSH on 2015/5/12.
 */
@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface UserRepository extends CrudRepository<User, Long> {

    User findByDeviceId(String deviceId);

    @Modifying
    @Transactional(timeout = 10)
    @Query("update User u set u.avatar = ?1 where u.deviceId = ?2")
    int setAvatarByDeviceId(byte[] avatar, String deviceId);
}
