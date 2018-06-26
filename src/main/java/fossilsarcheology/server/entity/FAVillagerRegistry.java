package fossilsarcheology.server.entity;

import fossilsarcheology.server.block.FABlockRegistry;
import fossilsarcheology.server.item.FAItemRegistry;
import fossilsarcheology.server.world.village.VillageArcheologistHouseCreator;
import fossilsarcheology.server.world.village.VillageComponentArcheologistHouse;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

public class FAVillagerRegistry {
    public static final VillagerRegistry.VillagerProfession ARCHEOLOGIST_PROFESSION = new VillagerRegistry.VillagerProfession("fossil:archeologist", "fossil:textures/model/archaeologist.png", "minecraft:textures/entity/zombie_villager/zombie_farmer.png");

    public static void register(){
        VillagerRegistry.VillagerCareer career = new VillagerRegistry.VillagerCareer(ARCHEOLOGIST_PROFESSION, "archeologist");
        career.addTrade(1, new EntityVillager.ListItemForEmeralds(FAItemRegistry.BIOFOSSIL, new EntityVillager.PriceInfo(1, 7)));
        career.addTrade(1, new EntityVillager.ListItemForEmeralds(FAItemRegistry.RELIC_SCRAP, new EntityVillager.PriceInfo(1, 6)));
        career.addTrade(1, new EntityVillager.EmeraldForItems(FAItemRegistry.RELIC_SCRAP, new EntityVillager.PriceInfo(3, 1)));
        career.addTrade(2, new EntityVillager.EmeraldForItems(FAItemRegistry.POTTERY_SHARD, new EntityVillager.PriceInfo(1, 3)));
        career.addTrade(2, new EntityVillager.ListItemForEmeralds(FAItemRegistry.TAR_FOSSIL, new EntityVillager.PriceInfo(1, 2)));
        career.addTrade(3, new EntityVillager.ListItemForEmeralds(new ItemStack(FABlockRegistry.ANALYZER), new EntityVillager.PriceInfo(6, 1)));
        career.addTrade(3, new EntityVillager.ListItemForEmeralds(new ItemStack(FABlockRegistry.AMPHORA_VASE, 1, 0), new EntityVillager.PriceInfo(5, 1)));
        career.addTrade(3, new EntityVillager.ListItemForEmeralds(new ItemStack(FABlockRegistry.KYLIX_VASE, 1, 0), new EntityVillager.PriceInfo(5, 1)));
        career.addTrade(3, new EntityVillager.ListItemForEmeralds(new ItemStack(FABlockRegistry.VOLUTE_VASE, 1, 0), new EntityVillager.PriceInfo(5, 1)));
        career.addTrade(4, new EntityVillager.ItemAndEmeraldToItem(FAItemRegistry.BROKEN_HELMET, new EntityVillager.PriceInfo(3, 1), FAItemRegistry.ANCIENT_HELMET, new EntityVillager.PriceInfo(1, 1)));
        career.addTrade(4, new EntityVillager.ItemAndEmeraldToItem(FAItemRegistry.BROKEN_SWORD, new EntityVillager.PriceInfo(4, 1), FAItemRegistry.ANCIENT_SWORD, new EntityVillager.PriceInfo(1, 1)));
        career.addTrade(5, new EntityVillager.ListItemForEmeralds(new ItemStack(FABlockRegistry.FIGURINE, 1, 15), new EntityVillager.PriceInfo(4, 1)));
        MapGenStructureIO.registerStructureComponent(VillageComponentArcheologistHouse.class, "archeologist_house");
        VillagerRegistry.instance().registerVillageCreationHandler(new VillageArcheologistHouseCreator());
    }

}
