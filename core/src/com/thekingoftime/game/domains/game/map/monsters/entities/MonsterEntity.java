package com.thekingoftime.game.domains.game.map.monsters.entities;

public class MonsterEntity {

    public String name;
    public float patrolAreaX;
    public float patrolAreaY;
    public float retreatDistance;
    public float attackDistance;
    public float attackTime;
    public float viewDistancePlayer;
    public float idleTime; // Duração do estado parado
    public float walkTime; // Duração do estado de movimento

    public MonsterEntity createGenericMonster() {
        name = "Globin";
        patrolAreaX = 1000;
        patrolAreaY = 1000;
        retreatDistance = 10f;
        attackDistance = 50f;
        attackTime = 2f;
        viewDistancePlayer = 200f;
        idleTime = 2f; // Ficar parado por 2 segundos
        walkTime = 3f; // Andar por 3 segundos

        return this;
    }

}
