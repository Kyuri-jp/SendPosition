package org.plugin.sendposition;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static org.bukkit.Bukkit.broadcastMessage;


public final class SendPosition extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Complete");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("GoodBye!");
    }


    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, Command command, @NotNull String label, String[] args) {

        if (command.getName().equalsIgnoreCase("sendposition")) {

            if (args.length == 1) {
                return Arrays.asList("global", "subject", "description");
            } else if (args.length == 2 && args[0].startsWith("g")) {
                return Arrays.asList("subject", "description");
            } else if (args.length == 3 && args[0].startsWith("s")) {
                return List.of("description");
            } else if (args.length == 3 && args[0].startsWith("d")) {
                return List.of("subject");
            } else if (args.length == 4 && args[0].startsWith("g") && args[1].startsWith("s")) {
                return List.of("description");
            } else if (args.length == 4 && args[0].startsWith("g") && args[1].startsWith("d")) {
                return List.of("subject");
            } else {
                return null;
            }
        } else {
            return null;
        }
    }


    static Sound sound = Sound.BLOCK_GLASS_BREAK;
    static float vol = 5.0f;
    static float pit = 1.0f;

    //playsound
    public static void playallplayer() {

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player.getLocation(), sound, vol, pit);

        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
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
            double x = Math.floor(loc.getX() * 100) / 100;
            double y = Math.floor(loc.getY() * 100) / 100;
            double z = Math.floor(loc.getZ() * 100) / 100;
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
                    player.sendMessage(base + " description:" + args[1]);
                    player.playSound(player.getLocation(), sound, vol, pit);
                    getLogger().info(name + " dimension:" + dim + " x:" + x + " y:" + y + " z:" + z + " description:" + args[1]);
                } else if (args.length == 4 && args[2].equalsIgnoreCase("subject")) {
                    player.sendMessage(base + " subject:" + args[3] + " description:" + args[1]);
                    player.playSound(player.getLocation(), sound, vol, pit);
                    getLogger().info(name + " dimension:" + dim + " x:" + x + " y:" + y + " z:" + z + " subject:" + args[3] + " description" + args[1]);
                }
                if (args.length == 1 && args[0].equalsIgnoreCase("global")) {
                    broadcastMessage(base + ChatColor.RESET);
                    player.playSound(player.getLocation(), sound, vol, pit);
                    getLogger().info(name + " is at " + "dimension:" + dim + " x:" + x + " y:" + y + " z:" + z + " global");
                } else {
                    if (args[0].startsWith("g") && args.length == 3 && args[1].equalsIgnoreCase("subject")) {
                        broadcastMessage(base + " subject:" + args[2]);
                        playallplayer();
                        getLogger().info(name + " is at " + "dimension:" + dim + " x:" + x + " y:" + y + " z:" + z + " subject:" + args[2] + " global");
                    } else if (args[0].startsWith("g") && args.length == 5 && args[3].equalsIgnoreCase("description")) {
                        broadcastMessage(base + " subject:" + args[2] + " description:" + args[4]);
                        playallplayer();
                        getLogger().info(name + "dimension:" + dim + " x:" + x + " y:" + y + " z:" + z + " subject:" + args[2] + " description:" + args[4] + " global");
                    } else if (args[0].startsWith("g") && args.length == 3 && args[1].equalsIgnoreCase("description")) {
                        broadcastMessage(base + " description:" + args[2]);
                        playallplayer();
                        getLogger().info(name + " dimension:" + dim + " x:" + x + " y:" + y + " z:" + z + " description:" + args[2] + " global");
                    } else if (args[0].startsWith("g") && args.length == 5 && args[3].equalsIgnoreCase("subject")) {
                        broadcastMessage(base + " subject:" + args[4] + " description:" + args[2]);
                        playallplayer();
                        getLogger().info(name + " dimension:" + dim + " x:" + x + " y:" + y + " z:" + z + " subject:" + args[4] + " description" + args[2] + " global");
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
