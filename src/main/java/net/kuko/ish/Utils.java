package net.kuko.ish;


public class Utils {
    // Source - https://stackoverflow.com/q/70432097
    // Posted by KepaArizzabalaga
    // Retrieved 2026-03-29, License - CC BY-SA 4.0
    public static int fromRGBA(final int red, final int green, final int blue, final int alpha) {
        int argb = (0xFF & alpha) << 24 | (0xFF & red) << 16 | (0xFF & green) << 8 | (0xFF & blue);
        return argb;
    }
}
