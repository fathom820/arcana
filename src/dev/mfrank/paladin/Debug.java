package dev.mfrank.paladin;

public abstract class Debug {

    private static boolean debug = false;                       // whether or not to show debug messages
    private static final String tellPrefix = "[Debug] ";           // prefix for debug messages
    private static final String msgPrefix = "[Paladin] ";           // prefix for debug warnings


    public static void toggle() {
        debug = !debug;                                         // toggle debug messages on or off
    }

    public static boolean getStatus() {
        return debug;
    }


    // If debug messages are enabled, send a message
    public static void tell (String msg) {
        if (debug)
            System.out.println(tellPrefix + msg);
    }


    // Force a debug message, even if messages are disabled
    public static void msg(String msg) {
        System.out.println(msgPrefix + msg);
    }


}
