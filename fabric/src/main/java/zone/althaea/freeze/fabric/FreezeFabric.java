package zone.althaea.freeze.fabric;

import zone.althaea.freeze.Freeze;
import net.fabricmc.api.ModInitializer;

public final class FreezeFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Freeze.init();
    }
}
