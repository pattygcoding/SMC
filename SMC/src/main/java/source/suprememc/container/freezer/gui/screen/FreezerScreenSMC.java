package source.suprememc.container.freezer.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.ITextProperties;
import source.suprememc.SupremeMC;
import source.suprememc.container.freezer.FreezerContainerSMC;
import source.suprememc.util.Dict;

public class FreezerScreenSMC extends ContainerScreen<FreezerContainerSMC>
{
    private static final ResourceLocation FREEZER_GUI_TEXTURES = new ResourceLocation(SupremeMC.MOD_ID, "textures/gui/container/" + Dict.FREEZER + ".png");

    public FreezerScreenSMC(FreezerContainerSMC container, PlayerInventory playerInventory, ITextComponent title)
    {
        super(container, playerInventory, title);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(MatrixStack matrixStack, int mouseX, int mouseY)
    {
    	 ITextProperties lvt_3_1_ = this.title;
         this.font.draw(matrixStack, lvt_3_1_.getString(), (float) (this.getXSize() / 2 - this.font.width(lvt_3_1_.getString()) / 2), 6.0F, 4210752);
         this.font.draw(matrixStack, this.inventory.getDisplayName().getString(), 8.0F, (float) (this.getYSize() - 96 + 2), 4210752);
    }

    @SuppressWarnings("deprecation")
	@Override
    protected void renderBg(MatrixStack matrixstack, float partialTicks, int mouseX, int mouseY)
    {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(FREEZER_GUI_TEXTURES);
        this.blit(matrixstack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        if(this.menu.isFueling())
        {
            int fuelLeft = this.menu.getFuelLeftScaled();
            this.blit(matrixstack, this.leftPos + 57, this.topPos + 37 + 12 - fuelLeft, 176, 12 - fuelLeft, 14, fuelLeft + 1);
        }
        int progress = this.menu.getSolidifyProgressionScaled();
        this.blit(matrixstack, this.leftPos + 79, this.topPos + 34, 176, 14, progress + 1, 16);
    }
}