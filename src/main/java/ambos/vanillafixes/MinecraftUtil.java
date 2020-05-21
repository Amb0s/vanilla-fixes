package ambos.vanillafixes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.shanerx.mojang.Mojang;
import org.shanerx.mojang.PlayerProfile;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public final class MinecraftUtil {
    public final static ArrayList<String> RESOURCES_PROXY_URLs = new ArrayList<>(Arrays.asList(
            "http://resourceproxy.pymcl.net/MinecraftResources/",
            "https://betacraft.pl/MinecraftResources/"
    ));

    private static final Logger logger = LogManager.getLogger(MinecraftUtil.class);

    private MinecraftUtil() {

    }

    private static boolean isDown(String url) {
        try {
            URL urlObject = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlObject.openConnection();
            httpURLConnection.connect();

            int statusCode = httpURLConnection.getResponseCode();
            if (!(statusCode == 404)) {
                return false;
            }
        } catch (IOException e) {

        }

        return true;
    }

    public static String getPlayerSkin(String username) {
        Mojang mojang = new Mojang();
        String uuid = mojang.getUUIDOfUsername(username);
        PlayerProfile playerProfile = mojang.getPlayerProfile(uuid);
        Optional<URL> playerSkin = playerProfile.getTextures().get().getSkin();

        if (playerSkin.isPresent()) {
            logger.info("Getting player skin: " + playerSkin.get().toString());
            return playerSkin.get().toString();
        }

        return null;
    }

    public static String getPlayerCape(String username) {
        Mojang mojang = new Mojang();
        String uuid = mojang.getUUIDOfUsername(username);
        PlayerProfile playerProfile = mojang.getPlayerProfile(uuid);
        Optional<URL> playerCape = playerProfile.getTextures().get().getCape();

        if (playerCape.isPresent()) {
            logger.info("Getting player cape: " + playerCape.get().toString());
            return playerCape.get().toString();
        }

        return null;
    }

    public static String getResourcesURL() {
        for (String url : RESOURCES_PROXY_URLs) {
            if (!isDown(url)) {
                logger.info("Resource proxy found: " + url);
                return url;
            }
        }

        return null;
    }
}
