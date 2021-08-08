package dev.mfrank.entity.enemy.lvl1;

import dev.mfrank.entity.enemy.Attack;
import dev.mfrank.entity.enemy.Enemy;
import dev.mfrank.item.scroll.ScrollPutridMusk;

public class EnemyZombie extends Enemy {

    public EnemyZombie () {
        setMaxHealth(30);
        setMaxArmor(5);
        setHealth(getMaxArmor());
        setArmor(getMaxArmor());
        setName("Zombie");
        setAlive(true);

        super.attacks[0] = new Attack (
            "Chomp",
            8,
            12,
            15,
            40
        );

        super.attacks[1] = new Attack (
            "Putrify",
            10,
            15,
            5,
            80
        );

        super.attacks[2] = new Attack (
            "Flesh Reave",
            12,
            18,
            30,
            60
        );

        addItemDrop(new ScrollPutridMusk(25));

    }
}
