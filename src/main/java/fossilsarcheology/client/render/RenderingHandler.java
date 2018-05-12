package fossilsarcheology.client.render;

import fossilsarcheology.Revival;
import fossilsarcheology.client.model.ModelAlligatorGar;
import fossilsarcheology.client.model.ModelAllosaurus;
import fossilsarcheology.client.model.ModelAnkylosaurus;
import fossilsarcheology.client.model.ModelBrachiosaurus;
import fossilsarcheology.client.model.ModelCeratosaurus;
import fossilsarcheology.client.model.ModelCoelacanth;
import fossilsarcheology.client.model.ModelCompsognathus;
import fossilsarcheology.client.model.ModelConfuciusornis;
import fossilsarcheology.client.model.ModelDeadAnu;
import fossilsarcheology.client.model.ModelDeinonychus;
import fossilsarcheology.client.model.ModelDilophosaurus;
import fossilsarcheology.client.model.ModelDodo;
import fossilsarcheology.client.model.ModelDryosaurus;
import fossilsarcheology.client.model.ModelElasmotherium;
import fossilsarcheology.client.model.ModelFailuresaurus;
import fossilsarcheology.client.model.ModelFriendlyPigZombie;
import fossilsarcheology.client.model.ModelGallimimus;
import fossilsarcheology.client.model.ModelHenodus;
import fossilsarcheology.client.model.ModelIcthyosaurus;
import fossilsarcheology.client.model.ModelLiopleurodon;
import fossilsarcheology.client.model.ModelMammoth;
import fossilsarcheology.client.model.ModelMosasaurus;
import fossilsarcheology.client.model.ModelNautilus;
import fossilsarcheology.client.model.ModelPachycephalosaurus;
import fossilsarcheology.client.model.ModelParasaurolophus;
import fossilsarcheology.client.model.ModelPlesiosaurus;
import fossilsarcheology.client.model.ModelPteranodon;
import fossilsarcheology.client.model.ModelSarcosuchus;
import fossilsarcheology.client.model.ModelSmilodon;
import fossilsarcheology.client.model.ModelSpinosaurus;
import fossilsarcheology.client.model.ModelStegosaurus;
import fossilsarcheology.client.model.ModelSturgeon;
import fossilsarcheology.client.model.ModelTerrorBird;
import fossilsarcheology.client.model.ModelTherizinosaurus;
import fossilsarcheology.client.model.ModelTriceratops;
import fossilsarcheology.client.model.ModelTyrannosaurus;
import fossilsarcheology.client.model.ModelVaseAmphora;
import fossilsarcheology.client.model.ModelVaseKylix;
import fossilsarcheology.client.model.ModelVaseVolute;
import fossilsarcheology.client.model.ModelVelociraptor;
import fossilsarcheology.client.render.entity.JavelinRenderer;
import fossilsarcheology.client.render.entity.RenderAnu;
import fossilsarcheology.client.render.entity.RenderAnuEffect;
import fossilsarcheology.client.render.entity.RenderAnubite;
import fossilsarcheology.client.render.entity.RenderBirdEgg;
import fossilsarcheology.client.render.entity.RenderDeadAnu;
import fossilsarcheology.client.render.entity.RenderDinoEgg;
import fossilsarcheology.client.render.entity.RenderFailuresaurus;
import fossilsarcheology.client.render.entity.RenderFish;
import fossilsarcheology.client.render.entity.RenderFriendlyPigZombie;
import fossilsarcheology.client.render.entity.RenderGlowingPrehistoric;
import fossilsarcheology.client.render.entity.RenderPrehistoric;
import fossilsarcheology.client.render.entity.RenderSentryPigman;
import fossilsarcheology.client.render.entity.RenderTarSlime;
import fossilsarcheology.client.render.entity.RenderToyBall;
import fossilsarcheology.client.render.entity.RenderToyScratchingPost;
import fossilsarcheology.client.render.entity.RenderToyTetheredLog;
import fossilsarcheology.client.render.entity.StoneTabletRenderer;
import fossilsarcheology.client.render.tile.TileEntityAncientChestRender;
import fossilsarcheology.client.render.tile.TileEntityAnuStatueRender;
import fossilsarcheology.client.render.tile.TileEntityAnubiteStatueRender;
import fossilsarcheology.client.render.tile.TileEntityCultivateRender;
import fossilsarcheology.client.render.tile.TileEntityFigurineRender;
import fossilsarcheology.client.render.tile.TileEntitySarcophagusRender;
import fossilsarcheology.client.render.tile.TileEntityTimeMachineRender;
import fossilsarcheology.client.render.tile.TileEntityVaseRenderer;
import fossilsarcheology.server.block.FABlockRegistry;
import fossilsarcheology.server.block.entity.TileEntityAmphora;
import fossilsarcheology.server.block.entity.TileEntityAncientChest;
import fossilsarcheology.server.block.entity.TileEntityAnuStatue;
import fossilsarcheology.server.block.entity.TileEntityAnubiteStatue;
import fossilsarcheology.server.block.entity.TileEntityCultivate;
import fossilsarcheology.server.block.entity.TileEntityFigurine;
import fossilsarcheology.server.block.entity.TileEntityKylix;
import fossilsarcheology.server.block.entity.TileEntitySarcophagus;
import fossilsarcheology.server.block.entity.TileEntityTimeMachine;
import fossilsarcheology.server.block.entity.TileEntityVolute;
import fossilsarcheology.server.entity.EntityDinosaurEgg;
import fossilsarcheology.server.entity.StoneTabletEntity;
import fossilsarcheology.server.entity.monster.EntityAnu;
import fossilsarcheology.server.entity.monster.EntityAnubite;
import fossilsarcheology.server.entity.monster.EntityFailuresaurus;
import fossilsarcheology.server.entity.monster.EntityFriendlyPigZombie;
import fossilsarcheology.server.entity.monster.EntitySentryPigman;
import fossilsarcheology.server.entity.monster.EntityTarSlime;
import fossilsarcheology.server.entity.prehistoric.EntityAlligatorGar;
import fossilsarcheology.server.entity.prehistoric.EntityAllosaurus;
import fossilsarcheology.server.entity.prehistoric.EntityAnkylosaurus;
import fossilsarcheology.server.entity.prehistoric.EntityBrachiosaurus;
import fossilsarcheology.server.entity.prehistoric.EntityCeratosaurus;
import fossilsarcheology.server.entity.prehistoric.EntityCoelacanth;
import fossilsarcheology.server.entity.prehistoric.EntityCompsognathus;
import fossilsarcheology.server.entity.prehistoric.EntityConfuciusornis;
import fossilsarcheology.server.entity.prehistoric.EntityDeinonychus;
import fossilsarcheology.server.entity.prehistoric.EntityDilophosaurus;
import fossilsarcheology.server.entity.prehistoric.EntityDodo;
import fossilsarcheology.server.entity.prehistoric.EntityDryosaurus;
import fossilsarcheology.server.entity.prehistoric.EntityElasmotherium;
import fossilsarcheology.server.entity.prehistoric.EntityGallimimus;
import fossilsarcheology.server.entity.prehistoric.EntityGastornis;
import fossilsarcheology.server.entity.prehistoric.EntityHenodus;
import fossilsarcheology.server.entity.prehistoric.EntityIcthyosaurus;
import fossilsarcheology.server.entity.prehistoric.EntityKelenken;
import fossilsarcheology.server.entity.prehistoric.EntityLiopleurodon;
import fossilsarcheology.server.entity.prehistoric.EntityMammoth;
import fossilsarcheology.server.entity.prehistoric.EntityMosasaurus;
import fossilsarcheology.server.entity.prehistoric.EntityNautilus;
import fossilsarcheology.server.entity.prehistoric.EntityPachycephalosaurus;
import fossilsarcheology.server.entity.prehistoric.EntityParasaurolophus;
import fossilsarcheology.server.entity.prehistoric.EntityPhorusrhacos;
import fossilsarcheology.server.entity.prehistoric.EntityPlesiosaurus;
import fossilsarcheology.server.entity.prehistoric.EntityPterosaur;
import fossilsarcheology.server.entity.prehistoric.EntitySarcosuchus;
import fossilsarcheology.server.entity.prehistoric.EntitySmilodon;
import fossilsarcheology.server.entity.prehistoric.EntitySpinosaurus;
import fossilsarcheology.server.entity.prehistoric.EntityStegosaurus;
import fossilsarcheology.server.entity.prehistoric.EntitySturgeon;
import fossilsarcheology.server.entity.prehistoric.EntityTherizinosaurus;
import fossilsarcheology.server.entity.prehistoric.EntityTitanis;
import fossilsarcheology.server.entity.prehistoric.EntityTriceratops;
import fossilsarcheology.server.entity.prehistoric.EntityTyrannosaurus;
import fossilsarcheology.server.entity.prehistoric.EntityVelociraptor;
import fossilsarcheology.server.entity.projectile.AncientJavelinEntity;
import fossilsarcheology.server.entity.projectile.EntityBirdEgg;
import fossilsarcheology.server.entity.projectile.JavelinEntity;
import fossilsarcheology.server.entity.utility.EntityAnuDead;
import fossilsarcheology.server.entity.utility.EntityAnuEffect;
import fossilsarcheology.server.entity.utility.EntityToyBall;
import fossilsarcheology.server.entity.utility.EntityToyScratchingPost;
import fossilsarcheology.server.entity.utility.EntityToyTetheredLog;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderingHandler {
    private static final Minecraft MINECRAFT = Minecraft.getMinecraft();

    public void onPreInit() {
        RenderingRegistry.registerEntityRenderingHandler(JavelinEntity.class, JavelinRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AncientJavelinEntity.class, JavelinRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(StoneTabletEntity.class, StoneTabletRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityAnuEffect.class, RenderAnuEffect::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityAnubite.class, RenderAnubite::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySentryPigman.class, RenderSentryPigman::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTarSlime.class, RenderTarSlime::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityAnu.class, RenderAnu::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDinosaurEgg.class, RenderDinoEgg::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityToyBall.class, RenderToyBall::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityToyScratchingPost.class, RenderToyScratchingPost::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityToyTetheredLog.class, RenderToyTetheredLog::new);
    }

    public void onInit() {
        RenderingRegistry.registerEntityRenderingHandler(EntityNautilus.class, new RenderFish(new ModelNautilus(), MINECRAFT.getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityCoelacanth.class, new RenderFish(new ModelCoelacanth(), MINECRAFT.getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityAlligatorGar.class, new RenderFish(new ModelAlligatorGar(), MINECRAFT.getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntitySturgeon.class, new RenderFish(new ModelSturgeon(), MINECRAFT.getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityFailuresaurus.class, new RenderFailuresaurus(new ModelFailuresaurus(), MINECRAFT.getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityFriendlyPigZombie.class, new RenderFriendlyPigZombie(new ModelFriendlyPigZombie(), MINECRAFT.getRenderManager()));
        RenderingRegistry.registerEntityRenderingHandler(EntityAnuDead.class, new RenderDeadAnu(MINECRAFT.getRenderManager(), new ModelDeadAnu(), 0.3F));
        RenderingRegistry.registerEntityRenderingHandler(EntityBirdEgg.class, new RenderBirdEgg(MINECRAFT.getRenderManager(), MINECRAFT.getRenderItem()));
        RenderingRegistry.registerEntityRenderingHandler(EntityTriceratops.class, new RenderPrehistoric(new ModelTriceratops()));
        RenderingRegistry.registerEntityRenderingHandler(EntityVelociraptor.class, new RenderPrehistoric(new ModelVelociraptor()));
        RenderingRegistry.registerEntityRenderingHandler(EntityTyrannosaurus.class, new RenderPrehistoric(new ModelTyrannosaurus()));
        RenderingRegistry.registerEntityRenderingHandler(EntityPterosaur.class, new RenderPrehistoric(new ModelPteranodon()));
        RenderingRegistry.registerEntityRenderingHandler(EntityPlesiosaurus.class, new RenderPrehistoric(new ModelPlesiosaurus()));
        RenderingRegistry.registerEntityRenderingHandler(EntityMosasaurus.class, new RenderPrehistoric(new ModelMosasaurus()));
        RenderingRegistry.registerEntityRenderingHandler(EntityStegosaurus.class, new RenderPrehistoric(new ModelStegosaurus()));
        RenderingRegistry.registerEntityRenderingHandler(EntityDilophosaurus.class, new RenderPrehistoric(new ModelDilophosaurus()));
        RenderingRegistry.registerEntityRenderingHandler(EntitySmilodon.class, new RenderPrehistoric(new ModelSmilodon()));
        RenderingRegistry.registerEntityRenderingHandler(EntityBrachiosaurus.class, new RenderPrehistoric(new ModelBrachiosaurus()));
        RenderingRegistry.registerEntityRenderingHandler(EntityMammoth.class, new RenderPrehistoric(new ModelMammoth()));
        RenderingRegistry.registerEntityRenderingHandler(EntitySpinosaurus.class, new RenderPrehistoric(new ModelSpinosaurus()));
        RenderingRegistry.registerEntityRenderingHandler(EntityCompsognathus.class, new RenderGlowingPrehistoric(new ModelCompsognathus(), new ResourceLocation(Revival.MODID, "textures/mob/compsognathus/compsognathus_overlay.png")));
        RenderingRegistry.registerEntityRenderingHandler(EntityDodo.class, new RenderPrehistoric(new ModelDodo()));
        RenderingRegistry.registerEntityRenderingHandler(EntityAnkylosaurus.class, new RenderPrehistoric(new ModelAnkylosaurus()));
        RenderingRegistry.registerEntityRenderingHandler(EntityPachycephalosaurus.class, new RenderPrehistoric(new ModelPachycephalosaurus()));
        RenderingRegistry.registerEntityRenderingHandler(EntityDeinonychus.class, new RenderPrehistoric(new ModelDeinonychus()));
        RenderingRegistry.registerEntityRenderingHandler(EntityGallimimus.class, new RenderPrehistoric(new ModelGallimimus()));
        RenderingRegistry.registerEntityRenderingHandler(EntitySarcosuchus.class, new RenderPrehistoric(new ModelSarcosuchus()));
        RenderingRegistry.registerEntityRenderingHandler(EntityAllosaurus.class, new RenderPrehistoric(new ModelAllosaurus()));
        RenderingRegistry.registerEntityRenderingHandler(EntityLiopleurodon.class, new RenderPrehistoric(new ModelLiopleurodon()));
        RenderingRegistry.registerEntityRenderingHandler(EntityElasmotherium.class, new RenderPrehistoric(new ModelElasmotherium()));
        RenderingRegistry.registerEntityRenderingHandler(EntityCeratosaurus.class, new RenderPrehistoric(new ModelCeratosaurus()));
        RenderingRegistry.registerEntityRenderingHandler(EntityConfuciusornis.class, new RenderPrehistoric(new ModelConfuciusornis()));
        RenderingRegistry.registerEntityRenderingHandler(EntityDryosaurus.class, new RenderPrehistoric(new ModelDryosaurus()));
        RenderingRegistry.registerEntityRenderingHandler(EntityTherizinosaurus.class, new RenderPrehistoric(new ModelTherizinosaurus()));
        RenderingRegistry.registerEntityRenderingHandler(EntityParasaurolophus.class, new RenderPrehistoric(new ModelParasaurolophus()));
        RenderingRegistry.registerEntityRenderingHandler(EntityGastornis.class, new RenderPrehistoric(new ModelTerrorBird()));
        RenderingRegistry.registerEntityRenderingHandler(EntityKelenken.class, new RenderPrehistoric(new ModelTerrorBird()));
        RenderingRegistry.registerEntityRenderingHandler(EntityPhorusrhacos.class, new RenderPrehistoric(new ModelTerrorBird()));
        RenderingRegistry.registerEntityRenderingHandler(EntityTitanis.class, new RenderPrehistoric(new ModelTerrorBird()));
        RenderingRegistry.registerEntityRenderingHandler(EntityHenodus.class, new RenderPrehistoric(new ModelHenodus()));
        RenderingRegistry.registerEntityRenderingHandler(EntityIcthyosaurus.class, new RenderPrehistoric(new ModelIcthyosaurus()));

        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAnuStatue.class, new TileEntityAnuStatueRender());
        ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(FABlockRegistry.ANU_STATUE), 0, TileEntityAnuStatue.class);
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAnubiteStatue.class, new TileEntityAnubiteStatueRender());
        ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(FABlockRegistry.ANUBITE_STATUE), 0, TileEntityAnubiteStatue.class);
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAncientChest.class, new TileEntityAncientChestRender());
        ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(FABlockRegistry.ANCIENT_CHEST), 0, TileEntityAncientChest.class);
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySarcophagus.class, new TileEntitySarcophagusRender());
        ForgeHooksClient.registerTESRItemStack(Item.getItemFromBlock(FABlockRegistry.SARCOPHAGUS), 0, TileEntitySarcophagus.class);
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFigurine.class, new TileEntityFigurineRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAmphora.class, new TileEntityVaseRenderer(new ModelVaseAmphora()));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityVolute.class, new TileEntityVaseRenderer(new ModelVaseVolute()));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityKylix.class, new TileEntityVaseRenderer(new ModelVaseKylix()));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTimeMachine.class, new TileEntityTimeMachineRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCultivate.class, new TileEntityCultivateRender());

        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, worldIn, pos, tintIndex) -> worldIn != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(worldIn, pos) : ColorizerFoliage.getFoliageColorBasic(), FABlockRegistry.FERNS);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
            @SuppressWarnings("deprecation")
            IBlockState state = ((ItemBlock) stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
            return Minecraft.getMinecraft().getBlockColors().colorMultiplier(state, null, null, tintIndex);
        }, FABlockRegistry.FERNS);
    }

    public void onPostInit() {
    }

    public void registerItemRenderer(Item item, String path, String type) {
        this.registerItemRenderer(item, 0, path, type);
    }

    public void registerItemRenderer(Item item, int meta, String path, String type) {
        ModelResourceLocation resource = new ModelResourceLocation(Revival.MODID + ":" + path, type);
        ModelLoader.setCustomModelResourceLocation(item, meta, resource);
    }

    public void registerBlockRenderer(Block block, int meta, String path, String type) {
        this.registerItemRenderer(Item.getItemFromBlock(block), meta, path, type);
    }

    public void registerBlockRenderer(Block block, final String path, final String type) {
        this.registerBlockRenderer(block, 0, path, type);
    }
}
