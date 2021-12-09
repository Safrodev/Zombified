package safro.zombified.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

@Config(name = "zombified")
@Config.Gui.Background("minecraft:textures/block/basalt_side.png")
public class ZombifiedConfig implements ConfigData {

    @ConfigEntry.Category("spawn_setting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int arctic_zombie_spawn_weight = 25;

    @ConfigEntry.Category("spawn_setting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int infernal_zombie_spawn_weight = 15;

    @ConfigEntry.Category("spawn_setting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int miner_zombie_spawn_weight = 5;

    @ConfigEntry.Category("spawn_setting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int beserk_zombie_spawn_weight = 5;

    @ConfigEntry.Category("spawn_setting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int sludge_zombie_spawn_weight = 30;

    @ConfigEntry.Category("spawn_setting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int crystal_zombie_spawn_weight = 25;

    @ConfigEntry.Category("spawn_setting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int brute_zombie_spawn_weight = 1;

    @ConfigEntry.Category("spawn_setting")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int squirm_zombie_spawn_weight = 10;
}