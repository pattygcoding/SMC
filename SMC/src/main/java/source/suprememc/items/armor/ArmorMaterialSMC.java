package source.suprememc.items.armor;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import source.suprememc.SupremeMC;

public class ArmorMaterialSMC implements IArmorMaterial
{
	private static final int[] max_damage_array = new int[]{13, 15, 16, 11};
	private String name;
	private int[] armorPoints;
	private int durability, enchantability;
	private Item repairItem;
	private float toughness;
	private float knockbackResistance;
	
	
	public ArmorMaterialSMC(String name, int[] armorPoints, int durability, int enchantability, Item repairItem, float toughness, float knockbackResistance)
	{
		this.name = name;
		this.armorPoints = armorPoints;
		this.durability = durability;
		this.enchantability = enchantability;
		this.repairItem = repairItem;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
	}
	
	public String getOtherName() { return this.name; }
	@Override
	public int getDefenseForSlot(EquipmentSlotType slot) { return this.armorPoints[slot.getIndex()]; }
	@Override
	public int getDurabilityForSlot(EquipmentSlotType slot) { return max_damage_array[slot.getIndex()] * this.durability; }
	@Override
	public int getEnchantmentValue() { return this.enchantability; }
	@Override
	public String getName() { return SupremeMC.MOD_ID + ":" + this.name; }
	@Override
	public Ingredient getRepairIngredient() { return Ingredient.of(this.repairItem); }
	@Override
	public SoundEvent getEquipSound() { return new SoundEvent(new ResourceLocation("item.armor.equip_diamond")); }
	@Override
	public float getToughness() { return this.toughness; }
	@Override
	public float getKnockbackResistance() { return this.knockbackResistance; }

}