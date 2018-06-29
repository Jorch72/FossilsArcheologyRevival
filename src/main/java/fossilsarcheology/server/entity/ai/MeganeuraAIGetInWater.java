package fossilsarcheology.server.entity.ai;

import fossilsarcheology.server.entity.prehistoric.EntityMeganeura;
import net.minecraft.entity.EntityCreature;

public class MeganeuraAIGetInWater extends DinoAIGetInWater {

	public MeganeuraAIGetInWater(EntityCreature theCreatureIn, double movementSpeedIn) {
		super(theCreatureIn, movementSpeedIn);
	}

	@Override
    public boolean shouldExecute() {
		return this.creature instanceof EntityMeganeura && this.creature.isChild() && super.shouldExecute();
	}

}
