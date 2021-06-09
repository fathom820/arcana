package dev.mfrank.entity;

import dev.mfrank.paladin.io.Io;

public class Entity {


    // INIT //

    private int maxHealth;
    private int maxArmor;
    private int health;
    private int armor;
    private int damageOut;
    private String name;
    private boolean alive;

    // CONSTR //

    public Entity () {
        setAlive(true);
    }

    // FUNC //

    /*
    Damage calculation function. Any damage done to armor will be halved,
    and if the damage is greater than the armor that is left,
    it will still absorb all of it before breaking.
     */
    public int takeDamage (int damage) {

        if (armor > 0) {
            damage /= 2;
            armor -= damage;

            if (armor <= 0) {
                Io.tell("Your armor has been broken!");
            }

        }
        if (armor < 0) {
            armor = 0;
        }
        if (armor == 0) {
            health -= damage;

            if (health <= 0) {
                die();
            }
        }

        return damage;
    }

    public void die () {
        alive = false;
    }


    // GET //

    public int getMaxHealth() {
        return maxHealth;
    }
    public int getMaxArmor() {
        return maxArmor;
    }
    public int getHealth() {
        return health;
    }
    public int getArmor() {
        return armor;
    }
    public int getDamageOut() {
        return damageOut;
    }
    public String getName() {
        return name;
    }
    public boolean getAlive() {
        return alive;
    }

    // SET //

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
    public void setMaxArmor(int maxArmor) {
        this.maxArmor = maxArmor;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void setArmor(int armor) {
        this.armor = armor;
    }
    public void setDamageOut(int damageOut) {
        this.damageOut = damageOut;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
