package dev.mfrank.entity.enemy.lvl1;

import dev.mfrank.entity.enemy.Attack;
import dev.mfrank.entity.enemy.Enemy;

public class EnemyZombie extends Enemy {

    public EnemyZombie () {
        setMaxHealth(30);
        setMaxArmor(5);
        setHealth(getMaxArmor());
        setArmor(getMaxArmor());
        setName("Zombie");
        setAlive(true);

        super.attacks[0] = new Attack(
            "Chomp",
            8,
            12,
            15,
            40
        );


    }
}
