package ambos.vanillafixes;

import net.fabricmc.api.ModInitializer;
import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VanillaFixes implements ModInitializer {
    public static final String MOD_ID = "vanillafixes";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final TomlConfigHandler CONFIG;
    public static final boolean JITERRING;
    public static final boolean FOG;
    public static final boolean F3_MENU;
    public static final boolean MOD_RESOURCES_PATH;
    public static final boolean SIMPLE_MAIN_MENU;
    public static final boolean IMPROVED_TOOLTIP;
    static {
        Toml toml = new Toml();
        toml.addCategory("Fixes");
        toml.addEntry("Fixes.jittering", "Fixes jitter at high coordinates", true);
        toml.addEntry("Fixes.fog", "Properly disables fog and fix underwater rendering", true);
        toml.addEntry("Fixes.mod_resources_path", "Changes mod resources path to '.minecraft/resources/mod'", true);
        toml.addCategory("Settings");
        toml.addEntry("Settings.f3_menu", "Deobfuscates debug menu", true);
        toml.addEntry("Settings.simple_main_menu", "Removes buttons, links and text on the main menu", true);
        toml.addEntry("Settings.improved_tooltip", "Shows item durability and/or damage value in the tooltip", true);

        CONFIG = new TomlConfigHandler(MOD_ID, toml);
        JITERRING = CONFIG.getBoolean("Fixes.jittering");
        FOG = CONFIG.getBoolean("Fixes.fog");
        MOD_RESOURCES_PATH = CONFIG.getBoolean("Fixes.mod_resources_path");
        F3_MENU = CONFIG.getBoolean("Settings.f3_menu");
        SIMPLE_MAIN_MENU = CONFIG.getBoolean("Settings.simple_main_menu");
        IMPROVED_TOOLTIP = CONFIG.getBoolean("Settings.improved_tooltip");
    }

    @Override
    public void onInitialize() {
        LOGGER.info("Vanilla Fixes initialized");
    }
}
