package com.bcaf.ivan.FinalProject.Util;


import com.bcaf.ivan.FinalProject.Entity.Agency;
import com.bcaf.ivan.FinalProject.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyDao extends JpaRepository<Agency,String> {

    @Query(nativeQuery = true,value="SELECT u.* FROM tb_user u WHERE u.email =:email ")
    User findEmailValidation(@Param("email") String email);

    @Query(nativeQuery = true,value="SELECT u.* FROM tb_agency u WHERE u.user_id =:user_id ")
    Agency findByUserId(@Param("user_id") String user_id);

}