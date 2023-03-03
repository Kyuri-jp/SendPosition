package org.plugin.sendposition;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public final class SendPosition extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Enable");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Disable");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String name = player.getName();
            Location loc = player.getLocation();
            double dx = loc.getX();
            double dy = loc.getY();
            double dz = loc.getZ();
            double x = Math.floor(dx * 100) / 100;
            double y = Math.floor(dy * 100) / 100;
            double z = Math.floor(dz * 100) / 100;
            sender.sendMessage("[Send Position] "+name + " is at " + "x:" + x + " y:" + y + " z:" + z);
            player.playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, 2.0f, 1.0f);
            getLogger().info(name + " is at " + "x:" + x + " y:" + y + " z:" + z);
        }
        return false;
    }
}
