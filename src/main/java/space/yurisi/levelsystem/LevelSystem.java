package space.yurisi.levelsystem;

import org.bukkit.plugin.java.JavaPlugin;
import space.yurisi.levelsystem.manager.EventManager;
import space.yurisi.levelsystem.manager.PlayerBossBarManager;
import space.yurisi.levelsystem.manager.PlayerLevelDataManager;
import space.yurisi.levelsystem.manager.TaskManager;
import space.yurisi.levelsystem.utils.connector.UniverseCoreAPIConnector;

public final class LevelSystem extends JavaPlugin {

    private PlayerLevelDataManager playerLevelDataManager;

    private PlayerBossBarManager playerBossBarManager;

    private TaskManager taskManager;

    private UniverseCoreAPIConnector connector;

    @Override
    public void onEnable() {
        this.connector = new UniverseCoreAPIConnector();
        playerLevelDataManager = new PlayerLevelDataManager(this);
        playerBossBarManager = new PlayerBossBarManager();
        taskManager = new TaskManager(this);
        new LevelSystemAPI(getPlayerLevelDataManager(), getConnector());
        new EventManager(this);
    }

    public UniverseCoreAPIConnector getConnector() {
        return this.connector;
    }

    public PlayerLevelDataManager getPlayerLevelDataManager() {
        return playerLevelDataManager;
    }

    public PlayerBossBarManager getPlayerBossBarManager() {
        return playerBossBarManager;
    }

    public TaskManager getTaskManager() {
        return taskManager;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
