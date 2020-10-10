package ambos.vanillafixes.mixin;

import net.minecraft.class_520;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_520.class)
final class class_520Mixin {
    @Shadow
    private int field_2187;

    @Inject(method = "method_1721", at = @At("RETURN"))
    private void changeHitDelay(CallbackInfo ci) {
        field_2187 = 0;
    }
}
