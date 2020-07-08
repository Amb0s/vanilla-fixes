package ambos.vanillafixes.mixin;

import net.minecraft.item.tool.Pickaxe;
import net.minecraft.tile.Tile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Pickaxe.class)
public class PickaxeMixin {
    @Shadow
    private static Tile[] effectiveBlocks = new Tile[]{Tile.STONEBRICK, Tile.DOUBLE_STONE_SLAB, Tile.STONE_SLAB,
            Tile.STONE, Tile.SANDSTONE, Tile.MOSSY_COBBLESTONE, Tile.IRON_ORE, Tile.BLOCK_IRON, Tile.COAL_ORE,
            Tile.BLOCK_GOLD, Tile.GOLD_ORE, Tile.ORE_DIAMOND, Tile.BLOCK_DIAMOND, Tile.ICE, Tile.NETHERRACK,
            Tile.LAPIS_LAZULI_ORE, Tile.LAPIS_LAZULI_BLOCK, Tile.REDSTONE_ORE, Tile.REDSTONE_ORE_LIT, Tile.STAIRS_STONE,
            Tile.DOOR_IRON, Tile.BRICK, Tile.FURNACE, Tile.FURNACE_LIT, Tile.DISPENSER, Tile.STONE_PRESSURE_PLATE,
            Tile.RAIL, Tile.DETECTOR_RAIL, Tile.GOLDEN_RAIL
    };
}
