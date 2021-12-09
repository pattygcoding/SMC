package source.suprememc.enchantments.types;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import source.suprememc.enchantments.EnchantmentSMC;
import source.suprememc.util.effects.EffectHandlerSMC;
import source.suprememc.util.effects.EffectSMC;

public class PoisoningSMC extends EnchantmentSMC
{
	public PoisoningSMC(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots, String name) {super(rarityIn, typeIn, slots, name);}
	
	@Override
	public int getMinCost(int enchantmentLevel) {return 20 * enchantmentLevel;}
	
	@Override
	public int getMaxCost(int enchantmentLevel) {return this.getMinCost(enchantmentLevel) + 10;}
	
	@Override
	public int getMaxLevel()
	{
		return 5;
	}
	
	@Override
	public void doPostHurt(LivingEntity user, Entity target, int level) 
	{
		EffectHandlerSMC.entityHitByEnchantment(user, target, EffectSMC.POISON, 10, 2, level, true);
		super.doPostHurt(user, target, level);
	}
}