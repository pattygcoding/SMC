package source.suprememc.init.objects.blockspecifics;

import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import source.suprememc.init.misc.SMCSounds;


public class SMCBlockProperties 
{
	private MaterialColor color1;
	//private MaterialColor color2;
	public SMCBlockProperties() {}
	public SMCBlockProperties(MaterialColor color)
	{
		this.color1 = color;
	}
	public SMCBlockProperties(MaterialColor sideColor, MaterialColor topColor)
	{
		this.color1 = sideColor;
		//this.color2 = topColor;
	}
	
	public final Properties AMETHYST = Properties.of(Material.METAL, this.color1).requiresCorrectToolForDrops().harvestLevel(2).harvestTool(ToolType.PICKAXE).strength(1.5F, 1.5F).sound(SMCSounds.AMETHYST);
	public final Properties BOOKSHELF = Properties.of(Material.WOOD, this.color1).harvestTool(ToolType.AXE).strength(1.5F).sound(SoundType.WOOD);
	public final Properties CALCITE = Properties.of(Material.STONE, this.color1).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(0.75F, 0.75F).sound(SoundType.STONE);
	public final Properties COBBLE = Properties.of(Material.STONE, this.color1).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(2.0F, 6.0F).sound(SoundType.STONE);
	public final Properties COPPER = Properties.of(Material.METAL, this.color1).requiresCorrectToolForDrops().harvestLevel(1).harvestTool(ToolType.PICKAXE).strength(3.0F, 6.0F).sound(SoundType.METAL);
	public final Properties DEEPSLATE = Properties.of(Material.STONE, this.color1).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(3.0F, 6.0F).sound(SoundType.STONE);
	public final Properties DIAMOND = Properties.of(Material.METAL, this.color1).requiresCorrectToolForDrops().harvestLevel(2).harvestTool(ToolType.PICKAXE).strength(5.0F, 6.0F).sound(SoundType.METAL);
	public final Properties END_STONE = Properties.of(Material.STONE, this.color1).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(3.0F, 9.0F).sound(SoundType.STONE);
	public final Properties EXPERIENCE = Properties.of(Material.METAL, this.color1).requiresCorrectToolForDrops().harvestLevel(1).harvestTool(ToolType.PICKAXE).strength(3.0F, 6.0F).sound(SoundType.METAL);
	public final Properties GLOWING_OBSIDIAN = Properties.of(Material.METAL, this.color1).requiresCorrectToolForDrops().harvestLevel(3).harvestTool(ToolType.PICKAXE).strength(50.0F, 1200.0F).lightLevel((state) -> {return 12;}).sound(SoundType.STONE);
	public final Properties GLOWSTONE = Properties.of(Material.GLASS, MaterialColor.SAND).strength(0.3F).sound(SoundType.GLASS).lightLevel((p_235464_0_) -> {return 15;});
	public final Properties GOLD = Properties.of(Material.METAL, this.color1).requiresCorrectToolForDrops().harvestLevel(2).harvestTool(ToolType.PICKAXE).strength(3.0F, 6.0F).sound(SoundType.METAL);
	public final Properties LIGHT = Properties.of(Material.GLASS, this.color1).strength(0.3F).sound(SoundType.GLASS).lightLevel((state) -> {return 15;});
	public final Properties NETHER_PLANKS = Properties.of(Material.NETHER_WOOD, this.color1).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD);
	public final Properties NETHER_WOOD = Properties.of(Material.NETHER_WOOD, this.color1).harvestTool(ToolType.AXE).strength(2.0F).sound(SoundType.STEM);
	public final Properties NYLIUM = Properties.of(Material.STONE, this.color1).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(0.4F).sound(SoundType.STEM).randomTicks();
	public final Properties ORE_0 = Properties.of(Material.STONE, this.color1).requiresCorrectToolForDrops().harvestLevel(0).harvestTool(ToolType.PICKAXE).strength(3.0F, 3.0F).sound(SoundType.STONE);
	public final Properties ORE_1 = Properties.of(Material.STONE, this.color1).requiresCorrectToolForDrops().harvestLevel(1).harvestTool(ToolType.PICKAXE).strength(3.0F, 3.0F).sound(SoundType.STONE);
	public final Properties ORE_2 = Properties.of(Material.STONE, this.color1).requiresCorrectToolForDrops().harvestLevel(2).harvestTool(ToolType.PICKAXE).strength(3.0F, 3.0F).sound(SoundType.STONE);
	public final Properties PLANKS = Properties.of(Material.WOOD, this.color1).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD);
	public final Properties QUARTZ = Properties.of(Material.STONE, this.color1).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(0.8F).sound(SoundType.STONE);
	public final Properties SAND = Properties.of(Material.SAND, this.color1).harvestTool(ToolType.SHOVEL).strength(0.5F).sound(SoundType.SAND);
	public final Properties SLIME = Properties.of(Material.CLAY, this.color1).friction(0.8F).sound(SoundType.SLIME_BLOCK).noOcclusion();
	public final Properties STONE = Properties.of(Material.STONE, this.color1).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(1.5F, 6.0F).sound(SoundType.STONE);
	public final Properties TUFF = Properties.of(Material.STONE, this.color1).requiresCorrectToolForDrops().harvestTool(ToolType.PICKAXE).strength(1.5F, 6.0F).sound(SoundType.STONE);
	public final Properties WART = Properties.of(Material.GRASS, this.color1).harvestTool(ToolType.HOE).strength(1.0F).sound(SoundType.WART_BLOCK);
	public final Properties WOOD = Properties.of(Material.WOOD, this.color1).harvestTool(ToolType.AXE).strength(2.0F, 3.0F).sound(SoundType.WOOD);
}
