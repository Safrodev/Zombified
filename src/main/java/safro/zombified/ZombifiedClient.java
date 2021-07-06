package safro.zombified;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.entity.IronGolemEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import safro.zombified.entity.MinerZombieEntity;
import safro.zombified.entity.render.*;

import java.util.UUID;

public class ZombifiedClient implements ClientModInitializer {

    public static final Identifier PacketID = new Identifier("zombified", "spawn_packet");


    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.INSTANCE.register(Zombified.ARCTIC, ArcticZombieRenderer::new);
        EntityRendererRegistry.INSTANCE.register(Zombified.INFERNAL, InfernalZombieRenderer::new);
        EntityRendererRegistry.INSTANCE.register(Zombified.CRYSTAL, CrystalZombieRenderer::new);
        EntityRendererRegistry.INSTANCE.register(Zombified.MINER, MinerZombieRenderer::new);
        EntityRendererRegistry.INSTANCE.register(Zombified.SLUDGE, SludgeZombieRenderer::new);
        EntityRendererRegistry.INSTANCE.register(Zombified.BESERKER, BeserkerZombieRenderer::new);
        // EntityRendererRegistry.INSTANCE.register(Zombified.BRUTE, BruteZombieRenderer::new);

        EntityRendererRegistry.INSTANCE.register(Zombified.ENERGY_CHARGE_PROJECTILE_ENTITY_TYPE, (context) ->
                new FlyingItemEntityRenderer(context));
        receiveEntityPacket();
    }

        public void receiveEntityPacket() {
            ClientSidePacketRegistry.INSTANCE.register(PacketID, (ctx, byteBuf) -> {
                EntityType<?> et = Registry.ENTITY_TYPE.get(byteBuf.readVarInt());
                UUID uuid = byteBuf.readUuid();
                int entityId = byteBuf.readVarInt();
                Vec3d pos = EntitySpawnPacket.PacketBufUtil.readVec3d(byteBuf);
                float pitch = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
                float yaw = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
                ctx.getTaskQueue().execute(() -> {
                    if (MinecraftClient.getInstance().world == null)
                        throw new IllegalStateException("Tried to spawn entity in a null world!");
                    Entity e = et.create(MinecraftClient.getInstance().world);
                    if (e == null)
                        throw new IllegalStateException("Failed to create instance of entity \"" + Registry.ENTITY_TYPE.getId(et) + "\"!");
                    e.updateTrackedPosition(pos);
                    e.setPos(pos.x, pos.y, pos.z);
                    e.setPitch(pitch);
                    e.setYaw(yaw);
                    e.setId(entityId);
                    e.setUuid(uuid);
                    MinecraftClient.getInstance().world.addEntity(entityId, e);
                });
            });
        }
    }
