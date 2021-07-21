package safro.zombified.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static final File CONFIG_FILE = FabricLoader.getInstance().getConfigDir().resolve("zombified.json")
            .toFile();

    public static final Integer ARCTIC_SR = 15;
    public static final Integer INFERNAL_SR = 20;
    public static final Integer CRYSTAL_SR = 25;
    public static final Integer MINER_SR = 5;
    public static final Integer SLUDGE_SR = 30;
    public static final Integer BESERK_SR = 10;

    public int arctic_zombie_spawn_chance;
    public int infernal_zombie_spawn_chance;
    public int crystal_zombie_spawn_chance;
    public int miner_zombie_spawn_chance;
    public int sludge_zombie_spawn_chance;
    public int beserk_zombie_spawn_chance;

    public Config() {
        this.arctic_zombie_spawn_chance = ARCTIC_SR;
        this.infernal_zombie_spawn_chance = INFERNAL_SR;
        this.crystal_zombie_spawn_chance = CRYSTAL_SR;
        this.miner_zombie_spawn_chance = MINER_SR;
        this.sludge_zombie_spawn_chance = SLUDGE_SR;
        this.beserk_zombie_spawn_chance = BESERK_SR;
    }

    public Config(Config config) {
        this.arctic_zombie_spawn_chance = config.arctic_zombie_spawn_chance;
        this.infernal_zombie_spawn_chance = config.infernal_zombie_spawn_chance;
        this.crystal_zombie_spawn_chance = config.crystal_zombie_spawn_chance;
        this.miner_zombie_spawn_chance = config.miner_zombie_spawn_chance;
        this.sludge_zombie_spawn_chance = config.sludge_zombie_spawn_chance;
        this.beserk_zombie_spawn_chance = config.beserk_zombie_spawn_chance;
    }

    public static Config loadOrCreate() {
        if (!CONFIG_FILE.exists()) {

            Config config = new Config();
            if (config.save()) {

            }
            return config;
        }
        try {
            FileReader reader = new FileReader(CONFIG_FILE);
            Config config = GSON.fromJson(reader, Config.class);
            return config != null ? config : new Config();
        } catch (IOException | JsonIOException | JsonSyntaxException e) {
            return new Config();
        }
    }

    public boolean save() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            writer.write(GSON.toJson(this));
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
