package source.suprememc.init.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZoglinEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import source.suprememc.SupremeMC;
import source.suprememc.client.RenderEntities;
import source.suprememc.entities.bear.black.BlackBearEntitySMC;
import source.suprememc.entities.bear.grizzly.GrizzlyBearEntitySMC;
import source.suprememc.entities.blackwidow.BlackWidowSpiderEntitySMC;
import source.suprememc.entities.boat.BoatEntitySMC;
import source.suprememc.entities.brownrecluse.BrownRecluseSpiderEntitySMC;
import source.suprememc.entities.caved.CavedEntitySMC;
import source.suprememc.entities.firecreeper.FireCreeperEntitySMC;
import source.suprememc.entities.miner.MinerEntitySMC;
import source.suprememc.entities.moobloom.MoobloomEntitySMC;
import source.suprememc.entities.netheritetrident.NetheriteTridentEntitySMC;
import source.suprememc.entities.soulblaze.SoulBlazeEntitySMC;
import source.suprememc.entities.soulblaze.fireball.SoulFireballEntitySMC;
import source.suprememc.entities.tropicaldrudge.TropicalDrudgeEntitySMC;

@Mod.EventBusSubscriber(modid = SupremeMC.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SMCEntities 
{
	public static final List<EntityType<?>> ENTITY_TYPES = new ArrayList<>();

	// Mobs
	public static final EntityType<BlackBearEntitySMC> BLACK_BEAR = register("black_bear", EntityType.Builder.<BlackBearEntitySMC>of(BlackBearEntitySMC::new, EntityClassification.CREATURE).sized(1.4F, 1.4F).clientTrackingRange(10));
	public static final EntityType<GrizzlyBearEntitySMC> GRIZZLY_BEAR = register("grizzly_bear", EntityType.Builder.<GrizzlyBearEntitySMC>of(GrizzlyBearEntitySMC::new, EntityClassification.CREATURE).sized(1.4F, 1.4F).clientTrackingRange(10));
	public static final EntityType<MinerEntitySMC> MINER = register("miner", EntityType.Builder.<MinerEntitySMC>of(MinerEntitySMC::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8));
	public static final EntityType<TropicalDrudgeEntitySMC> TROPICAL_DRUDGE = register("tropical_drudge", EntityType.Builder.<TropicalDrudgeEntitySMC>of(TropicalDrudgeEntitySMC::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8));
	public static final EntityType<BlackWidowSpiderEntitySMC> BLACK_WIDOW_SPIDER = register("black_widow_spider", EntityType.Builder.<BlackWidowSpiderEntitySMC>of(BlackWidowSpiderEntitySMC::new, EntityClassification.MONSTER).sized(1.4F, 0.9F).clientTrackingRange(8));
	public static final EntityType<BrownRecluseSpiderEntitySMC> BROWN_RECLUSE_SPIDER = register("brown_recluse_spider", EntityType.Builder.<BrownRecluseSpiderEntitySMC>of(BrownRecluseSpiderEntitySMC::new, EntityClassification.MONSTER).sized(1.4F, 0.9F).clientTrackingRange(8));
	public static final EntityType<SoulBlazeEntitySMC> SOUL_BLAZE = register("soul_blaze", EntityType.Builder.<SoulBlazeEntitySMC>of(SoulBlazeEntitySMC::new, EntityClassification.MONSTER).fireImmune().sized(0.6F, 1.8F).clientTrackingRange(8));
	public static final EntityType<CavedEntitySMC> CAVED = register("caved", EntityType.Builder.<CavedEntitySMC>of(CavedEntitySMC::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8));
	public static final EntityType<FireCreeperEntitySMC> FIRE_CREEPER = register("fire_creeper", EntityType.Builder.<FireCreeperEntitySMC>of(FireCreeperEntitySMC::new, EntityClassification.MONSTER).fireImmune().sized(0.6F, 1.7F).clientTrackingRange(8));
	public static final EntityType<MoobloomEntitySMC> MOOBLOOM = register("moobloom", EntityType.Builder.<MoobloomEntitySMC>of(MoobloomEntitySMC::new, EntityClassification.CREATURE).sized(0.9F, 1.4F).clientTrackingRange(10));

	// Other entities
	public static final EntityType<BoatEntitySMC> BOAT = register("boat", EntityType.Builder.<BoatEntitySMC>of(BoatEntitySMC::new, EntityClassification.MISC).setTrackingRange(80).setUpdateInterval(3).setShouldReceiveVelocityUpdates(true).sized(1.375f, 0.5625f).setCustomClientFactory(BoatEntitySMC::new));
	public static final EntityType<NetheriteTridentEntitySMC> NETHERITE_TRIDENT = register("netherite_trident", EntityType.Builder.<NetheriteTridentEntitySMC>of(NetheriteTridentEntitySMC::new, EntityClassification.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20));
	public static final EntityType<SoulFireballEntitySMC> SOUL_FIREBALL = register("soul_fireball", EntityType.Builder.<SoulFireballEntitySMC>of(SoulFireballEntitySMC::new, EntityClassification.MISC).sized(0.3125F, 0.3125F).clientTrackingRange(4).updateInterval(10));


	/**
	 * This event is needed for mobs to spawn
	 */
	@SubscribeEvent
	public static void registerAttributes(final EntityAttributeCreationEvent event) 
	{
		event.put(BLACK_BEAR, BlackBearEntitySMC.attribute().build());
		event.put(GRIZZLY_BEAR, GrizzlyBearEntitySMC.attribute().build());
		event.put(MINER, MinerEntitySMC.attribute().build());
		event.put(FIRE_CREEPER, FireCreeperEntitySMC.attribute().build());
		event.put(CAVED, ZombieEntity.createAttributes().build());
		event.put(MOOBLOOM, CowEntity.createAttributes().build());
		event.put(TROPICAL_DRUDGE, ZombieEntity.createAttributes().build());
		event.put(BLACK_WIDOW_SPIDER, BlackWidowSpiderEntitySMC.attribute().build());
		event.put(BROWN_RECLUSE_SPIDER, BrownRecluseSpiderEntitySMC.attribute().build());
		event.put(SOUL_BLAZE, SoulBlazeEntitySMC.attribute().build());
	}

	/**
	 * This event is needed for mobs to spawn in the correct places (i.e. not in mid air)
	 */
	@SubscribeEvent
	public static void registerSpawnBehavior(final RegistryEvent.Register<EntityType<?>> event) 
	{
		EntitySpawnPlacementRegistry.register(BLACK_BEAR, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
		EntitySpawnPlacementRegistry.register(GRIZZLY_BEAR, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
		EntitySpawnPlacementRegistry.register(CAVED, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
		EntitySpawnPlacementRegistry.register(FIRE_CREEPER, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
		EntitySpawnPlacementRegistry.register(MOOBLOOM, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::checkAnimalSpawnRules);
		EntitySpawnPlacementRegistry.register(TROPICAL_DRUDGE, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, TropicalDrudgeEntitySMC::checkTropicalDrudgeSpawnRules);
		EntitySpawnPlacementRegistry.register(MINER, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
		EntitySpawnPlacementRegistry.register(BLACK_WIDOW_SPIDER, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);
		EntitySpawnPlacementRegistry.register(BROWN_RECLUSE_SPIDER, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::checkMonsterSpawnRules);

		// Needed for some nether spawns
		EntitySpawnPlacementRegistry.register(EntityType.ZOGLIN, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SMCEntities::checkZoglinSpawnRules);
	}


	/**
	 * Helper function for registering spawn behavior
	 */
	public static boolean checkZoglinSpawnRules(EntityType<ZoglinEntity> entity, IWorld iWorld, SpawnReason spawn, BlockPos pos, Random rand) 
	{
		return iWorld.getDifficulty() != Difficulty.PEACEFUL && iWorld.getBlockState(pos.below()).getBlock() != Blocks.NETHER_WART_BLOCK;
	}
	
	
	/**
	 * Registry functions
	 */
	private static <T extends Entity> EntityType<T> register(String entity, EntityType.Builder<T> builder)
	{
		String name = SupremeMC.MOD_ID + ":" + entity;
		EntityType<T> type = builder.build(name);
		type.setRegistryName(name);
		ENTITY_TYPES.add(type);
		return type;
	}

	@SubscribeEvent
	public static void registerTypes(final RegistryEvent.Register<EntityType<?>> event)
	{
		ENTITY_TYPES.forEach(type -> event.getRegistry().register(type));
		RenderEntities.setup();
		ENTITY_TYPES.clear();
	}

}
