package safro.zombified.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import safro.zombified.Zombified;
import safro.zombified.ZombifiedClient;
import safro.zombified.entity.render.EntitySpawnPacket;

@SuppressWarnings("EntityConstructor")
public class EnergyChargeProjectileEntity extends ThrownItemEntity {
    public EnergyChargeProjectileEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public EnergyChargeProjectileEntity(World world, LivingEntity owner) {
        super(Zombified.ENERGY_CHARGE_PROJECTILE_ENTITY_TYPE, owner, world);
    }

    public EnergyChargeProjectileEntity(World world, double x, double y, double z) {
        super(Zombified.ENERGY_CHARGE_PROJECTILE_ENTITY_TYPE, x, y, z, world);
    }

    @Override
    public Packet createSpawnPacket() {
        return EntitySpawnPacket.create(this, ZombifiedClient.PacketID);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemRegistry.ENERGY_CHARGE;
    }

    @Environment(EnvType.CLIENT)
    private ParticleEffect getParticleParameters() {
        ItemStack itemStack = this.getItem();
        return (ParticleEffect) (itemStack.isEmpty() ? ParticleTypes.DRAGON_BREATH : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack));
    }

    @Environment(EnvType.CLIENT)
    public void handleStatus(byte status) {
        if (status == 3) {
            ParticleEffect particleEffect = this.getParticleParameters();

            for (int i = 0; i < 8; ++i) {
                this.world.addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }

    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.damage(DamageSource.thrownProjectile(this, this.getOwner()), 3.0F);

        if (entity instanceof LivingEntity) {
            if (!this.world.isClient) {
                boolean bl = this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING);
                this.world.createExplosion((Entity)null, this.getX(), this.getY(), this.getZ(), 2.0F, bl, bl ? Explosion.DestructionType.DESTROY : Explosion.DestructionType.NONE);
                this.discard();
            }
            entity.playSound(SoundEvents.BLOCK_BEACON_ACTIVATE, 2F, 1F);
            this.discard();
        }
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            this.world.sendEntityStatus(this, (byte) 3);
            this.remove(RemovalReason.DISCARDED);
        }

    }
}

