package fossilsarcheology.server.compat.thaumcraft;

import fossilsarcheology.server.compat.thaumcraft.ThaumcraftCompat;
import net.minecraftforge.fml.common.Loader;

/**
 * Created by Joseph on 6/23/2018.
 */
public class ThaumcraftCompatBridge {

	private static final String TC_MOD_ID = "thaumcraft";

	public static void registerAspects() {
		if (Loader.isModLoaded(TC_MOD_ID)) {
			ThaumcraftCompat.registerAspectsInternal();
		}
	}
}