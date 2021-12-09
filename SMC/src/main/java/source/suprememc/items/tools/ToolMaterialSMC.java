package source.suprememc.items.tools;

import net.minecraft.block.Blocks;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import source.suprememc.init.objects.SMCItems;

public class ToolMaterialSMC implements IItemTier
{
	private float attackDamage, efficiency;
	private int durability, harvestLevel, enchantability;
	private Item repairMaterial;
	private String name;
	
	public ToolMaterialSMC(String name, float attackDamage, float efficiency, int durability, int harvestLevel, int enchantability, Item repairMaterial) 
	{
		this.name = name;
		this.attackDamage = attackDamage;
		this.efficiency = efficiency;
		this.durability = durability;
		this.harvestLevel = harvestLevel;
		this.enchantability = enchantability;
		this.repairMaterial = repairMaterial;
	}

	public String getName() { return this.name; }
	@Override
	public float getAttackDamageBonus() { return this.attackDamage; }
	@Override
	public float getSpeed() { return this.efficiency; }
	@Override
	public int getEnchantmentValue() { return this.enchantability; }
	@Override
	public int getLevel() { return this.harvestLevel; }
	@Override
	public int getUses() { return this.durability; }
	@Override
	public Ingredient getRepairIngredient() { return Ingredient.of(this.repairMaterial); }

	public static final ToolMaterialSMC AMETHYST = new ToolMaterialSMC("amethyst", 2.75F, 6.75F, 1118, 3, 16, SMCItems.AMETHYST_SHARD);
	public static final ToolMaterialSMC AQUAMARINE = new ToolMaterialSMC("aquamarine", 3.0F, 8.0F, 1561, 3, 10, SMCItems.AQUAMARINE);
	public static final ToolMaterialSMC BURNING_DIAMOND = new ToolMaterialSMC("burning_diamond", 3.0F, 8.0F, 1561, 3, 10, SMCItems.BURNING_DIAMOND);
	public static final ToolMaterialSMC COPPER = new ToolMaterialSMC("copper", 2.0F, 6.0F, 404, 2, 14, SMCItems.COPPER_INGOT);
	public static final ToolMaterialSMC EMERALD = new ToolMaterialSMC("emerald", 2.5F, 7.0F, 906, 3, 12, Items.EMERALD);
	public static final ToolMaterialSMC FLINT = new ToolMaterialSMC("flint", 1.5F, 5.5F, 188, 2, 10, Items.FLINT);
	public static final ToolMaterialSMC OBSIDIAN = new ToolMaterialSMC("obsidian", 2.0F, 6.0F, 3600, 2, 5, Items.OBSIDIAN);
	public static final ToolMaterialSMC RUBY = new ToolMaterialSMC("ruby", 3.5F, 8.5F, 1796, 3, 14, SMCItems.RUBY);
	public static final ToolMaterialSMC TIGERS_EYE = new ToolMaterialSMC("tigers_eye", 8.0F, 11.0F, 3010, 3, 28, SMCItems.TIGERS_EYE);
	public static final ToolMaterialSMC ADMIN = new ToolMaterialSMC("admin", 3299996.0F, 11.0F, 3010, 3, 28, Blocks.BEDROCK.asItem());


}