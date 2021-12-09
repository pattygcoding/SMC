package source.suprememc.items.armor;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.init.objects.SMCItems;
import source.suprememc.util.RegUtil;
import source.suprememc.util.effects.EffectHandlerSMC;
import source.suprememc.util.effects.EffectSMC;

public class ArmorItemSMC extends ArmorItem
{
	/**
	 * @link net.minecraft.item.ArmorMaterial
	 */
	public static final ArmorMaterialSMC AMETHYST = new ArmorMaterialSMC("amethyst", new int[] {2, 6, 7, 3}, 27, 16, SMCItems.AMETHYST_SHARD, 0.0F, 0.0F);
	public static final ArmorMaterialSMC AQUAMARINE = new ArmorMaterialSMC("aquamarine", new int[] {3, 6, 8, 3}, 33, 10, SMCItems.AQUAMARINE, 2.0F, 0.0F);
	public static final ArmorMaterialSMC BEAR_LEATHER = new ArmorMaterialSMC("bear_leather", new int[] {1, 2, 3, 1}, 5, 15, SMCItems.BEAR_LEATHER, 0.0F, 0.0F);
	public static final ArmorMaterialSMC BURNING_DIAMOND = new ArmorMaterialSMC("burning_diamond", new int[] {3, 6, 8, 3}, 33, 10, SMCItems.BURNING_DIAMOND, 2.0F, 0.0F);
	public static final ArmorMaterialSMC COPPER = new ArmorMaterialSMC("copper", new int[] {2, 5, 6, 2}, 12, 14, SMCItems.COPPER_INGOT, 0.0F, 0.0F);
	public static final ArmorMaterialSMC COTTON = new ArmorMaterialSMC("cotton", new int[] {1, 2, 2, 1}, 4, 8, SMCItems.COTTON, 0.0F, 0.0F);
	public static final ArmorMaterialSMC EMERALD = new ArmorMaterialSMC("emerald", new int[] {2, 6, 7, 3}, 24, 12, Items.EMERALD, 0.0F, 0.0F);
	public static final ArmorMaterialSMC RUBY = new ArmorMaterialSMC("ruby", new int[] {3, 6, 8, 4}, 35, 14, SMCItems.RUBY, 2.5F, 0.0F);
	public static final ArmorMaterialSMC TIGERS_EYE = new ArmorMaterialSMC("tigers_eye", new int[] {4, 8, 10, 4}, 51, 28, SMCItems.TIGERS_EYE, 5.0F, 0.05F);
	
	private String name;
	public ArmorItemSMC(IArmorMaterial materialIn, EquipmentSlotType slot) 
	{
		super(materialIn, slot, new Properties().tab(SMCTabs.COMBAT));
		this.name = ((ArmorMaterialSMC)materialIn).getOtherName() + getArmorSubfix(slot);
		RegUtil.registerItem(this, this.name);
	}
	
	private static String getArmorSubfix(EquipmentSlotType slot)
	{
		if(slot == EquipmentSlotType.HEAD) return "_helmet";
		if(slot == EquipmentSlotType.CHEST) return "_chestplate";
		if(slot == EquipmentSlotType.LEGS) return "_leggings";
		if(slot == EquipmentSlotType.FEET) return "_boots";
		else
		{
			System.out.println("ERROR: Invalid armor slot!");
			System.exit(0);
			return null;
		}
	}
	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) 
	{
		if(checkNull(player))
		{
			if
			(
				checkFullSet(SMCItems.BURNING_DIAMOND_HELMET, SMCItems.BURNING_DIAMOND_CHESTPLATE, SMCItems.BURNING_DIAMOND_LEGGINGS, SMCItems.BURNING_DIAMOND_BOOTS, player)
			)
			{
				effectPlayer(player, EffectHandlerSMC.getEffect(EffectSMC.FIRE_RESISTANCE), 1, 1);
			}
			if
			(
				checkFullSet(SMCItems.AQUAMARINE_HELMET, SMCItems.AQUAMARINE_CHESTPLATE, SMCItems.AQUAMARINE_LEGGINGS, SMCItems.AQUAMARINE_BOOTS, player)
			)
			{
				effectPlayer(player, EffectHandlerSMC.getEffect(EffectSMC.CONDUIT_POWER), 1, 0);
				effectPlayer(player, EffectHandlerSMC.getEffect(EffectSMC.DOLPHINS_GRACE), 1, 0);
			}
			if
			(
				checkFullSet(SMCItems.BEAR_LEATHER_HELMET, SMCItems.BEAR_LEATHER_CHESTPLATE, SMCItems.BEAR_LEATHER_LEGGINGS, SMCItems.BEAR_LEATHER_BOOTS, player)
			)
			{
				effectPlayerNightVision(player, EffectHandlerSMC.getEffect(EffectSMC.NIGHT_VISION), 30, 0);
			}
			if
			(
				checkFullSet(SMCItems.TIGERS_EYE_HELMET, SMCItems.TIGERS_EYE_CHESTPLATE, SMCItems.TIGERS_EYE_LEGGINGS, SMCItems.TIGERS_EYE_BOOTS, player)
			)
			{
				effectPlayer(player, EffectHandlerSMC.getEffect(EffectSMC.SPEED), 1, 3);
				effectPlayer(player, EffectHandlerSMC.getEffect(EffectSMC.HASTE), 1, 3);
				effectPlayerNightVision(player, EffectHandlerSMC.getEffect(EffectSMC.NIGHT_VISION), 30, 0);
			}
			
		}
		super.onArmorTick(stack, world, player);
	}

	private boolean checkNull(PlayerEntity player)
	{
		if(player.inventory.getArmor(0) != null && player.inventory.getArmor(1) != null && player.inventory.getArmor(2) != null && player.inventory.getArmor(3) != null) return true;
		else return false;
	}
	private boolean checkFullSet(Item helmet, Item chestplate, Item leggings, Item boots, PlayerEntity player)
	{
		if(player.inventory.getArmor(3).getItem() == helmet && player.inventory.getArmor(2).getItem() == chestplate && player.inventory.getArmor(1).getItem() == leggings && player.inventory.getArmor(0).getItem() == boots) return true;
		else return false;
	}
	private void effectPlayer(PlayerEntity entity, Effect effect, int seconds, int amplifier) 
	{
		if (entity.getEffect(effect) == null || entity.getEffect(effect).getDuration() <= 1) 
		{
			entity.addEffect(new EffectInstance(effect, seconds * 20, amplifier - 1, false, false));
		}
	}
	private void effectPlayerNightVision(PlayerEntity entity, Effect effect, int seconds, int amplifier) 
	{
		if (entity.getEffect(effect) == null || entity.getEffect(effect).getDuration() <= (seconds * 20) - 20) 
		{
			entity.addEffect(new EffectInstance(effect, seconds * 20, amplifier - 1, false, false));
		}
	}
}