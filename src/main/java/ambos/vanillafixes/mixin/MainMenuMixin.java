package ambos.vanillafixes.mixin;

import net.minecraft.client.gui.screen.ScreenBase;
import net.minecraft.client.gui.screen.menu.MainMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MainMenu.class)
final class MainMenuMixin extends ScreenBase {
    @Inject(method = "init", at = @At("HEAD"))
    private void onInit(CallbackInfo ci) {
        minecraft.isApplet = false;
    }
}
