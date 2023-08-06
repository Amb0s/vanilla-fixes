package turniplabs.vanillafixes.mixin;

import net.minecraft.client.render.DisplayList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value = DisplayList.class, remap = false)
final class DisplayListMixin {
    private double offsetX;
    private double offsetY;
    private double offsetZ;

    @Shadow
    private int posX;

    @Shadow
    private int posY;

    @Shadow
    private int posZ;

    @Inject(method = "setToPos", at = @At("RETURN"), require = 0)
    private void onSetToPos(int blockX, int blockY, int blockZ, double offsetX, double offsetY, double offsetZ,
                            CallbackInfo ci) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
    }

    @ModifyArgs(method = "call", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glTranslatef(FFF)V"),
            require = 0)
    private void changeType(Args args) {
        args.set(0, (float) ((double) this.posX - this.offsetX));
        args.set(1, (float) ((double) this.posY - this.offsetY));
        args.set(2, (float) ((double) this.posZ - this.offsetZ));
    }
}
