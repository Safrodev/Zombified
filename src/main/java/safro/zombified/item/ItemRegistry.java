package safro.zombified.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.lwjgl.system.CallbackI;
import safro.zombified.Zombified;

public class ItemRegistry {

    public static final Item FROZEN_FLESH = new Item(new FabricItemSettings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(4).statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 600), 0.8F).build()));
    public static final Item CHARRED_FLESH = new Item(new FabricItemSettings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(4).saturationModifier(0.4F).build()));
    public static final Item ENERGON_CRYSTAL = new Item(new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item FLESH_HAMMER = new FleshHammer(ToolMaterials.WOOD, 3, -2.4F, new Item.Settings().group(ItemGroup.COMBAT).maxCount(1));
    public static final Item MUD_CLUMP = new Item(new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item SULFUR_DUST = new Item(new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item ENERGY_CHARGE = new EnergyCharge(new FabricItemSettings().group(ItemGroup.MISC).maxCount(16));


    public static void init() {

        Registry.register(Registry.ITEM, new Identifier("zombified", "frozen_flesh"), FROZEN_FLESH);
        Registry.register(Registry.ITEM, new Identifier("zombified", "charred_flesh"), CHARRED_FLESH);
        Registry.register(Registry.ITEM, new Identifier("zombified", "energon_crystal"), ENERGON_CRYSTAL);
        Registry.register(Registry.ITEM, new Identifier("zombified", "flesh_hammer"), FLESH_HAMMER);
        Registry.register(Registry.ITEM, new Identifier("zombified", "mud_clump"), MUD_CLUMP);
        Registry.register(Registry.ITEM, new Identifier("zombified", "sulfur_dust"), SULFUR_DUST);
        Registry.register(Registry.ITEM, new Identifier("zombified", "energy_charge"), ENERGY_CHARGE);

        Registry.register(Registry.ITEM, new Identifier("zombified", "arctic_spawn_egg"),
                new SpawnEggItem(Zombified.ARCTIC, 1208321, 1426874,
                        new Item.Settings().group(ItemGroup.MISC)));

        Registry.register(Registry.ITEM, new Identifier("zombified", "infernal_spawn_egg"),
                new SpawnEggItem(Zombified.INFERNAL, 1208321, 7212815,
                        new Item.Settings().group(ItemGroup.MISC)));

        Registry.register(Registry.ITEM, new Identifier("zombified", "crystal_spawn_egg"),
                new SpawnEggItem(Zombified.CRYSTAL, 1208321, 7349630,
                        new Item.Settings().group(ItemGroup.MISC)));

        Registry.register(Registry.ITEM, new Identifier("zombified", "miner_spawn_egg"),
                new SpawnEggItem(Zombified.MINER, 1208321, 4802889,
                        new Item.Settings().group(ItemGroup.MISC)));

        Registry.register(Registry.ITEM, new Identifier("zombified", "sludge_spawn_egg"),
                new SpawnEggItem(Zombified.SLUDGE, 1208321, 4573744,
                        new Item.Settings().group(ItemGroup.MISC)));

        Registry.register(Registry.ITEM, new Identifier("zombified", "beserker_spawn_egg"),
                new SpawnEggItem(Zombified.BESERKER, 1208321, 13015069,
                        new Item.Settings().group(ItemGroup.MISC)));

    }
}
