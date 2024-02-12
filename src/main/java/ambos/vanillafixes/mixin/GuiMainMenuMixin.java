package ambos.vanillafixes.mixin;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import ambos.vanillafixes.VanillaFixes;

@Mixin(value = GuiMainMenu.class, remap = false)
final class GuiMainMenuMixin extends GuiScreen {
    @Inject(method = "init", at = @At("RETURN"), require = 0)
    private void removeLinks(CallbackInfo ci) {
        if (VanillaFixes.SIMPLE_MAIN_MENU) {
            controlList.removeIf(button -> button.id == 5 /* Discord */ || button.id == 6 /* Minecraft Forums */ ||
                    button.id == 7 /* Youtube */);
        }
    }

    @Inject(method = "init", at = @At("RETURN"), require = 0)
    private void removeButtons(CallbackInfo ci) {
        if (VanillaFixes.SIMPLE_MAIN_MENU) {
            controlList.removeIf(button -> button.id == 8 /* Languages */);
        }
    }

    @ModifyArgs(method = "drawScreen", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/FontRenderer;drawCenteredString(Ljava/lang/String;III)V", ordinal = 0), require = 0)
    private void removeDownloadingResourcesString(Args args) {
        if (VanillaFixes.SIMPLE_MAIN_MENU) {
            args.set(0, "");
        }
    }
}
