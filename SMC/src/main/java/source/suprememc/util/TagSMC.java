package source.suprememc.util;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ResourceLocation;
import source.suprememc.SupremeMC;

@SuppressWarnings("unchecked")
public class TagSMC<T>
{
	private ITag<T> tag;
	private ResourceLocation resourceTag;
	private T type;
	
	public TagSMC(String name) 
	{
		 this.resourceTag = new ResourceLocation(SupremeMC.MOD_ID, name);
		 if(type instanceof Item) this.tag = (ITag<T>) ItemTags.getAllTags().getTag(this.resourceTag);
		 if(type instanceof Block) this.tag = (ITag<T>) BlockTags.getAllTags().getTag(this.resourceTag);
		 if(type instanceof Fluid) this.tag = (ITag<T>) FluidTags.getAllTags().getTag(this.resourceTag);
	}
	
	public ITag<T> getTag()
	{
		return this.tag;
	}
}
