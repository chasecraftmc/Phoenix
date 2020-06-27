package me.blazingtide.phoenix;

import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;

/**
 * I made this class static since I only want 1 instance of the GUI handler at all times.
 * So, each plugin should not be creating their own instances which will prevent overlaping of
 * GUIS.
 */
public class Phoenix {

    public static final String PREFIX = "[Phoenix GUI library] ";

    /**
     * Since we're going to be using multiple threads at the same time
     * when attempting to update GUIS for players, Concurrent HashMap will ensure that
     * there are 0 complications.
     */
    static final Map<UUID, GUI> OPEN_GUIS = Maps.newConcurrentMap();

    private static final GUIUpdater updater = new GUIUpdater();

    private static boolean setup = false;

    public static void init(JavaPlugin plugin) {
        if (setup) {
            return;
        }

        Bukkit.getLogger().info(PREFIX + "Attempting to register the library.");
        Bukkit.getPluginManager().registerEvents(new GUIListener(), plugin);

        updater.start();
        setup = true;
    }

}
