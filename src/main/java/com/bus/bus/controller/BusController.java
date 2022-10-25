package com.bus.bus.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bus.bus.model.Bus;
import com.bus.bus.service.BusService;

@RestController
@EnableAutoConfiguration
public class BusController {

    @Autowired
    private BusService busService;

    @GetMapping("/allBus")
    public List<Bus> getAllBus() {
        return busService.getAllBus();
    }

    @PostMapping("/postTest")
    public void registerTest(@RequestBody Map<String, Object> body) {
        System.out.println(body.get("test"));
    }

    @PostMapping("/searchArret")
    public Iterable<Object> searchArret(@RequestBody Map<String, Object> body) {
        return busService.searchArret(body.get("keyword").toString());
    }

    @PostMapping("/selectAllBusInArret")
    public List<Bus> selectAllBusInArret(@RequestBody Map<String, Object> body) {
        return busService.selectAllBusInArret(Long.parseLong(body.get("id_arret").toString()));
    }

    // Get the common bus between two vertices
    public List<Bus> check_bus_commun(Long id_depart, Long id_arrive) {
        List<Bus> busDepart = busService.selectAllBusInArret(id_depart);
        List<Bus> busArrive = busService.selectAllBusInArret(id_arrive);

        List<Bus> bus_list = new ArrayList<Bus>();

        for(Bus bus_depart:busDepart) {
            for(Bus bus_arrive:busArrive) {
                if(bus_depart.getId() == bus_arrive.getId()){
                    bus_list.add(bus_depart);
                }
            }
        }
        return bus_list;
    }

    

    @PostMapping("/coupMin")
    public List<Bus> coupMin(@RequestBody Map<String, Object> body) {
        Long id_depart = Long.parseLong(body.get("id_depart").toString());
        Long id_arrive = Long.parseLong(body.get("id_arrive").toString());
        //List<Bus> bus_result = new ArrayList<Bus>();

        Long temp_id = id_arrive;
        List<Long> file_in_progress = new ArrayList<Long>();
        List<Long> file_done = new ArrayList<Long>();

        while(true) {
            List<Bus> bus_commun = check_bus_commun(id_depart, temp_id);
            if(bus_commun.size() != 0) {
                return bus_commun;
            } 
            else {
                List<Long> new_voisin = busService.getVoisin(temp_id);
                for(Long voisin:new_voisin) {
                    if(!file_done.contains(voisin)){
                        file_in_progress.add(voisin);
                    }
                }
                temp_id = file_in_progress.get(0);
                file_in_progress.remove(0);
                file_done.add(temp_id);
            }

        }   

        //return null;
    }
}