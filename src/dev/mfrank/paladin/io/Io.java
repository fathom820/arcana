package dev.mfrank.paladin.io;

import dev.mfrank.Main;

import java.util.Scanner;

public class Io {

    private final static Scanner console = new Scanner(System.in);
    private final static String versionNumber = "Alpha 1.0";
    private static int indentLevel = 0;
    private final static String indent = "    ";
    private final static String tellPrefix = "[-] ";
    private final static String inPrefix = ">>> ";
    private final static int width = 76;

    // eng
    public static void printWelcome() throws InterruptedException {

        if (Main.enableConsole) {
            Io.delayType(
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
                            "Discover new spells, and combine them to create devastating attacks!\n",
                    1
            );
        }

        else {
            Io.tellRaw("*--------------------------------------------------------------------------*\n" +
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
                    "Discover new spells, and combine them to create devastating attacks!"
            );
        }
        printDivider();

    }


    public static void printVersion () {
        System.out.println("Current version: " + versionNumber);
    }


    public static void printDivider () {
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < width; i++) {
            out.append("=");
        }

        System.out.println(out);
    }

    public static void printSmallDivider () {
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < width; i++) {
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

    public static String wrap (String msg, int ind) {
        char[] chars = msg.toCharArray();
        String out = "";
        int charCount = 0;
        int tempWidth = width - ind * 4;

        for (char c : chars) {
            charCount++;
            out += c;

            if (charCount == tempWidth) {
                out += "\n";

                for (int i = 1; i <= ind; i++) {
                    out += indent;
                }

                charCount = 0;
            }
        }

        return out;
    }

    public static void delayType (String msg, int del) throws InterruptedException {
        char[] chars = msg.toCharArray();

        for (char c : chars) {
            System.out.print(c);

            if (Main.enableConsole) {
                Thread.sleep(del);

            }
        }
    }

    public static void pause() throws InterruptedException {
        Thread.sleep(1000);
    }

    public static String center (String msg) {
        String out = "";
        int len = msg.length();
        // TODO: center text function

        return out;
    }

    // getters and setters
    public static void setIndent(int indentLevel) {
        Io.indentLevel = indentLevel;
    }

    public static int getWidth() {
        return width;
    }
}