package space.yurisi.levelsystem.listener.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import space.yurisi.levelsystem.LevelSystemAPI;
import space.yurisi.levelsystem.exception.PlayerDataNotFoundException;

public class FishEvent implements Listener {

    @EventHandler
    public void onFish(PlayerFishEvent event){
        Player player = event.getPlayer();
        LevelSystemAPI api = LevelSystemAPI.getInstance();

        if(event.getState() == PlayerFishEvent.State.CAUGHT_FISH){
            try {
                api.addExp(player, 70);
            }catch (PlayerDataNotFoundException ignored){
            }
        }
    }
}
