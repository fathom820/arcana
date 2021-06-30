package dev.mfrank.paladin.context;

import dev.mfrank.Main;
import dev.mfrank.engine.EngineSpell;
import dev.mfrank.entity.Mage;
import dev.mfrank.entity.enemy.Enemy;
import dev.mfrank.paladin.io.Debug;
import dev.mfrank.paladin.io.Io;
import dev.mfrank.spell.Spell;

import java.io.IOException;
import java.util.Arrays;

public class ContextBattle extends Context {

    private Command cast, attack1, attack2, attack3, spells, hotbar, reset;

    private Enemy enemy;
    private boolean playerTurn;
    private boolean playerAttacked;


    public ContextBattle(Enemy enemy) {

        cast = new Command (
                "cast",
                "c",
                "Cast a spell of your choice."
        );

        attack1 = new Command (
            "attack1",
            "atk1",
            "Fire off the first spell in your hotbar."
        );

        attack2 = new Command (
            "attack2",
            "atk2",
            "Fire off the second spell in your hotbar."
        );

        attack3 = new Command (
            "attack3",
            "atk3",
            "Fire off the third spell in your hotbar."
        );

        spells = new Command (
            "spells",
            "sp",
            "View a list of all your unlocked spells."
        );

        hotbar = new Command (
                "hotbar",
                "hb",
                "View and change the spells in your hotbar."
        );

        reset = new Command (
            "reset",
            "r",
            "Resets the current mage to the beginning of the game."
        );

        super.addCommand(cast);
        super.addCommand(spells);
        super.addCommand(hotbar);
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

                case "cast":
                    Mage player = Main.getPlayer();
                    Spell toCast = EngineSpell.getById(Io.prompt("Cast spell"));
                    boolean success = false;

                    playerAttacked = player.castSpell(toCast, enemy);
                    playerTurn = !playerAttacked;
                break;

                case "attack1":
                    /*
                    Debug.tell("Player attempted atk1");
                    playerAttacked = Main.getPlayer().castSpell(Main.getPlayer().getHotbar()[0], enemy);
                    playerTurn = !playerAttacked; */

                case "attack2":
                    /*
                    Debug.tell("Player attempted atk2");
                    playerAttacked = Main.getPlayer().castSpell(Main.getPlayer().getHotbar()[1], enemy);;
                    playerTurn = !playerAttacked; */

                case "attack3":
                    Io.tell("* DEPRECATED *");
                    /*
                    Debug.tell("Player attempted atk3");
                    playerAttacked = Main.getPlayer().castSpell(Main.getPlayer().getHotbar()[2], enemy);;
                    playerTurn = !playerAttacked; */
                break;

                case "spells":
                    Io.printSmallDivider();
                    Io.setIndent(0);
                    Io.tellRaw("Your spells:\n");
                    Debug.tell(Arrays.toString(Main.getPlayer().getSpells()));

                    for (Spell s : Main.getPlayer().getSpells()) {
                        if (s != null) {
                            s.printInfo();
                        }
                        Io.lineBreak();
                    }
                    Io.printSmallDivider();
                    playerAttacked = false;
                break;

                case "hotbar":
                    Io.setIndent(0);
                    Io.printSmallDivider();
                    Io.tellRaw("HOTBAR:");
                    Io.lineBreak();

                    int slot = 1;
                    for (Spell s : Main.getPlayer().getHotbar()) {
                        Io.tellRaw("SLOT " + slot);
                        if (s != null) {
                            s.printInfo();
                        } else {
                            Io.tellRaw("[Empty]");
                        }
                        Io.lineBreak();
                        slot++;
                    }

                    Io.printSmallDivider();

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
        Io.setIndent(0);
        Io.tell("When you're ready, type \"next\" to proceed to the next room.");
        while (!Io.prompt().equals("next"));

        enemy.setAlive(true);
        Io.printDivider();
        Io.setIndent(0);
        Io.tell(enemy.getName() + " has appeared!");

        while(enemy.getAlive() && Main.getPlayer().getAlive()) {

            if (playerTurn) {
                interpret(Io.in());
                Io.pause();

                if (playerAttacked && enemy.getAlive()) {
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
