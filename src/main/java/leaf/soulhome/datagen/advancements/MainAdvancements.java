/*
 * File created ~ 13 - 7 - 2021 ~ Leaf
 */

package leaf.soulhome.datagen.advancements;

import leaf.soulhome.SoulHome;
import leaf.soulhome.registry.ItemsRegistry;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.*;
import net.minecraft.commands.CommandFunction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.MutableComponent;

import java.util.function.Consumer;

public class MainAdvancements implements Consumer<Consumer<Advancement>>
{
    public MainAdvancements()
    {
    }

    public void accept(Consumer<Advancement> advancementConsumer)
    {
        final String tabName = "main";

        final String titleFormat = "advancements.soulhome.%s.title";
        final String descriptionFormat = "advancements.soulhome.%s.description";
        final String achievementPathFormat = "soulhome:%s/%s";

        Advancement root = Advancement.Builder.advancement()
                .display(ItemsRegistry.SOUL_KEY.get(),
                        Component.translatable(String.format(titleFormat, tabName)),
                        Component.translatable(String.format(descriptionFormat, tabName)),
                        new ResourceLocation("textures/gui/advancements/backgrounds/stone.png"),
                        FrameType.TASK,
                        false,//showToast
                        false,//announceChat
                        false)//hidden
                .addCriterion("tick", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.ANY))
                .save(advancementConsumer, String.format(achievementPathFormat, tabName, "root"));


        final String obtainedSoulKey = "obtained_soul_key";
        Advancement advancement1 = Advancement.Builder.advancement()
                .parent(root)
                .display(
                        ItemsRegistry.GUIDE.get(),
                        Component.translatable(String.format(titleFormat, obtainedSoulKey)),
                        Component.translatable(String.format(descriptionFormat, obtainedSoulKey)),
                        (ResourceLocation)null,
                        FrameType.TASK,
                        true, //showToast
                        true, //announce
                        false)//hidden
                .addCriterion(
                        "has_item",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ItemsRegistry.SOUL_KEY.get()))
                .rewards(new AdvancementRewards(50, new ResourceLocation[]{new ResourceLocation("soulhome:guide")}, new ResourceLocation[0], CommandFunction.CacheableFunction.NONE))
                .save(advancementConsumer, String.format(achievementPathFormat, tabName, obtainedSoulKey));



        final String obtainedGuide = "obtained_guide";
        Advancement advancement2 = Advancement.Builder.advancement()
                .parent(advancement1)
                .display(
                        ItemsRegistry.GUIDE.get(),
                        Component.translatable(String.format(titleFormat, obtainedGuide)),
                        Component.translatable(String.format(descriptionFormat, obtainedGuide)),
                        (ResourceLocation)null,
                        FrameType.TASK,
                        true, //showToast
                        true, //announce
                        false)//hidden
                .addCriterion(
                        "has_item",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ItemsRegistry.GUIDE.get()))
                .rewards(new AdvancementRewards(5, new ResourceLocation[0], new ResourceLocation[0], CommandFunction.CacheableFunction.NONE))
                .save(advancementConsumer, String.format(achievementPathFormat, tabName, obtainedGuide));


        final String enteredSoulDimension = "entered_soul_dimension";
        Advancement advancement3 = Advancement.Builder.advancement()
                .parent(advancement1)
                .display(
                        ItemsRegistry.GUIDE.get(),
                        Component.translatable(String.format(titleFormat, enteredSoulDimension)),
                        Component.translatable(String.format(descriptionFormat, enteredSoulDimension)),
                        (ResourceLocation)null,
                        FrameType.TASK,
                        true, //showToast
                        true, //announce
                        false)//hidden
                .addCriterion("entered_soul", PlayerTrigger.TriggerInstance.located(LocationPredicate.inBiome(ResourceKey.create(Registry.BIOME_REGISTRY, SoulHome.SOULHOME_LOC))))
                .rewards(new AdvancementRewards(5, new ResourceLocation[0], new ResourceLocation[0], CommandFunction.CacheableFunction.NONE))
                .save(advancementConsumer, String.format(achievementPathFormat, tabName, enteredSoulDimension));

        final String blank = "blank";
        Advancement advancement4 = Advancement.Builder.advancement()
                .parent(advancement2)
                .display(
                        ItemsRegistry.GUIDE.get(),
                        Component.translatable(String.format(titleFormat, blank)),
                        Component.translatable(String.format(descriptionFormat, blank)),
                        (ResourceLocation)null,
                        FrameType.TASK,
                        true, //showToast
                        true, //announce
                        true)//hidden
                .addCriterion("impossible", new ImpossibleTrigger.TriggerInstance())
                .rewards(new AdvancementRewards(5, new ResourceLocation[0], new ResourceLocation[0], CommandFunction.CacheableFunction.NONE))
                .save(advancementConsumer, String.format(achievementPathFormat, tabName, blank));


    }
}