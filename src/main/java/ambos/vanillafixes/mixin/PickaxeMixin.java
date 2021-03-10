package ambos.vanillafixes.mixin;

import net.minecraft.item.tool.Pickaxe;
import net.minecraft.block.BlockBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Pickaxe.class)
final class PickaxeMixin {
    @Shadow
    private static BlockBase[] effectiveBlocks = new BlockBase[]{
            BlockBase.BRICKS, BlockBase.DOUBLE_STONE_SLAB, BlockBase.STONE_SLAB,
            BlockBase.STONE, BlockBase.SANDSTONE, BlockBase.MOSSY_COBBLESTONE, BlockBase.IRON_ORE, BlockBase.IRON_BLOCK,
            BlockBase.COAL_ORE, BlockBase.GOLD_BLOCK, BlockBase.GOLD_ORE, BlockBase.DIAMOND_ORE, BlockBase.DIAMOND_BLOCK,
            BlockBase.ICE, BlockBase.NETHERRACK, BlockBase.LAPIS_LAZULI_ORE, BlockBase.LAPIS_LAZULI_BLOCK,
            BlockBase.REDSTONE_ORE, BlockBase.REDSTONE_ORE_LIT, BlockBase.COBBLESTONE_STAIRS, BlockBase.IRON_DOOR,
            BlockBase.COBBLESTONE, BlockBase.FURNACE, BlockBase.FURNACE_LIT, BlockBase.DISPENSER,
            BlockBase.STONE_PRESSURE_PLATE, BlockBase.RAIL, BlockBase.DETECTOR_RAIL, BlockBase.GOLDEN_RAIL
    };
}
