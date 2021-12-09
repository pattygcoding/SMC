package source.suprememc.entities.miner;

import java.util.EnumSet;
import java.util.Map;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.AbstractIllagerEntity;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.RavagerEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.util.DamageSource;
import net.minecraft.util.GroundPathHelper;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.raid.Raid;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MinerEntitySMC extends AbstractIllagerEntity 
{
	private static final Predicate<Difficulty> DOOR_BREAKING_PREDICATE = (difficulty) -> 
	{
		return difficulty == Difficulty.NORMAL || difficulty == Difficulty.HARD;
	};

	public MinerEntitySMC(EntityType<? extends MinerEntitySMC> entity, World world) 
	{
		super(entity, world);
	}

	@Override
	protected void registerGoals() 
	{
		super.registerGoals();
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new MinerEntitySMC.BreakDoorGoal(this));
		this.goalSelector.addGoal(2, new AbstractIllagerEntity.RaidOpenDoorGoal(this));
		this.goalSelector.addGoal(3, new AbstractRaiderEntity.FindTargetGoal(this, 10.0F));
		this.goalSelector.addGoal(4, new MinerEntitySMC.AttackGoal(this));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, AbstractRaiderEntity.class)).setAlertOthers());
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
		this.goalSelector.addGoal(8, new RandomWalkingGoal(this, 0.6D));
		this.goalSelector.addGoal(9, new LookAtGoal(this, PlayerEntity.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(10, new LookAtGoal(this, MobEntity.class, 8.0F));
	}

	@Override
	protected void customServerAiStep() 
	{
		if(!this.isNoAi() && GroundPathHelper.hasGroundPathNavigation(this)) 
		{
			boolean flag = ((ServerWorld)this.level).isRaided(this.blockPosition());
			((GroundPathNavigator)this.getNavigation()).setCanOpenDoors(flag);
		}
		super.customServerAiStep();
	}

	public static AttributeModifierMap.MutableAttribute attribute() 
	{
		return MonsterEntity.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, (double)0.35F).add(Attributes.FOLLOW_RANGE, 12.0D).add(Attributes.MAX_HEALTH, 24.0D).add(Attributes.ATTACK_DAMAGE, 5.0D);
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compoundNBT) 
	{
		super.addAdditionalSaveData(compoundNBT);
	}

	@Override @OnlyIn(Dist.CLIENT)
	public AbstractIllagerEntity.ArmPose getArmPose() 
	{
		if(this.isAggressive()) return AbstractIllagerEntity.ArmPose.ATTACKING;
		else return this.isCelebrating() ? AbstractIllagerEntity.ArmPose.CELEBRATING : AbstractIllagerEntity.ArmPose.CROSSED;
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compoundNBT) 
	{
		super.readAdditionalSaveData(compoundNBT);
	}

	@Override
	public SoundEvent getCelebrateSound() 
	{
		return SoundEvents.VINDICATOR_CELEBRATE;
	}

	@Override @Nullable
	public ILivingEntityData finalizeSpawn(IServerWorld serverWorld, DifficultyInstance difficultyInstance, SpawnReason spawnReason, @Nullable ILivingEntityData iLivingEntityData, @Nullable CompoundNBT compoundNBT)
	{
		ILivingEntityData ilivingentitydata = super.finalizeSpawn(serverWorld, difficultyInstance, spawnReason, iLivingEntityData, compoundNBT);
		((GroundPathNavigator)this.getNavigation()).setCanOpenDoors(true);
		this.populateDefaultEquipmentSlots(difficultyInstance);
		this.populateDefaultEquipmentEnchantments(difficultyInstance);
		return ilivingentitydata;
	}

	@Override
	protected void populateDefaultEquipmentSlots(DifficultyInstance difficultyInstance) 
	{
		if(this.getCurrentRaid() == null) this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.IRON_PICKAXE));
	}

	@Override
	public boolean isAlliedTo(Entity entity) 
	{
		if(super.isAlliedTo(entity)) return true;
		else if (entity instanceof LivingEntity && ((LivingEntity)entity).getMobType() == CreatureAttribute.ILLAGER)return this.getTeam() == null && entity.getTeam() == null;
		else return false;
	}

	@Override
	public void setCustomName(@Nullable ITextComponent iTextComponet) 
	{
		super.setCustomName(iTextComponet);
	}

	@Override
	protected SoundEvent getAmbientSound() 
	{
		return SoundEvents.VINDICATOR_AMBIENT;
	}

	@Override
	protected SoundEvent getDeathSound() 
	{
		return SoundEvents.VINDICATOR_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource) 
	{
		return SoundEvents.VINDICATOR_HURT;
	}

	@Override
	public void applyRaidBuffs(int groups, boolean param) 
	{
		ItemStack itemstack = new ItemStack(Items.IRON_PICKAXE);
		Raid raid = this.getCurrentRaid();
		int i = 1;
		if(groups > raid.getNumGroups(Difficulty.NORMAL)) i = 2;
		boolean flag = this.random.nextFloat() <= raid.getEnchantOdds();
		if (flag) 
		{
			Map<Enchantment, Integer> map = Maps.newHashMap();
			map.put(Enchantments.SHARPNESS, i);
			EnchantmentHelper.setEnchantments(map, itemstack);
		}
		this.setItemSlot(EquipmentSlotType.MAINHAND, itemstack);
	}

	class AttackGoal extends MeleeAttackGoal 
	{
		public AttackGoal(MinerEntitySMC entity) 
		{
			super(entity, 1.0D, false);
		}

		protected double getAttackReachSqr(LivingEntity livingEntity) 
		{
			if (this.mob.getVehicle() instanceof RavagerEntity) 
			{
				float f = this.mob.getVehicle().getBbWidth() - 0.1F;
				return (double)(f * 2.0F * f * 2.0F + livingEntity.getBbWidth());
			} 
			else return super.getAttackReachSqr(livingEntity);
		}
	}

	static class BreakDoorGoal extends net.minecraft.entity.ai.goal.BreakDoorGoal 
	{
		public BreakDoorGoal(MobEntity mobEntity) 
		{
			super(mobEntity, 6, MinerEntitySMC.DOOR_BREAKING_PREDICATE);
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		@Override
		public boolean canContinueToUse() 
		{
			MinerEntitySMC vindicatorentity = (MinerEntitySMC)this.mob;
			return vindicatorentity.hasActiveRaid() && super.canContinueToUse();
		}

		@Override
		public boolean canUse() 
		{
			MinerEntitySMC vindicatorentity = (MinerEntitySMC)this.mob;
			return vindicatorentity.hasActiveRaid() && vindicatorentity.random.nextInt(10) == 0 && super.canUse();
		}

		@Override
		public void start() 
		{
			super.start();
			this.mob.setNoActionTime(0);
		}
	}
}
