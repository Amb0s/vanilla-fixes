package ambos.vanillafixes.mixin;

import net.minecraft.network.ClientPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ClientPlayNetworkHandler.class)
final class ClientPlayNetworkHandlerMixin {
    @ModifyConstant(method = "method_1471", constant = @Constant(stringValue = "http://www.minecraft.net/game/joinserver.jsp?user="))
    private String changeURL(String url) {
        return "http://session.minecraft.net/game/joinserver.jsp?user=";
    }
}
