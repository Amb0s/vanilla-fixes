package ambos.vanillafixes.mixin;

import ambos.vanillafixes.MinecraftUtil;
import net.minecraft.entity.player.Player;
import net.minecraft.entity.player.RemoteClientPlayer;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RemoteClientPlayer.class)
abstract class RemoteClientPlayerMixin extends Player {
    public RemoteClientPlayerMixin(Level level) {
        super(level);
    }

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Ljava/lang/StringBuilder;toString()Ljava/lang/String;"))
    private String toStr(StringBuilder builder, Level level, String username) {
        return MinecraftUtil.getPlayerSkin(username);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void RemoteClientPlayer(Level level, String username, CallbackInfo ci) {
        if (username != null && username.length() > 0) {
            cloakUrl = MinecraftUtil.getPlayerCape(username);
        }
    }
}
