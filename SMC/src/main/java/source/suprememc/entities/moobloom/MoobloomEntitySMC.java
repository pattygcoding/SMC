package source.suprememc.entities.moobloom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IShearable;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import source.suprememc.init.objects.SMCBlocks;
import source.suprememc.init.objects.SMCEntities;
import source.suprememc.util.SMCSustainability;

@SuppressWarnings("deprecation")
public class MoobloomEntitySMC extends CowEntity implements IShearable, net.minecraftforge.common.IForgeShearable {
	private Effect effect;
	private int effectDuration;

	public MoobloomEntitySMC(EntityType<? extends MoobloomEntitySMC> p_i50257_1_, World p_i50257_2_) {
		super(p_i50257_1_, p_i50257_2_);
	}


	public void shear(SoundCategory p_230263_1_) {
		this.level.playSound((PlayerEntity)null, this, SoundEvents.MOOSHROOM_SHEAR, p_230263_1_, 1.0F, 1.0F);
		if (!this.level.isClientSide()) {
			((ServerWorld)this.level).sendParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY(0.5D), this.getZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
			this.remove();
			CowEntity cowentity = EntityType.COW.create(this.level);
			cowentity.moveTo(this.getX(), this.getY(), this.getZ(), this.yRot, this.xRot);
			cowentity.setHealth(this.getHealth());
			cowentity.yBodyRot = this.yBodyRot;
			if (this.hasCustomName()) {
				cowentity.setCustomName(this.getCustomName());
				cowentity.setCustomNameVisible(this.isCustomNameVisible());
			}

			if (this.isPersistenceRequired()) {
				cowentity.setPersistenceRequired();
			}

			cowentity.setInvulnerable(this.isInvulnerable());
			this.level.addFreshEntity(cowentity);

			for(int i = 0; i < 5; ++i) {
				this.level.addFreshEntity(new ItemEntity(this.level, this.getX(), this.getY(1.0D), this.getZ(), new ItemStack(SMCBlocks.BUTTERCUP.getBlock())));
			}
		}

	}

	public boolean readyForShearing() {
		return this.isAlive() && !this.isBaby();
	}

	public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
		super.addAdditionalSaveData(p_213281_1_);
		if (this.effect != null) {
			p_213281_1_.putByte("EffectId", (byte)Effect.getId(this.effect));
			p_213281_1_.putInt("EffectDuration", this.effectDuration);
		}

	}

	public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
		super.readAdditionalSaveData(p_70037_1_);
		if (p_70037_1_.contains("EffectId", 1)) {
			this.effect = Effect.byId(p_70037_1_.getByte("EffectId"));
		}

		if (p_70037_1_.contains("EffectDuration", 3)) {
			this.effectDuration = p_70037_1_.getInt("EffectDuration");
		}

	}




	public MoobloomEntitySMC getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
		MoobloomEntitySMC MoobloomEntitySMC = SMCEntities.MOOBLOOM.create(p_241840_1_);
		return MoobloomEntitySMC;
	}

	@Override
	public void aiStep() 
	{
		super.aiStep();
		if (!this.level.isClientSide) 
		{
			if(!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this))  return;
			BlockState blockstate = SMCBlocks.BUTTERCUP.defaultBlockState();
			for(int l = 0; l < 4; ++l) 
			{
				int i = MathHelper.floor(this.getX() + (double)((float)(l % 2 * 2 - 1) * 0.25F));
				int j = MathHelper.floor(this.getY());
				int k = MathHelper.floor(this.getZ() + (double)((float)(l / 2 % 2 * 2 - 1) * 0.25F));
				BlockPos blockpos = new BlockPos(i, j, k);
				if (this.level.isEmptyBlock(blockpos) && SMCSustainability.isPlant(SMCSustainability.PLAINS, this.level.getBlockState(blockpos.below()).getBlock()) && blockstate.canSurvive(this.level, blockpos)) 
				{
	         		this.level.setBlockAndUpdate(blockpos, blockstate);
				}
	         }
		}
	}

	@Override
	public boolean isShearable(@javax.annotation.Nonnull ItemStack item, World world, BlockPos pos) {
		return readyForShearing();
	}

	@javax.annotation.Nonnull
	@Override
	public java.util.List<ItemStack> onSheared(@javax.annotation.Nullable PlayerEntity player, @javax.annotation.Nonnull ItemStack item, World world, BlockPos pos, int fortune) {
		world.playSound(null, this, SoundEvents.MOOSHROOM_SHEAR, player == null ? SoundCategory.BLOCKS : SoundCategory.PLAYERS, 1.0F, 1.0F);
		if (!world.isClientSide()) {
			((ServerWorld)this.level).sendParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY(0.5D), this.getZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
			this.remove();
			CowEntity cowentity = EntityType.COW.create(this.level);
			cowentity.moveTo(this.getX(), this.getY(), this.getZ(), this.yRot, this.xRot);
			cowentity.setHealth(this.getHealth());
			cowentity.yBodyRot = this.yBodyRot;
			if (this.hasCustomName()) {
				cowentity.setCustomName(this.getCustomName());
				cowentity.setCustomNameVisible(this.isCustomNameVisible());
			}

			if (this.isPersistenceRequired()) {
				cowentity.setPersistenceRequired();
			}

			cowentity.setInvulnerable(this.isInvulnerable());
			this.level.addFreshEntity(cowentity);

			java.util.List<ItemStack> items = new java.util.ArrayList<>();
			for (int i = 0; i < 5; ++i) {
				items.add(new ItemStack(Blocks.DANDELION.getBlock()));
			}

			return items;
		}
		return java.util.Collections.emptyList();
	}



}
