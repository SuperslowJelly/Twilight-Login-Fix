package complex.twilightloginfix;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import io.github.nucleuspowered.nucleus.api.NucleusAPI;
import io.github.nucleuspowered.nucleus.api.nucleusdata.Warp;
import io.github.nucleuspowered.nucleus.api.service.NucleusWarpService;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

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
    public void onPlayerLeave(ClientConnectionEvent.Disconnect event, @First Player player) {
        String worldName = player.getWorld().getName();
        if (Objects.equal(worldName, "DIM7") || (Objects.equal(worldName, "DIM-777") || (Objects.equal(worldName, "DIM5")))) {
            NucleusWarpService warpService;
            if (!Objects.equal(warpService = NucleusAPI.getWarpService().orElse(null), null)) {
                Warp spawn;
                if (!Objects.equal(spawn = warpService.getWarp("spawn").orElse(null), null)) {
                    Location location;
                    if (!Objects.equal(location = spawn.getLocation().orElse(null), null)) {
                        World world;
                        if (!Objects.equal(world = Sponge.getServer().getWorld(location.getExtent().getUniqueId()).orElse(null), null)) {
                            player.setLocation(world.getSpawnLocation());
                            logger.info("Player, " + player.getName() + ", logged out in the Twilight Forest: Re-locating to spawn...");
                        }
                    }
                }
            }
        }
    }
}
