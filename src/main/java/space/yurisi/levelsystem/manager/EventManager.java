package space.yurisi.levelsystem.manager;

import org.bukkit.Bukkit;
import space.yurisi.levelsystem.LevelSystem;
import space.yurisi.levelsystem.event.block.BreakEvent;
import space.yurisi.levelsystem.event.player.JoinEvent;
import space.yurisi.levelsystem.event.player.LoginEvent;
import space.yurisi.levelsystem.event.player.QuitEvent;
import space.yurisi.levelsystem.event.plugin_event.level.UpEvent;

public class EventManager {

    public EventManager(LevelSystem main) {
        Bukkit.getServer().getPluginManager().registerEvents(new LoginEvent(main), main);
        Bukkit.getServer().getPluginManager().registerEvents(new JoinEvent(main), main);
        Bukkit.getServer().getPluginManager().registerEvents(new QuitEvent(main), main);
        Bukkit.getServer().getPluginManager().registerEvents(new BreakEvent(), main);
        Bukkit.getServer().getPluginManager().registerEvents(new UpEvent(), main);
    }
}
