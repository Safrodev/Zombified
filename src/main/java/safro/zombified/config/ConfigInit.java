package safro.zombified.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;

public class ConfigInit {

    public static ZombifiedConfig CONFIG = new ZombifiedConfig();

    public static void init() {
        AutoConfig.register(ZombifiedConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(ZombifiedConfig.class).getConfig();
    }
}
