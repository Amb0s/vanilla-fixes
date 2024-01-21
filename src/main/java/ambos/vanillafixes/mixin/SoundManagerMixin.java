package ambos.vanillafixes.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.sound.SoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.io.File;

@Mixin(value = SoundManager.class, remap = false)
final class SoundManagerMixin {
    @Redirect(method = "loadModAudio", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/Minecraft;getAppDir(Ljava/lang/String;)Ljava/io/File;", ordinal = 0))
    private static File doNotGetAppDir(String s) {
        return new File(s);
    }

    @ModifyArgs(method = "loadSoundSettings", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/sound/SoundManager;loadModAudio(Ljava/lang/String;Lnet/minecraft/client/sound/SoundPool;)V"),
            require = 0)
    private void changeModResourcesPath(Args args) {
        // Note: "mod" directory is now inside .minecraft/resources instead of $HOME/.minecraft-bta/resources
        // (MultiMC users should be happy)
        Minecraft mc = Minecraft.getMinecraft(Minecraft.class);

        if (args.get(0).equals("minecraft-bta/resources/mod/sound")) {
            args.set(0, mc.getMinecraftDir() + "/resources/mod/sound");
        } else if (args.get(0).equals("minecraft-bta/resources/mod/streaming")) {
            args.set(0, mc.getMinecraftDir() + "/resources/mod/streaming");
        } else if (args.get(0).equals("minecraft-bta/resources/mod/music")) {
            args.set(0, mc.getMinecraftDir() + "/resources/mod/music");
        } else {
            args.set(0, mc.getMinecraftDir() + "/resources/mod/cavemusic");
        }
    }
}
