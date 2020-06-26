package ambos.vanillafixes.mixin;

import net.minecraft.server.network.PendingConnection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PendingConnection.class)
final class MixinPendingConnection {
    @ModifyConstant(method = "handleHandshake", constant = @Constant(stringValue = "http://www.minecraft.net/game/checkserver.jsp?user="))
    private String changeURL(String url) {
        return "http://session.minecraft.net/game/checkserver.jsp?user=";
    }
}
