package space.yurisi.levelsystem.listener.entity;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import space.yurisi.levelsystem.LevelSystemAPI;
import space.yurisi.levelsystem.exception.PlayerDataNotFoundException;

public class DeathEvent implements Listener {
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event){
        Player killer = event.getEntity().getKiller();
        LevelSystemAPI api = LevelSystemAPI.getInstance();

        if(killer == null){
            return;
        }

        try {
            api.addExp(killer, 3);
        } catch (PlayerDataNotFoundException ignored) {
        }
    }
}
