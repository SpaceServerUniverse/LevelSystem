package space.yurisi.levelsystem.manager;

import org.bukkit.Bukkit;
import space.yurisi.levelsystem.LevelSystem;
import space.yurisi.levelsystem.listener.block.BreakEvent;
import space.yurisi.levelsystem.listener.block.PlaceEvent;
import space.yurisi.levelsystem.listener.entity.DeathEvent;
import space.yurisi.levelsystem.listener.inventory.FurnaceExtractEvent;
import space.yurisi.levelsystem.listener.player.*;
import space.yurisi.levelsystem.listener.plugin_event.level.UpEvent;

public class EventManager {

    public EventManager(LevelSystem main) {
        Bukkit.getServer().getPluginManager().registerEvents(new BreakEvent(), main);
        Bukkit.getServer().getPluginManager().registerEvents(new PlaceEvent(), main);

        Bukkit.getServer().getPluginManager().registerEvents(new DeathEvent(), main);

        Bukkit.getServer().getPluginManager().registerEvents(new FurnaceExtractEvent(), main);

        Bukkit.getServer().getPluginManager().registerEvents(new FishEvent(), main);
        Bukkit.getServer().getPluginManager().registerEvents(new ItemConsumeEvent(), main);
        Bukkit.getServer().getPluginManager().registerEvents(new LoginEvent(main), main);
        Bukkit.getServer().getPluginManager().registerEvents(new JoinEvent(main), main);
        Bukkit.getServer().getPluginManager().registerEvents(new QuitEvent(main), main);

        Bukkit.getServer().getPluginManager().registerEvents(new UpEvent(), main);
    }
}
