package space.yurisi.levelsystem.event.block;

import org.bukkit.GameMode;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import space.yurisi.levelsystem.LevelSystemAPI;
import space.yurisi.levelsystem.exception.PlayerDataNotFoundException;

import java.util.Map;

public class BreakEvent implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        LevelSystemAPI api = LevelSystemAPI.getInstance();

        if(!player.getGameMode().equals(GameMode.SURVIVAL)){
            return;
        }

        Map<Enchantment, Integer> main_hand_enchantment = player.getInventory().getItemInMainHand().getEnchantments();
        if (main_hand_enchantment.containsKey(Enchantment.SILK_TOUCH)){
            return;
        }

        try {
            switch (event.getBlock().getType()){
                case COAL_ORE ->  api.addExp(player, 10);
                case LAPIS_ORE, REDSTONE_ORE -> api.addExp(player, 20);
                case DIAMOND_ORE -> api.addExp(player, 100);
                case EMERALD_ORE ->  api.addExp(player, 200);
                default -> api.addExp(player, 3);
            }
        } catch (PlayerDataNotFoundException ignored) {
        }
    }
}
