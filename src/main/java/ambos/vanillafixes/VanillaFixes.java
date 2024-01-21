package ambos.vanillafixes;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VanillaFixes implements ModInitializer {
    public static final String MOD_ID = "vanillafixes";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Vanilla Fixes initialized");
    }
}
