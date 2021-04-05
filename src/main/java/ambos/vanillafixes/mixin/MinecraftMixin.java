package ambos.vanillafixes.mixin;

import net.minecraft.client.Minecraft;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.PixelFormat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Minecraft.class)
final class MinecraftMixin {
    @Redirect(method = "init", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;create()V"), remap = false, require = 0)
    private void changeDepthBuffer() {
        PixelFormat pixelformat = new PixelFormat();
        pixelformat = pixelformat.withDepthBits(24);
        try {
            Display.create(pixelformat);
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }
}
