package ambos.vanillafixes.mixin;

import ambos.vanillafixes.MinecraftUtil;
import net.minecraft.client.util.ThreadDownloadResources;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ThreadDownloadResources.class)
final class ThreadDownloadResourcesMixin {
    @ModifyConstant(method = "run", constant = @Constant(stringValue = "http://s3.amazonaws.com/MinecraftResources/"), remap = false)
    private String changeURL(String url) {
        return MinecraftUtil.getResources();
    }
}
