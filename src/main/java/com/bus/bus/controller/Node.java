package com.bus.bus.controller;

import java.util.List;
import java.util.Map;

public class Node {

    public int index; // index ireferencéna @le Node
    public int score; // time andehanana eo @le sommet raha miala eo initial;
    public List<Map<String, Object>> voisins;
    public int parentId;

    public Node(int id, int score, List<Map<String, Object>> v) {
        this.index = id;
        this.score = score;
        this.voisins = v;
    }

    public String toString() {
        return this.index+" "+this.score;
    }
}
