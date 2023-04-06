package me.minegroup;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.entity.Player;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.event.Listener;

public final class AcidRain extends JavaPlugin implements Listener {
    private static AcidRain plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getWorld("world").setClearWeatherDuration(1200);
    }

    @EventHandler
    public void RainDetector(WeatherChangeEvent event) {
        if (event.toWeatherState()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    for (Player player : plugin.getServer().getOnlinePlayers()) {
                        int blocklocation = player.getLocation().getWorld().getHighestBlockYAt(player.getLocation());
                        if (!player.getWorld().isThundering() && !player.getWorld().hasStorm())
                            this.cancel();
                        if (blocklocation <= player.getLocation().getY()) {
                            player.damage(1);
                            player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXTINGUISH_FIRE,
                                    SoundCategory.PLAYERS, 1.0F, 1.0F);
                        }
                    }
                }
            }.runTaskTimer(plugin, 20, 20);
        }
    }

    public static AcidRain getPlugin() {
        return plugin;
    }
}