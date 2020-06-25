package ambos.vanillafixes.mixin;

import ambos.vanillafixes.MinecraftUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.Player;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
final class PlayerMixin extends LivingEntity {
    @Shadow
    public String playerCloakUrl;

    @Shadow
    public String name;

    private PlayerMixin(Level level) {
        super(level);
    }

    @Inject(method = "initCloak", at = @At("RETURN"))
    private void onInitCloak(CallbackInfo ci) {
        playerCloakUrl = MinecraftUtil.getPlayerCape(name);
        cloakUrl = playerCloakUrl;
    }
}
