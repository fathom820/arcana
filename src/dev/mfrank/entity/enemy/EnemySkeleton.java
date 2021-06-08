package dev.mfrank.entity.enemy;

public class EnemySkeleton extends Enemy{

    public EnemySkeleton () {
        super.setMaxHealth(20);
        super.setMaxArmor(10);
        super.setHealth(super.getMaxHealth());
        super.setArmor(super.getMaxArmor());
        super.setDamageOut(0);
        super.setName("Skeleton");
        super.setAlive(true);

        super.attacks[0] = new Attack (
            "Bone Throw",
            10,
            18,
            0,
            90
        );

        super.attacks[1] = new Attack (
            "Femur Blade",
            15,
            20,
            10,
            60
        );

        super.attacks[3] = new Attack (
            "Mandible Assault",
            20,
            30,
            25,
            75
        );


    }

}
