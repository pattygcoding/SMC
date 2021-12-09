package source.suprememc.init.objects;

import net.minecraft.item.Food;
import net.minecraft.potion.Effects;
import source.suprememc.util.effects.EffectHandlerSMC;

@SuppressWarnings("deprecation")
public class SMCFoods 
{
	public static final Food CALAMARI = (new Food.Builder()).nutrition(2).saturationMod(0.1F).meat().build();
	public static final Food CARAMEL = (new Food.Builder()).nutrition(1).saturationMod(0.1F).effect(EffectHandlerSMC.foodEffect(Effects.MOVEMENT_SPEED, 5, 2), 0.8F).build();
	public static final Food CHEESE = (new Food.Builder()).nutrition(2).saturationMod(0.3F).build();
	public static final Food CHEESE_SANDWICH = (new Food.Builder()).nutrition(8).saturationMod(0.6F).build();
	public static final Food CHICKEN_SANDWICH = (new Food.Builder()).nutrition(10).saturationMod(0.8F).build();
	public static final Food CHOCOLATE = (new Food.Builder()).nutrition(3).saturationMod(0.2F).alwaysEat().effect(EffectHandlerSMC.foodEffect(Effects.MOVEMENT_SPEED, 10, 2), 1.0F).build();
	public static final Food CHOCOLATE_ICE_CREAM = (new Food.Builder()).nutrition(6).saturationMod(0.4F).effect(EffectHandlerSMC.foodEffect(Effects.MOVEMENT_SPEED, 15, 3), 1.0F).build();
	public static final Food COOKED_CALAMARI = (new Food.Builder()).nutrition(6).saturationMod(0.8F).meat().build();
	public static final Food COOKED_FUGU = (new Food.Builder()).nutrition(7).saturationMod(0.8F).effect(EffectHandlerSMC.foodEffect(Effects.POISON, 2, 1), 0.01F).effect(EffectHandlerSMC.foodEffect(Effects.CONFUSION, 15, 1), 0.1F).build();
	public static final Food CORN = (new Food.Builder()).nutrition(2).saturationMod(0.3F).build();
	public static final Food DARK_CHOCOLATE = (new Food.Builder()).nutrition(2).saturationMod(0.1F).alwaysEat().effect(EffectHandlerSMC.foodEffect(Effects.MOVEMENT_SPEED, 5, 1), 1.0F).build();
	public static final Food FRIED_EGG = (new Food.Builder()).nutrition(4).saturationMod(0.4F).build();
	public static final Food FUGU = (new Food.Builder()).nutrition(2).saturationMod(0.1F).effect(EffectHandlerSMC.foodEffect(Effects.POISON, 5, 2), 0.1F).effect(EffectHandlerSMC.foodEffect(Effects.CONFUSION, 30, 1), 0.5F).build();
	public static final Food GRAPES = (new Food.Builder()).nutrition(2).saturationMod(0.3F).build();
	public static final Food ICE_CREAM_CONE = (new Food.Builder()).nutrition(1).saturationMod(0.1F).build();
	public static final Food RESET_HUNGER_BAR = (new Food.Builder()).nutrition(0).saturationMod(0.0F).effect(EffectHandlerSMC.foodEffect(Effects.SATURATION, 1, -1), 1.0F).alwaysEat().build();
	public static final Food TOMATO = (new Food.Builder()).nutrition(2).saturationMod(0.4F).build();
	public static final Food VANILLA_ICE_CREAM = (new Food.Builder()).nutrition(6).saturationMod(0.4F).build();
}
