package me.blazingtide.phoenix;

import me.blazingtide.phoenix.button.Button;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class GUIListener implements Listener {

    @EventHandler
    public void onOpen(InventoryOpenEvent event) {
        final HumanEntity player = event.getPlayer();

        //We can assume that the player's open GUI is the same GUI that we store in the Map.
        if (GUIHandler.OPEN_GUIS.containsKey(player.getUniqueId())) {
            GUIHandler.OPEN_GUIS.get(player.getUniqueId()).onOpen(event);
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        final HumanEntity player = event.getPlayer();

        //We can assume that the player's open GUI is the same GUI that we store in the Map.
        if (GUIHandler.OPEN_GUIS.containsKey(player.getUniqueId())) {
            GUIHandler.OPEN_GUIS.remove(player.getUniqueId()).onClose(event);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        final HumanEntity player = event.getWhoClicked();

        if (GUIHandler.OPEN_GUIS.containsKey(player.getUniqueId())) {
            final GUI gui = GUIHandler.OPEN_GUIS.get(player.getUniqueId());

            Button button = gui.getButtons()[event.getRawSlot()];

            if (button != null) {
                button.getClickConsumer().accept(event);
            }
        }
    }

}
