package safro.zombified.mixin;

import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import safro.zombified.Zombified;
import safro.zombified.config.ConfigInit;
// Unused Code, from testing
@Mixin(DefaultBiomeFeatures.class)
public class DefaultBiomeFeaturesMixin {
//    @Inject(method = "addMonsters(Lnet/minecraft/world/biome/SpawnSettings$Builder;III)V", at = @At("TAIL"))
    private static void addZombies(SpawnSettings.Builder builder, int zombieWeight, int zombieVillagerWeight, int skeletonWeight, CallbackInfo ci) {
        if (zombieWeight > 0) {
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(Zombified.INFERNAL, ConfigInit.CONFIG.infernal_zombie_spawn_weight, 1, 2));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(Zombified.ARCTIC, ConfigInit.CONFIG.arctic_zombie_spawn_weight, 1, 3));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(Zombified.MINER, ConfigInit.CONFIG.miner_zombie_spawn_weight, 1, 1));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(Zombified.SLUDGE, ConfigInit.CONFIG.sludge_zombie_spawn_weight, 1, 2));
            builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(Zombified.BESERKER, ConfigInit.CONFIG.beserk_zombie_spawn_weight, 1, 1));
        }
    }

    @Inject(method = "addCaveMobs", at = @At("TAIL"))
    private static void addCrystalZombie(SpawnSettings.Builder builder, CallbackInfo ci) {
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(Zombified.CRYSTAL, ConfigInit.CONFIG.crystal_zombie_spawn_weight, 1, 2));
    }
}
