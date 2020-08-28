package com.bcaf.ivan.FinalProject.Util;


import com.bcaf.ivan.FinalProject.Entity.Agency;
import com.bcaf.ivan.FinalProject.Entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BusDao extends JpaRepository<Bus,String> {

    @Query(nativeQuery = true,value="SELECT u.* FROM tb_buy u WHERE u.agency_id =:agency_id ")
    Bus findByBusAgencyId(@Param("agency_id") String agency_id);
}