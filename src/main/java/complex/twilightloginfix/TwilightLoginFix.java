package complex.twilightloginfix;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.plugin.Plugin;

@Plugin(
        id = "twilight-login-fix",
        name = "Twilight Login Fix",
        description = "Fix for the twilight forest login issue on FTB Revelation and FTB Direwolf20",
        url = "https://github.com/SuperslowJelly",
        authors = {
                "Jelly"
        }
)
public class TwilightLoginFix {

    @Inject
    private Logger logger;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        logger.info("Twilight Login Fix initialised successfully!");
    }

    @Listener
    public void onPlayerJoin(ClientConnectionEvent.Disconnect event) {
        Player player = event.getTargetEntity();
        logger.info("Player disconnection detected!\nPlayer: " + player.getName() + "\nWorld: " + player.getWorld().getName() + "\nLocation: " + player.getLocation().getPosition());
        if (Objects.equal(player.getWorld().getName(), "DIM7") || (Objects.equal(player.getWorld().getName(), "DIM-777"))) {
            player.setLocation((Sponge.getServer().getWorld("world").orElse(null)).getSpawnLocation());
            logger.info("Player, " + player.getName() + ", re-located to spawn!");
        } else {
            logger.info("Player, " + player.getName() + ", re-location not required!");
        }
    }
}
