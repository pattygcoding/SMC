package source.suprememc.util;

import net.minecraft.block.material.MaterialColor;

public enum SMCColors
{
	RED("red", MaterialColor.COLOR_RED), 
	ORANGE("orange", MaterialColor.COLOR_ORANGE), 
	YELLOW("yellow", MaterialColor.COLOR_YELLOW), 
	LIME("lime", MaterialColor.COLOR_LIGHT_GREEN), 
	GREEN("green", MaterialColor.COLOR_GREEN), 
	CYAN("cyan", MaterialColor.COLOR_CYAN), 
	LIGHT_BLUE("light_blue", MaterialColor.COLOR_LIGHT_BLUE), 
	BLUE("blue", MaterialColor.COLOR_BLUE), 
	PURPLE("purple", MaterialColor.COLOR_PURPLE), 
	MAGENTA("magenta", MaterialColor.COLOR_MAGENTA), 
	PINK("pink", MaterialColor.COLOR_PINK), 
	WHITE("white", MaterialColor.SNOW), 
	LIGHT_GRAY("light_gray", MaterialColor.COLOR_LIGHT_GRAY),
	GRAY("gray", MaterialColor.COLOR_GRAY), 
	BLACK("black", MaterialColor.COLOR_BLACK), 
	BROWN("brown", MaterialColor.COLOR_BROWN);
	
	private String name;
	private MaterialColor color;
	
	SMCColors(String name, MaterialColor color)
	{
		this.name = name;
		this.color = color;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public MaterialColor getColor()
	{
		return this.color;
	}
}