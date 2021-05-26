package dev.mfrank.utils;

public class Text {

    private final static String versionNumber = "Alpha 1.0";

    public static void printWelcome() {

        System.out.print(
            "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n" +
            "┃   ▄████████    ▄████████  ▄████████    ▄████████ ███▄▄▄▄      ▄████████ ┃\n" +
            "┃  ███    ███   ███    ███ ███    ███   ███    ███ ███▀▀▀██▄   ███    ███ ┃\n" +
            "┃  ███    ███   ███    ███ ███    █▀    ███    ███ ███   ███   ███    ███ ┃\n" +
            "┃  ███    ███  ▄███▄▄▄▄██▀ ███          ███    ███ ███   ███   ███    ███ ┃\n" +
            "┃▀███████████ ▀▀███▀▀▀▀▀   ███        ▀███████████ ███   ███ ▀███████████ ┃\n" +
            "┃  ███    ███ ▀███████████ ███    █▄    ███    ███ ███   ███   ███    ███ ┃\n" +
            "┃  ███    ███   ███    ███ ███    ███   ███    ███ ███   ███   ███    ███ ┃\n" +
            "┃  ███    █▀    ███    ███ ████████▀    ███    █▀   ▀█   █▀    ███    █▀  ┃\n" +
            "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n\n" +

            "Welcome to Arcana, a spellbound game of mystery and intrigue!\n" +
            "The land of Arcana has been overrun by demonic invaders...\n" +
            "You are a novice mage, seeking new spells to learn and enemies to conquer.\n" +
            "Discover new spells, and combine them to create devastating attacks!\n"
        );
    }

    public static void printVersion () {
        System.out.println("Current version: " + versionNumber);
    }


}
