package fossilsarcheology.server.entity.ai;

import fossilsarcheology.server.entity.prehistoric.EntityMegaloceros;
import fossilsarcheology.server.entity.prehistoric.EntityPrehistoric;
import fossilsarcheology.server.entity.prehistoric.PrehistoricEntityTypeAI;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;

public class DinoAIOwnerHurtByTarget extends EntityAIOwnerHurtByTarget {
    EntityPrehistoric prehistoric;

    public DinoAIOwnerHurtByTarget(EntityPrehistoric prehistoric) {
        super(prehistoric);
        this.prehistoric = prehistoric;
    }

    public boolean shouldExecute(){
        if(prehistoric.isChild() || prehistoric.aiResponseType() == PrehistoricEntityTypeAI.Response.SCARED){
            return false;
        }
        return super.shouldExecute();
    }
}
