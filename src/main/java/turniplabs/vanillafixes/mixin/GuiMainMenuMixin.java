package turniplabs.vanillafixes.mixin;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value = GuiMainMenu.class, remap = false)
final class GuiMainMenuMixin extends GuiScreen {
    @Inject(method = "initGui", at = @At("RETURN"), require = 0)
    private void removeLinks(CallbackInfo ci) {
        controlList.removeIf(button ->
                button.id == 5 /* Discord */ || button.id == 6 /* Minecraft Forums */ ||
                button.id == 7 /* Youtube */);
    }

    @Inject(method = "initGui", at = @At("RETURN"), require = 0)
    private void removeButtons(CallbackInfo ci) {
        controlList.removeIf(button ->
                button.id == 3 /* Texture packs */ || button.id == 8 /* Languages */ ||
                button.id == 100 /* Mod menu */);
    }

    @ModifyArgs(method = "drawScreen", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/render/FontRenderer;drawCenteredString(Ljava/lang/String;III)V",
            ordinal = 0), require = 0)
    private void removeDownloadingResourcesString(Args args) {
        args.set(0, "");
    }
}
