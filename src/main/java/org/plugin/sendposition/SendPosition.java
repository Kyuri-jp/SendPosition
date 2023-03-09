package org.plugin.sendposition;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.ChatPaginator;
import org.jetbrains.annotations.NotNull;

import java.util.*;


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

    //TabComplete
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!command.getName().equalsIgnoreCase("sendposition"))
            return super.onTabComplete(sender, command, alias, args);
        if (args.length == 1) {
            if (args[0].length() == 0) {
                return Arrays.asList("global", "subject", "description");
            } else {
                if ("global".startsWith(args[0])) {
                    return Collections.singletonList("global");
                }
                if ("subject".startsWith(args[0])) {
                    return Collections.singletonList("subject");
                } else if ("description".startsWith(args[0])) {
                    return Collections.singletonList("description");
                }
                if ("description".startsWith(args[0])) {
                    return Collections.singletonList("description");
                } else if ("subject".startsWith(args[0])) {
                    return Collections.singletonList("subject");
                }
            }
        }
        return super.onTabComplete(sender, command, alias, args);
    }

    static Sound sound = Sound.BLOCK_GLASS_BREAK;
    static float vol = 2.0f;
    static float pit = 1.0f;

    //playsound
    public static void playallplayer() {
        List<Player> players = (List<Player>) Bukkit.getOnlinePlayers();
        for (Player player : players) {
            player.playSound(player.getLocation(), sound, vol, pit);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = null;
        if (sender instanceof Player) {
            player = (Player) sender;
            String name = "name:" + ChatColor.BOLD + player.getName() + ChatColor.RESET;
            Location loc = player.getLocation();
            @NotNull String ddim = ((Player) sender).getWorld().getName();
            String dim = switch (ddim) {
                case "world" -> ChatColor.DARK_GREEN + "OverWorld" + ChatColor.RESET;
                case "world_nether" -> ChatColor.DARK_RED + "The Nether" + ChatColor.RESET;
                case "world_the_end" -> ChatColor.DARK_PURPLE + "The End" + ChatColor.RESET;
                default -> ChatColor.DARK_GRAY + ddim + ChatColor.RESET;
            };
            double dx = loc.getX();
            double dy = loc.getY();
            double dz = loc.getZ();
            double x = Math.floor(dx * 100) / 100;
            double y = Math.floor(dy * 100) / 100;
            double z = Math.floor(dz * 100) / 100;
            String base = "[Send Position] " + name + " dimension:" + dim + ChatColor.RED + " x:" + ChatColor.RESET + x + ChatColor.GREEN + " y:" + ChatColor.RESET + y + ChatColor.BLUE + " z:" + ChatColor.RESET + z;
            if (args.length == 0) {
                player.sendMessage(base);
                player.playSound(player.getLocation(), sound, vol, pit);
                getLogger().info(name + " is at " + "dimension:" + dim + " x:" + x + " y:" + y + " z:" + z);
            } else {
                if (args.length == 2 && args[0].equalsIgnoreCase("subject")) {
                    player.sendMessage(base + " subject:" + args[1]);
                    player.playSound(player.getLocation(), sound, vol, pit);
                    getLogger().info(name + " is at " + "dimension:" + dim + " x:" + x + " y:" + y + " z:" + z + " subject:" + args[1]);
                } else if (args.length == 4 && args[2].equalsIgnoreCase("description")) {
                    player.sendMessage(base + " subject:" + args[1] + " description:" + args[3]);
                    player.playSound(player.getLocation(), sound, vol, pit);
                    getLogger().info(name + "dimension:" + dim + " x:" + x + " y:" + y + " z:" + z + " subject:" + args[1] + " description:" + args[3]);
                } else if (args.length == 2 && args[0].equalsIgnoreCase("description")) {
                    player.sendMessage(base+" description:" + args[1]);
                    player.playSound(player.getLocation(), sound, vol, pit);
                    getLogger().info(name + " dimension:" + dim + " x:" + x + " y:" + y + " z:" + z + " description:" + args[1]);
                } else if (args.length == 4 && args[2].equalsIgnoreCase("subject")) {
                    player.sendMessage(base+ " subject:" + args[3] + " description:" + args[1]);
                    player.playSound(player.getLocation(), sound, vol, pit);
                    getLogger().info(name + " dimension:" + dim + " x:" + x + " y:" + y + " z:" + z + " subject:" + args[3] + " description" + args[1]);
                }
                if (args.length == 1 && args[0].equalsIgnoreCase("global")) {
                    player.sendMessage(base+ ChatColor.RESET + z);
                    player.playSound(player.getLocation(), sound, vol, pit);
                    getLogger().info(name + " is at " + "dimension:" + dim + " x:" + x + " y:" + y + " z:" + z + " global");
                } else {
                    if (args.length == 3 && args[1].equalsIgnoreCase("subject")) {
                        Bukkit.broadcastMessage(base+ " subject:" + args[2]);
                        playallplayer();
                        getLogger().info(name + " is at " + "dimension:" + dim + " x:" + x + " y:" + y + " z:" + z + " subject:" + args[2] + " global");
                    } else if (args.length == 5 && args[3].equalsIgnoreCase("description")) {
                        Bukkit.broadcastMessage(base+ " subject:" + args[2] + " description:" + args[4]);
                        playallplayer();
                        getLogger().info(name + "dimension:" + dim + " x:" + x + " y:" + y + " z:" + z + " subject:" + args[2] + " description:" + args[4] + " global");
                    } else if (args.length == 3 && args[1].equalsIgnoreCase("description")) {
                        Bukkit.broadcastMessage(base+ " description:" + args[2]);
                        playallplayer();
                        getLogger().info(name + " dimension:" + dim + " x:" + x + " y:" + y + " z:" + z + " description:" + args[2] + " global");
                    } else if (args.length == 5 && args[3].equalsIgnoreCase("subject")) {
                        Bukkit.broadcastMessage(base+ " subject:" + args[4] + " description:" + args[2]);
                        playallplayer();
                        getLogger().info(name + " dimension:" + dim + " x:" + x + " y:" + y + " z:" + z + " subject:" + args[4] + " description" + args[2] + " global");
                    } else {
                        player.sendMessage(ChatColor.DARK_RED + "that command cannot be executed");
                    }
                    return false;

                }

                return false;
            }
            return false;
        }
        return false;
    }
}
