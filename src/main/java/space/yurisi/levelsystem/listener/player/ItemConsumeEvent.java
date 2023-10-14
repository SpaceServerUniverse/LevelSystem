package space.yurisi.levelsystem.listener.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import space.yurisi.levelsystem.LevelSystemAPI;
import space.yurisi.levelsystem.exception.PlayerDataNotFoundException;

public class ItemConsumeEvent implements Listener {

    @EventHandler
    public void onItemConsume(PlayerItemConsumeEvent event){
        Player player = event.getPlayer();
        LevelSystemAPI api = LevelSystemAPI.getInstance();

        try {
            api.addExp(player, 3);
        }catch (PlayerDataNotFoundException ignored){
        }
    }
}
