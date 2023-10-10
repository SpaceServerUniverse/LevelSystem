package space.yurisi.levelsystem.event.block;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import space.yurisi.levelsystem.LevelSystemAPI;
import space.yurisi.levelsystem.exception.PlayerDataNotFoundException;

public class PlaceEvent implements Listener {
    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        Player player = event.getPlayer();

        if(!player.getGameMode().equals(GameMode.SURVIVAL)){
            return;
        }

        try {
            LevelSystemAPI.getInstance().addExp(player, 3);
        } catch (PlayerDataNotFoundException exception) {
        }
    }
}
