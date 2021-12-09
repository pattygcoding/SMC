package source.suprememc.init.objects;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import source.suprememc.enchantments.types.BlastTouchSMC;
import source.suprememc.enchantments.types.BountySMC;
import source.suprememc.enchantments.types.PoisoningSMC;
import source.suprememc.enchantments.types.WitheringSMC;

public class SMCEnchantments 
{
	public static final List<Enchantment> ENCHANTMENTS = new ArrayList<Enchantment>();
	
	public static final Enchantment BOUNTY = new BountySMC(Rarity.VERY_RARE, EnchantmentType.WEAPON, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND}, "bounty");
	public static final Enchantment BLAST_TOUCH = new BlastTouchSMC(Rarity.RARE, EnchantmentType.DIGGER, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND}, "blast_touch");
	public static final Enchantment POISON_BLADE = new PoisoningSMC(Rarity.RARE, EnchantmentType.WEAPON, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND}, "poison_blade");
	public static final Enchantment WITHER_BLADE = new WitheringSMC(Rarity.RARE, EnchantmentType.WEAPON, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND}, "wither_blade");
}
