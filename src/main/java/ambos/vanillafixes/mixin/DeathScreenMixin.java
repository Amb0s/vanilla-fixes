package ambos.vanillafixes.mixin;

import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.screen.DeathScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(DeathScreen.class)
final class DeathScreenMixin extends Screen {
    @ModifyArgs(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/DeathScreen;drawTextWithShadowCentred(Lnet/minecraft/client/render/TextRenderer;Ljava/lang/String;III)V"))
    public void changeDeathMessage(Args args) {
        if (args.get(1) != "Game over!") {
            args.set(1, "Score: §e" + minecraft.player.method_481());
        }
    }
}
