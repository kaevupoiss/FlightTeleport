package me.kaevupoiss.flightteleport;

import org.bukkit.plugin.java.JavaPlugin;

public class FlightTeleport extends JavaPlugin {
    @Override
    public void onEnable() {
        this.getCommand("fteleport").setExecutor(new FTelePort());
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
