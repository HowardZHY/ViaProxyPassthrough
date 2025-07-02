package viaproxy.addons.passthrough;

import net.lenni0451.optconfig.ConfigLoader;
import net.lenni0451.optconfig.annotations.Description;
import net.lenni0451.optconfig.annotations.OptConfig;
import net.lenni0451.optconfig.annotations.Option;
import net.lenni0451.optconfig.provider.ConfigProvider;
import net.raphimc.viaproxy.util.logging.Logger;

import java.io.File;

@OptConfig
public class PassthroughConfig {

    @Option("passthrough-max-protocol")
    @Description("Allow clients with this protocol version or below to connect as passthrough (no translation). Set to -1 to disable.")
    public static int protocol = -1;

    public static int getPassthroughMaxProtocol() {
        return protocol;
    }

    public static void load() {
        try {
            ConfigLoader<PassthroughConfig> configLoader = new ConfigLoader<>(PassthroughConfig.class);
            configLoader.getConfigOptions().setResetInvalidOptions(true);
            configLoader.loadStatic(ConfigProvider.file(new File("passthrough.yml")));
        } catch (Throwable t) {
            Logger.LOGGER.error("Failed to load the passthrough configuration!", t);
            System.exit(-1);
        }
    }
}
