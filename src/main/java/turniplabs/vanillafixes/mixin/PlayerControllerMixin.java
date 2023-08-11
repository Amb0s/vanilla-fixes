package turniplabs.vanillafixes.mixin;

import net.minecraft.client.player.controller.PlayerController;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = PlayerController.class, remap = false)
final class PlayerControllerMixin {
    @Shadow
    protected int blockHitDelay;

    @Redirect(method = "mine", at = @At(value = "FIELD",
            target = "Lnet/minecraft/client/player/controller/PlayerController;blockHitDelay:I",
            opcode = Opcodes.PUTFIELD, ordinal = 3))
    private void changeBlockHitDelay(PlayerController instance, int value) {
        blockHitDelay = 0;
    }
}
