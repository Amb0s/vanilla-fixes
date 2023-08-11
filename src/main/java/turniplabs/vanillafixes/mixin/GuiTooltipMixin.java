package turniplabs.vanillafixes.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiTooltip;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.tool.ItemTool;
import net.minecraft.core.item.tool.ItemToolSword;
import net.minecraft.core.net.command.TextFormatting;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.player.inventory.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = GuiTooltip.class, remap = false)
final class GuiTooltipMixin extends Gui {
    @Shadow
    Minecraft mc;

    // Stores hovered inventory slot.
    private Slot hoveredSlot;

    @Inject(method = "getTooltipText", at = @At("HEAD"))
    private void getSlot(ItemStack itemStack, boolean showDescription, Slot slot, CallbackInfoReturnable cir) {
        hoveredSlot = slot;
    }

    @Redirect(method = "getTooltipText", at = @At(value = "INVOKE",
            target = "Ljava/lang/StringBuilder;append(Ljava/lang/String;)Ljava/lang/StringBuilder;", ordinal = 2))
    private StringBuilder addStats(StringBuilder stringBuilder, String str) {
        // The first calls to 'append' in the original method come from translateKey params.
        // Target: text.append(itemName)
        stringBuilder.append(str);

        // Gets player inventory.
        InventoryPlayer inventory = mc.thePlayer.inventory;

        // Disables the unused option (just to be safe).
        mc.gameSettings.showItemDurability.value = false;

        if (inventory.getHeldItemStack() == null && hoveredSlot != null &&
                hoveredSlot.hasStack() && hoveredSlot.getStack().getItemName() != null) { // If the slot isn't empty...
            if (hoveredSlot.getStack().getItem() instanceof ItemToolSword ||
                    hoveredSlot.getStack().getItem() instanceof ItemTool) { // If it's a sword or a tool...

                // Gets durability.
                int toolDurability = hoveredSlot.getStack().getMaxDamage() - hoveredSlot.getStack()
                        .getItemDamageForDisplay();

                // Gets damage.
                int toolDamage = hoveredSlot.getStack().getItem().getDamageVsEntity(null);

                /* Formats durability display */
                String durabilityTooltip = (new StringBuilder())
                        .append("Durability: ")
                        .append(toolDurability)
                        .append("/")
                        .append(hoveredSlot
                                .getStack()
                                .getMaxDamage())
                        .toString();
                stringBuilder.append('\n').append(TextFormatting.LIGHT_GRAY + durabilityTooltip);

                /* Formats damage display */
                String damageTooltip = (new StringBuilder())
                        .append("+")
                        .append(toolDamage)
                        .append(" Attack Damage")
                        .toString();
                stringBuilder.append('\n').append(TextFormatting.LIGHT_GRAY + damageTooltip);
            }
        }

        return stringBuilder;
    }
}
