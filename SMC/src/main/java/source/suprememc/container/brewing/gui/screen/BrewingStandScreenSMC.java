package source.suprememc.container.brewing.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import source.suprememc.SupremeMC;
import source.suprememc.container.brewing.SupremeBrewingStandContainerSMC;

@SuppressWarnings("deprecation")
@OnlyIn(Dist.CLIENT)
public class BrewingStandScreenSMC extends ContainerScreen<SupremeBrewingStandContainerSMC> {
	private static final ResourceLocation BREWING_STAND_LOCATION = new ResourceLocation(SupremeMC.MOD_ID, "textures/gui/container/brewing_stand.png");
	private static final int[] BUBBLELENGTHS = new int[]{29, 24, 20, 16, 11, 6, 0};

	public BrewingStandScreenSMC(SupremeBrewingStandContainerSMC p_i51097_1_, PlayerInventory p_i51097_2_, ITextComponent p_i51097_3_) {
		super(p_i51097_1_, p_i51097_2_, p_i51097_3_);
	}

	protected void init() {
		super.init();
		this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
	}

	public void render(MatrixStack p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
		this.renderBackground(p_230430_1_);
		super.render(p_230430_1_, p_230430_2_, p_230430_3_, p_230430_4_);
		this.renderTooltip(p_230430_1_, p_230430_2_, p_230430_3_);
	}

	
	protected void renderBg(MatrixStack p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bind(BREWING_STAND_LOCATION);
		int i = (this.width - this.imageWidth) / 2;
		int j = (this.height - this.imageHeight) / 2;
		this.blit(p_230450_1_, i, j, 0, 0, this.imageWidth, this.imageHeight);
		int k = this.menu.getFuel();
		int l = MathHelper.clamp((18 * k + 20 - 1) / 20, 0, 18);
		if (l > 0) {
			this.blit(p_230450_1_, i + 60, j + 44, 176, 29, l, 4);
		}

		int i1 = this.menu.getBrewingTicks();
		if (i1 > 0) {
			int j1 = (int)(28.0F * (1.0F - (float)i1 / 400.0F));
			if (j1 > 0) {
				this.blit(p_230450_1_, i + 97, j + 16, 176, 0, 9, j1);
			}

			j1 = BUBBLELENGTHS[i1 / 2 % 7];
			if (j1 > 0) {
				this.blit(p_230450_1_, i + 63, j + 14 + 29 - j1, 185, 29 - j1, 12, j1);
			}
		}

	}
}
