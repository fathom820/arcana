package dev.mfrank.entity.enemy.lvl1;

import dev.mfrank.entity.enemy.Attack;
import dev.mfrank.entity.enemy.Enemy;
import dev.mfrank.item.scroll.ScrollFireball;

public class EnemySkeleton extends Enemy {

    public EnemySkeleton () {
        setMaxHealth(20);
        setMaxArmor(0);
        setHealth(super.getMaxHealth());
        setArmor(super.getMaxArmor());
        setName("Skeleton");
        setAlive(true);

        super.attacks[0] = new Attack(
            "Bone Throw",
            5,
            8,
            0,
            90
        );

        super.attacks[1] = new Attack (
            "Femur Blade",
            8,
            12,
            10,
            70
        );

        super.attacks[2] = new Attack (
            "Mandible Assault",
            10,
            15,
            15,
            20
        );

        super.addItemDrop(new ScrollFireball(75));


    }

}
