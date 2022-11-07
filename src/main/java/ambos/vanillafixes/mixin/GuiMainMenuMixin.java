package ambos.vanillafixes.mixin;

import net.minecraft.src.GuiMainMenu;
import net.minecraft.src.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GuiMainMenu.class, remap = false)
final class GuiMainMenuMixin extends GuiScreen {
    @Inject(method = "initGui", at = @At("RETURN"), require = 0)
    private void removeLinks(CallbackInfo ci) {
        controlList.removeIf(button -> button.id == 5 || button.id == 6 || button.id == 7); // Remove the links
    }
}
