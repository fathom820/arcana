package dev.mfrank.paladin.io;

import java.util.Scanner;

public class Io {

    private final static Scanner console = new Scanner(System.in);
    private final static String versionNumber = "Alpha 1.0";
    private static int indentLevel = 0;
    private final static String indent = "    ";
    private final static String tellPrefix = "[-] ";
    private final static String inPrefix = ">>> ";

    // utils
    public static void printWelcome() {

        System.out.print(
            "*--------------------------------------------------------------------------*\n" +
            "|    ▄████████    ▄████████  ▄████████    ▄████████ ███▄▄▄▄      ▄████████ |\n" +
            "|   ███    ███   ███    ███ ███    ███   ███    ███ ███▀▀▀██▄   ███    ███ |\n" +
            "|   ███    ███   ███    ███ ███    █▀    ███    ███ ███   ███   ███    ███ |\n" +
            "|   ███    ███  ▄███▄▄▄▄██▀ ███          ███    ███ ███   ███   ███    ███ |\n" +
            "| ▀███████████ ▀▀███▀▀▀▀▀   ███        ▀███████████ ███   ███ ▀███████████ |\n" +
            "|   ███    ███ ▀███████████ ███    █▄    ███    ███ ███   ███   ███    ███ |\n" +
            "|   ███    ███   ███    ███ ███    ███   ███    ███ ███   ███   ███    ███ |\n" +
            "|   ███    █▀    ███    ███ ████████▀    ███    █▀   ▀█   █▀    ███    █▀  |\n" +
            "*--------------------------------------------------------------------------*\n" +

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

    public static void tell(String msg) {
        StringBuilder out = new StringBuilder();
        for (int i = 1; i <= indentLevel; i++) {
            out.append(indent);
        }

        System.out.println(tellPrefix + out + msg);
    }

    public static void tellRaw (String msg) {
        StringBuilder out = new StringBuilder();
        for (int i = 1; i <= indentLevel; i++) {
            out.append(indent);
        }
        System.out.println(out + msg);
    }

    public static String in () {
        System.out.print(inPrefix);
        return console.nextLine();
    }

    // getters and setters
    public static void setIndent(int indentLevel) {
        Io.indentLevel = indentLevel;
    }
}