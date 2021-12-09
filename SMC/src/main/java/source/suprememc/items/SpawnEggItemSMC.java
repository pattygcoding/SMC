package source.suprememc.items;

import java.util.Objects;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.spawner.AbstractSpawner;
import source.suprememc.init.misc.SMCTabs;
import source.suprememc.util.RegUtil;

public class SpawnEggItemSMC extends SpawnEggItem 
{

	private EntityType<?> entityType;
	public SpawnEggItemSMC(String name, EntityType<?> entityType, int color1, int color2) 
	{
		super(null, color1, color2, new Properties().tab(SMCTabs.MISCELLANEOUS));
		this.entityType = entityType;
		RegUtil.registerItem(this, name);
	}
	
	
	@Override
	public ActionResultType useOn(ItemUseContext context) 
	{
		World world = context.getLevel();
		if(!(world instanceof ServerWorld)) return ActionResultType.SUCCESS;
		else 
		{
			ItemStack itemstack = context.getItemInHand();
			BlockPos blockpos = context.getClickedPos();
			Direction direction = context.getClickedFace();
			BlockState blockstate = world.getBlockState(blockpos);
			if(blockstate.is(Blocks.SPAWNER)) 
			{
				TileEntity tileentity = world.getBlockEntity(blockpos);
				if(tileentity instanceof MobSpawnerTileEntity) 
				{
					AbstractSpawner abstractspawner = ((MobSpawnerTileEntity)tileentity).getSpawner();
					EntityType<?> entitytype1 = this.getType(itemstack.getTag());
					abstractspawner.setEntityId(entitytype1);
					tileentity.setChanged();
					world.sendBlockUpdated(blockpos, blockstate, blockstate, 3);
					itemstack.shrink(1);
					return ActionResultType.CONSUME;
				}
			}
			BlockPos blockpos1;
			if(blockstate.getCollisionShape(world, blockpos).isEmpty()) blockpos1 = blockpos;
			else blockpos1 = blockpos.relative(direction);
			//EntityType<?> entitytype = this.getType(itemstack.getTag());
			ServerWorld serverWorld = (ServerWorld)(world);
			if(this.entityType.spawn(serverWorld, itemstack, context.getPlayer(), blockpos1, SpawnReason.SPAWN_EGG, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP) != null) 
			{
				itemstack.shrink(1);
			}
			return ActionResultType.CONSUME;
		}
	}
}
