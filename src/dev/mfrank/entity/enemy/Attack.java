package dev.mfrank.entity.enemy;

public class Attack {

    private String name;
    private int damageMin;
    private int damageMax;
    private int piercing; // chance to pierce player armor
    private int precision; // chance to hit

    public Attack (String name, int damageMin, int damageMax, int piercing, int precision) {
        this.name = name;
        this.damageMin = damageMin;
        this.damageMax = damageMax;
        this.piercing = piercing;
        this.precision = precision;
    }


    // GETTERS //
    public String getName() {
        return name;
    }

    public int getDamageMin() {
        return damageMin;
    }

    public int getDamageMax() {
        return damageMax;
    }

    public int getPiercing() {
        return piercing;
    }

    public int getPrecision() {
        return precision;
    }
}
