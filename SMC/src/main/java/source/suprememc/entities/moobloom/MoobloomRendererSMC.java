package source.suprememc.entities.moobloom;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.CowModel;
import net.minecraft.util.ResourceLocation;
import source.suprememc.SupremeMC;

public class MoobloomRendererSMC extends MobRenderer<MoobloomEntitySMC, CowModel<MoobloomEntitySMC>> {
	public static final ResourceLocation LOCATION = new ResourceLocation(SupremeMC.MOD_ID, "textures/entity/cow/moobloom.png");


	public MoobloomRendererSMC(EntityRendererManager p_i47200_1_) {
		super(p_i47200_1_, new CowModel<>(), 0.7F);
		this.addLayer(new MoobloomFlowerLayer<>(this));
	}

	public ResourceLocation getTextureLocation(MoobloomEntitySMC p_110775_1_) 
	{
		return LOCATION;
	}
}
