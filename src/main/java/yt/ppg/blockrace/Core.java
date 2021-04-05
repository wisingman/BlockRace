package yt.ppg.blockrace;

import lombok.Getter;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import yt.ppg.blockrace.commands.GameCommand;
import yt.ppg.blockrace.listeners.PlayerListener;
import yt.ppg.blockrace.util.CC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Core extends JavaPlugin {

    @Getter private static Core instance;
    @Getter private final List<PlayerSettings> playerSettingsList = new ArrayList<>();
    @Getter private int timeLeft = 1200;
    @Getter private boolean gameStarted = false;
    @Getter private final List<Material> possibleBlocks = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        getCommand("game").setExecutor(new GameCommand());

        for (Material material : Material.values()) {
            String name = material.name().toLowerCase();

            if (!material.isBlock()) {
                continue;
            }

            if (name.contains("sandstone") || name.contains("slab") || name.contains("stair") || name.contains("netherite") || name.contains("smooth") || name.contains("stripped")) {
                continue;
            }

            List<String> contains = Arrays.asList("log", "plank", "ore", "gravel", "dirt");

            for (String possible : contains) {
                if (name.contains(possible)) {
                    possibleBlocks.add(material);
                }
            }

            List<String> exact = Arrays.asList("stone", "cobblestone");

            for (String possible : exact) {
                if (name.equals(possible)) {
                    possibleBlocks.add(material);
                }
            }
        }
    }

    public PlayerSettings getPlayer(Player player) {
        return playerSettingsList.stream().filter(playerSettings -> playerSettings.getPlayer() == player).findFirst().orElse(null);
    }

    public void startGame(Player trigger) {
        if (gameStarted) {
            trigger.sendMessage("Game already started");
            return;
        }

        gameStarted = true;

        for (PlayerSettings settings : getPlayerSettingsList()) {
            settings.change(false);
        }

        new BukkitRunnable() {
            @Override
            public void run() {

                if (timeLeft == 0) {
                    PlayerSettings winner = playerSettingsList.get(0);
                    for (PlayerSettings all : getPlayerSettingsList()) {
                        if (all.getScore() > winner.getScore()) {
                            winner = all;
                        }
                    }

                    for (PlayerSettings settings : getPlayerSettingsList()) {
                        settings.endBossBar(winner);
                    }

                    Bukkit.broadcastMessage(CC.translate("&6&l" + winner.getPlayer().getName() + " has won the game! Congratulations!"));
                    this.cancel();
                    return;
                }

                // loop
                for (PlayerSettings settings : getPlayerSettingsList()) {
                    settings.bossBarLoop();
                    settings.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(CC.translate("&6&lCurrent block - " + settings.getCurrentBlock().name().toUpperCase().replace("_", " "))));
                }

                timeLeft--;
            }
        }.runTaskTimer(this, 0, 20);
    }

    protected void loadMetrics() {
        new Metrics(this, 10926);
    }
}
