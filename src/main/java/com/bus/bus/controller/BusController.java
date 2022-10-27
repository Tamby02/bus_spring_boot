package com.bus.bus.controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bus.bus.model.Bus;
import com.bus.bus.model.Trajet;
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
        return busService.selectAllBusInArret((int)body.get("id_arret"));
    }

    // Get the common bus between two vertices
    // public List<Bus> check_bus_commun(int id_depart, int id_arrive) {
    //     List<Bus> busDepart = busService.selectAllBusInArret(id_depart);
    //     List<Bus> busArrive = busService.selectAllBusInArret(id_arrive);

    //     List<Bus> bus_list = new ArrayList<Bus>();

    //     for(Bus bus_depart:busDepart) {
    //         for(Bus bus_arrive:busArrive) {
    //             if(bus_depart.getId() == bus_arrive.getId()){
    //                 bus_list.add(bus_depart);
    //             }
    //         }
    //     }
    //     return bus_list;
    // }

    @PostMapping("/getListTrajet")
    public List<Trajet> getListTrajet(@RequestBody Map<String, Object> body) { 
        return busService.getNeighbor((int)(body.get("id_depart")));
    }
    @PostMapping("/coupMin")
    public List<Node> coupMin(@RequestBody Map<String, Object> body) {

        Djikstra djikstra = new Djikstra(busService);

        List<Node> nodes = djikstra.findPath((int)(body.get("id_depart")), (int)(body.get("id_arrive")));
            
        System.out.println(nodes);
        return nodes;

        //return null;
    }
}