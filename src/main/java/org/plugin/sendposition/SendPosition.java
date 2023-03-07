package org.plugin.sendposition;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.ChatPaginator;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


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
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!command.getName().equalsIgnoreCase("sendposition"))
            return super.onTabComplete(sender, command, alias, args);
        if (args.length == 1) {
            if (args[0].length() == 0) {
                return Arrays.asList("subject", "description");
            } else {
                if ("subject".startsWith(args[0])) {
                    return Collections.singletonList("subject");
                } else if ("description".startsWith(args[0])) {
                    return Collections.singletonList("description");
                }
                if ("description".startsWith(args[2])) {
                    return Collections.singletonList("description");
                } else if ("subject".startsWith(args[2])) {
                    return Collections.singletonList("subject");
                }
            }
        }
        return super.onTabComplete(sender, command, alias, args);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String name = "name:" + ChatColor.BOLD + player.getName() + ChatColor.RESET;
            Location loc = player.getLocation();
            @NotNull String ddim = ((Player) sender).getWorld().getName();
            String dim;
            if (ddim.equals("world")) {
                dim = ChatColor.DARK_GREEN + "OverWorld" + ChatColor.RESET;
            } else if (ddim.equals("world_nether")) {
                dim = ChatColor.DARK_RED + "The Nether" + ChatColor.RESET;
            } else if (ddim.equals("world_the_end")) {
                dim = ChatColor.DARK_PURPLE + "The End" + ChatColor.RESET;
            } else {
                dim = ChatColor.DARK_GRAY + ddim + ChatColor.RESET;
            }
            double dx = loc.getX();
            double dy = loc.getY();
            double dz = loc.getZ();
            double x = Math.floor(dx * 100) / 100;
            double y = Math.floor(dy * 100) / 100;
            double z = Math.floor(dz * 100) / 100;
            if (args.length == 0) {
                sender.sendMessage("[Send Position] " + name + " dimension:" + dim + ChatColor.RED + " x:" + ChatColor.RESET + x + ChatColor.GREEN + " y:" + ChatColor.RESET + y + ChatColor.BLUE + " z:" + ChatColor.RESET + z);
                player.playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, 2.0f, 1.0f);
                getLogger().info(name + " is at " + "dimension:" + dim + " x:" + x + " y:" + y + " z:" + z);
                return true;
            } else {
                if (args.length == 2 && args[0].equalsIgnoreCase("subject")) {
                    sender.sendMessage("[Send Position] " + name + " is at " + " dimension:" + dim + ChatColor.RED + " x:" + ChatColor.RESET + x + ChatColor.GREEN + " y:" + ChatColor.RESET + y + ChatColor.BLUE + " z:" + ChatColor.RESET + z + " subject:" + args[1]);
                    player.playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, 2.0f, 1.0f);
                    getLogger().info(name + " is at " + "dimension:" + dim + " x:" + x + " y:" + y + " z:" + z + " subject:" + args[1]);
                } else if (args.length == 4 && args[2].equalsIgnoreCase("description")) {
                    sender.sendMessage("[Send Position] " + name + " is at " + " dimension:" + dim + ChatColor.RED + " x:" + ChatColor.RESET + x + ChatColor.GREEN + " y:" + ChatColor.RESET + y + ChatColor.BLUE + " z:" + ChatColor.RESET + z + " subject:" + args[1] + " description:" + args[3]);
                    player.playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, 2.0f, 1.0f);
                    getLogger().info(name + "dimension:" + dim + " x:" + x + " y:" + y + " z:" + z + " subject:" + args[1] + " description:" + args[3]);
                } else if (args.length == 2 && args[0].equalsIgnoreCase("description")) {
                    sender.sendMessage("[Send Position] " + name + " dimension:" + dim + ChatColor.RED + " x:" + ChatColor.RESET + x + ChatColor.GREEN + " y:" + ChatColor.RESET + y + ChatColor.BLUE + " z:" + ChatColor.RESET + z + " description:" + args[1]);
                    player.playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, 2.0f, 1.0f);
                    getLogger().info(name + " dimension:" + dim + " x:" + x + " y:" + y + " z:" + z + " description:" + args[1]);
                } else if (args.length == 4 && args[2].equalsIgnoreCase("subject")) {
                    sender.sendMessage("[Send Position] " + name + " dimension:" + dim + ChatColor.RED + " x:" + ChatColor.RESET + x + ChatColor.GREEN + " y:" + ChatColor.RESET + y + ChatColor.BLUE + " z:" + ChatColor.RESET + z + " subject:" + args[3] + " description:" + args[1]);
                    player.playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, 2.0f, 1.0f);
                    getLogger().info(name + " dimension:" + dim + " x:" + x + " y:" + y + " z:" + z + " subject:" + args[3] + " description" + args[1]);
                } else {
                    sender.sendMessage("that command cannot be executed");
                }

            }
            return false;
        }
        return false;
    }
}
