package net.zypr.imgTrail;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class TrailExecutor {

    private final HashMap<Player, Trail> playerTrails;

    public TrailExecutor() {
        this.playerTrails = new HashMap<>();
        this.run();
    }

    public void setPlayerTrail(Player player, Trail trail) {
        playerTrails.put(player, trail);
    }

    public Trail getPlayerTrail(Player player) {
        return playerTrails.get(player);
    }

    public void removePlayerTrail(Player player) {
        playerTrails.remove(player);
    }

    private void run() {
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(ImgTrail.getInstance(), () -> {
            for (Player player : playerTrails.keySet()) {
                if (player.isOnline()) {
                    playerTrails.get(player).display(player);
                }
            }
        }, 0, 40);
    }


}
