package com.bus.bus.model;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.Table;

import lombok.Data;

@Data
// @Entity
// @Table(name = "trajet")
public class Trajet {
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // @Column(name = "distance")
    private int distance;

    // @Column(name = "duree_creuse")
    private int duree_creuse;

    // @Column(name = "duree_pointe")
    private int duree_pointe;

    // @Column(name = "id_arret_depart")
    private int id_arret_depart;

    // @Column(name = "id_arret_arrive")
    private int id_arret_arrive;

    public Trajet(int id, int distance, int dc, int dp, int idDep, int idArr) {
        this.id = id;
        this.distance = distance;
        this.duree_creuse = dc;
        this.duree_pointe = dp;
        this.id_arret_depart = idDep;
        this.id_arret_arrive = idArr;
    }



}
