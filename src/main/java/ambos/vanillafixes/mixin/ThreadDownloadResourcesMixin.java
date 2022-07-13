package ambos.vanillafixes.mixin;

import net.minecraft.src.ThreadDownloadResources;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

@Mixin(value = ThreadDownloadResources.class, remap = false)
final class ThreadDownloadResourcesMixin {
    private final static ArrayList<String> RESOURCES_DOMAINS = new ArrayList<>(Arrays.asList(
            "http://mcresources.modification-station.net/MinecraftResources/",
            "http://resourceproxy.pymcl.net/MinecraftResources/"
    ));

    private static boolean isDown(String url) {
        try {
            URL urlObject = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlObject.openConnection();
            httpURLConnection.connect();

            int statusCode = httpURLConnection.getResponseCode();
            if (!(statusCode == 404)) {
                return false;
            }
        } catch (Exception ignored) {

        }

        return true;
    }

    private static String getResources() {
        for (String url : RESOURCES_DOMAINS) {
            if (!isDown(url)) {
                return url;
            }
        }

        return null;
    }

    @Shadow
    public static String resourceDomain = getResources();
}
