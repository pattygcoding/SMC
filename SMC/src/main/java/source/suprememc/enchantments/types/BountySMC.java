package source.suprememc.enchantments.types;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import source.suprememc.enchantments.EnchantmentSMC;

public class BountySMC extends EnchantmentSMC
{
	public BountySMC(Rarity rarity, EnchantmentType typeIn, EquipmentSlotType[] slots, String name) 
	{
		super(rarity, typeIn, slots, name);
	}
	@Override
	public int getMinCost(int enchantmentLevel) { return 20 * enchantmentLevel; }
	
	@Override
	public int getMaxCost(int enchantmentLevel) { return this.getMinCost(enchantmentLevel) + 10; }
	
	
	@Override
	public int getMaxLevel() { return 1; }
	
}
