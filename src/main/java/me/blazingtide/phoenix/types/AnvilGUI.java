package me.blazingtide.phoenix.types;

import me.blazingtide.phoenix.GUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

/**
 * Class made to open anvil gui and get the resultant text
 */
public abstract class AnvilGUI extends GUI {

    private final String defaultText;

    protected AnvilGUI(Player player, String title, String defaultText) {
        super(player, title, 3);
        this.defaultText = defaultText;
    }

    @Override
    public Inventory createInventory() {
        return Bukkit.createInventory(null, InventoryType.ANVIL, ChatColor.translateAlternateColorCodes('&', getTitle()));
    }
}
