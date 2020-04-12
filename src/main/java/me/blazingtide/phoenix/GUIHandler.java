package me.blazingtide.phoenix;

import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;

import java.util.Map;
import java.util.UUID;

/**
 * I made this class static since I only want 1 instance of the GUI handler at all times.
 * So, each plugin should not be creating their own instances which will prevent overlaping of
 * GUIS.
 */
public class GUIHandler {

    public static final String PREFIX = "[Phoenix GUI library] ";

    /**
     * Since we're going to be using multiple threads at the same time
     * when attempting to update GUIS for players, Concurrent HashMap will ensure that
     * there are 0 complications.
     */
    static final Map<UUID, GUI> OPEN_GUIS = Maps.newConcurrentMap();

    private static final GUIUpdater updater = new GUIUpdater();

    static {
        Bukkit.getLogger().info(PREFIX + "Attempting to register the library.");
        //Just register it with the first plugin that is on Spigot (Sort of a bad way to do it but it is what it is LOL)
        Bukkit.getPluginManager().registerEvents(new GUIListener(), Bukkit.getPluginManager().getPlugins()[0]);

        updater.start();
    }

}
