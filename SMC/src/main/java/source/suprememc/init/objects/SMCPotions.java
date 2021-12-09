package source.suprememc.init.objects;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import source.suprememc.potions.PotionSMC;

public class SMCPotions 
{
	public static final List<Potion> POTIONS = new ArrayList<Potion>();
	
	// Hunger
	public static final Potion HUNGER = new PotionSMC("hunger", new EffectInstance(Effects.HUNGER, 900));
	public static final Potion LONG_HUNGER = new PotionSMC("long_hunger", new EffectInstance(Effects.HUNGER, 1800));
	public static final Potion EXTRA_LONG_HUNGER = new PotionSMC("extra_long_hunger", new EffectInstance(Effects.HUNGER, 2400));
	public static final Potion STRONG_HUNGER = new PotionSMC("strong_hunger", new EffectInstance(Effects.HUNGER, 432, 1));
	public static final Potion EXTRA_STRONG_HUNGER = new PotionSMC("extra_strong_hunger", new EffectInstance(Effects.HUNGER, 360, 2));
	public static final Potion ULTRA_STRONG_HUNGER = new PotionSMC("ultra_strong_hunger", new EffectInstance(Effects.HUNGER, 280, 3));
	
	// Saturation
	public static final Potion SATURATION = new PotionSMC("saturation", new EffectInstance(Effects.SATURATION, 80));
	public static final Potion LONG_SATURATION = new PotionSMC("long_saturation", new EffectInstance(Effects.SATURATION, 160));
	public static final Potion EXTRA_LONG_SATURATION = new PotionSMC("extra_long_saturation", new EffectInstance(Effects.SATURATION, 400));
	public static final Potion STRONG_SATURATION = new PotionSMC("strong_saturation", new EffectInstance(Effects.SATURATION, 60, 1));
	public static final Potion EXTRA_STRONG_SATURATION = new PotionSMC("extra_strong_saturation", new EffectInstance(Effects.SATURATION, 40, 2));
	public static final Potion ULTRA_STRONG_SATURATION = new PotionSMC("ultra_strong_saturation", new EffectInstance(Effects.SATURATION, 20, 3));
		
	// Unluck
	public static final Potion UNLUCK = new PotionSMC("unluck", new EffectInstance(Effects.UNLUCK, 6000));
	public static final Potion LONG_UNLUCK = new PotionSMC("long_unluck", new EffectInstance(Effects.UNLUCK, 15000));
	public static final Potion EXTRA_LONG_UNLUCK = new PotionSMC("extra_long_unluck", new EffectInstance(Effects.UNLUCK, 30000));
	public static final Potion STRONG_UNLUCK = new PotionSMC("strong_unluck", new EffectInstance(Effects.UNLUCK, 3000, 1));
	public static final Potion EXTRA_STRONG_UNLUCK = new PotionSMC("extra_strong_unluck", new EffectInstance(Effects.UNLUCK, 1800, 2));
	public static final Potion ULTRA_STRONG_UNLUCK = new PotionSMC("ultra_strong_unluck", new EffectInstance(Effects.UNLUCK, 1200, 3));
		
	// Levitation
	public static final Potion LEVITATION = new PotionSMC("levitation", new EffectInstance(Effects.LEVITATION, 200));
	public static final Potion LONG_LEVITATION = new PotionSMC("long_levitation", new EffectInstance(Effects.LEVITATION, 400));
	public static final Potion EXTRA_LONG_LEVITATION = new PotionSMC("extra_long_levitation", new EffectInstance(Effects.LEVITATION, 600));
	public static final Potion STRONG_LEVITATION = new PotionSMC("strong_levitation", new EffectInstance(Effects.LEVITATION, 160, 1));
	public static final Potion EXTRA_STRONG_LEVITATION = new PotionSMC("extra_strong_levitation", new EffectInstance(Effects.LEVITATION, 120, 2));
	public static final Potion ULTRA_STRONG_LEVITATION = new PotionSMC("ultra_strong_levitation", new EffectInstance(Effects.LEVITATION, 80, 3));
	
	// Luck
	public static final Potion LONG_LUCK = new PotionSMC("long_luck", new EffectInstance(Effects.LUCK, 15000));
	public static final Potion EXTRA_LONG_LUCK = new PotionSMC("extra_long_luck", new EffectInstance(Effects.LUCK, 30000));
	public static final Potion STRONG_LUCK = new PotionSMC("strong_luck", new EffectInstance(Effects.LUCK, 3000, 1));
	public static final Potion EXTRA_STRONG_LUCK = new PotionSMC("extra_strong_luck", new EffectInstance(Effects.LUCK, 1800, 2));
	public static final Potion ULTRA_STRONG_LUCK = new PotionSMC("ultra_strong_luck", new EffectInstance(Effects.LUCK, 1200, 3));
	
	// Wither
	public static final Potion WITHER = new PotionSMC("wither", new EffectInstance(Effects.WITHER, 900));
	public static final Potion LONG_WITHER = new PotionSMC("long_wither", new EffectInstance(Effects.WITHER, 1800));
	public static final Potion EXTRA_LONG_WITHER = new PotionSMC("extra_long_wither", new EffectInstance(Effects.WITHER, 2400));
	public static final Potion STRONG_WITHER = new PotionSMC("strong_wither", new EffectInstance(Effects.WITHER, 432, 1));
	public static final Potion EXTRA_STRONG_WITHER = new PotionSMC("extra_strong_wither", new EffectInstance(Effects.WITHER, 360, 2));
	public static final Potion ULTRA_STRONG_WITHER = new PotionSMC("ultra_strong_wither", new EffectInstance(Effects.WITHER, 280, 3));
	
	// Vanilla Additions
	public static final Potion EXTRA_LONG_NIGHT_VISION = new PotionSMC("extra_long_night_vision", new EffectInstance(Effects.NIGHT_VISION, 24000));
	public static final Potion EXTRA_LONG_INVISIBILITY = new PotionSMC("extra_long_invisibility", new EffectInstance(Effects.INVISIBILITY, 24000));
	public static final Potion EXTRA_LONG_FIRE_RESISTANCE = new PotionSMC("extra_long_fire_resistance", new EffectInstance(Effects.FIRE_RESISTANCE, 24000));
	public static final Potion EXTRA_LONG_LEAPING = new PotionSMC("extra_long_leaping", new EffectInstance(Effects.JUMP, 24000));
	public static final Potion EXTRA_STRONG_LEAPING = new PotionSMC("extra_strong_leaping", new EffectInstance(Effects.JUMP, 1500, 2));
	public static final Potion ULTRA_STRONG_LEAPING = new PotionSMC("ultra_strong_leaping", new EffectInstance(Effects.JUMP, 900, 3));
	public static final Potion EXTRA_LONG_SWIFTNESS = new PotionSMC("extra_long_swiftness", new EffectInstance(Effects.MOVEMENT_SPEED, 24000));
	public static final Potion EXTRA_STRONG_SWIFTNESS = new PotionSMC("extra_strong_swiftness", new EffectInstance(Effects.MOVEMENT_SPEED, 1500, 2));
	public static final Potion ULTRA_STRONG_SWIFTNESS = new PotionSMC("ultra_strong_swiftness", new EffectInstance(Effects.MOVEMENT_SPEED, 900, 3));
	public static final Potion EXTRA_LONG_SLOWNESS = new PotionSMC("extra_long_slowness", new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 24000));
	public static final Potion EXTRA_STRONG_SLOWNESS = new PotionSMC("extra_strong_slowness", new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 1500, 2));
	public static final Potion ULTRA_STRONG_SLOWNESS = new PotionSMC("ultra_strong_slowness", new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 900, 3));
	public static final Potion EXTRA_LONG_WATER_BREATHING = new PotionSMC("extra_long_water_breathing", new EffectInstance(Effects.WATER_BREATHING, 24000));
	public static final Potion EXTRA_STRONG_HEALING = new PotionSMC("extra_strong_healing", new EffectInstance(Effects.HEAL, 1, 2));
	public static final Potion ULTRA_STRONG_HEALING = new PotionSMC("ultra_strong_healing", new EffectInstance(Effects.HEAL, 1, 3));
	public static final Potion EXTRA_STRONG_HARMING = new PotionSMC("extra_strong_harming", new EffectInstance(Effects.HARM, 1, 2));
	public static final Potion ULTRA_STRONG_HARMING = new PotionSMC("ultra_strong_harming", new EffectInstance(Effects.HARM, 1, 3));
	public static final Potion EXTRA_LONG_POISON = new PotionSMC("extra_long_poison", new EffectInstance(Effects.POISON, 2400));
	public static final Potion EXTRA_STRONG_POISON = new PotionSMC("extra_strong_poison", new EffectInstance(Effects.POISON, 360, 2));
	public static final Potion ULTRA_STRONG_POISON = new PotionSMC("ultra_strong_poison", new EffectInstance(Effects.POISON, 280, 3));
	public static final Potion EXTRA_LONG_REGENERATION = new PotionSMC("extra_long_regeneration", new EffectInstance(Effects.REGENERATION, 2400));
	public static final Potion EXTRA_STRONG_REGENERATION = new PotionSMC("extra_strong_regeneration", new EffectInstance(Effects.REGENERATION, 360, 2));
	public static final Potion ULTRA_STRONG_REGENERATION = new PotionSMC("ultra_strong_regeneration", new EffectInstance(Effects.REGENERATION, 280, 3));
	public static final Potion EXTRA_LONG_STRENGTH = new PotionSMC("extra_long_strength", new EffectInstance(Effects.DAMAGE_BOOST, 24000));
	public static final Potion EXTRA_STRONG_STRENGTH = new PotionSMC("extra_strong_strength", new EffectInstance(Effects.DAMAGE_BOOST, 1500, 2));
	public static final Potion ULTRA_STRONG_STRENGTH = new PotionSMC("ultra_strong_strength", new EffectInstance(Effects.DAMAGE_BOOST, 900, 3));
	public static final Potion EXTRA_LONG_WEAKNESS = new PotionSMC("extra_long_weakness", new EffectInstance(Effects.WEAKNESS, 24000));
	public static final Potion STRONG_WEAKNESS = new PotionSMC("strong_weakness", new EffectInstance(Effects.WEAKNESS, 1800, 2));
	public static final Potion EXTRA_STRONG_WEAKNESS = new PotionSMC("extra_strong_weakness", new EffectInstance(Effects.WEAKNESS, 1500, 2));
	public static final Potion ULTRA_STRONG_WEAKNESS = new PotionSMC("ultra_strong_weakness", new EffectInstance(Effects.WEAKNESS, 900, 3));
	public static final Potion EXTRA_LONG_SLOW_FALLING = new PotionSMC("extra_long_slow_falling", new EffectInstance(Effects.SLOW_FALLING, 9600));
			
}
