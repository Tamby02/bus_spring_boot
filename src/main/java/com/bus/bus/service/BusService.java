package com.bus.bus.service;

import java.util.List;

// import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.bus.model.Bus;
import com.bus.bus.repository.BusRepository;

import lombok.Data;
@Data
@Service

public class BusService {
    
    @Autowired
    private BusRepository busRepository;
    // public Optional<Bus> getBus(final Long id) {
    //     return busRepository.findById(id);
    // }

    public List<Bus> getAllBus() {
        return busRepository.findMany();
    }

    public Iterable<Object> searchArret(String keyWord) {
        return busRepository.searchArret(keyWord);
    }

    public List<Bus> selectAllBusInArret(Long id_arret) {
        return busRepository.selectAllBusInArret(id_arret);
    }

    // public void deleteBus(final Long id) {
    //     busRepository.deleteById(id);
    // }

    // public Bus saveBus(Bus bus) {
    //     Bus savedBus = busRepository.save(bus);
    //     return savedBus;
    // }

}
