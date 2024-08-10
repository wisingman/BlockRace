package uk.maxmoore.blockrace.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerListPingEvent;
import uk.maxmoore.blockrace.BlockRace;
import uk.maxmoore.blockrace.PlayerSettings;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (BlockRace.getInstance().getPlayer(player) != null) {
            // Player has already joined so let them back in
            player.setGameMode(GameMode.SURVIVAL);
        } else {
            if (!BlockRace.getInstance().isGameStarted()) {
                player.setGameMode(GameMode.SURVIVAL);
                // Game isn't started, initialize
                BlockRace.getInstance().getPlayerSettingsList().add(new PlayerSettings(player));
                BlockRace.getInstance().getPlayer(player).init();
            } else {
                // Game has started, spectate
                player.setGameMode(GameMode.SPECTATOR);
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        PlayerSettings settings = BlockRace.getInstance().getPlayer(player);
        if (settings.getCurrentBlock() == e.getBlock().getType()) {
            settings.change(true);
        }
    }

    @EventHandler
    public void onUpdate(InventoryPickupItemEvent e) {

        if (!(e.getInventory().getHolder() instanceof Player)) {
            return;
        }

        Player player = (Player) e.getInventory().getHolder();
        PlayerSettings settings = BlockRace.getInstance().getPlayer(player);
        if (e.getItem().getItemStack().getType() == settings.getCurrentBlock()) {
            settings.change(true);
        }
    }

    @EventHandler
    public void onCraft(CraftItemEvent e) {
        Player player = (Player) e.getWhoClicked();
        PlayerSettings settings = BlockRace.getInstance().getPlayer(player);
        if (e.getRecipe().getResult().getType() == settings.getCurrentBlock()) {
            settings.change(true);
        }
    }

    @EventHandler
    public void onPing(ServerListPingEvent e) {
        e.setMotd("Game is currently " + (BlockRace.getInstance().isGameStarted() ? "running" : "not running"));
    }

}
