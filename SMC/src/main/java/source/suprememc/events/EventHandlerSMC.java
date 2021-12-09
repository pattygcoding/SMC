package source.suprememc.events;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import source.suprememc.SupremeMC;
import source.suprememc.init.objects.SMCBlocks;

@Mod.EventBusSubscriber(modid = SupremeMC.MOD_ID, bus=Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandlerSMC 
{
	

	@SubscribeEvent
	public static void doBonemealEvents(final BonemealEvent event) 
	{
		BlockPos pos = event.getPos();
		World world = event.getWorld();
		BlockState block = event.getBlock();
		if(block.is(Blocks.NETHERRACK))
		{
			boolean crimsonFlag = false;
			boolean warpedFlag = false;
			boolean mustardFlag = false;

			for(BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, 1, 1))) 
			{
				BlockState blockstate = world.getBlockState(blockpos);
				if(blockstate.is(Blocks.WARPED_NYLIUM)) warpedFlag = true;
				if(blockstate.is(Blocks.CRIMSON_NYLIUM)) crimsonFlag = true;
				if(blockstate.is(SMCBlocks.MUSTARD_NYLIUM)) mustardFlag = true;
				if((crimsonFlag && warpedFlag) || (mustardFlag && warpedFlag) || (crimsonFlag && mustardFlag)) break;
			}

			if(warpedFlag && crimsonFlag) world.setBlock(pos, world.random.nextBoolean() ? Blocks.WARPED_NYLIUM.defaultBlockState() : Blocks.CRIMSON_NYLIUM.defaultBlockState(), 3);
			else if(warpedFlag && mustardFlag) world.setBlock(pos, world.random.nextBoolean() ? Blocks.WARPED_NYLIUM.defaultBlockState() : SMCBlocks.MUSTARD_NYLIUM.defaultBlockState(), 3);
			else if(mustardFlag && crimsonFlag) world.setBlock(pos, world.random.nextBoolean() ? SMCBlocks.MUSTARD_NYLIUM.defaultBlockState() : Blocks.CRIMSON_NYLIUM.defaultBlockState(), 3);
			else if(warpedFlag) world.setBlock(pos, Blocks.WARPED_NYLIUM.defaultBlockState(), 3);
			else if(crimsonFlag) world.setBlock(pos, Blocks.CRIMSON_NYLIUM.defaultBlockState(), 3);
			else if(mustardFlag) world.setBlock(pos, SMCBlocks.MUSTARD_NYLIUM.defaultBlockState(), 3);
			spawnBonemealParticles(world, pos, 0);
		}
		bonemealAdjBlock(block, Blocks.END_STONE, SMCBlocks.LAVENDER_ENDSPAR, pos, world);
		bonemealAdjBlock(block, Blocks.SAND, SMCBlocks.ARABLE_SAND, pos, world);
	}

	private static void bonemealAdjBlock(BlockState eventBlock, Block target, Block newBlock, BlockPos pos, World world)
	{
		if(!eventBlock.is(target)) return;
		else
		{
			boolean arableSandFlag = false;

			for(BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, 1, 1))) 
			{
				BlockState blockstate = world.getBlockState(blockpos);
				if(blockstate.is(newBlock)) arableSandFlag = true;
			}
			if(arableSandFlag)
			{
				world.setBlock(pos, newBlock.defaultBlockState(), 3);
				spawnBonemealParticles(world, pos, 0);
			}

		}
	}

	@SuppressWarnings("deprecation")
	@OnlyIn(Dist.CLIENT)
	private static void spawnBonemealParticles(IWorld worldIn, BlockPos posIn, int data) 
	{
		Random random = new Random();
		if(data == 0) data = 15;
		BlockState blockstate = worldIn.getBlockState(posIn);
		if (!blockstate.isAir(worldIn, posIn)) 
		{
			double d0 = 0.5D;
			double d1;
			if (blockstate.is(Blocks.WATER)) {
				data *= 3;
				d1 = 1.0D;
				d0 = 3.0D;
			} else if (blockstate.isCollisionShapeFullBlock(worldIn, posIn)) {
				posIn = posIn.above();
				data *= 3;
				d0 = 3.0D;
				d1 = 1.0D;
			} else {
				d1 = blockstate.getShape(worldIn, posIn).max(Direction.Axis.Y);
			}

			worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, (double)posIn.getX() + 0.5D, (double)posIn.getY() + 0.5D, (double)posIn.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);

			for(int i = 0; i < data; ++i) {
				double d2 = random.nextGaussian() * 0.02D;
				double d3 = random.nextGaussian() * 0.02D;
				double d4 = random.nextGaussian() * 0.02D;
				double d5 = 0.5D - d0;
				double d6 = (double)posIn.getX() + d5 + random.nextDouble() * d0 * 2.0D;
				double d7 = (double)posIn.getY() + random.nextDouble() * d1;
				double d8 = (double)posIn.getZ() + d5 + random.nextDouble() * d0 * 2.0D;
				if (!worldIn.getBlockState((new BlockPos(d6, d7, d8)).below()).isAir()) {
					worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, d6, d7, d8, d2, d3, d4);
				}
			}

		}
	}

}
