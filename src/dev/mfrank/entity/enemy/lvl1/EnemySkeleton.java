package dev.mfrank.entity.enemy.lvl1;

import dev.mfrank.entity.enemy.Attack;
import dev.mfrank.entity.enemy.Enemy;

public class EnemySkeleton extends Enemy {

    public EnemySkeleton () {
        super.setMaxHealth(20);
        super.setMaxArmor(10);
        super.setHealth(super.getMaxHealth());
        super.setArmor(super.getMaxArmor());
        super.setDamageOut(0);
        super.setName("Skeleton");
        super.setAlive(true);

        super.attacks[0] = new Attack(
            "Bone Throw",
            5,
            8,
            0,
            90
        );

        super.attacks[1] = new Attack (
            "Femur Blade",
            15,
            20,
            10,
            35
        );

        super.attacks[2] = new Attack (
            "Mandible Assault",
            10,
            15,
            50,
            50
        );


    }

}
