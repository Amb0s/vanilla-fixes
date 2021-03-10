package ambos.vanillafixes;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.shanerx.mojang.Mojang;
import org.shanerx.mojang.PlayerProfile;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public final class MinecraftUtil {
    private final static ArrayList<String> PROXY_URLs = new ArrayList<>(Arrays.asList(
            "http://resourceproxy.pymcl.net/MinecraftResources/",
            "http://mcresources.modification-station.net/MinecraftResources/",
            "https://betacraft.pl/MinecraftResources/"
    ));

    private static final Logger logger = LogManager.getLogger(MinecraftUtil.class);

    private MinecraftUtil() {}

    private static boolean isDown(String url) {
        try {
            URL urlObject = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlObject.openConnection();
            httpURLConnection.connect();

            int statusCode = httpURLConnection.getResponseCode();
            if (!(statusCode == 404)) {
                return false;
            }
        } catch (IOException ignored) {

        }

        return true;
    }

    private static boolean isSlim(PlayerProfile playerProfile) {
        JSONObject value = null;

        try {
            Optional<PlayerProfile.Property> property = playerProfile.getProperties().stream().findFirst();

            if (property.isPresent()) {
                value = (JSONObject) new JSONParser()
                        .parse(new String(Base64.decodeBase64(property.get().getValue())));
            }

        } catch (ParseException e) {
            return true;
        }

        return value != null && value.toJSONString().contains(Mojang.SkinType.SLIM.toString());
    }

    private static PlayerProfile getPlayerProfile(String username) {
        Mojang mojang = new Mojang();
        String uuid = mojang.getUUIDOfUsername(username);

        return mojang.getPlayerProfile(uuid);
    }

    public static String getPlayerSkin(String username) {
        try {
            PlayerProfile playerProfile = getPlayerProfile(username);
            Optional<URL> playerSkin = playerProfile.getTextures().flatMap(PlayerProfile.TexturesProperty::getSkin);

            if (playerSkin.isPresent() && !isSlim(playerProfile)) {
                logger.info("Getting player skin: " + playerSkin.get().toString());
                return playerSkin.get().toString();
            }
        } catch (RuntimeException ignored) {

        }

        return null;
    }

    public static String getPlayerCape(String username) {
        try {
            PlayerProfile playerProfile = getPlayerProfile(username);
            Optional<URL> playerCape = playerProfile.getTextures().flatMap(PlayerProfile.TexturesProperty::getCape);

            if (playerCape.isPresent()) {
                logger.info("Getting player cape: " + playerCape.get().toString());
                return playerCape.get().toString();
            }
        } catch (RuntimeException ignored) {

        }

        return null;
    }

    public static String getResources() {
        for (String url : PROXY_URLs) {
            if (!isDown(url)) {
                logger.info("Resources proxy found: " + url);
                return url;
            }
        }

        return null;
    }
}