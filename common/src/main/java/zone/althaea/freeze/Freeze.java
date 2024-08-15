package zone.althaea.freeze;

import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.event.events.common.PlayerEvent;
import net.minecraft.server.level.ServerPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Freeze {
    public static final String MOD_ID = "freeze";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static void init() {
        PlayerEvent.PLAYER_JOIN.register((ServerPlayer player) -> {
            if (player.server.tickRateManager().isFrozen()) {
                player.server.tickRateManager().setFrozen(false);
                LOGGER.info("Server unfrozen");
            }
        });

        PlayerEvent.PLAYER_QUIT.register((player) -> {
            if (player.server.getPlayerCount() == 1) {
                player.server.tickRateManager().setFrozen(true);
                LOGGER.info("Server frozen until a player joins");
            }
        });

        LifecycleEvent.SERVER_STARTED.register((server) -> {
            server.tickRateManager().setFrozen(true);
            LOGGER.info("Server frozen until the first player joins");
        });
    }
}
