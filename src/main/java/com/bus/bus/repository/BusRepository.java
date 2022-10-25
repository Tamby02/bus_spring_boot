package com.bus.bus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.CrudRepository;
// import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import com.bus.bus.model.Bus;

// @Repository
// public interface BusRepository extends CrudRepository<Bus, Long> {
    
// }


public interface BusRepository extends JpaRepository<Bus, Long> {

    @Query(value = "SELECT * FROM bus",nativeQuery=true)
    List<Bus> findMany();

    @Query(value = "SELECT * FROM arret WHERE nom LIKE %:keyword%", nativeQuery = true)
    Iterable<Object> searchArret(@Param("keyword") String keyword);

    @Query(value = "SELECT bus.id, nom FROM bus INNER JOIN bus_arret b ON b.id_bus = bus.id WHERE b.id_arret = :id_arret", nativeQuery = true)
    List<Bus> selectAllBusInArret(@Param("id_arret") Long id_arret);

    @Query(value = "SELECT id_arret_arrive FROM trajet WHERE id_arret_depart=:id_depart;",nativeQuery=true)
    List<Long> getVoisin(@Param("id_depart") Long id_depart);

}



