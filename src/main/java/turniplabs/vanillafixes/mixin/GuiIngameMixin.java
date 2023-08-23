package turniplabs.vanillafixes.mixin;

import net.minecraft.client.gui.GuiIngame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(value = GuiIngame.class, remap = false)
final class GuiIngameMixin {
    @ModifyConstant(method = "renderGameOverlay", constant = @Constant(stringValue = "s I: "))
    private String deobfuscateWeatherIntensity(String value) {
        return "s, Intensity: ";
    }

    @ModifyConstant(method = "renderGameOverlay", constant = @Constant(stringValue = "% P: "))
    private String deobfuscateWeatherPower(String value) {
        return "%, Power: ";
    }
}
