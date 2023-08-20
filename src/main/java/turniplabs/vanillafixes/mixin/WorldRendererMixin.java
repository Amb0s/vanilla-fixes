package turniplabs.vanillafixes.mixin;

import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value = WorldRenderer.class, remap = false)
public class WorldRendererMixin {
    @Shadow
    private float farPlaneDistance = 0.0F;

    @ModifyArgs(method = "setupFog", at = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glFogf(IF)V", ordinal = 2), require = 0)
    private void fixFogStart(Args args) {
        args.set(1, farPlaneDistance * 16);
    }

    @ModifyArgs(method = "setupFog", at = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glFogf(IF)V", ordinal = 3), require = 0)
    private void fixFogEnd(Args args) {
        args.set(1, farPlaneDistance * 32);
    }

    @ModifyArgs(method = "setupFog", at = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glFogf(IF)V", ordinal = 4), require = 0)
    private void fixFogDensity(Args args) {
        args.set(1, 0.0f);
    }
}
