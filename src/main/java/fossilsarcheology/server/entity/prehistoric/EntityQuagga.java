package fossilsarcheology.server.entity.prehistoric;


import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.*;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

import javax.annotation.Nullable;

public class EntityQuagga extends AbstractHorse {

    public EntityQuagga(World par1World) {
        super(par1World);
    }

    @Nullable
    protected ResourceLocation getLootTable() {
        return LootTableList.ENTITIES_DONKEY;
    }

    protected SoundEvent getAmbientSound() {
        super.getAmbientSound();
        return SoundEvents.ENTITY_HORSE_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        super.getDeathSound();
        return SoundEvents.ENTITY_HORSE_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        super.getHurtSound(damageSourceIn);
        return SoundEvents.ENTITY_HORSE_HURT;
    }

    public boolean canMateWith(EntityAnimal otherAnimal) {
        if (otherAnimal == this) {
            return false;
        } else if (!(otherAnimal instanceof EntityQuagga)) {
            return false;
        } else {
            return this.canMate() && ((EntityQuagga) otherAnimal).canMate();
        }
    }

    public EntityAgeable createChild(EntityAgeable ageable) {
        EntityQuagga abstracthorse = new EntityQuagga(this.world);
        this.setOffspringAttributes(ageable, abstracthorse);
        return abstracthorse;
    }

}