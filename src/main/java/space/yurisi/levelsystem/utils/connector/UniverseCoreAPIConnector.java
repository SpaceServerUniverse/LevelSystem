package space.yurisi.levelsystem.utils.connector;

import org.bukkit.entity.Player;
import space.yurisi.levelsystem.utils.PlayerLevelData;
import space.yurisi.levelsystem.utils.levelmode.LevelModes;
import space.yurisi.universecore.UniverseCoreAPI;
import space.yurisi.universecore.database.DatabaseManager;
import space.yurisi.universecore.database.models.PlayerLevel;
import space.yurisi.universecore.database.models.PlayerNormalLevel;
import space.yurisi.universecore.database.models.User;
import space.yurisi.universecore.database.repositories.PlayerLevelRepository;
import space.yurisi.universecore.database.repositories.PlayerNormalLevelRepository;
import space.yurisi.universecore.database.repositories.UserRepository;
import space.yurisi.universecore.exception.PlayerLevelNotFoundException;
import space.yurisi.universecore.exception.PlayerNormalLevelNotFoundException;
import space.yurisi.universecore.exception.UserNotFoundException;

import java.util.Date;

public class UniverseCoreAPIConnector {

    private UserRepository userRepository;
    private PlayerLevelRepository playerLevelRepository;
    private PlayerNormalLevelRepository playerNormalLevelRepository;

    public UniverseCoreAPIConnector() {
        DatabaseManager dbManager = UniverseCoreAPI.getInstance().getDatabaseManager();
        this.userRepository = dbManager.getUserRepository();
        this.playerLevelRepository = dbManager.getPlayerLevelRepository();
        this.playerNormalLevelRepository = dbManager.getPlayerNormalLevelRepository();
    }

    public void createLevel(Player player) throws UserNotFoundException {
        User user = getUser(player);
        this.playerLevelRepository.createPlayerLevel(player, user);
    }

    public PlayerNormalLevel createNormalLevel(Player player) throws UserNotFoundException {
        User user = getUser(player);
        return this.playerNormalLevelRepository.createPlayerNormalLevel(user);
    }

    public PlayerLevel getPlayerLevel(Player player) throws UserNotFoundException, PlayerLevelNotFoundException {
        return this.playerLevelRepository.getPlayerLevelFromUser(getUser(player));
    }

    public PlayerLevel getLevelFromPlayerName(String name) throws UserNotFoundException, PlayerLevelNotFoundException {
        User user = this.userRepository.getUserFromPlayerName(name);
        return this.playerLevelRepository.getPlayerLevelFromUser(user);
    }

    public PlayerNormalLevel getPlayerNormalLevel(Player player) throws UserNotFoundException, PlayerNormalLevelNotFoundException {
        return this.playerNormalLevelRepository.getPlayerNormalLevelFromUser(getUser(player));
    }

    public PlayerNormalLevel getNormalLevelFromPlayerName(String name) throws UserNotFoundException, PlayerNormalLevelNotFoundException {
        User user = this.userRepository.getUserFromPlayerName(name);
        return this.playerNormalLevelRepository.getPlayerNormalLevelFromUser(user);
    }

    public boolean existsPlayerLevel(Player player) {
        try {
            User user = getUser(player);
            return this.playerLevelRepository.existsPlayerLevelFromUser(user);
        } catch (UserNotFoundException exception) {
            throw new RuntimeException(exception);
        }
    }

    public boolean existsPlayerNormalLevel(Player player) {
        try {
            User user = getUser(player);
            return this.playerNormalLevelRepository.existsPlayerLevelFromUser(user);
        } catch (UserNotFoundException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void save(PlayerLevelData playerLevelData) {
        try {
            User user = getUser(playerLevelData.getPlayer());
            PlayerLevel level = this.playerLevelRepository.getPlayerLevelFromUser(user);
            level.setTotal_exp(playerLevelData.getTotal_exp());
            level.setUpdated_at(new Date());
            this.playerLevelRepository.updatePlayerLevel(level);

            if(playerLevelData.getLevelMode().equals(LevelModes.NORMAL)){
                PlayerNormalLevel normalLevel = this.playerNormalLevelRepository.getPlayerNormalLevelFromUser(user);
                normalLevel.setLevel(playerLevelData.getLevel());
                normalLevel.setExp_for_next_level(playerLevelData.getExp_for_next_level());
                normalLevel.setExp(playerLevelData.getExp());
                normalLevel.setLevel_mode_total_exp(playerLevelData.getLevel_mode_total_exp());
                normalLevel.setUpdated_at(new Date());
                this.playerNormalLevelRepository.updatePlayerNormalLevel(normalLevel);
            }

        } catch (UserNotFoundException | PlayerNormalLevelNotFoundException | PlayerLevelNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private User getUser(Player player) throws UserNotFoundException {
        return this.userRepository.getUserFromUUID(player.getUniqueId());
    }
}
