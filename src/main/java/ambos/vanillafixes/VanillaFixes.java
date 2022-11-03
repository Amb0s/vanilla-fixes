package ambos.vanillafixes;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VanillaFixes implements ModInitializer {
    public static final String MOD_ID = "vanillafixes";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static String name(String name) {
        return VanillaFixes.MOD_ID + "." + name;
    }

    @Override
    public void onInitialize() {
        LOGGER.info("VanillaFixes initialized");
    }
}
