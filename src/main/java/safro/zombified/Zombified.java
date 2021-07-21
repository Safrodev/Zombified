package safro.zombified;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.fabricmc.fabric.mixin.object.builder.SpawnRestrictionAccessor;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import safro.zombified.config.Config;
import safro.zombified.entity.*;
import safro.zombified.item.EnergyChargeProjectileEntity;
import safro.zombified.item.ItemRegistry;

public class Zombified implements ModInitializer {

	public static Config config;

	public static final EntityType<ArcticZombieEntity> ARCTIC =
			Registry.register(Registry.ENTITY_TYPE,
					new Identifier("zombified", "arctic_zombie"),
					FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, ArcticZombieEntity::new).dimensions(EntityDimensions.fixed(0.6F, 1.95F)).trackRangeBlocks(10).build());

	public static final EntityType<InfernalZombieEntity> INFERNAL =
			Registry.register(Registry.ENTITY_TYPE,
					new Identifier("zombified", "infernal_zombie"),
					FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, InfernalZombieEntity::new).dimensions(EntityDimensions.fixed(0.6F, 1.95F)).trackRangeBlocks(6).fireImmune().build());

	public static final EntityType<CrystalZombieEntity> CRYSTAL =
			Registry.register(Registry.ENTITY_TYPE,
					new Identifier("zombified", "crystal_zombie"),
					FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, CrystalZombieEntity::new).dimensions(EntityDimensions.fixed(0.6F, 1.95F)).trackRangeBlocks(8).build());

	/* public static final EntityType<BruteZombieEntity> BRUTE =
			Registry.register(Registry.ENTITY_TYPE,
					new Identifier("zombified", "brute_zombie"),
					FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, BruteZombieEntity::new).dimensions(EntityDimensions.fixed(1.4F, 2.7F)).trackRangeBlocks(10).build());
	*/
	public static final EntityType<MinerZombieEntity> MINER =
			Registry.register(Registry.ENTITY_TYPE,
					new Identifier("zombified", "miner_zombie"),
					FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, MinerZombieEntity::new).dimensions(EntityDimensions.fixed(0.6F, 1.95F)).trackRangeBlocks(8).build());

	public static final EntityType<SludgeZombieEntity> SLUDGE =
			Registry.register(Registry.ENTITY_TYPE,
					new Identifier("zombified", "sludge_zombie"),
					FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SludgeZombieEntity::new).dimensions(EntityDimensions.fixed(0.6F, 1.95F)).trackRangeBlocks(10).build());

	public static final EntityType<BeserkerZombieEntity> BESERKER =
			Registry.register(Registry.ENTITY_TYPE,
					new Identifier("zombified", "beserker_zombie"),
					FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, BeserkerZombieEntity::new).dimensions(EntityDimensions.fixed(0.6F, 1.95F)).trackRangeBlocks(12).build());

	public static final EntityType<EnergyChargeProjectileEntity> ENERGY_CHARGE_PROJECTILE_ENTITY_TYPE = Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier("zombified", "energy_charge_projectile"),
			FabricEntityTypeBuilder.<EnergyChargeProjectileEntity>create(SpawnGroup.MISC, EnergyChargeProjectileEntity::new)
					.dimensions(EntityDimensions.fixed(0.25F, 0.25F))
					.trackRangeBlocks(4).trackedUpdateRate(10)
					.build());

	@Override
	public void onInitialize() {
		config = Config.loadOrCreate();

		ItemRegistry.init();

		FabricDefaultAttributeRegistry.register(ARCTIC, ArcticZombieEntity.createZombieAttributes());
		FabricDefaultAttributeRegistry.register(INFERNAL, InfernalZombieEntity.createZombieAttributes());
		FabricDefaultAttributeRegistry.register(CRYSTAL, CrystalZombieEntity.createZombieAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D));
		// FabricDefaultAttributeRegistry.register(BRUTE, BruteZombieEntity.createZombieAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 100.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 15.0D));
		FabricDefaultAttributeRegistry.register(MINER, MinerZombieEntity.createZombieAttributes());
		FabricDefaultAttributeRegistry.register(SLUDGE, SludgeZombieEntity.createZombieAttributes());
		FabricDefaultAttributeRegistry.register(BESERKER, BeserkerZombieEntity.createZombieAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D).add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.5D).add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0D));

		BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.SNOWY_TUNDRA, BiomeKeys.SNOWY_MOUNTAINS, BiomeKeys.SNOWY_TAIGA, BiomeKeys.ICE_SPIKES), SpawnGroup.MONSTER, Zombified.ARCTIC, Config.ARCTIC_SR, 1, 3);
		BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.NETHER_WASTES, BiomeKeys.CRIMSON_FOREST, BiomeKeys.WARPED_FOREST), SpawnGroup.MONSTER, Zombified.INFERNAL, Config.INFERNAL_SR, 2, 4);
		BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.MONSTER, Zombified.CRYSTAL, Config.CRYSTAL_SR, 1, 2);
		// BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.BADLANDS, BiomeKeys.JUNGLE, BiomeKeys.DARK_FOREST, BiomeKeys.DARK_FOREST_HILLS), SpawnGroup.MONSTER, Zombified.BRUTE, 10, 1, 1);
		BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.MONSTER, Zombified.MINER, Config.MINER_SR, 1, 1);
		BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.SWAMP, BiomeKeys.SWAMP_HILLS, BiomeKeys.JUNGLE), SpawnGroup.MONSTER, Zombified.SLUDGE, Config.MINER_SR, 1, 2);
		BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.MONSTER, Zombified.BESERKER, Config.BESERK_SR, 1, 1);

		SpawnRestrictionAccessor.callRegister(CRYSTAL, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, CrystalZombieEntity::canSpawn);
	}
}
