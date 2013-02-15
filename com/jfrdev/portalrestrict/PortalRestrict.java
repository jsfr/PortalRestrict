
package com.jfrdev.portalrestrict;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;

import com.jfrdev.portalrestrict.PortalEventListener;

/**
 * Sample plugin for Bukkit
 *
 * @author jfrdev
 */
public class PortalRestrict extends JavaPlugin {
    private final PortalEventListener portalListener = new PortalEventListener(this);

    @Override
    public void onDisable() {
        getLogger().info("Goodbye world!");
    }

    @Override
    public void onEnable() {

        // Register our events
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(portalListener, this);
    }
}
