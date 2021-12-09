package source.suprememc.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import source.suprememc.SupremeMC;
import source.suprememc.init.objects.SMCFluids;

public class SMCFluidTags extends FluidTagsProvider
{
	public SMCFluidTags(DataGenerator generator, ExistingFileHelper exFileHelper) 
	{
		super(generator, SupremeMC.MOD_ID, exFileHelper);
	}
	
	public static final ITag.INamedTag<Fluid> RADIATED_WATER = FluidTags.createOptional(new ResourceLocation(SupremeMC.MOD_ID, "radiated_water"));
	
	@Override
	protected void addTags() 
	{
		this.tag(RADIATED_WATER).add(SMCFluids.RADIATED_WATER, SMCFluids.FLOWING_RADIATED_WATER);
	}
}
