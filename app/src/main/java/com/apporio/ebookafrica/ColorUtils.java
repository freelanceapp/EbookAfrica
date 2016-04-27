package com.apporio.ebookafrica;

/**
 * Created by spinnosolutions on 4/27/16.
 */
public class ColorUtils {

    private ColorUtils() {}

    public static int applyAlpha(int color, int alpha) {
        return (color & 0x00FFFFFF) | (alpha << 24);
    }
}
