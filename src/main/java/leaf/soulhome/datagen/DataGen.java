/*
 * File created ~ 24 - 4 - 2021 ~ Leaf
 * Special Thank you to ChampionAsh5357 from the forge project discord!
 * They provided a series of tutorials with examples of how to add new sections of data generation
 * Generating 20+ different metal related blocks, items, curios etc would have been a nightmare without it.
 */

package leaf.soulhome.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import leaf.soulhome.SoulHome;
import leaf.soulhome.datagen.advancements.AdvancementGen;
import leaf.soulhome.datagen.blocks.BlockModelsGen;
import leaf.soulhome.datagen.items.ItemModelsGen;
import leaf.soulhome.datagen.language.EngLangGen;
import leaf.soulhome.datagen.loottables.LootTableGen;
import leaf.soulhome.datagen.patchouli.PatchouliGen;
import leaf.soulhome.datagen.recipe.RecipeGen;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = SoulHome.MODID, bus = Bus.MOD)
public class DataGen
{

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @SubscribeEvent
    public static void onDataGen(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(new EngLangGen(generator));

        if (!event.includeClient())
        {
            return;
        }

        generator.addProvider(new AdvancementGen(generator));
        generator.addProvider(new ItemModelsGen(generator, existingFileHelper));
        generator.addProvider(new BlockModelsGen(generator, existingFileHelper));
        generator.addProvider(new LootTableGen(generator));
        generator.addProvider(new RecipeGen(generator));

        generator.addProvider(new PatchouliGen(generator));

    }

}
