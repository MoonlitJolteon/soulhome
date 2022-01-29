/*
 * File created ~ 24 - 4 - 2021 ~ Leaf
 */

package leaf.soulhome.commands.subcommands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;

public class TestCommand extends ModCommand
{

    private static int testSub(CommandContext<CommandSource> context, ServerPlayerEntity player)
    {
        return testSub(context, player.getLevel());
    }

    private static int testSub(CommandContext<CommandSource> context, ServerWorld world)
    {
        CommandSource source = context.getSource();
        source.sendSuccess(new TranslationTextComponent("command.soulhome.test.sub"), true);

        return SINGLE_SUCCESS;
    }

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher)
    {
        return Commands.literal("test")
                .requires(context -> context.hasPermission(2)).then(Commands.literal("sub").executes(context -> testSub(context, context.getSource().getPlayerOrException()))); // end add
    }

    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException
    {
        return SINGLE_SUCCESS;
    }

}