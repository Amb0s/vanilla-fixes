package turniplabs.vanillafixes.mixin;

import net.minecraft.core.Global;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = Global.class, remap = false)
final class GlobalMixin {
    @Shadow
    public static final String VERSION;

    static {
        VERSION = "1.7.7.0_02";
    }
}
