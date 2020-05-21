package ambos.vanillafixes.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.network.ClientPlayNetworkHandler;
import net.minecraft.packet.login.ClientUsernameC2S;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.net.MalformedURLException;
import java.net.URL;

@Mixin(ClientPlayNetworkHandler.class)
final class ClientPlayNetworkHandlerMixin {
    @Shadow
    private Minecraft minecraft;

    @Redirect(method = "method_1471", at = @At(value = "NEW", target = "java/net/URL"))
    private URL toURL(String s, ClientUsernameC2S arg) {
        try {
            return new URL("http://session.minecraft.net/game/joinserver.jsp?user=" + minecraft.session.username +
                    "&sessionId=" + minecraft.session.field_873 + "&serverId=" + arg.serverId);
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
