package space.yurisi.levelsystem.manager;

import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import space.yurisi.levelsystem.LevelSystem;
import space.yurisi.levelsystem.task.BossBarTask;
import space.yurisi.levelsystem.utils.PlayerLevelData;

import java.util.HashMap;
import java.util.UUID;

public class TaskManager {

    private LevelSystem main;

    private final HashMap<UUID, Integer> list = new HashMap<>();

    public TaskManager(LevelSystem main) {
        this.main = main;
    }

    public void createBossBarTask(Player player, PlayerLevelData playerLevelData, BossBar bossBar) {
        BossBarTask task = new BossBarTask(playerLevelData, bossBar);
        task.runTaskTimer(this.main, 0L, 10L);
        this.list.put(player.getUniqueId(), task.getTaskId());
    }

    public void destroyBossBarTask(Player player) {
        Integer task_id = this.list.get(player.getUniqueId());
        if (task_id != null) {
            Bukkit.getScheduler().cancelTask(task_id);
        }

    }
}
