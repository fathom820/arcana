package com.arcana.config;

public abstract class Debug {

    private static boolean debug = false;                       // whether or not to show debug messages
    private static final String tellPrefix = "[::] ";           // prefix for debug messages
    private static final String warnPrefix = "[!!] ";           // prefix for debug warnings


    public static void toggle() {
        debug = !debug;                                         // toggle debug messages on or off
    }

    public static boolean getStatus() {
        return debug;
    }

    public static void tell (String msg) {
        if (debug)
            System.out.println(tellPrefix + msg);
    }

    public static void warn (String msg) {
        System.out.println(warnPrefix + msg);
    }


}
