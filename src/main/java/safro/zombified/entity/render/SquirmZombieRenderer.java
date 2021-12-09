package safro.zombified.entity.render;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.SilverfishEntityModel;
import net.minecraft.util.Identifier;
import safro.zombified.entity.SquirmZombieEntity;

public class SquirmZombieRenderer extends MobEntityRenderer<SquirmZombieEntity, SilverfishEntityModel<SquirmZombieEntity>> {
    private static final Identifier TEXTURE = new Identifier("zombified", "textures/entity/squirm_zombie.png");

    public SquirmZombieRenderer(EntityRendererFactory.Context context) {
        super(context, new SilverfishEntityModel(context.getPart(EntityModelLayers.SILVERFISH)), 0.3F);
    }

    protected float getLyingAngle(SquirmZombieEntity silverfishEntity) {
        return 180.0F;
    }

    public Identifier getTexture(SquirmZombieEntity silverfishEntity) {
        return TEXTURE;
    }
}
