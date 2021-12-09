package source.suprememc.client;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import source.suprememc.SupremeMC;
import source.suprememc.init.objects.SMCItems;
import source.suprememc.items.tools.NetheriteCrossbowItemSMC;

public class RenderItems 
{
	public static void setup()
	{
		ItemModelsProperties.register(SMCItems.NETHERITE_BOW, new ResourceLocation(SupremeMC.MOD_ID, "pull"), (stack, world, entity) ->
		{
			if (entity == null) return 0.0F;
			else return entity.getUseItem() != stack ? 0.0F : (float)(stack.getUseDuration() - entity.getUseItemRemainingTicks()) / 2.0F;
		});
		ItemModelsProperties.register(SMCItems.NETHERITE_BOW, new ResourceLocation(SupremeMC.MOD_ID, "pulling"), (stack, world, entity) ->
		{
			return entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F;

		});
		ItemModelsProperties.register(SMCItems.NETHERITE_FISHING_ROD, new ResourceLocation(SupremeMC.MOD_ID, "cast"), (stack, world, entity) -> 
		{
			if (entity == null) return 0.0F;
			else {
				boolean flag = entity.getMainHandItem() == stack;
				boolean flag1 = entity.getOffhandItem() == stack;
				if (entity.getMainHandItem().getItem() instanceof FishingRodItem) {
					flag1 = false;
				}

				return (flag || flag1) && entity instanceof PlayerEntity && ((PlayerEntity)entity).fishing != null ? 1.0F : 0.0F;
			}
		});
		ItemModelsProperties.register(SMCItems.NETHERITE_CROSSBOW, new ResourceLocation(SupremeMC.MOD_ID,"pull"), (stack, world, entity) -> {
			if (entity == null) {
				return 0.0F;
			} else {
				return NetheriteCrossbowItemSMC.isCharged(stack) ? 0.0F : (float)(stack.getUseDuration() - entity.getUseItemRemainingTicks()) / (float)NetheriteCrossbowItemSMC.getChargeDuration(stack);
			}
		});
		ItemModelsProperties.register(SMCItems.NETHERITE_CROSSBOW, new ResourceLocation(SupremeMC.MOD_ID,"pulling"), (stack, world, entity) -> {
			return entity != null && entity.isUsingItem() && entity.getUseItem() == stack && !NetheriteCrossbowItemSMC.isCharged(stack) ? 1.0F : 0.0F;
		});
		ItemModelsProperties.register(SMCItems.NETHERITE_CROSSBOW, new ResourceLocation(SupremeMC.MOD_ID,"charged"), (stack, world, entity) -> {
			return entity != null && NetheriteCrossbowItemSMC.isCharged(stack) ? 1.0F : 0.0F;
		});
		ItemModelsProperties.register(SMCItems.NETHERITE_CROSSBOW, new ResourceLocation(SupremeMC.MOD_ID,"firework"), (stack, world, entity) -> {
			return entity != null && NetheriteCrossbowItemSMC.isCharged(stack) && NetheriteCrossbowItemSMC.containsChargedProjectile(stack, Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
		});
		ItemModelsProperties.register(SMCItems.NETHERITE_TRIDENT, new ResourceLocation(SupremeMC.MOD_ID,"throwing"), (stack, world, entity) -> {
	         return entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F;
		});

		
		

		
		
		
		

	}
}
