
package com.jfrdev.portalrestrict;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.entity.Player;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.block.Action;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.ChatColor;

/**
 * Handle events for all Player related events
 * @author jfrdev
 */
public class PortalEventListener implements Listener {
    private final PortalRestrict plugin;

    public PortalEventListener(PortalRestrict instance) {
        plugin = instance;
    }

    @EventHandler
    public void onPlayerCreatePortal(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (player.getWorld().getName().equals(plugin.getConfig().getString("world"))) {
            Action action = event.getAction();

            if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
                ItemStack item = event.getItem();

                if (item != null && item.getType().equals(Material.FLINT_AND_STEEL)) {
                    Block block = event.getClickedBlock();

                    if (block != null && block.getType().equals(Material.OBSIDIAN)) {
                        Location location = block.getLocation();
                        int xc = (int)location.getX() - plugin.getConfig().getInt("x");
                        int zc = (int)location.getZ() - plugin.getConfig().getInt("z");
                        int radius = plugin.getConfig().getInt("radius");

                        if (xc*xc+zc*zc >= radius*radius) {
                            player.sendMessage(plugin.getConfig().getString("message"));
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }
}