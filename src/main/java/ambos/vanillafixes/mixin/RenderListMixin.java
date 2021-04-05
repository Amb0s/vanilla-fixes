package ambos.vanillafixes.mixin;

import net.minecraft.client.render.RenderList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(RenderList.class)
final class RenderListMixin {
    private double field_2483_proxy;
    private double field_2484_proxy;
    private double field_2485_proxy;

    @Shadow
    private int field_2480;

    @Shadow
    private int field_2481;

    @Shadow
    private int field_2482;

    @Inject(method = "method_1912", at = @At("RETURN"), require = 0)
    private void onMethod_1912(int var1, int var2, int var3, double var4, double var6, double var8, CallbackInfo ci) {
        this.field_2483_proxy = var4;
        this.field_2484_proxy = var6;
        this.field_2485_proxy = var8;
    }

    @ModifyArgs(method = "method_1909", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glTranslatef(FFF)V"), remap = false, require = 0)
    private void changeType(Args args) {
        args.set(0, (float) ((double) this.field_2480 - this.field_2483_proxy));
        args.set(1, (float) ((double) this.field_2481 - this.field_2484_proxy));
        args.set(2, (float) ((double) this.field_2482 - this.field_2485_proxy));
    }
}
