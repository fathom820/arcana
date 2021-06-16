package dev.mfrank.paladin.context;

import dev.mfrank.Main;
import dev.mfrank.entity.enemy.Enemy;
import dev.mfrank.paladin.io.Debug;
import dev.mfrank.paladin.io.Io;
import dev.mfrank.spell.Spell;

import java.io.IOException;
import java.util.Arrays;

public class ContextBattle extends Context {

    private Command attack1, attack2, attack3, spells, inventory, reset;

    private Enemy enemy;
    private boolean playerTurn;
    private boolean playerAttacked;


    public ContextBattle(Enemy enemy) {

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

        reset = new Command (
            "reset",
            "r",
            "Resets the current mage to the beginning of the game."
        );


        super.addCommand(attack1);
        super.addCommand(attack2);
        super.addCommand(attack3);
        super.addCommand(spells);
        super.addCommand(reset);

        this.enemy = enemy;
        playerTurn = false;
        playerAttacked = false;

    }


    public boolean interpret(String cmd) throws IOException, InterruptedException {
        this.playerAttacked = false;

        if (!super.interpret(cmd)) {

            switch(cmd) {
                default:
                    tellInvalidCmd();
                    return false;

                case "attack1":
                    Debug.tell("Player attempted atk1");
                    playerAttacked = Main.getPlayer().castSpell(Main.getPlayer().getHotbar()[0], enemy);
                    playerTurn = !playerAttacked;
                break;

                case "attack2":
                    Debug.tell("Player attempted atk2");
                    playerAttacked = Main.getPlayer().castSpell(Main.getPlayer().getHotbar()[1], enemy);;
                    playerTurn = !playerAttacked;
                break;

                case "attack3":
                    Debug.tell("Player attempted atk3");
                    playerAttacked = Main.getPlayer().castSpell(Main.getPlayer().getHotbar()[2], enemy);;
                    playerTurn = !playerAttacked;
                break;

                case "spells":
                    Io.printSmallDivider();
                    Io.setIndent(0);
                    Io.tellRaw("Your spells:\n");
                    Debug.tell(Arrays.toString(Main.getPlayer().getSpells()));

                    for (Spell s : Main.getPlayer().getSpells()) {
                        if (s != null) {
                            Io.tellRaw(s.getName().toUpperCase());
                            Io.tellRaw(s.getDescription());
                            Io.setIndent(1);
                            Io.tellRaw("Minimum damage: " + s.getDamageMin());
                            Io.tellRaw("Maximum damage: " + s.getDamageMax());
                            Io.tellRaw("Piercing chance: " + s.getPiercing() + "%");
                            Io.tellRaw("Precision: " + s.getPrecision() + "%\n");
                            Io.setIndent(0);
                        }
                    }
                    Io.printSmallDivider();
                    playerAttacked = false;
                break;

                case "reset":
                    Debug.tell("Resetting save file");
                    Io.tell("Nothing here yet.");
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
        Io.tellRaw("Health: " + Main.getPlayer().getHealth() + "/" + Main.getPlayer().getMaxHealth());
        Io.tellRaw("Armor: " + Main.getPlayer().getArmor() + "/" + Main.getPlayer().getMaxArmor());
        Io.tellRaw("Mana: " + Main.getPlayer().getMana() + "/" + Main.getPlayer().getMaxMana());
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
        enemy.setAlive(true);
        Io.printDivider();
        Io.setIndent(0);
        Io.tell(enemy.getName() + " has appeared!");

        while(enemy.getAlive() && Main.getPlayer().getAlive()) {

            if (playerTurn) {
                interpret(Io.in());
                Io.pause();

                if (playerAttacked) {
                    printEnemyStatus();
                }

            } else {
                Io.pause();
                enemy.attack(Main.getPlayer());
                Io.longPause();

                printPlayerStatus();

                playerTurn = true;
            }
        }

        if (!enemy.getAlive()) {
            Io.tell(enemy.getName() + " has been defeated!");
        }
    }
}
