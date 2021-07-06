package safro.zombified.entity;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import safro.zombified.Zombified;

import java.util.Random;

@SuppressWarnings("EntityConstructor")
public class CrystalZombieEntity extends ZombieEntity {
    public CrystalZombieEntity(EntityType<? extends CrystalZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    public static boolean canSpawn(EntityType<CrystalZombieEntity> type, ServerWorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        if (pos.getY() >= world.getSeaLevel()) {
            return false;
        } else {
            int i = world.getLightLevel(pos);
            int j = 4;
            if (random.nextBoolean()) {
                return false;
            }

            return i > random.nextInt(j) ? false : canMobSpawn(type, world, spawnReason, pos, random);
        }
    }


    protected boolean burnsInDaylight() {
        return true;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_ZOMBIE_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_ZOMBIE_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.BLOCK_AMETHYST_BLOCK_STEP;
    }

    public boolean tryAttack(Entity target) {
        boolean bl = super.tryAttack(target);
        if (bl && this.getMainHandStack().isEmpty() && target instanceof LivingEntity) {
            float f = this.world.getLocalDifficulty(this.getBlockPos()).getLocalDifficulty();

        }

        return bl;
    }


    protected ItemStack getSkull() {
        return ItemStack.EMPTY;
    }
}
