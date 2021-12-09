package safro.zombified.entity.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.IronGolemEntityRenderer;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.ZombieEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.IronGolemEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3f;
import safro.zombified.entity.BruteZombieEntity;
import safro.zombified.entity.model.BruteZombieEntityModel;

@Environment(EnvType.CLIENT)
public class BruteZombieRenderer extends MobEntityRenderer<BruteZombieEntity, BruteZombieEntityModel<BruteZombieEntity>> {
    private static final Identifier TEXTURE = new Identifier("zombified","textures/entity/brute_zombie.png");

    public BruteZombieRenderer(EntityRendererFactory.Context context) {
        super(context, new BruteZombieEntityModel(context.getPart(EntityModelLayers.IRON_GOLEM)), 0.7F);
    }

    public void setupTransforms(BruteZombieEntity ironGolemEntity, MatrixStack matrixStack, float f, float g, float h) {
        super.setupTransforms(ironGolemEntity, matrixStack, f, g, h);
        if (!((double)ironGolemEntity.limbDistance < 0.01D)) {
            float i = 13.0F;
            float j = ironGolemEntity.limbAngle - ironGolemEntity.limbDistance * (1.0F - h) + 6.0F;
            float k = (Math.abs(j % 13.0F - 6.5F) - 3.25F) / 3.25F;
            matrixStack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(6.5F * k));
        }
    }

    public Identifier getTexture(BruteZombieEntity ironGolemEntity) {
        return TEXTURE;
    }
}
