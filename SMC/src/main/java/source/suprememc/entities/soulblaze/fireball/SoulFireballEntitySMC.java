package source.suprememc.entities.soulblaze.fireball;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import source.suprememc.init.objects.SMCEntities;

public class SoulFireballEntitySMC extends AbstractFireballEntity 
{
	public SoulFireballEntitySMC(EntityType<? extends SoulFireballEntitySMC> fireball, World world) 
	{
		super(fireball, world);
	}

	public SoulFireballEntitySMC(World world, LivingEntity entity, double x, double y, double z) 
	{
		super(SMCEntities.SOUL_FIREBALL, entity, x, y, z, world);
	}

	public SoulFireballEntitySMC(World world, double x, double y, double z, double px, double py, double pz) 
	{
		super(SMCEntities.SOUL_FIREBALL, x, y, z, px, py, pz, world);
	}

	protected void onHitEntity(EntityRayTraceResult trace) 
	{
		super.onHitEntity(trace);
		if (!this.level.isClientSide) 
		{
			Entity entity = trace.getEntity();
			if(!entity.fireImmune()) 
			{
				Entity entity1 = this.getOwner();
				int i = entity.getRemainingFireTicks();
				entity.setSecondsOnFire(5);
				boolean flag = entity.hurt(DamageSource.fireball(this, entity1), 10.0F);
				if(!flag) entity.setRemainingFireTicks(i);
				else if(entity1 instanceof LivingEntity) this.doEnchantDamageEffects((LivingEntity)entity1, entity);
			}
		}
	}

	@Override
	protected void onHitBlock(BlockRayTraceResult trace) 
	{
		super.onHitBlock(trace);
		if(!this.level.isClientSide) 
		{
			Entity entity = this.getOwner();
			if(entity == null || !(entity instanceof MobEntity) || this.level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING) || net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this.getEntity())) 
			{
				BlockPos blockpos = trace.getBlockPos().relative(trace.getDirection());
				if(this.level.isEmptyBlock(blockpos)) this.level.setBlockAndUpdate(blockpos, AbstractFireBlock.getState(this.level, blockpos));
			}

		}
	}

	@Override
	protected void onHit(RayTraceResult trace) 
	{
		super.onHit(trace);
		if(!this.level.isClientSide) this.remove();
	}

	@Override
	public boolean isPickable() 
	{
		return false;
	}

	@Override
	public boolean hurt(DamageSource damageSource, float damage) 
	{
		return false;
	}
}