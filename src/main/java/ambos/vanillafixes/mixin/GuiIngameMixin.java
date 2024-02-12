package ambos.vanillafixes.mixin;

import net.minecraft.client.gui.GuiIngame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

import ambos.vanillafixes.VanillaFixes;

@Mixin(value = GuiIngame.class, remap = false)
final class GuiIngameMixin {
    @ModifyConstant(method = "renderGameOverlay", constant = @Constant(stringValue = "s I: "))
    private String deobfuscateWeatherIntensity(String value) {
        if (VanillaFixes.F3_MENU) {
            return "s, Intensity: ";
        }

        return value;
    }

    @ModifyConstant(method = "renderGameOverlay", constant = @Constant(stringValue = "% P: "))
    private String deobfuscateWeatherPower(String value) {
        if (VanillaFixes.F3_MENU) {
            return "%, Power: ";
        }

        return value;
    }
}
