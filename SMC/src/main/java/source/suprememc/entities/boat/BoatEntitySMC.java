package source.suprememc.entities.boat;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LilyPadBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.client.CSteerBoatPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;
import source.suprememc.init.objects.SMCBlocks;
import source.suprememc.init.objects.SMCEntities;
import source.suprememc.init.objects.SMCItems;

@SuppressWarnings("deprecation")
public class BoatEntitySMC extends BoatEntity {
    private static final DataParameter<Integer> DATA_ID_HURT = EntityDataManager.defineId(BoatEntity.class, DataSerializers.INT);
    private static final DataParameter<Integer> DATA_ID_HURTDIR = EntityDataManager.defineId(BoatEntity.class, DataSerializers.INT);
    private static final DataParameter<Float> DATA_ID_DAMAGE = EntityDataManager.defineId(BoatEntity.class, DataSerializers.FLOAT);
    private static final DataParameter<Integer> DATA_ID_TYPE = EntityDataManager.defineId(BoatEntity.class, DataSerializers.INT);
    private static final DataParameter<Boolean> DATA_ID_PADDLE_LEFT = EntityDataManager.defineId(BoatEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> DATA_ID_PADDLE_RIGHT = EntityDataManager.defineId(BoatEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> DATA_ID_BUBBLE_TIME = EntityDataManager.defineId(BoatEntity.class, DataSerializers.INT);
    private final float[] paddlePositions = new float[2];
    private float invFriction;
    private float outOfControlTicks;
    private float deltaRotation;
    private int lerpSteps;
    private double lerpX;
    private double lerpY;
    private double lerpZ;
    private double lerpYRot;
    private double lerpXRot;
    private boolean inputLeft;
    private boolean inputRight;
    private boolean inputUp;
    private boolean inputDown;
    private double waterLevel;
    private float landFriction;
    private BoatEntity.Status status;
    private BoatEntity.Status oldStatus;
    private double lastYd;
    private boolean isAboveBubbleColumn;
    private boolean bubbleColumnDirectionIsDown;
    private float bubbleMultiplier;
    private float bubbleAngle;
    private float bubbleAngleO;

    public BoatEntitySMC(EntityType<? extends BoatEntity> p_i50129_1_, World p_i50129_2_) {
        super(p_i50129_1_, p_i50129_2_);
        this.blocksBuilding = true;
    }

    public BoatEntitySMC(World worldIn, double x, double y, double z) {
        this(SMCEntities.BOAT, worldIn);
        this.setPos(x, y, z);
        this.setDeltaMovement(Vector3d.ZERO);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    public BoatEntitySMC(FMLPlayMessages.SpawnEntity spawnEntity, World world)
    {
        this(SMCEntities.BOAT, world);
    }

    @Override
    protected boolean isMovementNoisy() {
        return false;
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(DATA_ID_HURT, 0);
        this.entityData.define(DATA_ID_HURTDIR, 1);
        this.entityData.define(DATA_ID_DAMAGE, 0.0F);
        this.entityData.define(DATA_ID_TYPE, BoatEntity.Type.OAK.ordinal());
        this.entityData.define(DATA_ID_PADDLE_LEFT, false);
        this.entityData.define(DATA_ID_PADDLE_RIGHT, false);
        this.entityData.define(DATA_ID_BUBBLE_TIME, 0);
    }

   
    
    @Override
    public boolean canCollideWith(Entity p_241849_1_) {
        return canVehicleCollide(this, p_241849_1_);
     }

    @Override
    @Nullable
    public AxisAlignedBB getBoundingBox() {
        return super.getBoundingBox();
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    public double getPassengersRidingOffset() {
        return -0.1D;
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean hurt(DamageSource p_70097_1_, float p_70097_2_) {
        if (this.isInvulnerableTo(p_70097_1_)) {
           return false;
        } else if (!this.level.isClientSide && !this.removed) {
           this.setHurtDir(-this.getHurtDir());
           this.setHurtTime(10);
           this.setDamage(this.getDamage() + p_70097_2_ * 10.0F);
           this.markHurt();
           boolean flag = p_70097_1_.getEntity() instanceof PlayerEntity && ((PlayerEntity)p_70097_1_.getEntity()).abilities.instabuild;
           if (flag || this.getDamage() > 40.0F) {
              if (!flag && this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                 this.spawnAtLocation(this.getDropItem());
              }

              this.remove();
           }

           return true;
        } else {
           return true;
        }
     }

    @Override
    public void onAboveBubbleCol(boolean p_203002_1_) {
        if (!this.level.isClientSide) {
           this.isAboveBubbleColumn = true;
           this.bubbleColumnDirectionIsDown = p_203002_1_;
           if (this.getBubbleTime() == 0) {
              this.setBubbleTime(60);
           }
        }

        this.level.addParticle(ParticleTypes.SPLASH, this.getX() + (double)this.random.nextFloat(), this.getY() + 0.7D, this.getZ() + (double)this.random.nextFloat(), 0.0D, 0.0D, 0.0D);
        if (this.random.nextInt(20) == 0) {
           this.level.playLocalSound(this.getX(), this.getY(), this.getZ(), this.getSwimSplashSound(), this.getSoundSource(), 1.0F, 0.8F + 0.4F * this.random.nextFloat(), false);
        }

     }

    @Override
    public Item getDropItem() 
    {
        switch(this.getBoatModel()) 
        {
        	case PALM:
        	default:
        		return SMCItems.PALM_BOAT;
        }
	}

    /**
     * Setups the entity to do the hurt animation. Only used by packets in multiplayer.
     */
    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateHurt() {
        this.setHurtDir(-this.getHurtDir());
        this.setHurtTime(10);
        this.setDamage(this.getDamage() * 11.0F);
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    @Override
    public boolean isPickable() {
        return !this.removed;
    }

    /**
     * Sets a target for the client to interpolate towards over the next few ticks
     */
    @Override
    @OnlyIn(Dist.CLIENT)
    public void lerpTo(double p_180426_1_, double p_180426_3_, double p_180426_5_, float p_180426_7_, float p_180426_8_, int p_180426_9_, boolean p_180426_10_) {
       this.lerpX = p_180426_1_;
       this.lerpY = p_180426_3_;
       this.lerpZ = p_180426_5_;
       this.lerpYRot = (double)p_180426_7_;
       this.lerpXRot = (double)p_180426_8_;
       this.lerpSteps = 10;
    }

    /**
     * Gets the horizontal facing direction of this Entity, adjusted to take specially-treated entity types into account.
     */
    @Override
    public Direction getMotionDirection() {
        return this.getDirection().getClockWise();
     }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void tick() {
        this.oldStatus = this.status;
        this.status = this.getStatus();
        if (this.status != BoatEntity.Status.UNDER_WATER && this.status != BoatEntity.Status.UNDER_FLOWING_WATER) {
            this.outOfControlTicks = 0.0F;
        } else {
            ++this.outOfControlTicks;
        }

        if (!this.level.isClientSide && this.outOfControlTicks >= 60.0F) {
            this.ejectPassengers();
        }

        if (this.getHurtTime() > 0) {
            this.setHurtTime(this.getHurtTime() - 1);
        }

        if (this.getDamage() > 0.0F) {
            this.setDamage(this.getDamage() - 1.0F);
        }

        // SUPER
        if (!this.level.isClientSide) {
            this.setSharedFlag(6, this.isGlowing());
        }

        this.baseTick();
        // END SUPER

        this.tickLerp();
        if (this.isControlledByLocalInstance()) {
            if (this.getPassengers().isEmpty() || !(this.getPassengers().get(0) instanceof PlayerEntity)) {
                this.setPaddleState(false, false);
            }

            this.floatBoat();
            if (this.level.isClientSide) {
                this.controlBoat();
                this.level.sendPacketToServer(new CSteerBoatPacket(this.getPaddleState(0), this.getPaddleState(1)));
            }

            this.move(MoverType.SELF, this.getDeltaMovement());
        } else {
            this.setDeltaMovement(Vector3d.ZERO);
        }

        this.tickBubbleColumn();

        for(int i = 0; i <= 1; ++i) {
            if (this.getPaddleState(i)) {
                if (!this.isSilent() && (double)(this.paddlePositions[i] % ((float)Math.PI * 2F)) <= (double)((float)Math.PI / 4F) && ((double)this.paddlePositions[i] + (double)((float)Math.PI / 8F)) % (double)((float)Math.PI * 2F) >= (double)((float)Math.PI / 4F)) {
                    SoundEvent soundevent = this.getPaddleSound();
                    if (soundevent != null) {
                        Vector3d vec3d = this.getViewVector(1.0F);
                        double d0 = i == 1 ? -vec3d.z : vec3d.z;
                        double d1 = i == 1 ? vec3d.x : -vec3d.x;
                        this.level.playSound((PlayerEntity)null, this.getX() + d0, this.getY(), this.getZ() + d1, soundevent, this.getSoundSource(), 1.0F, 0.8F + 0.4F * this.random.nextFloat());
                    }
                }

                this.paddlePositions[i] = (float)((double)this.paddlePositions[i] + (double)((float)Math.PI / 8F));
            } else {
                this.paddlePositions[i] = 0.0F;
            }
        }

        this.checkInsideBlocks();
        List<Entity> list = this.level.getEntities(this, this.getBoundingBox().inflate((double)0.2F, (double)-0.01F, (double)0.2F), EntityPredicates.pushableBy(this));
        if (!list.isEmpty()) {
            boolean flag = !this.level.isClientSide && !(this.getControllingPassenger() instanceof PlayerEntity);

            for(int j = 0; j < list.size(); ++j) {
                Entity entity = list.get(j);
                if (!entity.hasPassenger(this)) {
                    if (flag && this.getPassengers().size() < 2 && !entity.isPassenger() && entity.getBbWidth() < this.getBbWidth() && entity instanceof LivingEntity && !(entity instanceof WaterMobEntity) && !(entity instanceof PlayerEntity)) {
                        entity.startRiding(this);
                    } else {
                        this.push(entity);
                    }
                }
            }
        }

    }

    private void tickBubbleColumn() {
        if (this.level.isClientSide) {
           int i = this.getBubbleTime();
           if (i > 0) {
              this.bubbleMultiplier += 0.05F;
           } else {
              this.bubbleMultiplier -= 0.1F;
           }

           this.bubbleMultiplier = MathHelper.clamp(this.bubbleMultiplier, 0.0F, 1.0F);
           this.bubbleAngleO = this.bubbleAngle;
           this.bubbleAngle = 10.0F * (float)Math.sin((double)(0.5F * (float)this.level.getGameTime())) * this.bubbleMultiplier;
        } else {
           if (!this.isAboveBubbleColumn) {
              this.setBubbleTime(0);
           }

           int k = this.getBubbleTime();
           if (k > 0) {
              --k;
              this.setBubbleTime(k);
              int j = 60 - k - 1;
              if (j > 0 && k == 0) {
                 this.setBubbleTime(0);
                 Vector3d vector3d = this.getDeltaMovement();
                 if (this.bubbleColumnDirectionIsDown) {
                    this.setDeltaMovement(vector3d.add(0.0D, -0.7D, 0.0D));
                    this.ejectPassengers();
                 } else {
                    this.setDeltaMovement(vector3d.x, this.hasPassenger(PlayerEntity.class) ? 2.7D : 0.6D, vector3d.z);
                 }
              }

              this.isAboveBubbleColumn = false;
           }
        }

     }

    @Override
    @Nullable
    protected SoundEvent getPaddleSound() {
       switch(this.getStatus()) {
       case IN_WATER:
       case UNDER_WATER:
       case UNDER_FLOWING_WATER:
          return SoundEvents.BOAT_PADDLE_WATER;
       case ON_LAND:
          return SoundEvents.BOAT_PADDLE_LAND;
       case IN_AIR:
       default:
          return null;
       }
    }


    private void tickLerp() {
        if (this.isControlledByLocalInstance()) {
           this.lerpSteps = 0;
           this.setPacketCoordinates(this.getX(), this.getY(), this.getZ());
        }

        if (this.lerpSteps > 0) {
           double d0 = this.getX() + (this.lerpX - this.getX()) / (double)this.lerpSteps;
           double d1 = this.getY() + (this.lerpY - this.getY()) / (double)this.lerpSteps;
           double d2 = this.getZ() + (this.lerpZ - this.getZ()) / (double)this.lerpSteps;
           double d3 = MathHelper.wrapDegrees(this.lerpYRot - (double)this.yRot);
           this.yRot = (float)((double)this.yRot + d3 / (double)this.lerpSteps);
           this.xRot = (float)((double)this.xRot + (this.lerpXRot - (double)this.xRot) / (double)this.lerpSteps);
           --this.lerpSteps;
           this.setPos(d0, d1, d2);
           this.setRot(this.yRot, this.xRot);
        }
     }

    @Override
    public void setPaddleState(boolean p_184445_1_, boolean p_184445_2_) {
        this.entityData.set(DATA_ID_PADDLE_LEFT, p_184445_1_);
        this.entityData.set(DATA_ID_PADDLE_RIGHT, p_184445_2_);
     }

    @Override
    @OnlyIn(Dist.CLIENT)
    public float getRowingTime(int p_184448_1_, float p_184448_2_) {
       return this.getPaddleState(p_184448_1_) ? (float)MathHelper.clampedLerp((double)this.paddlePositions[p_184448_1_] - (double)((float)Math.PI / 8F), (double)this.paddlePositions[p_184448_1_], (double)p_184448_2_) : 0.0F;
    }
    /**
     * Determines whether the boat is in water, gliding on land, or in air
     */
    private BoatEntity.Status getStatus() {
        BoatEntity.Status boatentity$status = this.isUnderwater();
        if (boatentity$status != null) {
           this.waterLevel = this.getBoundingBox().maxY;
           return boatentity$status;
        } else if (this.checkInWater()) {
           return BoatEntity.Status.IN_WATER;
        } else {
           float f = this.getGroundFriction();
           if (f > 0.0F) {
              this.landFriction = f;
              return BoatEntity.Status.ON_LAND;
           } else {
              return BoatEntity.Status.IN_AIR;
           }
        }
     }

    @Override
    public float getWaterLevelAbove() {
        AxisAlignedBB axisalignedbb = this.getBoundingBox();
        int i = MathHelper.floor(axisalignedbb.minX);
        int j = MathHelper.ceil(axisalignedbb.maxX);
        int k = MathHelper.floor(axisalignedbb.maxY);
        int l = MathHelper.ceil(axisalignedbb.maxY - this.lastYd);
        int i1 = MathHelper.floor(axisalignedbb.minZ);
        int j1 = MathHelper.ceil(axisalignedbb.maxZ);
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

        label39:
        for(int k1 = k; k1 < l; ++k1) {
           float f = 0.0F;

           for(int l1 = i; l1 < j; ++l1) {
              for(int i2 = i1; i2 < j1; ++i2) {
                 blockpos$mutable.set(l1, k1, i2);
                 FluidState fluidstate = this.level.getFluidState(blockpos$mutable);
                 if (fluidstate.is(FluidTags.WATER)) {
                    f = Math.max(f, fluidstate.getHeight(this.level, blockpos$mutable));
                 }

                 if (f >= 1.0F) {
                    continue label39;
                 }
              }
           }

           if (f < 1.0F) {
              return (float)blockpos$mutable.getY() + f;
           }
        }

        return (float)(l + 1);
     }

    /**
     * Decides how much the boat should be gliding on the land (based on any slippery blocks)
     */
    @Override
    public float getGroundFriction() {
        AxisAlignedBB axisalignedbb = this.getBoundingBox();
        AxisAlignedBB axisalignedbb1 = new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY - 0.001D, axisalignedbb.minZ, axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        int i = MathHelper.floor(axisalignedbb1.minX) - 1;
        int j = MathHelper.ceil(axisalignedbb1.maxX) + 1;
        int k = MathHelper.floor(axisalignedbb1.minY) - 1;
        int l = MathHelper.ceil(axisalignedbb1.maxY) + 1;
        int i1 = MathHelper.floor(axisalignedbb1.minZ) - 1;
        int j1 = MathHelper.ceil(axisalignedbb1.maxZ) + 1;
        VoxelShape voxelshape = VoxelShapes.create(axisalignedbb1);
        float f = 0.0F;
        int k1 = 0;
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

        for(int l1 = i; l1 < j; ++l1) {
           for(int i2 = i1; i2 < j1; ++i2) {
              int j2 = (l1 != i && l1 != j - 1 ? 0 : 1) + (i2 != i1 && i2 != j1 - 1 ? 0 : 1);
              if (j2 != 2) {
                 for(int k2 = k; k2 < l; ++k2) {
                    if (j2 <= 0 || k2 != k && k2 != l - 1) {
                       blockpos$mutable.set(l1, k2, i2);
                       BlockState blockstate = this.level.getBlockState(blockpos$mutable);
                       if (!(blockstate.getBlock() instanceof LilyPadBlock) && VoxelShapes.joinIsNotEmpty(blockstate.getCollisionShape(this.level, blockpos$mutable).move((double)l1, (double)k2, (double)i2), voxelshape, IBooleanFunction.AND)) {
                          f += blockstate.getSlipperiness(this.level, blockpos$mutable, this);
                          ++k1;
                       }
                    }
                 }
              }
           }
        }

        return f / (float)k1;
     }

    private boolean checkInWater() {
        AxisAlignedBB axisalignedbb = this.getBoundingBox();
        int i = MathHelper.floor(axisalignedbb.minX);
        int j = MathHelper.ceil(axisalignedbb.maxX);
        int k = MathHelper.floor(axisalignedbb.minY);
        int l = MathHelper.ceil(axisalignedbb.minY + 0.001D);
        int i1 = MathHelper.floor(axisalignedbb.minZ);
        int j1 = MathHelper.ceil(axisalignedbb.maxZ);
        boolean flag = false;
        this.waterLevel = Double.MIN_VALUE;
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

        for(int k1 = i; k1 < j; ++k1) {
           for(int l1 = k; l1 < l; ++l1) {
              for(int i2 = i1; i2 < j1; ++i2) {
                 blockpos$mutable.set(k1, l1, i2);
                 FluidState fluidstate = this.level.getFluidState(blockpos$mutable);
                 if (fluidstate.is(FluidTags.WATER)) {
                    float f = (float)l1 + fluidstate.getHeight(this.level, blockpos$mutable);
                    this.waterLevel = Math.max((double)f, this.waterLevel);
                    flag |= axisalignedbb.minY < (double)f;
                 }
              }
           }
        }

        return flag;
     }

    /**
     * Decides whether the boat is currently underwater.
     */
    @Nullable
    private BoatEntity.Status isUnderwater() {
        AxisAlignedBB axisalignedbb = this.getBoundingBox();
        double d0 = axisalignedbb.maxY + 0.001D;
        int i = MathHelper.floor(axisalignedbb.minX);
        int j = MathHelper.ceil(axisalignedbb.maxX);
        int k = MathHelper.floor(axisalignedbb.maxY);
        int l = MathHelper.ceil(d0);
        int i1 = MathHelper.floor(axisalignedbb.minZ);
        int j1 = MathHelper.ceil(axisalignedbb.maxZ);
        boolean flag = false;
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

        for(int k1 = i; k1 < j; ++k1) {
           for(int l1 = k; l1 < l; ++l1) {
              for(int i2 = i1; i2 < j1; ++i2) {
                 blockpos$mutable.set(k1, l1, i2);
                 FluidState fluidstate = this.level.getFluidState(blockpos$mutable);
                 if (fluidstate.is(FluidTags.WATER) && d0 < (double)((float)blockpos$mutable.getY() + fluidstate.getHeight(this.level, blockpos$mutable))) {
                    if (!fluidstate.isSource()) {
                       return BoatEntity.Status.UNDER_FLOWING_WATER;
                    }

                    flag = true;
                 }
              }
           }
        }

        return flag ? BoatEntity.Status.UNDER_WATER : null;
     }

    /**
     * Update the boat's speed, based on momentum.
     */
    private void floatBoat() {
        double d1 = this.isNoGravity() ? 0.0D : (double)-0.04F;
        double d2 = 0.0D;
        this.invFriction = 0.05F;
        if (this.oldStatus == BoatEntity.Status.IN_AIR && this.status != BoatEntity.Status.IN_AIR && this.status != BoatEntity.Status.ON_LAND) {
           this.waterLevel = this.getY(1.0D);
           this.setPos(this.getX(), (double)(this.getWaterLevelAbove() - this.getBbHeight()) + 0.101D, this.getZ());
           this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D));
           this.lastYd = 0.0D;
           this.status = BoatEntity.Status.IN_WATER;
        } else {
           if (this.status == BoatEntity.Status.IN_WATER) {
              d2 = (this.waterLevel - this.getY()) / (double)this.getBbHeight();
              this.invFriction = 0.9F;
           } else if (this.status == BoatEntity.Status.UNDER_FLOWING_WATER) {
              d1 = -7.0E-4D;
              this.invFriction = 0.9F;
           } else if (this.status == BoatEntity.Status.UNDER_WATER) {
              d2 = (double)0.01F;
              this.invFriction = 0.45F;
           } else if (this.status == BoatEntity.Status.IN_AIR) {
              this.invFriction = 0.9F;
           } else if (this.status == BoatEntity.Status.ON_LAND) {
              this.invFriction = this.landFriction;
              if (this.getControllingPassenger() instanceof PlayerEntity) {
                 this.landFriction /= 2.0F;
              }
           }

           Vector3d vector3d = this.getDeltaMovement();
           this.setDeltaMovement(vector3d.x * (double)this.invFriction, vector3d.y + d1, vector3d.z * (double)this.invFriction);
           this.deltaRotation *= this.invFriction;
           if (d2 > 0.0D) {
              Vector3d vector3d1 = this.getDeltaMovement();
              this.setDeltaMovement(vector3d1.x, (vector3d1.y + d2 * 0.06153846016296973D) * 0.75D, vector3d1.z);
           }
        }

     }

    private void controlBoat() {
        if (this.isVehicle()) {
           float f = 0.0F;
           if (this.inputLeft) {
              --this.deltaRotation;
           }

           if (this.inputRight) {
              ++this.deltaRotation;
           }

           if (this.inputRight != this.inputLeft && !this.inputUp && !this.inputDown) {
              f += 0.005F;
           }

           this.yRot += this.deltaRotation;
           if (this.inputUp) {
              f += 0.04F;
           }

           if (this.inputDown) {
              f -= 0.005F;
           }

           this.setDeltaMovement(this.getDeltaMovement().add((double)(MathHelper.sin(-this.yRot * ((float)Math.PI / 180F)) * f), 0.0D, (double)(MathHelper.cos(this.yRot * ((float)Math.PI / 180F)) * f)));
           this.setPaddleState(this.inputRight && !this.inputLeft || this.inputUp, this.inputLeft && !this.inputRight || this.inputUp);
        }
     }

    @Override
    public void positionRider(Entity p_184232_1_) {
        if (this.hasPassenger(p_184232_1_)) {
           float f = 0.0F;
           float f1 = (float)((this.removed ? (double)0.01F : this.getPassengersRidingOffset()) + p_184232_1_.getMyRidingOffset());
           if (this.getPassengers().size() > 1) {
              int i = this.getPassengers().indexOf(p_184232_1_);
              if (i == 0) {
                 f = 0.2F;
              } else {
                 f = -0.6F;
              }

              if (p_184232_1_ instanceof AnimalEntity) {
                 f = (float)((double)f + 0.2D);
              }
           }

           Vector3d vector3d = (new Vector3d((double)f, 0.0D, 0.0D)).yRot(-this.yRot * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));
           p_184232_1_.setPos(this.getX() + vector3d.x, this.getY() + (double)f1, this.getZ() + vector3d.z);
           p_184232_1_.yRot += this.deltaRotation;
           p_184232_1_.setYHeadRot(p_184232_1_.getYHeadRot() + this.deltaRotation);
           this.clampRotation(p_184232_1_);
           if (p_184232_1_ instanceof AnimalEntity && this.getPassengers().size() > 1) {
              int j = p_184232_1_.getId() % 2 == 0 ? 90 : 270;
              p_184232_1_.setYBodyRot(((AnimalEntity)p_184232_1_).yBodyRot + (float)j);
              p_184232_1_.setYHeadRot(p_184232_1_.getYHeadRot() + (float)j);
           }

        }
     }

    /**
     * Applies this boat's yaw to the given entity. Used to update the orientation of its passenger.
     */
    @Override
    protected void clampRotation(Entity p_184454_1_) {
        p_184454_1_.setYBodyRot(this.yRot);
        float f = MathHelper.wrapDegrees(p_184454_1_.yRot - this.yRot);
        float f1 = MathHelper.clamp(f, -105.0F, 105.0F);
        p_184454_1_.yRotO += f1 - f;
        p_184454_1_.yRot += f1 - f;
        p_184454_1_.setYHeadRot(p_184454_1_.yRot);
     }

    /**
     * Applies this entity's orientation (pitch/yaw) to another entity. Used to update passenger orientation.
     */
    @Override
    @OnlyIn(Dist.CLIENT)
    public void onPassengerTurned(Entity p_184190_1_) {
        this.clampRotation(p_184190_1_);
     }
    @Override
    protected void addAdditionalSaveData(CompoundNBT p_213281_1_) {
        p_213281_1_.putString("Type", this.getBoatType().getName());
     }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    protected void readAdditionalSaveData(CompoundNBT p_70037_1_) {
        if (p_70037_1_.contains("Type", 8)) {
           this.setType(BoatEntity.Type.byName(p_70037_1_.getString("Type")));
        }

     }

    @Override
    public ActionResultType interact(PlayerEntity p_184230_1_, Hand p_184230_2_) {
        if (p_184230_1_.isSecondaryUseActive()) {
           return ActionResultType.PASS;
        } else if (this.outOfControlTicks < 60.0F) {
           if (!this.level.isClientSide) {
              return p_184230_1_.startRiding(this) ? ActionResultType.CONSUME : ActionResultType.PASS;
           } else {
              return ActionResultType.SUCCESS;
           }
        } else {
           return ActionResultType.PASS;
        }
     }

    @Override
    protected void checkFallDamage(double p_184231_1_, boolean p_184231_3_, BlockState p_184231_4_, BlockPos p_184231_5_) {
        this.lastYd = this.getDeltaMovement().y;
        if (!this.isPassenger()) {
           if (p_184231_3_) {
              if (this.fallDistance > 3.0F) {
                 if (this.status != BoatEntity.Status.ON_LAND) {
                    this.fallDistance = 0.0F;
                    return;
                 }

                 this.causeFallDamage(this.fallDistance, 1.0F);
                 if (!this.level.isClientSide && !this.removed) {
                    this.remove();
                    if (this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                       for(int i = 0; i < 3; ++i) {
                          this.spawnAtLocation(this.getBoatType().getPlanks());
                       }

                       for(int j = 0; j < 2; ++j) {
                          this.spawnAtLocation(Items.STICK);
                       }
                    }
                 }
              }

              this.fallDistance = 0.0F;
           } else if (!this.level.getFluidState(this.blockPosition().below()).is(FluidTags.WATER) && p_184231_1_ < 0.0D) {
              this.fallDistance = (float)((double)this.fallDistance - p_184231_1_);
           }

        }
     }

    @Override public boolean getPaddleState(int side) {return this.entityData.<Boolean>get(side == 0 ? DATA_ID_PADDLE_LEFT : DATA_ID_PADDLE_RIGHT) && this.getControllingPassenger() != null;}
    @Override public void setDamage(float damageTaken) {this.entityData.set(DATA_ID_DAMAGE, damageTaken);}
    @Override public float getDamage() {return this.entityData.get(DATA_ID_DAMAGE);}
    @Override public void setHurtTime(int timeSinceHit) {this.entityData.set(DATA_ID_HURT, timeSinceHit);}
    @Override public int getHurtTime() {return this.entityData.get(DATA_ID_HURT);}
    private void setBubbleTime(int p_203055_1_) {this.entityData.set(DATA_ID_BUBBLE_TIME, p_203055_1_);}
    private int getBubbleTime() {return this.entityData.get(DATA_ID_BUBBLE_TIME);}
    @Override @OnlyIn(Dist.CLIENT) public float getBubbleAngle(float partialTicks) {return MathHelper.lerp(partialTicks, this.bubbleAngleO, this.bubbleAngle);}
    @Override public void setHurtDir(int forwardDirection) {this.entityData.set(DATA_ID_HURTDIR, forwardDirection);}
    @Override public int getHurtDir() {return this.entityData.get(DATA_ID_HURTDIR);}

    public void setBoatModel(BoatEntitySMC.Type boatType) 
    {
        this.entityData.set(DATA_ID_TYPE, boatType.ordinal());
    }

    public BoatEntitySMC.Type getBoatModel() 
    {
        return BoatEntitySMC.Type.byId(this.entityData.get(DATA_ID_TYPE));
    }

    @Override
    protected boolean canAddPassenger(Entity passenger) 
    {
        return this.getPassengers().size() < 2 && !this.isEyeInFluid(FluidTags.WATER);
    }

    @Override @Nullable
    public Entity getControllingPassenger() 
    {
        List<Entity> list = this.getPassengers();
        return list.isEmpty() ? null : list.get(0);
    }

    @Override @OnlyIn(Dist.CLIENT)
    public void setInput(boolean inputLeft, boolean InputRight, boolean inputUp, boolean inputDown) 
    {
        this.inputLeft = inputLeft;
        this.inputRight = InputRight;
        this.inputUp = inputUp;
        this.inputDown = inputDown;
    }

    @Override
    protected void addPassenger(Entity passenger) 
    {
        super.addPassenger(passenger);
        if (this.isControlledByLocalInstance() && this.lerpSteps > 0) 
        {
            this.lerpSteps = 0;
            this.absMoveTo(this.lerpX, this.lerpY, this.lerpZ, (float)this.lerpYRot, (float)this.lerpXRot);
        }
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public enum Type 
    {
    	CHERRY(SMCBlocks.CHERRY_PLANKS, "cherry"),
        PALM(SMCBlocks.PALM_PLANKS, "palm"),
    	WARPED(Blocks.WARPED_PLANKS, "warped"),
    	CRIMSON(Blocks.CRIMSON_PLANKS, "crimson"),
    	MUSTARD(SMCBlocks.MUSTARD_PLANKS, "mustard"),
    	LAVENDER(SMCBlocks.LAVENDER_PLANKS, "lavender");

        private final String name;
        private final Block block;

        private Type(Block p_i48146_3_, String p_i48146_4_) {
            this.name = p_i48146_4_;
            this.block = p_i48146_3_;
        }
        
        public static List<String> boatNames()
        {
        	List<String> names = new ArrayList<String>();
        	for(Type type: Type.values()) names.add(type.name);
        	return names;
        }

        public String getName() 
        {
            return this.name;
        }

        public Block getPlanks() 
        {
            return this.block;
        }

        public String toString() 
        {
            return this.name;
        }
        
        public static Type byId(int id) 
        {
            Type[] boatType = values();
            if (id < 0 || id >= boatType.length) id = 0;
            return boatType[id];
        }

        public static Type byName(String nameIn) 
        {
            Type[] boatType = values();
            for(int i = 0; i < boatType.length; ++i) 
            {
                if(boatType[i].getName().equals(nameIn)) return boatType[i];
            }
            return boatType[0];
        }
    }
    
}