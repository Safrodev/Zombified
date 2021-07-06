package safro.zombified.item;

import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class FleshHammer extends SwordItem {

    public FleshHammer(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target.getGroup().equals(EntityGroup.UNDEAD)) {
            target.damage(target.getRecentDamageSource(), 3.0F);
        }
        return super.postHit(stack, target, attacker);
    }
}
