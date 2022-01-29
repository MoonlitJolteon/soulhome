/*
 * File created ~ 24 - 4 - 2021 ~ Leaf
 */

package leaf.soulhome.utils;

import leaf.soulhome.SoulHome;
import net.minecraft.util.ResourceLocation;

import java.util.Locale;

public class ResourceLocationHelper
{
    public static ResourceLocation prefix(String path)
    {
        return new ResourceLocation(SoulHome.MODID, path.toLowerCase(Locale.ROOT));
    }
}
