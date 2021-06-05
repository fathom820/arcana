package dev.mfrank.paladin.io;

public abstract class Debug {

    private static boolean debug = false;                       // whether or not to show debug messages
    private static final String tellPrefix = "[::] ";           // prefix for debug messages
    private static final String msgPrefix = "[-] ";           // prefix for debug warnings


    public static void setState (boolean dbg) {
        debug = dbg;                                    // toggle debug messages on or off
    }

    public static boolean getState() {
        return debug;
    }


    // If debug messages are enabled, send a message
    public static void tell (String msg) {
        if (debug)
            System.out.println(tellPrefix + msg);
    }


    // Force a debug message, even if messages are disabled


}
