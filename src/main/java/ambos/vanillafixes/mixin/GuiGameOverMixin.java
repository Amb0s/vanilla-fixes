package ambos.vanillafixes.mixin;

import net.minecraft.src.GuiGameOver;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.command.ChatColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value = GuiGameOver.class, remap = false)
final class GuiGameOverMixin extends GuiScreen {
    @ModifyArgs(method = "drawScreen", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/src/GuiGameOver;drawCenteredString(Lnet/minecraft/src/FontRenderer;Ljava/lang/String;III)V"),
            require = 0)
    private void changeGameOverMessage(Args args) {
        if (args.get(1) != "Game over!") {
            args.set(1, "Score: " + ChatColor.yellow + this.mc.thePlayer.getScore());
        }
    }
}
