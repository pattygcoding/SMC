package source.suprememc.data;

import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import source.suprememc.SupremeMC;
import source.suprememc.init.objects.SMCBlocks;

public class SMCBlockTags extends BlockTagsProvider
{
	public SMCBlockTags(DataGenerator generator, ExistingFileHelper exFileHelper) 
	{
		super(generator, SupremeMC.MOD_ID, exFileHelper);
	}
	
	public static final ITag.INamedTag<Block> DEEPDARK_STONE = BlockTags.createOptional(new ResourceLocation(SupremeMC.MOD_ID, "deepdark_stone"));
	
	@Override
	protected void addTags() 
	{
		this.tag(DEEPDARK_STONE).add(SMCBlocks.DEEPSLATE);
		this.tag(DEEPDARK_STONE).add(SMCBlocks.TUFF);
	}
}
