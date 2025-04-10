package net.zypr.imgTrail;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ImgTrail extends JavaPlugin {

    private static ImgTrail instance;
    private TrailExecutor trailExecutor;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("ImgTrail plugin has been enabled!");
        getCommand("imgtrail").setExecutor(new CommandController());
        instance = this;
        trailExecutor = new TrailExecutor();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public TrailExecutor getTrailExecutor() {
        return trailExecutor;
    }

    public void setTrailExecutor(TrailExecutor trailExecutor) {
        this.trailExecutor = trailExecutor;
    }

    public static ImgTrail getInstance() {
        return instance;
    }
}
