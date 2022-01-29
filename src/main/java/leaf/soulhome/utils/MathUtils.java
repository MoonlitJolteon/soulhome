/*
 * File created ~ 24 - 4 - 2021 ~ Leaf
 */

package leaf.soulhome.utils;

import java.util.Random;

public class MathUtils
{
    final static Random rand = new Random();

    public static int randomInt(int min, int max)
    {
        return rand.nextInt((max - min) + 1) + min;
    }

    public static boolean randomBool()
    {
        return rand.nextBoolean();
    }


    public static float clamp01(final float f)
    {
        return Math.max(0.0f, Math.min(1.0f, f));
    }


    public static boolean inTriangle(
            final double x1,
            final double y1,
            final double x2,
            final double y2,
            final double x3,
            final double y3,
            final double x,
            final double y)
    {
        final double ab = (x1 - x) * (y2 - y) - (x2 - x) * (y1 - y);
        final double bc = (x2 - x) * (y3 - y) - (x3 - x) * (y2 - y);
        final double ca = (x3 - x) * (y1 - y) - (x1 - x) * (y3 - y);
        return sign(ab) == sign(bc) && sign(bc) == sign(ca);
    }

    public static int sign(final double n)
    {
        return n > 0 ? 1 : -1;
    }
}
