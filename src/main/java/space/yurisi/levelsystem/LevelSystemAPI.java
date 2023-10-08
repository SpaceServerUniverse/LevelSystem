package space.yurisi.levelsystem;

import org.bukkit.entity.Player;
import space.yurisi.levelsystem.exception.PlayerDataNotFoundException;
import space.yurisi.levelsystem.manager.PlayerLevelDataManager;
import space.yurisi.levelsystem.utils.connector.UniverseCoreAPIConnector;
import space.yurisi.levelsystem.utils.levelmode.LevelModes;
import space.yurisi.universecore.exception.PlayerLevelNotFoundException;
import space.yurisi.universecore.exception.PlayerNormalLevelNotFoundException;
import space.yurisi.universecore.exception.UserNotFoundException;

public class LevelSystemAPI {

    private PlayerLevelDataManager manager;

    private UniverseCoreAPIConnector connector;

    private static LevelSystemAPI api;

    public LevelSystemAPI(PlayerLevelDataManager manager, UniverseCoreAPIConnector connector) {
        this.manager = manager;
        this.connector = connector;
        api = this;
    }

    public void addExp(Player player, int exp) throws PlayerDataNotFoundException {
        if (!this.manager.exists(player)) {
            throw new PlayerDataNotFoundException("プレイヤーデータが見つかりません。プレイヤーがオンラインではない可能性があります。");
        }
        this.manager.get(player).addExp(exp);
    }

    public int getLevel(Player player) throws PlayerDataNotFoundException {
        if (!this.manager.exists(player)) {
            throw new PlayerDataNotFoundException("プレイヤーデータが見つかりません。プレイヤーがオンラインではない可能性があります。");
        }
        return this.manager.get(player).getLevel();
    }

    public int getLevelFromPlayerName(String name) throws UserNotFoundException, PlayerNormalLevelNotFoundException {
        //TODO LevelMode
        return connector.getNormalLevelFromPlayerName(name).getLevel();
    }

    public LevelModes getLevelMode(Player player) throws PlayerDataNotFoundException {
        if (this.manager.exists(player)) {
            throw new PlayerDataNotFoundException("プレイヤーデータが見つかりません。プレイヤーがオンラインではない可能性があります。");
        }
        return this.manager.get(player).getLevelMode();
    }

    public LevelModes getLevelModeFromPlayerName(String name) throws UserNotFoundException, PlayerLevelNotFoundException {
        int level_mode = connector.getLevelFromPlayerName(name).getLevel_mode();
        return LevelModes.fromInteger(level_mode);
    }

    public static LevelSystemAPI getInstance() {
        return api;
    }
}
