package turniplabs.vanillafixes.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.render.EffectRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Minecraft.class, remap = false)
final class MinecraftMixin {
    @Shadow
    public EffectRenderer effectRenderer;

    @Inject(method = "getEntityCountsInfoString", at = @At("HEAD"), require = 0, cancellable = true)
    private void changeFormatting(CallbackInfoReturnable<String> cir) {
        cir.setReturnValue("Particles: " + this.effectRenderer.getNumParticlesString());
    }
}
