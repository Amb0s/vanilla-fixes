package ambos.vanillafixes.mixin;

import net.minecraft.src.ThreadCheckHasPaid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ThreadCheckHasPaid.class, remap = false)
final class ThreadCheckHasPaidMixin {
    @Inject(method = "run", at = @At("HEAD"), cancellable = true, require = 0)
    private void onRun(CallbackInfo ci) {
        ci.cancel();
    }
}
