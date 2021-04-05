package ambos.vanillafixes.mixin;

import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityBase.class)
final class EntityBaseMixin {
    @Redirect(method = "move", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/EntityBase;x:D", opcode = Opcodes.PUTFIELD), require = 0)
    private void fixX(EntityBase entityBase, double value) {
        if (!entityBase.level.isClient || entityBase instanceof PlayerBase || !(entityBase instanceof Living))
            entityBase.x = value;
    }

    @Redirect(method = "move", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/EntityBase;y:D", opcode = Opcodes.PUTFIELD), require = 0)
    private void fixY(EntityBase entityBase, double value) {
        if (!entityBase.level.isClient || entityBase instanceof PlayerBase || !(entityBase instanceof Living))
            entityBase.y = value;
    }

    @Redirect(method = "move", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/EntityBase;z:D", opcode = Opcodes.PUTFIELD), require = 0)
    private void fixZ(EntityBase entityBase, double value) {
        if (!entityBase.level.isClient || entityBase instanceof PlayerBase || !(entityBase instanceof Living))
            entityBase.z = value;
    }
}
