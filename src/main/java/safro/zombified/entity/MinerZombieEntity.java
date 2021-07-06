package safro.zombified.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.GameRules;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

import java.util.Random;

@SuppressWarnings("EntityConstructor")
public class MinerZombieEntity extends ZombieEntity {
    public MinerZombieEntity(EntityType<? extends MinerZombieEntity> entityType, World world) {
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
        return false;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_ZOMBIE_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_ZOMBIE_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ZOMBIE_DEATH;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.ENTITY_ZOMBIE_STEP;
    }

    public boolean tryAttack(Entity target) {
        boolean bl = super.tryAttack(target);
        if (bl && this.getMainHandStack().isEmpty() && target instanceof LivingEntity) {
            float f = this.world.getLocalDifficulty(this.getBlockPos()).getLocalDifficulty();

        }

        return bl;
    }

    protected void initEquipment(LocalDifficulty difficulty) {
        super.initEquipment(difficulty);
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_PICKAXE));
    }

    @Override
    public void tickMovement() {
        if (this.isAlive()) {
            int m;
            int l;
                if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
                    l = MathHelper.floor(this.getY());
                    m = MathHelper.floor(this.getX());
                    int n = MathHelper.floor(this.getZ());
                    boolean bl = false;

                    for(int o = -1; o <= 1; ++o) {
                        for(int p = -1; p <= 1; ++p) {
                            for(int q = 0; q <= 3; ++q) {
                                int r = m + o;
                                int s = l + q;
                                int t = n + p;
                                BlockPos blockPos = new BlockPos(r, s, t);
                                BlockState blockState = this.world.getBlockState(blockPos);
                                if (canDestroy(blockState)) {
                                    bl = this.world.breakBlock(blockPos, true, this) || bl;
                                    this.swingHand(Hand.MAIN_HAND);
                                }
                            }
                        }
                    }

                    if (bl) {
                        this.world.syncWorldEvent((PlayerEntity)null, 1022, this.getBlockPos(), 0);
                    }
                }
            super.tickMovement();
        }
    }

    public static boolean canDestroy(BlockState block) {
        return !block.isAir() && block.isOf(Blocks.STONE) || block.isOf(Blocks.COBBLESTONE);
    }

    protected ItemStack getSkull() {
        return ItemStack.EMPTY;
    }
}

