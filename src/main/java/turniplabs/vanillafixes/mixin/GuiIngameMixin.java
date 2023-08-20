package turniplabs.vanillafixes.mixin;

import net.minecraft.client.gui.GuiIngame;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value = GuiIngame.class, remap = false)
final class GuiIngameMixin {
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

    @Inject(method = "renderGameOverlay", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/GuiIngame;drawDebugScreenLineRight(Ljava/lang/String;)V", ordinal = 5),
            require = 0)
    private void displayVRAM(float partialTicks, boolean flag, int mouseX, int mouseY, CallbackInfo ci) {
        // https://developer.download.nvidia.com/opengl/specs/GL_NVX_gpu_memory_info.txt
        /* GPU_MEMORY_INFO_DEDICATED_VIDMEM_NVX */
        long maxVRAM = GL11.glGetInteger(36935);

        /* GPU_MEMORY_INFO_TOTAL_AVAILABLE_MEMORY_NVX */
        long totalAvailableVRAM = GL11.glGetInteger(36936);

        /* GPU_MEMORY_INFO_CURRENT_AVAILABLE_VIDMEM_NVX */
        long availableVRAM = GL11.glGetInteger(36937);

        long usedVRAM = maxVRAM - availableVRAM;

        drawDebugScreenLineRight("VRAM: " + usedVRAM * 100L / maxVRAM + "% ("+ usedVRAM / 1024L +
                "/" + maxVRAM / 1024L  + "MB)");
    }

    @ModifyArgs(method = "renderGameOverlay", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/GuiIngame;drawDebugScreenLineRight(Ljava/lang/String;)V", ordinal = 0),
            require = 0)
    private void changeRAMDisplay(Args args) {
        // https://stackoverflow.com/questions/3571203/what-are-runtime-getruntime-totalmemory-and-freememory
        long maxRAM = Runtime.getRuntime().maxMemory();
        long totalRAM = Runtime.getRuntime().totalMemory();
        long freeRAM = Runtime.getRuntime().freeMemory();
        long usedRAM = totalRAM - freeRAM;

        args.set(0, "RAM: " + usedRAM * 100L / maxRAM + "% (" + usedRAM / 1024L / 1024L + "/" +
                maxRAM / 1024L / 1024L + "MB)");
    }

    @ModifyArgs(method = "renderGameOverlay", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/gui/GuiIngame;drawDebugScreenLineRight(Ljava/lang/String;)V", ordinal = 1),
            require = 0)
    private void changeRAMDisplay2(Args args) {
        args.set(0, Runtime.getRuntime().totalMemory() / 1024L / 1024L + "MB allocated");
    }
}
