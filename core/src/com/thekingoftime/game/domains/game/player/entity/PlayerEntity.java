package com.thekingoftime.game.domains.game.player.entity;

public class PlayerEntity {
    public String name;
    public VisualEntity visualEntity = new VisualEntity();
    public float playerSpeed = 200;
    public float positionX = 0;
    public float positionY = 0;
    public float width = 0;
    public float height = 0;
    public int level;
    public int experience;
    public int health;
    public int mana;
    public int strength;
    public int dexterity;
    public int intelligence;
    public int vitality;
    public int luck;
    public int defense;
    public int magicDefense;
    public int attack;
    public int magicAttack;
    public int criticalChance;
    public int criticalDamage;
    public int evasion;
    public int accuracy;
    public int attackSpeed;
    public int magicSpeed;
    public int money;

    public PlayerEntity createGenericPlayer() {
        name = "Edward Elric";

        playerSpeed = 200;
        positionX = 0;
        positionY = 0;
        width = 0;
        height = 0;
        level = 1;
        experience = 0;
        health = 100;
        mana = 100;
        strength = 10;
        dexterity = 10;
        intelligence = 10;
        vitality = 10;
        luck = 10;
        defense = 10;
        magicDefense = 10;
        attack = 10;
        magicAttack = 10;
        criticalChance = 10;
        criticalDamage = 10;
        evasion = 10;
        accuracy = 10;
        attackSpeed = 10;
        magicSpeed = 10;
        money = 10;

        return this;
    }

}
