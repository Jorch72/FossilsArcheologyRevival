package fossilsarcheology.client;

import fossilsarcheology.client.gui.AnalyzerGUI;
import fossilsarcheology.client.gui.CultivateGUI;
import fossilsarcheology.client.gui.FeederGUI;
import fossilsarcheology.client.gui.SifterGUI;
import fossilsarcheology.client.gui.TimeMachineGUI;
import fossilsarcheology.client.gui.WorktableGUI;
import fossilsarcheology.client.gui.dinopedia.GuiPedia;
import fossilsarcheology.client.model.ModelAncientHelmet;
import fossilsarcheology.client.particle.BubbleFX;
import fossilsarcheology.client.render.RenderingHandler;
import fossilsarcheology.server.ServerProxy;
import fossilsarcheology.server.api.DefaultRenderedItem;
import fossilsarcheology.server.api.IgnoreRenderProperty;
import fossilsarcheology.server.api.SubtypeRenderedItem;
import fossilsarcheology.server.block.FABlockRegistry;
import fossilsarcheology.server.block.entity.AnalyzerBlockEntity;
import fossilsarcheology.server.block.entity.TileEntityCultivate;
import fossilsarcheology.server.block.entity.TileEntitySifter;
import fossilsarcheology.server.block.entity.TileEntityTimeMachine;
import fossilsarcheology.server.block.entity.TileEntityWorktable;
import fossilsarcheology.server.container.CultivateContainer;
import fossilsarcheology.server.entity.EntityFishBase;
import fossilsarcheology.server.event.FossilHelmetOverlayEvent;
import fossilsarcheology.server.event.FossilMainMenuEvent;
import fossilsarcheology.server.event.FossilTarOverlayEvent;
import fossilsarcheology.server.item.FAItemRegistry;
import fossilsarcheology.server.item.FossilSeedsItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class ClientProxy extends ServerProxy {
    @SideOnly(Side.CLIENT)
    public static final Minecraft MINECRAFT = Minecraft.getMinecraft();
    @SideOnly(Side.CLIENT)
    public static final RenderingHandler RENDER_HANDLER = new RenderingHandler();
    @SideOnly(Side.CLIENT)
    private static final ModelAncientHelmet helmetModel = new ModelAncientHelmet(1.0f);
    private static final ModelResourceLocation BLOCK_TAR_MODEL = new ModelResourceLocation("fossil:tar", "fluid");

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent event) {
        ModelLoader.setCustomStateMapper(FABlockRegistry.PALM_LEAVES, (new StateMap.Builder()).ignore(BlockLeaves.CHECK_DECAY, BlockLeaves.DECAYABLE).build());
        for (Block block : FABlockRegistry.BLOCKS) {
            if (block instanceof IgnoreRenderProperty) {
                IProperty<?>[] ignoredProperties = ((IgnoreRenderProperty) block).getIgnoredProperties();
                ModelLoader.setCustomStateMapper(block, new StateMap.Builder().ignore(ignoredProperties).build());
            }
        }
        for (Block block : FABlockRegistry.BLOCKS) {
            if (block instanceof DefaultRenderedItem) {
                RENDER_HANDLER.registerBlockRenderer(block, ((DefaultRenderedItem) block).getResource(block.getRegistryName()), "inventory");
            } else if (block instanceof SubtypeRenderedItem) {
                SubtypeRenderedItem subtypeItem = (SubtypeRenderedItem) block;
                int[] subtypes = subtypeItem.getUsedSubtypes();
                for (int metadata : subtypes) {
                    RENDER_HANDLER.registerBlockRenderer(block, metadata, subtypeItem.getResource(block.getRegistryName(), metadata), "inventory");
                }
            }
        }

        Item tar = Item.getItemFromBlock(FABlockRegistry.TAR);
        ModelBakery.registerItemVariants(tar);
        ModelLoader.setCustomMeshDefinition(tar, stack -> BLOCK_TAR_MODEL);
        ModelLoader.setCustomStateMapper(FABlockRegistry.TAR, new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return BLOCK_TAR_MODEL;
            }
        });
        for (Item item : FAItemRegistry.ITEMS) {
            if (item instanceof DefaultRenderedItem) {
                String resource = ((DefaultRenderedItem) item).getResource(item.getRegistryName());
                RENDER_HANDLER.registerItemRenderer(item, resource, "inventory");
            } else if (item instanceof SubtypeRenderedItem) {
                SubtypeRenderedItem subtypeItem = (SubtypeRenderedItem) item;
                int[] subtypes = subtypeItem.getUsedSubtypes();
                for (int metadata : subtypes) {
                    RENDER_HANDLER.registerItemRenderer(item, metadata, subtypeItem.getResource(item.getRegistryName(), metadata), "inventory");
                }
            }
        }

        RENDER_HANDLER.registerItemRenderer(FAItemRegistry.TAR_BUCKET, "tar_bucket", "inventory");

        for (int i = 0; i < 16; i++) {
            RENDER_HANDLER.registerItemRenderer(FAItemRegistry.TOY_BALL, i, "toyball_" + EnumDyeColor.byDyeDamage(i).getName(), "inventory");
        }

        for (int i = 0; i < 16; i++) {
            RENDER_HANDLER.registerItemRenderer(Item.getItemFromBlock(FABlockRegistry.FIGURINE), i, "figurine_" + i, "inventory");
        }

        for (int i = 0; i < FossilSeedsItem.fossilSeeds.length; i++) {
            RENDER_HANDLER.registerItemRenderer(FAItemRegistry.FOSSIL_SEED, i, "fossil_seed_" + FossilSeedsItem.fossilSeeds[i], "inventory");
            RENDER_HANDLER.registerItemRenderer(FAItemRegistry.SEED, i, "seed_" + FossilSeedsItem.fossilSeeds[i], "inventory");
        }

        for (int i = 0; i < 5; i++) {
            RENDER_HANDLER.registerItemRenderer(Item.getItemFromBlock(FABlockRegistry.AMPHORA_VASE), i, "vase_amphora_" + i, "inventory");
            RENDER_HANDLER.registerItemRenderer(Item.getItemFromBlock(FABlockRegistry.KYLIX_VASE), i, "vase_kylix_" + i, "inventory");
            RENDER_HANDLER.registerItemRenderer(Item.getItemFromBlock(FABlockRegistry.VOLUTE_VASE), i, "vase_volute_" + i, "inventory");
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void onPreInit() {
        super.onPreInit();
        RENDER_HANDLER.onPreInit();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void onInit() {
        super.onInit();
        MinecraftForge.EVENT_BUS.register(new FossilMainMenuEvent());
        MinecraftForge.EVENT_BUS.register(new FossilTarOverlayEvent());
        MinecraftForge.EVENT_BUS.register(new FossilHelmetOverlayEvent());
        RENDER_HANDLER.onInit();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void onPostInit() {
        super.onPostInit();
        RENDER_HANDLER.onPostInit();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void calculateChainBuffer(EntityFishBase entity) {
        entity.chainBuffer.calculateChainSwingBuffer(70, 10, 4, entity);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        if (id == GUI_ANALYZER) {
            return new AnalyzerGUI(player.inventory, (AnalyzerBlockEntity) world.getTileEntity(pos));
        }
        if (id == GUI_CULTIVATE) {
            TileEntityCultivate entity = (TileEntityCultivate) world.getTileEntity(pos);
            return new CultivateGUI(entity, new CultivateContainer(player.inventory, entity));
        }
        if (id == GUI_FEEDER) {
            return new FeederGUI(player.inventory, world.getTileEntity(pos));
        }
        if (id == GUI_WORKTABLE) {
            return new WorktableGUI(player.inventory, (TileEntityWorktable) world.getTileEntity(pos));
        }
        if (id == GUI_SIFTER) {
            return new SifterGUI(player.inventory, (TileEntitySifter) world.getTileEntity(pos));
        }
        if (id == GUI_TIME_MACHINE) {
            return new TimeMachineGUI(player.inventory, (TileEntityTimeMachine) world.getTileEntity(pos));
        }
        if (id == GUI_DINOPEDIA) {
            return new GuiPedia();
        }
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void spawnBubbleParticles(World world, float f, float f1, float f2, double motionX, double motionY, double motionZ) {
        Minecraft.getMinecraft().effectRenderer.addEffect(new BubbleFX(world, f, f1, f2, motionX, motionY, motionZ));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public net.minecraft.client.model.ModelBiped getArmorModel(int id) {
        return id == 0 ? helmetModel : null;
    }
}
