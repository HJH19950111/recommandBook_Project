package com.project.recommandBook.repository;

import com.project.recommandBook.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    public Optional<UserInfo> findByUserId(String userId);

    public UserInfo findById(Long userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE t_user_info SET is_get_point = :isPoint WHERE is_get_point = true", nativeQuery = true)
    public void changeIsGetPoint(@Param("isPoint") boolean isPoint);
}
