package ambos.vanillafixes.mixin;

import net.minecraft.client.RemoteClientInteractionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RemoteClientInteractionManager.class)
final class RemoteClientInteractionManagerMixin {
    @Shadow
    private int hitDelay;

    @Inject(method = "method_1721", at = @At("RETURN"), require = 0)
    private void changeHitDelay(CallbackInfo ci) {
        hitDelay = 0;
    }
}
