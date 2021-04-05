package ambos.vanillafixes.mixin;

import ambos.vanillafixes.MinecraftUtil;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerBase.class)
final class PlayerBaseMixin extends Living {
    @Shadow
    public String playerCloakUrl;

    @Shadow
    public String name;

    private PlayerBaseMixin(Level level) {
        super(level);
    }

    @Inject(method = "initCloak", at = @At("RETURN"), require = 0)
    private void onInitCloak(CallbackInfo ci) {
        playerCloakUrl = MinecraftUtil.getPlayerCape(name);
        cloakUrl = playerCloakUrl;
    }
}
