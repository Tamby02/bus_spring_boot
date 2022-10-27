package com.bus.bus.service;

import java.util.ArrayList;
import java.util.List;

// import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.bus.model.Bus;
import com.bus.bus.model.Trajet;
import com.bus.bus.repository.BusRepository;

import lombok.Data;


@Data
@Service

public class BusService {
    
    @Autowired
    private BusRepository busRepository;
    // public Optional<Bus> getBus(final int id) {
    //     return busRepository.findById(id);
    // }

    public List<Bus> getAllBus() {
        return busRepository.findMany();
    }

    public Iterable<Object> searchArret(String keyWord) {
        return busRepository.searchArret(keyWord);
    }

    public List<Bus> selectAllBusInArret(int id_arret) {
        return busRepository.selectAllBusInArret(id_arret);
    }

    public List<Trajet> getVoisin(int id_depart) {
        return busRepository.getVoisin(id_depart);
    }

    public List<Trajet> getNeighbor(int id_depart) {
        List<int[]> voisins = busRepository.getNeighbor(id_depart);
        List<Trajet> trajets = new ArrayList<Trajet>();
        for(int[] voisin : voisins) {
            if(id_depart == 3) {
                System.out.println("voisin => "+voisin[5]);
            }
            Trajet tempTrajet = new Trajet((int)voisin[0], (int)voisin[1], (int)voisin[2], (int)voisin[3], (int)voisin[4], (int)voisin[5]);
            trajets.add(tempTrajet);
        }
        return trajets;
    }

    public List<Integer> getAllBustStop() {
        return busRepository.getAllBusStop();
    }

    // public void deleteBus(final int id) {
    //     busRepository.deleteById(id);
    // }

    // public Bus saveBus(Bus bus) {
    //     Bus savedBus = busRepository.save(bus);
    //     return savedBus;
    // }

}
