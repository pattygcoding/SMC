package source.suprememc.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.ResourceLocation;
import source.suprememc.SupremeMC;
import source.suprememc.init.objects.SMCEnchantments;

public class EnchantmentSMC extends Enchantment
{
	protected EnchantmentSMC(Rarity rarity, EnchantmentType type, EquipmentSlotType[] slots, String name) 
	{
		super(rarity, type, slots);
		setRegistryName(new ResourceLocation(SupremeMC.MOD_ID, name));
		SMCEnchantments.ENCHANTMENTS.add(this);
	}
}