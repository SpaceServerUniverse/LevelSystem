package space.yurisi.levelsystem;

import org.bukkit.plugin.java.JavaPlugin;
import space.yurisi.levelsystem.manager.*;
import space.yurisi.levelsystem.task.SaveDataTask;
import space.yurisi.levelsystem.utils.DayOfWeek;
import space.yurisi.levelsystem.utils.connector.UniverseCoreAPIConnector;

public final class LevelSystem extends JavaPlugin {

    private PlayerLevelDataManager playerLevelDataManager;

    private PlayerBossBarManager playerBossBarManager;

    private TaskManager taskManager;

    private UniverseCoreAPIConnector connector;

    @Override
    public void onEnable() {
        new DayOfWeek();
        this.connector = new UniverseCoreAPIConnector();
        playerLevelDataManager = new PlayerLevelDataManager(this);
        playerBossBarManager = new PlayerBossBarManager();
        taskManager = new TaskManager(this);
        new LevelSystemAPI(getPlayerLevelDataManager(), getConnector());
        new EventManager(this);
        new CommandManager(this);
        new SaveDataTask(getPlayerLevelDataManager()).runTaskTimer(this, 0L, 12000L);
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
        LevelSystemAPI.getInstance().saveAll();
    }
}
