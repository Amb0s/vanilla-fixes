package turniplabs.vanillafixes.mixin;

import net.minecraft.core.item.material.ToolMaterial;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ToolMaterial.class, remap = false)
final class ToolMaterialMixin {
    @Shadow
    private int blockHitDelay;

    @Inject(method = "<init>", at = @At("RETURN"), require = 0)
    private void changeBlockHitDelay(CallbackInfo ci) {
        this.blockHitDelay = 0;
    }
}
