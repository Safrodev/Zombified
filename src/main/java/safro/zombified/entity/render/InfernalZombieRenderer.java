package safro.zombified.entity.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ZombieEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.GlowSquidEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import safro.zombified.entity.InfernalZombieEntity;

@Environment(EnvType.CLIENT)
public class InfernalZombieRenderer extends ZombieEntityRenderer {
    private static final Identifier TEXTURE = new Identifier("zombified","textures/entity/infernal_zombie.png");

    public InfernalZombieRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    protected void scale(ZombieEntity zombieEntity, MatrixStack matrixStack, float f) {
        float g = 1.0625F;
        matrixStack.scale(1.0625F, 1.0625F, 1.0625F);
        super.scale(zombieEntity, matrixStack, f);
    }

    protected int getBlockLight(InfernalZombieEntity infernalZombieEntity, BlockPos blockPos) {
        int i = (int) MathHelper.method_37166(0.0F, 15.0F, 1.0F - 100 / 10.0F);
        return i == 15 ? 15 : Math.max(i, super.getBlockLight(infernalZombieEntity, blockPos));
    }

    public Identifier getTexture(ZombieEntity zombieEntity) {
        return TEXTURE;
    }
}
