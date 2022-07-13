package ambos.vanillafixes.mixin;

import net.minecraft.src.RenderList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value = RenderList.class, remap = false)
public class RenderListMixin {
    private double field_1239_d_store;
    private double field_1238_e_store;
    private double field_1237_f_store;

    @Shadow
    private int field_1242_a;

    @Shadow
    private int field_1241_b;

    @Shadow
    private int field_1240_c;

    @Inject(method = "func_861_a", at = @At("RETURN"), cancellable = true, require = 0)
    private void onFunc_861_a(int i, int j, int k, double d, double d1, double d2, CallbackInfo ci) {
        this.field_1239_d_store = d;
        this.field_1238_e_store = d1;
        this.field_1237_f_store = d2;
    }

    @ModifyArgs(method = "func_860_a", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glTranslatef(FFF)V"),
            require = 0)
    private void changeType(Args args) {
        args.set(0, (float) ((double) this.field_1242_a - this.field_1239_d_store));
        args.set(1, (float) ((double) this.field_1241_b - this.field_1238_e_store));
        args.set(2, (float) ((double) this.field_1240_c - this.field_1237_f_store));
    }
}
