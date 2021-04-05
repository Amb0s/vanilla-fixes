package ambos.vanillafixes.mixin;

import net.minecraft.level.Level;
import net.minecraft.block.Fence;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Fence.class)
final class FenceMixin {
    @Inject(method = "canPlaceAt", at = @At("RETURN"), cancellable = true, require = 0)
    private void onCanPlaceAt(Level level, int x, int y, int z, CallbackInfoReturnable cir) {
        cir.setReturnValue(true);
    }
}
