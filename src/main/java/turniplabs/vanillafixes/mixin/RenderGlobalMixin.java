package turniplabs.vanillafixes.mixin;

import net.minecraft.client.render.RenderGlobal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = RenderGlobal.class, remap = false)
final class RenderGlobalMixin {
    @Shadow
    private int countEntitiesTotal;
    @Shadow
    private int countEntitiesRendered;
    @Shadow
    private int renderersLoaded;
    @Shadow
    private int renderersBeingRendered;

    @Inject(method = "getRendererDebugInfo", at = @At("HEAD"), require = 0, cancellable = true)
    private void changeFormatting(CallbackInfoReturnable<String> cir) {
        cir.setReturnValue("Renderers: " + this.renderersBeingRendered + "/" + this.renderersLoaded);
    }

    @Inject(method = "getEntityDebugInfo", at = @At("HEAD"), require = 0, cancellable = true)
    private void changeFormatting2(CallbackInfoReturnable<String> cir) {
        cir.setReturnValue("Entities: " + this.countEntitiesRendered + "/" + this.countEntitiesTotal);
    }

    @Redirect(method = "renderSortedRenderers", at = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 0))
    private void cancelCallglEnable(int cap) {}

    @Redirect(method = "renderSortedRenderers", at = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V", ordinal = 0))
    private void cancelCallglDisable(int cap) {}
}
