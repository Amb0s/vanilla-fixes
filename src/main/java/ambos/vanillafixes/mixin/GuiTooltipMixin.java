package ambos.vanillafixes.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import ambos.vanillafixes.VanillaFixes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiTooltip;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.tool.ItemTool;
import net.minecraft.core.item.tool.ItemToolSword;
import net.minecraft.core.net.command.TextFormatting;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.player.inventory.slot.Slot;

@Mixin(value = GuiTooltip.class, remap = false)
final class GuiTooltipMixin extends Gui {
    @Shadow
    Minecraft mc;

    private Slot hoveredSlot;

    @Inject(method = "getTooltipText(Lnet/minecraft/core/item/ItemStack;ZLnet/minecraft/core/player/inventory/slot/Slot;)Ljava/lang/String;", at = @At("HEAD"))
    private void getSlot(ItemStack itemStack, boolean showDescription, Slot slot, CallbackInfoReturnable<Object> cir) {
        if (VanillaFixes.IMPROVED_TOOLTIP) {
            // Stores hovered inventory slot.
            hoveredSlot = slot;
        }
    }

    @Redirect(method = "getTooltipText(Lnet/minecraft/core/item/ItemStack;ZLnet/minecraft/core/player/inventory/slot/Slot;)Ljava/lang/String;", at = @At(value = "INVOKE", target = "Ljava/lang/StringBuilder;append(Ljava/lang/String;)Ljava/lang/StringBuilder;", ordinal = 0))
    private StringBuilder addStats(StringBuilder stringBuilder, String str) {
        stringBuilder.append(str);

        if (!VanillaFixes.IMPROVED_TOOLTIP) {
            return stringBuilder;
        }

        if (mc.gameSettings.heldItemCountOverlay.value) {
            // Gets player inventory.
            InventoryPlayer inventory = mc.thePlayer.inventory;

            if (inventory.getHeldItemStack() == null && hoveredSlot != null && hoveredSlot.hasStack() &&
                    hoveredSlot.getStack().getItemName() != null) { // If the slot isn't empty...
                if (hoveredSlot.getStack().getItem() instanceof ItemToolSword ||
                        hoveredSlot.getStack().getItem() instanceof ItemTool) { // If it's a sword or a tool...

                    // Gets durability.
                    int toolDurability = hoveredSlot.getStack().getMaxDamage() - hoveredSlot.getStack()
                            .getItemDamageForDisplay();

                    // Gets damage.
                    int toolDamage = hoveredSlot.getStack().getItem().getDamageVsEntity(null);

                    /* Formats durability display */
                    String durabilityTooltip = "Durability: " + toolDurability + "/" + hoveredSlot
                            .getStack()
                            .getMaxDamage();
                    stringBuilder.append('\n').append(TextFormatting.LIGHT_GRAY).append(durabilityTooltip);

                    /* Formats damage display */
                    String damageTooltip = "+" + toolDamage + " Attack Damage";
                    stringBuilder.append('\n').append(TextFormatting.LIGHT_GRAY).append(damageTooltip);
                }
            }
        }

        return stringBuilder;
    }
}
