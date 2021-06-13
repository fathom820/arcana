package dev.mfrank.entity;

import dev.mfrank.paladin.io.Debug;
import dev.mfrank.paladin.io.Io;

import java.util.Random;

public abstract class Entity {


    // INIT //

    private int maxHealth;
    private int maxArmor;
    private int health;
    private int armor;
    private int damageOut;
    private String name;
    private boolean alive;

    private Random rand;

    // CONSTR //

    public Entity () {
        setAlive(true);
        rand = new Random();
    }

    // FUNC //

    /*
    Damage calculation function. Any damage done to armor will be halved,
    and if the damage is greater than the armor that is left,
    it will still absorb all of it before breaking.
     */
    public int takeDamage (int damage) {

        if (armor > 0) {
            Debug.tell("prearmor: " + damage);
            damage /= 2;
            Debug.tell("postarmor: " + damage);

            armor -= damage;

            if (armor <= 0) {
                Io.tell(name +"'s armor has been broken!");
            }
        }

        if (armor < 0) {
            armor = 0;
        }

        if (armor == 0) {
            health -= damage;


        }

        if (health <= 0) {
            health = 0;
            die();
        }

        return damage;
    }

    public void die () {
        alive = false;
    }

    protected int randInt(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("randInt error: max must be greater than min");
        }

        return rand.nextInt((max - min) + 1) + min;
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
