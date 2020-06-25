package ambos.vanillafixes.mixin;

import ambos.vanillafixes.MinecraftUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.Session;
import net.minecraft.entity.player.AbstractClientPlayer;
import net.minecraft.entity.player.Player;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractClientPlayer.class)
abstract class AbstractClientPlayerMixin extends Player {
    private AbstractClientPlayerMixin(Level level) {
        super(level);
    }

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Ljava/lang/StringBuilder;toString()Ljava/lang/String;"))
    private String changeSkin(StringBuilder builder, Minecraft minecraft, Level level, Session session, int integer) {
        return MinecraftUtil.getPlayerSkin(session.username);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void AbstractClientPlayer(Minecraft minecraft, Level level, Session session, int integer, CallbackInfo ci) {
        if (session != null && session.username != null && session.username.length() > 0) {
            cloakUrl = MinecraftUtil.getPlayerCape(session.username);
        }
    }
}
