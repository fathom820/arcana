package dev.mfrank.paladin;

import java.util.Scanner;

public class Io {

    private final static Scanner console = new Scanner(System.in);

    private final static String versionNumber = "Alpha 1.0";

    public static void printWelcome() {

        System.out.print(
            "   ▄████████    ▄████████  ▄████████    ▄████████ ███▄▄▄▄      ▄████████ \n" +
            "  ███    ███   ███    ███ ███    ███   ███    ███ ███▀▀▀██▄   ███    ███ \n" +
            "  ███    ███   ███    ███ ███    █▀    ███    ███ ███   ███   ███    ███ \n" +
            "  ███    ███  ▄███▄▄▄▄██▀ ███          ███    ███ ███   ███   ███    ███ \n" +
            "▀███████████ ▀▀███▀▀▀▀▀   ███        ▀███████████ ███   ███ ▀███████████ \n" +
            "  ███    ███ ▀███████████ ███    █▄    ███    ███ ███   ███   ███    ███ \n" +
            "  ███    ███   ███    ███ ███    ███   ███    ███ ███   ███   ███    ███ \n" +
            "  ███    █▀    ███    ███ ████████▀    ███    █▀   ▀█   █▀    ███    █▀  \n" +

            "Welcome to Arcana, a spellbound game of mystery and intrigue!\n" +
            "The land of Arcana has been overrun by demonic invaders...\n" +
            "You are a novice mage, seeking new spells to learn and enemies to conquer.\n" +
            "Discover new spells, and combine them to create devastating attacks!\n"
        );
    }

    public static void printVersion () {
        System.out.println("Current version: " + versionNumber);
    }

    public static void printDivider (int length) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < length; i++) {
            out.append("-");
        }
        System.out.println(out);
    }

    public static void tellRaw (String msg) {
        System.out.println(msg);
    }

    public static String in () {
        return console.nextLine();
    }
}
