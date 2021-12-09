package source.suprememc.potions;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import source.suprememc.util.RegUtil;

public class PotionSMC extends Potion
{
	public PotionSMC(String name, EffectInstance... effects) 
	{
		super(effects);
		RegUtil.registerPotion(this, name);
	}
}
