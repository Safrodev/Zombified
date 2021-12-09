package safro.zombified.entity.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ZombieEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

@Environment(EnvType.CLIENT)
public class InfernalZombieRenderer extends ZombieEntityRenderer {
    private static final Identifier TEXTURE = new Identifier("zombified","textures/entity/infernal_zombie.png");

    public InfernalZombieRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    protected void scale(ZombieEntity zombieEntity, MatrixStack matrixStack, float f) {
        matrixStack.scale(1.0625F, 1.0625F, 1.0625F);
        super.scale(zombieEntity, matrixStack, f);
    }

    protected int getBlockLight(ZombieEntity entity, BlockPos blockPos) {
        return 10;
    }

    public Identifier getTexture(ZombieEntity zombieEntity) {
        return TEXTURE;
    }
}
