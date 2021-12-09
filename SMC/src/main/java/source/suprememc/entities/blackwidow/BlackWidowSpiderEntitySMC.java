package source.suprememc.entities.blackwidow;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

public class BlackWidowSpiderEntitySMC extends SpiderEntity {
	public BlackWidowSpiderEntitySMC(EntityType<? extends BlackWidowSpiderEntitySMC> p_i50214_1_, World p_i50214_2_) {
		super(p_i50214_1_, p_i50214_2_);
	}

	public static AttributeModifierMap.MutableAttribute attribute() {
		return SpiderEntity.createAttributes().add(Attributes.MAX_HEALTH, 14.0D);
	}

	public boolean doHurtTarget(Entity p_70652_1_) {
		if (super.doHurtTarget(p_70652_1_)) {
			if (p_70652_1_ instanceof LivingEntity) {
				int i = 0;
				if (this.level.getDifficulty() == Difficulty.NORMAL) {
					i = 7;
				} else if (this.level.getDifficulty() == Difficulty.HARD) {
					i = 15;
				}

				if (i > 0) {
					((LivingEntity)p_70652_1_).addEffect(new EffectInstance(Effects.WITHER, i * 20, 2));
					((LivingEntity)p_70652_1_).addEffect(new EffectInstance(Effects.DIG_SLOWDOWN, i * 20, 2));
					((LivingEntity)p_70652_1_).addEffect(new EffectInstance(Effects.WEAKNESS, i * 20, 2));
					((LivingEntity)p_70652_1_).addEffect(new EffectInstance(Effects.BLINDNESS, i * 20, 0));
					((LivingEntity)p_70652_1_).addEffect(new EffectInstance(Effects.NIGHT_VISION, i * 20, 0));
					
					
				}
			}

			return true;
		} else {
			return false;
		}
	}


}
