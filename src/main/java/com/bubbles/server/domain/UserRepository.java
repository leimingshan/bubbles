package com.bubbles.server.domain;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Interface using spring-data-jpa for uses CRUD operation
 * @author Mingshan Lei
 */
@RepositoryRestResource(collectionResourceRel = "user", path="users-rest")
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByDeviceId(String deviceId);

    @Modifying
    @Transactional(timeout = 5)
    @Query("update User u set u.avatar = ?2 where u.id = ?1")
    int setAvatarById(long userId, byte[] avatar);

    @Modifying
    @Transactional(timeout = 5)
    @Query("update User u set u.score = ?2 where u.id = ?1")
    int setScoreById(long userId, int score);

    @Modifying
    @Transactional(timeout = 5)
    @Query("update User u set u.nickname = ?2 where u.id = ?1")
    int setNicknameById(long userId, String nickname);

    @Modifying
    @Transactional(timeout = 5)
    @Query("update User u set u.gender = ?2 where u.id = ?1")
    int setGenderById(long userId, String gender);
}
