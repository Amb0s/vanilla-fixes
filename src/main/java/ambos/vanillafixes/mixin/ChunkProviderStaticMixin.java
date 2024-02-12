package ambos.vanillafixes.mixin;

import net.minecraft.client.world.chunk.provider.ChunkProviderStatic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import ambos.vanillafixes.VanillaFixes;

@Mixin(value = ChunkProviderStatic.class, remap = false)
final class ChunkProviderStaticMixin {
    @ModifyConstant(method = "getInfoString", constant = @Constant(stringValue = "ChunkCache: "))
    private String reformatChunkCache(String value) {
        if (VanillaFixes.F3_MENU) {
            return "Chunk Cache: ";
        }

        return value;
    }
}
