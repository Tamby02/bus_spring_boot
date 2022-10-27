package com.bus.bus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.CrudRepository;
// import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import com.bus.bus.model.Bus;
import com.bus.bus.model.Trajet;

// @Repository
// public interface BusRepository extends CrudRepository<Bus, Long> {
    
// }


public interface BusRepository extends JpaRepository<Bus, Integer> {

    @Query(value = "SELECT * FROM bus_tana",nativeQuery=true)
    List<Bus> findMany();

    @Query(value = "SELECT * FROM arret WHERE nom LIKE %:keyword%", nativeQuery = true)
    Iterable<Object> searchArret(@Param("keyword") String keyword);

    @Query(value = "SELECT bus_tana.id, nom FROM bus_tana INNER JOIN arret_bus b ON b.id_bus = bus_tana.id WHERE b.id_arret = :id_arret", nativeQuery = true)
    List<Bus> selectAllBusInArret(@Param("id_arret") int id_arret);

    @Query(value = "SELECT * FROM trajet WHERE id_arret_depart=:id_depart",nativeQuery=true)
    List<Trajet> getVoisin(@Param("id_depart") int id_depart);

    @Query(value = "SELECT * FROM trajet WHERE id_arret_depart=:id_depart",nativeQuery=true)
    List<int[]> getNeighbor(@Param("id_depart") int id_depart);

    @Query(value = "SELECT id FROM arret",nativeQuery=true)
    List<Integer> getAllBusStop();
    

}



