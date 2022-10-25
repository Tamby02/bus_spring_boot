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

    @PostMapping("/coupMin")
    public List<Bus> coupMin(@RequestBody Map<String, Object> body) {
        String id_depart = body.get("id_depart").toString();
        String id_arrive = body.get("id_arrive").toString();

        List<Bus> busDepart = busService.selectAllBusInArret(Long.parseLong(id_depart));
        List<Bus> busArrive = busService.selectAllBusInArret(Long.parseLong(id_arrive));

        

        return busDepart;
    }
}