package fossilsarcheology.server.entity;

import fossilsarcheology.server.item.FAItemRegistry;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

public class FAVillagerRegistry {
    public static final VillagerRegistry.VillagerProfession ARCHEOLOGIST_PROFESSION = new VillagerRegistry.VillagerProfession("fossil:archeologist", "fossil:textures/models/archeologist.png", "minecraft:textures/entity/zombie_villager/zombie_farmer.png");

    public static void register(){
        VillagerRegistry.VillagerCareer career = new VillagerRegistry.VillagerCareer(ARCHEOLOGIST_PROFESSION, "archeologist");
        career.addTrade(1, new EntityVillager.ListItemForEmeralds(FAItemRegistry.BIOFOSSIL, new EntityVillager.PriceInfo(1, 3)));
    }

}
