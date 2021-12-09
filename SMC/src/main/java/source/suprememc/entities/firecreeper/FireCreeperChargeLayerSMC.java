package source.suprememc.entities.firecreeper;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.EnergyLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import source.suprememc.SupremeMC;

public class FireCreeperChargeLayerSMC extends EnergyLayer<FireCreeperEntitySMC, FireCreeperModelSMC<FireCreeperEntitySMC>> {
	private static final ResourceLocation POWER_LOCATION = new ResourceLocation(SupremeMC.MOD_ID, "textures/entity/fire_creeper/fire_creeper_armor.png");
	private final FireCreeperModelSMC<FireCreeperEntitySMC> model = new FireCreeperModelSMC<>(2.0F);

	public FireCreeperChargeLayerSMC(IEntityRenderer<FireCreeperEntitySMC, FireCreeperModelSMC<FireCreeperEntitySMC>> p_i50947_1_) {
		super(p_i50947_1_);
	}

	protected float xOffset(float p_225634_1_) {
		return p_225634_1_ * 0.01F;
	}

	protected ResourceLocation getTextureLocation() {
		return POWER_LOCATION;
	}

	protected EntityModel<FireCreeperEntitySMC> model() {
		return this.model;
	}
}