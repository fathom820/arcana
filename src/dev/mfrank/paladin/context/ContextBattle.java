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

    private Command cast, spells, skip, reset;

    private final Enemy enemy;
    private Mage player = Main.getPlayer();

    private boolean playerTurn;
    private boolean playerAttacked;

    private int skipCount = 0;


    public ContextBattle(Enemy enemy) {

        this.enemy = enemy;
        cast = new Command (
                "cast",
                "c",
                "Cast a spell of your choice."
        );

        spells = new Command (
            "spells",
            "sp",
            "View a list of all your unlocked spells."
        );

        skip = new Command (
            "skip",
            "sk",
            "Skip your turn and regenerate mana in the process."
        );

        reset = new Command (
            "reset",
            "r",
            "Resets the current mage to the beginning of the game."
        );

        super.addCommand(cast);
        super.addCommand(spells);
        super.addCommand(skip);
        super.addCommand(reset);

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
                    skipCount = 0;
                    Mage player = Main.getPlayer();
                    Spell toCast = EngineSpell.getById(Io.prompt("Cast spell"));

                    playerAttacked = player.castSpell(toCast, enemy);
                    playerTurn = !playerAttacked;
                break;

                case "spells":
                    skipCount = 0;
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
                    playerTurn = true;
                break;

                /*
                    In the case of a player skipping their turn, they will regenerate mana.
                    Depending on how many times in a row they've skipped a turn, they will
                    regenerate more exponentially. This is what the variable skipCount is used for.
                    The amount regenerated per turn will double for every skipped turn, up to 4 times.
                 */
                case "skip":
                    skipCount++;
                    Io.tell("You skipped your turn.");

                    Main.getPlayer().setMana((int) (Main.getPlayer().getMana()  + 5 * (Math.pow(2.0, skipCount))));

                    Io.pause();
                    Io.tell("You regenerated " + (int)(Math.pow(2.0, skipCount)) + " mana.");

                    playerAttacked = false;
                    playerTurn = false;
                break;

                case "reset":
                    skipCount = 0;
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
