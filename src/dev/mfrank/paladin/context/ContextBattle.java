package dev.mfrank.paladin.context;

import dev.mfrank.Main;
import dev.mfrank.entity.enemy.Enemy;
import dev.mfrank.paladin.Command;
import dev.mfrank.paladin.Paladin;
import dev.mfrank.paladin.io.Debug;
import dev.mfrank.paladin.io.Io;
import dev.mfrank.spell.Spell;

import java.io.IOException;

public class ContextBattle extends Context {

    private static Command attack1;
    private static Command attack2;
    private static Command attack3;
    private static Command spells;
    private static Enemy enemy;
    private static boolean playerTurn;
    private static boolean playerAttacked;


    public ContextBattle(Enemy enemy) {
        super.init();
        super.setDisabledCommands(new String[] {
            "credits",
            "version",
            "save",
        });
        attack1 = new Command (
            "attack1",
            "atk1",
            "Fire off the first spell in your quick-attack scroll."
            );

        attack2 = new Command (
            "attack2",
            "atk2",
            "Fire off the second spell in your quick-attack scroll."
        );

        attack3 = new Command (
            "attack3",
            "atk3",
            "Fire off the third spell in your quick-attack scroll."
        );

        spells = new Command (
            "spells",
            "sp",
            "Bring up all of your unlocked spells and change what is stored\n        in your quick-attack scroll."
        );

        super.addCommand(attack1);
        super.addCommand(attack2);
        super.addCommand(attack3);
        super.addCommand(spells);

        ContextBattle.enemy = enemy;
        playerTurn = false;
        playerAttacked = false;

    }


    public static boolean interpret(String cmd) throws IOException, InterruptedException {

        if (!Context.interpret(cmd)) {

            switch(cmd) {
                default:
                    Paladin.tellInvalidCmd();
                    return false;

                case "attack1":
                    Debug.tell("Player attempted atk1");
                    playerAttacked = Main.player.castSpell(Main.player.getScroll()[0], enemy);
                    playerTurn = !playerAttacked;
                break;

                case "attack2":
                    Debug.tell("Player attempted atk2");
                    playerAttacked = Main.player.castSpell(Main.player.getScroll()[1], enemy);;
                    playerTurn = !playerAttacked;
                break;

                case "attack3":
                    Debug.tell("Player attempted atk3");
                    playerAttacked = Main.player.castSpell(Main.player.getScroll()[2], enemy);;
                    playerTurn = !playerAttacked;
                break;

                case "spells":
                    Io.printSmallDivider();
                    Io.setIndent(0);
                    Io.tellRaw("Your spells:");

                    for (Spell s : Main.player.getSpells()) {
                        if (s != null) {
                            Io.tell(s.getName().toUpperCase());
                            Io.setIndent(1);
                            Io.tellRaw("Minimum damage: " + s.getDamageMin());
                            Io.tellRaw("Maximum damage: " + s.getDamageMax());
                            Io.tellRaw("Piercing chance: " + s.getPiercing() + "%");
                            Io.tellRaw("Precision: " + s.getPrecision() + "%");
                            Io.setIndent(0);
                        }
                    }
                    Io.printSmallDivider();
                break;
            }
        }
        return false;
    }

    public void printPlayerStatus () throws InterruptedException {
        Io.printSmallDivider();
        Io.setIndent(0);
        Io.tellRaw("PLAYER STATUS:");
        Io.setIndent(1);
        Io.tellRaw("Health: " + Main.player.getHealth() + "/" + Main.player.getMaxHealth());
        Io.tellRaw("Armor: " + Main.player.getArmor() + "/" + Main.player.getMaxArmor());
        Io.tellRaw("Mana: " + Main.player.getMana() + "/" + Main.player.getMaxMana());
        Io.setIndent(0);
        Io.printSmallDivider();
        Io.pause();
    }

    public void printEnemyStatus () throws InterruptedException {
        Io.printSmallDivider();
        Io.setIndent(0);
        Io.tellRaw("ENEMY STATUS:");
        Io.setIndent(1);
        Io.tellRaw("Health: " + enemy.getHealth() + "/" + enemy.getMaxHealth());
        Io.tellRaw("Armor: " + enemy.getArmor() + "/" + enemy.getMaxArmor());
        Io.setIndent(0);
        Io.printSmallDivider();
        Io.pause();
    }

    public void run () throws IOException, InterruptedException {
        Io.printDivider();
        Io.setIndent(0);
        Io.tell(enemy.getName() + " has appeared!");

        while(enemy.getAlive() && Main.player.getAlive()) {

            if (playerTurn) {
                ContextBattle.interpret(Io.in());
                Io.pause();

                if (playerAttacked) {
                    printEnemyStatus();
                }

            } else {
                enemy.attack(Main.player);
                Io.pause();

                printPlayerStatus();

                playerTurn = true;
            }
        }
    }
}
