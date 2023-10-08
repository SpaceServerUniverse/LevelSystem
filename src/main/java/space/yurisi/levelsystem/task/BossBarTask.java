package space.yurisi.levelsystem.task;

import org.bukkit.boss.BossBar;
import org.bukkit.scheduler.BukkitRunnable;
import space.yurisi.levelsystem.utils.PlayerLevelData;

public class BossBarTask extends BukkitRunnable {

    private BossBar bossBar;

    private PlayerLevelData levelData;

    public BossBarTask(PlayerLevelData levelData, BossBar bossBar) {
        this.bossBar = bossBar;
        this.levelData = levelData;
    }

    @Override
    public void run() {
        this.bossBar.setTitle("レベル:" + levelData.getLevel() + " " + levelData.getLevelModeAllExp() + "/" + levelData.getNextLevelExp());
        this.bossBar.setProgress(levelData.getPercentage());
    }
}
