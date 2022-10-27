package com.bus.bus.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bus.bus.model.Trajet;
import com.bus.bus.service.BusService;

public class Djikstra {

    List<Node> nodes = new ArrayList<Node>();
    List<Node> nodesDone = new ArrayList<Node>();

    @Autowired
    private BusService busService;

    public Djikstra(BusService busService) {
        this.busService = busService;
        System.out.println("********* create Djikstra ***************");
    }

    public List<Node> findPath(int id_depart, int id_arrive) {
        System.out.println("********* find path ***************" + id_depart);

        this.generateGraphe();
        for (Node node : this.nodes) {
            if (node.index == id_depart) {
                node.score = 0;
            }
        }

        System.out.println("all Nodes =>" + this.nodes);
        while (this.nodes.size() != 0) {
            // extraire min this.nodes
            Node minNode = this.findMinNodeScore();
            this.nodes.removeIf(node -> node.score == minNode.score);
            if (minNode.score < 50000000) {
                this.nodesDone.add(minNode);
                for (Map<String, Object> voisin : minNode.voisins) {
                    if (!isChecked((int) voisin.get("id"))) {
                        if ((int) voisin.get("score") > minNode.score + (int) voisin.get("time")) {
                            int newScore = minNode.score + (int) voisin.get("time");
                            voisin.put("score", newScore);
                            System.out.println("voisin with new score =>"+ voisin);
                            this.setNeighbor((int) voisin.get("id"), newScore, minNode.index);
                        }
                    }
                }
            }
        }

        System.out.println("********* find path ***************");

        boolean isReachable = false;
        for(Node node : this.nodesDone) {
            if(node.index == id_arrive) {
                isReachable = true;
                break;
            }
        }

        List<Node> paths = new ArrayList<>();
        if(isReachable) {
            int tempId = id_arrive;
            //int temp_parent_id = id_arrive;
            System.out.println("this node done"+ this.nodesDone);

            while(tempId != id_depart) {
                
                for(Node node : this.nodesDone) {
                    if(node.index == tempId) {
                        paths.add(node);
                        tempId = node.parentId;
                       // temp_parent_id = node.parentId;
                        break;
                    }
                }
            }
            
        } else {
            return new ArrayList<>();
        }

        return paths;
    }

    public boolean isChecked(int id) {
        for (Node node : this.nodesDone) {
            if (node.index == id) {
                return true;
            }
        }
        return false;
    }

    public void setNeighbor(int id, int newScore, int parentId) {
        for (Node node : this.nodes) {
            if (node.index == id) {
                node.score = newScore;
                node.parentId = parentId;
            }
        }
    }

    public Node findMinNodeScore() {
        Node minNode = this.nodes.get(0);
        for (Node node : this.nodes) {
            if (node.score < minNode.score) {
                minNode = node;
            }
        }
        return minNode;
    }

    public void generateGraphe() {

        // initialization
        List<Integer> allBusStop = busService.getAllBustStop();
        for (int id_depart : allBusStop) {
            List<Trajet> neighbors = busService.getNeighbor(id_depart);
            // System.out.println("neighbors "+ neighbors);

            List<Map<String, Object>> voisins = new ArrayList<Map<String, Object>>();

            for (Trajet neighbor : neighbors) {
                // if(id_depart == 3) {
                // System.out.println("voisin => "+neighbor.getId_arret_arrive());
                // }
                Map<String, Object> tempMap = new HashMap<String, Object>();

                tempMap.put("id", neighbor.getId_arret_arrive());
                tempMap.put("time", neighbor.getDuree_creuse());
                tempMap.put("score", 50000000);
                voisins.add(tempMap);
            }
            if (id_depart == 3) {
                System.out.println("voisin => " + voisins);
            }
            Node tempNode = new Node(id_depart, 50000000, voisins);
            tempNode.parentId = -100;
            this.nodes.add(tempNode);
        }

    }

    public boolean isInListNode(Map<String, Object> node) {
        for (Node tempNode : this.nodes) {
            if (tempNode.index == (int) node.get("id")) {
                return true;
            }
        }
        return false;
    }
}
