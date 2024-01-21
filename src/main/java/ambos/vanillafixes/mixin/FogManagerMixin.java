package ambos.vanillafixes.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.option.enums.RenderDistance;
import net.minecraft.client.render.FogManager;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value = FogManager.class, remap = false)
public class FogManagerMixin {
    @Shadow
    private Minecraft mc;

    @ModifyArgs(method = "setupFog", at = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glFogf(IF)V"), require = 0)
    private void changeFogStart(Args args) {
        if (!(Boolean)this.mc.gameSettings.fog.value
        && args.get(0).equals(2915) // https://legacy.lwjgl.org/javadoc/constant-values.html
        && this.mc.gameSettings.renderDistance.value.chunks == RenderDistance.EXTREME.chunks) {
            args.set(1, Float.valueOf(RenderDistance.EXTREME.chunks * 12));
        }
    }

    @ModifyArgs(method = "setupFog", at = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glFogf(IF)V"), require = 0)
    private void changeFogEnd(Args args) {
        if (!(Boolean)this.mc.gameSettings.fog.value
        && args.get(0).equals(2916) // https://legacy.lwjgl.org/javadoc/constant-values.html
        && this.mc.gameSettings.renderDistance.value.chunks == RenderDistance.EXTREME.chunks) {
            args.set(1, Float.valueOf(RenderDistance.EXTREME.chunks * 16));
        }
    }
}
