package source.suprememc.entities.netheritetrident;

import javax.annotation.Nullable;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import source.suprememc.init.objects.SMCEntities;

public class NetheriteTridentEntitySMC extends AbstractArrowEntity 
{
	private static final DataParameter<Byte> ID_LOYALTY = EntityDataManager.defineId(NetheriteTridentEntitySMC.class, DataSerializers.BYTE);
	private static final DataParameter<Boolean> ID_FOIL = EntityDataManager.defineId(NetheriteTridentEntitySMC.class, DataSerializers.BOOLEAN);
	private ItemStack tridentItem = new ItemStack(Items.TRIDENT);
	private boolean dealtDamage;
	public int clientSideReturnTridentTickCount;

	public NetheriteTridentEntitySMC(EntityType<? extends NetheriteTridentEntitySMC> p_i50148_1_, World p_i50148_2_) {
		super(p_i50148_1_, p_i50148_2_);
	}

	public NetheriteTridentEntitySMC(World world, LivingEntity entity, ItemStack itemStack) 
	{
		super(SMCEntities.NETHERITE_TRIDENT, entity, world);
		this.tridentItem = itemStack.copy();
		this.entityData.set(ID_LOYALTY, (byte)EnchantmentHelper.getLoyalty(itemStack));
		this.entityData.set(ID_FOIL, itemStack.hasFoil());
	}

	@OnlyIn(Dist.CLIENT)
	public NetheriteTridentEntitySMC(World world, double x, double y, double z) 
	{
		super(SMCEntities.NETHERITE_TRIDENT, x, y, z, world);
	}

	@Override
	protected void defineSynchedData() 
	{
		super.defineSynchedData();
		this.entityData.define(ID_LOYALTY, (byte)0);
		this.entityData.define(ID_FOIL, false);
	}

	@Override
	public void tick() 
	{
		if (this.inGroundTime > 4) this.dealtDamage = true;
		Entity entity = this.getOwner();
		if ((this.dealtDamage || this.isNoPhysics()) && entity != null) {
			int i = this.entityData.get(ID_LOYALTY);
			if (i > 0 && !this.isAcceptibleReturnOwner()) 
			{
				if (!this.level.isClientSide && this.pickup == AbstractArrowEntity.PickupStatus.ALLOWED) this.spawnAtLocation(this.getPickupItem(), 0.1F);
				this.remove();
			} 
			else if (i > 0) 
			{
				this.setNoPhysics(true);
				Vector3d vector3d = new Vector3d(entity.getX() - this.getX(), entity.getEyeY() - this.getY(), entity.getZ() - this.getZ());
				this.setPosRaw(this.getX(), this.getY() + vector3d.y * 0.015D * (double)i, this.getZ());
				if (this.level.isClientSide) this.yOld = this.getY();
				double d0 = 0.05D * (double)i;
				this.setDeltaMovement(this.getDeltaMovement().scale(0.95D).add(vector3d.normalize().scale(d0)));
				if (this.clientSideReturnTridentTickCount == 0) this.playSound(SoundEvents.TRIDENT_RETURN, 10.0F, 1.0F);
				++this.clientSideReturnTridentTickCount;
			}
		}
		super.tick();
	}

	private boolean isAcceptibleReturnOwner() 
	{
		Entity entity = this.getOwner();
		if (entity != null && entity.isAlive()) return !(entity instanceof ServerPlayerEntity) || !entity.isSpectator();
		else return false;
	}

	@Override
	protected ItemStack getPickupItem() 
	{
		return this.tridentItem.copy();
	}

	@OnlyIn(Dist.CLIENT)
	public boolean isFoil() 
	{
		return this.entityData.get(ID_FOIL);
	}

	@Override
	@Nullable
	protected EntityRayTraceResult findHitEntity(Vector3d vec3d, Vector3d vec3d2) 
	{
		return this.dealtDamage ? null : super.findHitEntity(vec3d, vec3d2);
	}

	@Override
	protected void onHitEntity(EntityRayTraceResult rayTrace) 
	{
		Entity entity = rayTrace.getEntity();
		float f = 8.0F;
		if (entity instanceof LivingEntity) 
		{
			LivingEntity livingentity = (LivingEntity)entity;
			f += EnchantmentHelper.getDamageBonus(this.tridentItem, livingentity.getMobType());
		}
		Entity entity1 = this.getOwner();
		DamageSource damagesource = DamageSource.trident(this, (Entity)(entity1 == null ? this : entity1));
		this.dealtDamage = true;
		SoundEvent soundevent = SoundEvents.TRIDENT_HIT;
		if(entity.hurt(damagesource, f)) 
		{
			if(entity.getType() == EntityType.ENDERMAN) return;
			if(entity instanceof LivingEntity) 
			{
				LivingEntity livingentity1 = (LivingEntity)entity;
				if(entity1 instanceof LivingEntity) 
				{
					EnchantmentHelper.doPostHurtEffects(livingentity1, entity1);
					EnchantmentHelper.doPostDamageEffects((LivingEntity)entity1, livingentity1);
				}
				this.doPostHurtEffects(livingentity1);
			}
		}
		this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01D, -0.1D, -0.01D));
		float f1 = 1.0F;
		if (this.level instanceof ServerWorld && this.level.isThundering() && EnchantmentHelper.hasChanneling(this.tridentItem)) 
		{
			BlockPos blockpos = entity.blockPosition();
			if (this.level.canSeeSky(blockpos)) {
				LightningBoltEntity lightningboltentity = EntityType.LIGHTNING_BOLT.create(this.level);
				lightningboltentity.moveTo(Vector3d.atBottomCenterOf(blockpos));
				lightningboltentity.setCause(entity1 instanceof ServerPlayerEntity ? (ServerPlayerEntity)entity1 : null);
				this.level.addFreshEntity(lightningboltentity);
				soundevent = SoundEvents.TRIDENT_THUNDER;
				f1 = 5.0F;
			}
		}
		this.playSound(soundevent, f1, 1.0F);
	}

	@Override
	protected SoundEvent getDefaultHitGroundSoundEvent() 
	{
		return SoundEvents.TRIDENT_HIT_GROUND;
	}

	@Override
	public void playerTouch(PlayerEntity playerEntity) 
	{
		Entity entity = this.getOwner();
		if (entity == null || entity.getUUID() == playerEntity.getUUID()) super.playerTouch(playerEntity);
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compoundNBT) 
	{
		super.readAdditionalSaveData(compoundNBT);
		if (compoundNBT.contains("Trident", 10)) this.tridentItem = ItemStack.of(compoundNBT.getCompound("Trident"));
		this.dealtDamage = compoundNBT.getBoolean("DealtDamage");
		this.entityData.set(ID_LOYALTY, (byte)EnchantmentHelper.getLoyalty(this.tridentItem));
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compoundNBT) 
	{
		super.addAdditionalSaveData(compoundNBT);
		compoundNBT.put("Trident", this.tridentItem.save(new CompoundNBT()));
		compoundNBT.putBoolean("DealtDamage", this.dealtDamage);
	}

	@Override
	public void tickDespawn() 
	{
		int i = this.entityData.get(ID_LOYALTY);
		if(this.pickup != AbstractArrowEntity.PickupStatus.ALLOWED || i <= 0) super.tickDespawn();
	}

	@Override
	protected float getWaterInertia() 
	{
		return 0.99F;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public boolean shouldRender(double x, double y, double z) 
	{
		return true;
	}
}