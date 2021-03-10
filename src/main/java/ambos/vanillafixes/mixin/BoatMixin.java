package ambos.vanillafixes.mixin;

import net.minecraft.entity.Boat;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Item;
import net.minecraft.item.ItemBase;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Boat.class)
abstract class BoatMixin extends EntityBase {
    public boolean field_1624;

    public BoatMixin(Level level) {
        super(level);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(CallbackInfo ci) {
        field_1624 = false;
    }

    @Redirect(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Boat;dropItem(IIF)Lnet/minecraft/entity/Item;"))
    private Item onDropItem(Boat boat, int i, int j, float f) {
        return null;
    }

    @Inject(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Boat;remove()V"), cancellable = true)
    private void dropBoat(CallbackInfoReturnable<Boolean> ci) {
        this.dropItem(ItemBase.boat.id, 1, 0.0F);
    }
}
