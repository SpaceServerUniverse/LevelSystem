package space.yurisi.levelsystem.event.plugin_event.level;

import net.kyori.adventure.text.Component;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public final class UpEvent implements Listener {

    @EventHandler
    public void onLevelUp(space.yurisi.levelsystem.plugin_event.level.LevelUpEvent event) {
        Player player = event.getPlayer();
        int before_level = event.getLevel() - 1;
        player.sendActionBar(Component.text("§l§eLevelUP! " + before_level + " → " + event.getLevel()));
        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 1);
    }
}
