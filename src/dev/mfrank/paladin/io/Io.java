package dev.mfrank.paladin.io;

import dev.mfrank.Main;

import java.util.Locale;
import java.util.Scanner;

public class Io {

    private final static Scanner console = new Scanner(System.in);
    private final static String versionNumber = "Alpha 1.0";
    private static int indentLevel = 0;
    private final static String indent = "    ";
    private final static String tellPrefix = "[-] ";
    private final static String inPrefix = ">>> ";
    private final static int width = 76;
    private static boolean pauseEnabled = true;


    public static void printVersion () {
        System.out.println("Current version: " + versionNumber);
    }

    public static void lineBreak () {
        System.out.println();
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

    public static void printList(String[] list) {
        for (String s : list) {
            Io.tellRaw("| " + s);
        }
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

    public static String prompt(String msg) {
        System.out.print(msg + " " + inPrefix);
        return console.nextLine().toLowerCase();
    }

    public static String prompt () {
        System.out.println(inPrefix);
        return console.nextLine().toLowerCase();
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
        if (pauseEnabled) {
            Thread.sleep(750);
        }
    }

    public static void longPause () throws InterruptedException {
        if (pauseEnabled) {
            Thread.sleep(1250);
        }
    }


    // getters and setters
    public static void setIndent(int indentLevel) {
        Io.indentLevel = indentLevel;
    }

    public static void setPauseEnabled(boolean pauseEnabled) {
        Io.pauseEnabled = pauseEnabled;
    }

    public static int getWidth() {
        return width;
    }
}