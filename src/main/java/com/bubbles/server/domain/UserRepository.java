package com.bubbles.server.domain;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * Interface using spring-data-jpa for uses CRUD operation
 *
 * @author Mingshan Lei
 * @since 2015/5/11
 */
@RepositoryRestResource(collectionResourceRel = "user", path = "users-rest")
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByDeviceId(String deviceId);

    @Query("select u.avatarUrl from User u where u.id = ?1")
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    String findAvatarUrlById(long userId);

    @Modifying
    @Transactional(timeout = 5)
    @Query("update User u set u.avatarUrl = ?2 where u.id = ?1")
    int setAvatarUrlById(long userId, String avatarPath);

    @Modifying
    @Transactional(timeout = 5)
    @Query("update User u set u.score = ?2 where u.id = ?1")
    int setScoreById(long userId, int score);

    @Modifying
    @Transactional
    @Query("update User u set u.score = u.score + ?2 where u.id = ?1")
    int addScoreById(long userId, int addNum);

    @Modifying
    @Transactional(timeout = 5)
    @Query("update User u set u.nickname = ?2 where u.id = ?1")
    int setNicknameById(long userId, String nickname);

    @Modifying
    @Transactional(timeout = 5)
    @Query("update User u set u.gender = ?2 where u.id = ?1")
    int setGenderById(long userId, String gender);

    @Query(value = "SELECT COUNT(*) FROM bubble WHERE author_id = ?1 AND ISNULL(parent_id)", nativeQuery = true)
    int findPostBubblesCountById(long userId);

    @Query(value = "SELECT COUNT(*) FROM bubble WHERE author_id = ?1 AND NOT ISNULL(parent_id)", nativeQuery = true)
    int findPostRepliesCountById(long userId);

    @Query(value = "SELECT COUNT(*) FROM bubble WHERE bubble.parent_bubble_id IN ( SELECT id FROM bubble WHERE bubble.author_id = ?1)", nativeQuery = true)
    int findGetRepliesCountById(long userId);

    @Query(value = "SELECT COALESCE(SUM(score), 0) FROM bubble WHERE bubble.author_id = ?1", nativeQuery = true)
    int findApprovalCountById(long userId);
}
