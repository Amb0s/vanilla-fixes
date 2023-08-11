package turniplabs.vanillafixes.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.File;

@Mixin(value = Minecraft.class, remap = false)
final class MinecraftMixin {
    @Shadow
    private File mcDataDir;
    @Shadow
    private static File minecraftDir;

    @Shadow
    public static File getAppDir(String s) {
        return null;
    }

    @Inject(method = "getMinecraftDir", at = @At("TAIL"), require = 0)
    private void renameMinecraftdirectory(CallbackInfoReturnable<File> cir) {
        minecraftDir = getAppDir("bta");
        mcDataDir = getAppDir("bta");
    }
}
