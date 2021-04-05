package ambos.vanillafixes.mixin;

import net.minecraft.level.Level;
import net.minecraft.block.Stairs;
import net.minecraft.block.BlockBase;
import net.minecraft.block.material.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(Stairs.class)
final class StairsMixin extends BlockBase {
    private StairsMixin(int id, Material material) {
        super(id, material);
    }

    @Inject(method = "getDropId", at = @At("RETURN"), cancellable = true, require = 0)
    private void onGetDropId(int meta, Random rand, CallbackInfoReturnable cir) {
        cir.setReturnValue(id);
    }

    @Inject(method = "beforeDestroyedByExplosion", at = @At("HEAD"), cancellable = true, require = 0)
    private void onBeforeDestroyedByExplosion(Level level, int x, int y, int z, int meta, float dropChance, CallbackInfo ci) {
        super.beforeDestroyedByExplosion(level, x, y, z, meta, dropChance);
        ci.cancel();
    }
}
