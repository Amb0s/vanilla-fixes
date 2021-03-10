package ambos.vanillafixes.mixin;

import net.minecraft.item.tool.Hatchet;
import net.minecraft.block.BlockBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Hatchet.class)
final class HatchetMixin {
    @Shadow
    private static BlockBase[] effectiveBlocks = new BlockBase[]{
            BlockBase.WOOD, BlockBase.BOOKSHELF, BlockBase.LOG, BlockBase.CHEST, BlockBase.WORKBENCH,
            BlockBase.WOOD_DOOR, BlockBase.WOOD_STAIRS, BlockBase.WOODEN_PRESSURE_PLATE, BlockBase.FENCE,
            BlockBase.TRAPDOOR, BlockBase.JUKEBOX, BlockBase.PUMPKIN, BlockBase.JACK_O_LANTERN, BlockBase.STANDING_SIGN,
            BlockBase.WALL_SIGN, BlockBase.NOTEBLOCK, BlockBase.LADDER
    };
}
