package space.yurisi.levelsystem.task;

import com.google.protobuf.Api;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import space.yurisi.levelsystem.LevelSystemAPI;
import space.yurisi.levelsystem.exception.PlayerDataNotFoundException;
import space.yurisi.levelsystem.manager.PlayerLevelDataManager;
import space.yurisi.levelsystem.utils.DayOfWeek;

import java.util.Calendar;
import java.util.Collection;

public class SaveDataTask extends BukkitRunnable {

    private PlayerLevelDataManager manager;

    public SaveDataTask(PlayerLevelDataManager manager) {
        this.manager = manager;
    }

    @Override
    public void run() {
        manager.saveAll();
        Collection<? extends Player> online = Bukkit.getServer().getOnlinePlayers();
        for (Player player: online){
            try {
                LevelSystemAPI.getInstance().addExp(player, 1000);
                player.sendMessage("[管理AI] 遊んでくれてありがとうございます！1000EXPプレゼントです！");
            } catch (PlayerDataNotFoundException ignored) {
            }
        }

        DayOfWeek.getInstance().checkHoliday();
    }
}
