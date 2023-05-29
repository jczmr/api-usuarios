package com.jc.apiusuarios.repository;

import com.jc.apiusuarios.model.Rol;
import com.jc.apiusuarios.model.Rol.RolStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer> {


    @Query("SELECT r FROM Rol r WHERE r.status = :status")
    List<Rol> findAllActive(RolStatus status);

    @Transactional
    @Modifying
    @Query("UPDATE Rol r SET r.status = :status WHERE r.id = :id")
    void swapDeleteRol(@Param("status") RolStatus status, @Param("id")  Integer id);

    Rol findByRoleName(String roleName);

}
