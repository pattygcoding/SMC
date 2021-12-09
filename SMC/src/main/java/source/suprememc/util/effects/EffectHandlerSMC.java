package source.suprememc.util.effects;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;

public class EffectHandlerSMC
{
	private static void addOrMergePotionEffect(EffectSMC type, LivingEntity entity, EffectInstance effect) 
	{
		if (entity.hasEffect(getEffect(type)))
		{
			Effect eff = effect.getEffect();
			EffectInstance p = entity.getEffect(eff);
			int amp = Math.max(p.getAmplifier(), effect.getAmplifier());
			int dur = p.getDuration() + effect.getDuration();
			entity.addEffect(new EffectInstance(eff, amp, dur, false, false));
		}
		else entity.addEffect(effect);
	}
	
	public static void entityHitByEnchantment(LivingEntity user, Entity target, EffectSMC effect, int baseTime, int additionalTime, int level, boolean seconds)
	{
		if (target instanceof LivingEntity == false) return;
		LivingEntity livingEntity = (LivingEntity) target;
	    if (level > 0) 
	    {
	    	if(seconds == true)
	    	{
	    		EffectHandlerSMC.addOrMergePotionEffect(effect, livingEntity, new EffectInstance(getEffect(effect), ((baseTime*20) + ((additionalTime*20) * level)), level, false, false));
	    	}
	    	else
	    	{
	    		EffectHandlerSMC.addOrMergePotionEffect(effect, livingEntity, new EffectInstance(getEffect(effect), (baseTime + (additionalTime * level)), level, false, false));
	    	}
	    }
	}
	
	public static EffectInstance foodEffect(Effect effect, int seconds, int amplifier)
	{
		return new EffectInstance(effect, seconds * 20, amplifier - 1);
	}
	
	/**
	 * 
	 * @param effect: the effect chosen from the enumeration EffectSMC.java
	 * @return the corresponding effect from the class Effect.class
	 */
	public static Effect getEffect(EffectSMC effect)
	{
		if(effect == EffectSMC.SPEED) return Effect.byId(1);
		if(effect == EffectSMC.SLOWNESS) return Effect.byId(2);
		if(effect == EffectSMC.HASTE) return Effect.byId(3);
		if(effect == EffectSMC.MINING_FATIGUE) return Effect.byId(4);
		if(effect == EffectSMC.STRENGTH) return Effect.byId(5);
		if(effect == EffectSMC.INSTANT_HEALTH) return Effect.byId(6);
		if(effect == EffectSMC.INSTANT_DAMAGE) return Effect.byId(7);
		if(effect == EffectSMC.JUMP_BOOST) return Effect.byId(8);
		if(effect == EffectSMC.NAUSEA) return Effect.byId(9);
		if(effect == EffectSMC.REGENERATION) return Effect.byId(10);
		if(effect == EffectSMC.RESISTANCE) return Effect.byId(11);
		if(effect == EffectSMC.FIRE_RESISTANCE) return Effect.byId(12);
		if(effect == EffectSMC.WATER_BREATHING) return Effect.byId(13);
		if(effect == EffectSMC.INVISIBILITY) return Effect.byId(14);
		if(effect == EffectSMC.BLINDNESS) return Effect.byId(15);
		if(effect == EffectSMC.NIGHT_VISION) return Effect.byId(16);
		if(effect == EffectSMC.HUNGER) return Effect.byId(17);
		if(effect == EffectSMC.WEAKNESS) return Effect.byId(18);
		if(effect == EffectSMC.POISON) return Effect.byId(19);
		if(effect == EffectSMC.WITHER) return Effect.byId(20);
		if(effect == EffectSMC.HEALTH_BOOST) return Effect.byId(21);
		if(effect == EffectSMC.ABSORPTION) return Effect.byId(22);
		if(effect == EffectSMC.SATURATION) return Effect.byId(23);
		if(effect == EffectSMC.GLOWING) return Effect.byId(24);
		if(effect == EffectSMC.LEVITATION) return Effect.byId(25);
		if(effect == EffectSMC.LUCK) return Effect.byId(26);
		if(effect == EffectSMC.BAD_LUCK) return Effect.byId(27);
		if(effect == EffectSMC.SLOW_FALLING) return Effect.byId(28);
		if(effect == EffectSMC.CONDUIT_POWER) return Effect.byId(29);
		if(effect == EffectSMC.DOLPHINS_GRACE) return Effect.byId(30);
		if(effect == EffectSMC.BAD_OMEN) return Effect.byId(31);
		if(effect == EffectSMC.HERO_OF_THE_VILLAGE) return Effect.byId(32);
		return Effect.byId(1);
	}
}
