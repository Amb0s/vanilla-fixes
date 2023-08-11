package turniplabs.vanillafixes.mixin;

import net.minecraft.client.sound.SoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value = SoundManager.class, remap = false)
final class SoundManagerMixin {
    @ModifyArgs(method = "loadSoundSettings", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/sound/SoundManager;loadModAudio(Ljava/lang/String;Lnet/minecraft/client/sound/SoundPool;)V"),
            require = 0)
    private void renameMinecraftdirectory(Args args) {
        if (args.get(0) == "minecraft-bta/resources/mod/sound") {
            args.set(0, "bta/resources/mod/sound");
        } else if (args.get(0) == "minecraft-bta/resources/mod/streaming") {
            args.set(0, "bta/resources/mod/streaming");
        } else if (args.get(0) == "minecraft-bta/resources/mod/music") {
            args.set(0, "bta/resources/mod/music");
        } else {
            args.set(0, "bta/resources/mod/cavemusic");
        }
    }
}
