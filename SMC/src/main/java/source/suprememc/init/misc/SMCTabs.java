package source.suprememc.init.misc;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import source.suprememc.SupremeMC;
import source.suprememc.init.objects.SMCBlocks;
import source.suprememc.init.objects.SMCItems;

public class SMCTabs {
	private static class BuildingBlocksTab extends ItemGroup {
		BuildingBlocksTab(String name) {
			super(SupremeMC.MOD_ID + "." + name);
		}

		@Override
		public ItemStack makeIcon() {
			return new ItemStack(SMCBlocks.GLOWING_OBSIDIAN);
		}
	}

	private static class DecorationBlocksTab extends ItemGroup {
		DecorationBlocksTab(String name) {
			super(SupremeMC.MOD_ID + "." + name);
		}

		@Override
		public ItemStack makeIcon() {
			return new ItemStack(SMCBlocks.LAVENDER_BOOKSHELF);
		}
	}

	private static class RedstoneBlocksTab extends ItemGroup {
		RedstoneBlocksTab(String name) {
			super(SupremeMC.MOD_ID + "." + name);
		}

		@Override
		public ItemStack makeIcon() {
			return new ItemStack(SMCBlocks.BLUE_WIRELESS_RECEIVER);
		}
	}

	private static class TransportationTab extends ItemGroup {
		TransportationTab(String name) {
			super(SupremeMC.MOD_ID + "." + name);
		}

		@Override
		public ItemStack makeIcon() {
			return new ItemStack(SMCItems.PALM_BOAT);
		}
	}

	private static class MiscellaneousTab extends ItemGroup {
		MiscellaneousTab(String name) {
			super(SupremeMC.MOD_ID + "." + name);
		}

		@Override
		public ItemStack makeIcon() {
			return new ItemStack(SMCItems.RUBY);
		}
	}

	private static class FoodsTab extends ItemGroup {
		FoodsTab(String name) {
			super(SupremeMC.MOD_ID + "." + name);
		}

		@Override
		public ItemStack makeIcon() {
			return new ItemStack(SMCItems.CHEESE);
		}
	}

	private static class ToolsTab extends ItemGroup {
		ToolsTab(String name) {
			super(SupremeMC.MOD_ID + "." + name);
		}

		@Override
		public ItemStack makeIcon() {
			return new ItemStack(SMCItems.AMETHYST_SHOVEL);
		}
	}

	private static class CombatTab extends ItemGroup {
		CombatTab(String name) {
			super(SupremeMC.MOD_ID + "." + name);
		}

		@Override
		public ItemStack makeIcon() {
			return new ItemStack(SMCItems.NETHERITE_TRIDENT);
		}
	}

	private static class BrewingTab extends ItemGroup {
		BrewingTab(String name) {
			super(SupremeMC.MOD_ID + "." + name);
		}

		@Override
		public ItemStack makeIcon() {
			return new ItemStack(SMCBlocks.SOUL_BREWING_STAND);
		}
	}

	public static final ItemGroup BUILDING_BLOCKS = new BuildingBlocksTab("building_blocks");
	public static final ItemGroup DECORATION_BLOCKS = new DecorationBlocksTab("decoration_blocks");
	public static final ItemGroup REDSTONE = new RedstoneBlocksTab("redstone");
	public static final ItemGroup TRANSPORTATION = new TransportationTab("transportation");
	public static final ItemGroup MISCELLANEOUS = new MiscellaneousTab("miscellaneous");
	public static final ItemGroup FOODS = new FoodsTab("foods");
	public static final ItemGroup TOOLS = new ToolsTab("tools");
	public static final ItemGroup COMBAT = new CombatTab("combat");
	public static final ItemGroup BREWING = new BrewingTab("brewing");
}
