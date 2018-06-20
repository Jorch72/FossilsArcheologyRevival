package fossilsarcheology.server.config;

import net.ilexiconn.llibrary.server.config.ConfigEntry;

public class FossilConfig {
	@ConfigEntry(category = "generation")
	public final boolean generatePalaeoraphe = false;
	@ConfigEntry(category = "generation")
	public final boolean generateHellShips = true;
	@ConfigEntry(category = "generation")
	public boolean generateAcademy = true;
	@ConfigEntry(category = "generation")
	public boolean generateShips = true;
	@ConfigEntry(category = "generation")
	public boolean generateTemple = true;
	@ConfigEntry(category = "generation")
	public final boolean generateFossils = true;
	@ConfigEntry(category = "generation")
	public final boolean generatePermafrost = true;
	@ConfigEntry(category = "generation")
	public final boolean generateVolcanicRock = true;
	@ConfigEntry(category = "generation")
	public boolean generateAztecWeaponShops = true;
	@ConfigEntry(category = "generation")
	public boolean generateMoai = true;
	@ConfigEntry(category = "generation")
	public final boolean generateTarSites = true;
	@ConfigEntry(category = "generation")
	public boolean generateFossilSites = true;
	@ConfigEntry(category = "generation")
	public final int[] oreGenerationDimensions = {0};

	@ConfigEntry(category = "entity spawning")
	public final boolean spawnCoelacanth = true;
	@ConfigEntry(category = "entity spawning")
	public final boolean spawnSturgeon = true;
	@ConfigEntry(category = "entity spawning")
	public final boolean spawnAlligatorGar = true;
	@ConfigEntry(category = "entity spawning")
	public final boolean spawnNautilus = true;
	@ConfigEntry
	public final boolean healingDinos = true;
	@ConfigEntry
	public final boolean starvingDinos = true;
	@ConfigEntry
	public final boolean dinoBlockBreaking = true;
	@ConfigEntry
	public boolean skullOverlay = true;
	@ConfigEntry
	public boolean loginMessage = true;
	@ConfigEntry
	public boolean allowTableEnchantments = true;
	@ConfigEntry
	public boolean allowBookEnchantments = true;
	@ConfigEntry
	public boolean allowBreeding = true;
	@ConfigEntry(category = "client")
	public final boolean customMainMenu = true;

	@ConfigEntry(category = "dinosaurs")
	public final boolean featheredTRex = true;
	@ConfigEntry(category = "dinosaurs")
	public final boolean featheredDeinonychus = true;
	@ConfigEntry(category = "dinosaurs")
	public final boolean featheredGallimimus = true;
	@ConfigEntry(category = "dinosaurs")
	public final boolean featheredCompsognathus = true;
	@ConfigEntry(category = "dinosaurs")
	public final boolean quilledTriceratops = true;
	@ConfigEntry(category = "dinosaurs")
	public final boolean featheredVelociraptor = true;
	@ConfigEntry(category = "dinosaurs")
	public final boolean featheredTherizinosaurus = true;
	@ConfigEntry(category = "dinosaurs")
	public final boolean eggsLikeChickens = false;
	@ConfigEntry(category = "dinosaurs")
	public boolean sleepingParticles = true;
	@ConfigEntry(category = "dinosaurs")
	public final int flyingTargetMaxHeight = 128;
	@ConfigEntry(category = "ids")
	public int biomeIDDarknessLair = 128;
	@ConfigEntry(category = "ids")
	public int biomeIDTreasure = 127;
	@ConfigEntry(category = "ids")
	public final int dimensionIDDarknessLair = -23;
	@ConfigEntry(category = "ids")
	public final int dimensionIDTreasure = -34;
	@ConfigEntry(category = "ids")
	public int enchantmentIDArcheology = 91;
	@ConfigEntry(category = "ids")
	public int enchantmentIDPaleontology = 90;
	@ConfigEntry(category = "ids")
	public int villagerId = 303;
	@ConfigEntry(category = "generation")
	public boolean logCascadingWorldGen = false;
}
