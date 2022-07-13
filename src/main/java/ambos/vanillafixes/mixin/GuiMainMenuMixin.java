package ambos.vanillafixes.mixin;

import net.minecraft.src.GuiMainMenu;
import net.minecraft.src.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GuiMainMenu.class, remap = false)
final class GuiMainMenuMixin extends GuiScreen {
    @Inject(method = "initGui", at = @At("RETURN"), cancellable = true, require = 0)
    private void removeLinks(CallbackInfo ci) {
        for (int i = 1; i <= 3; i++) { // Removes the last 3 buttons (links) from the main menu.
            this.controlList.remove(this.controlList.size() - 1);
        }
    }
}
