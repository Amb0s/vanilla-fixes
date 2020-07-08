package ambos.vanillafixes.mixin;

import net.minecraft.item.tool.Hatchet;
import net.minecraft.tile.Tile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Hatchet.class)
public class HatchetMixin {
    @Shadow
    private static Tile[] effectiveBlocks = new Tile[]{Tile.WOOD, Tile.BOOKSHELF, Tile.LOG, Tile.CHEST, Tile.WORKBENCH,
            Tile.DOOR_WOOD, Tile.STAIRS_WOOD, Tile.WOODEN_PRESSURE_PLATE, Tile.FENCE, Tile.TRAPDOOR, Tile.JUKEBOX,
            Tile.PUMPKIN, Tile.LIT_PUMPKIN, Tile.STANDING_SIGN, Tile.WALL_SIGN, Tile.NOTEBLOCK, Tile.LADDER
    };
}
