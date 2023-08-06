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
        controlList.removeIf(button -> button.id == 5 || button.id == 6 || button.id == 7);
    }

    @Inject(method = "initGui", at = @At("RETURN"), require = 0)
    private void removeButtons(CallbackInfo ci) {
        controlList.removeIf(button -> button.id == 3 || button.id == 8 || button.id == 100);
    }

    @ModifyArgs(method = "drawScreen", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/render/FontRenderer;drawCenteredString(Ljava/lang/String;III)V"),
            require = 0)
    private void removeDownloadingResourcesString(Args args) {
        args.set(0, "");
    }

    /*
    @ModifyConstant(method = "drawScreen", constant = @Constant(stringValue = ""))
    private String removeConstantString(String value) {
        return "";
    }
    */
}