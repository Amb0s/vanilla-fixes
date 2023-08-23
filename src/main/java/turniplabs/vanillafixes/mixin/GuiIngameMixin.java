package turniplabs.vanillafixes.mixin;

import net.minecraft.client.gui.GuiIngame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value = GuiIngame.class, remap = false)
final class GuiIngameMixin {
    private static long toMiB(long bytes) {
        return bytes / 1024L / 1024L;
    }

    @Shadow
    private void drawDebugScreenLineRight(String string) {

    }

    @ModifyConstant(method = "renderGameOverlay", constant = @Constant(stringValue = "s I: "))
    private String deobfuscateWeatherIntensity(String value) {
        return "s, Intensity: ";
    }

    @ModifyConstant(method = "renderGameOverlay", constant = @Constant(stringValue = "% P: "))
    private String deobfuscateWeatherPower(String value) {
        return "%, Power: ";
    }

    @ModifyArgs(method = "renderGameOverlay", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/GuiIngame;drawDebugScreenLineRight(Ljava/lang/String;)V", ordinal = 0),
            require = 0)
    private void changeUsedRAMDisplay(Args args) {
        // https://stackoverflow.com/questions/3571203/what-are-runtime-getruntime-totalmemory-and-freememory
        long maxRAM = Runtime.getRuntime().maxMemory();
        long totalRAM = Runtime.getRuntime().totalMemory();
        long freeRAM = Runtime.getRuntime().freeMemory();
        long usedRAM = totalRAM - freeRAM;

        args.set(0, "RAM: " + usedRAM * 100L / maxRAM + "% (" + toMiB(usedRAM) + "/" + toMiB(maxRAM)+ "MB)");
    }

    @ModifyArgs(method = "renderGameOverlay", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/GuiIngame;drawDebugScreenLineRight(Ljava/lang/String;)V", ordinal = 1),
            require = 0)
    private void changeAllocatedRAMDisplay2(Args args) {
        args.set(0, toMiB(Runtime.getRuntime().totalMemory()) + "MB allocated");
    }
}
