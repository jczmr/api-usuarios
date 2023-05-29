package com.jc.apiusuarios.repository;

import com.jc.apiusuarios.model.User;
import com.jc.apiusuarios.model.User.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.status = :status")
    List<User> findAllActive(UserStatus status);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.status = :status WHERE u.id = :id")
    void swapDeleteUser(@Param("status") UserStatus status, @Param("id") Integer id);

    User findByUsername(String username);

}
