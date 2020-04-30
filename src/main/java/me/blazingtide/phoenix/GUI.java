package me.blazingtide.phoenix;

import lombok.Getter;
import me.blazingtide.phoenix.button.Button;
import me.blazingtide.phoenix.enums.TickResult;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Getter
public abstract class GUI {

    /**
     * Basically how long until the GUI autoupdates for the player.
     * <p>
     * Default will be 1 minute.
     */
    private static final long REGULAR_UPDATE_TICK = TimeUnit.MILLISECONDS.toSeconds(1);

    protected final Player player;
    protected final String title;
    protected final int size;

    protected final Button[] buttons;

    protected long lastTick;

    protected Inventory inventory;

    protected GUI(Player player, String title, int size) {
        this.player = player;
        this.title = ChatColor.translateAlternateColorCodes('&', title);
        this.size = size;

        buttons = new Button[size];
    }

    public String getID() {
        return this.getClass().getSimpleName();
    }

    /**
     * Called whenever the GUI is ready to update.
     * The correct usage would be to return a Optional of ERROR on error
     * and to set the buttons to update the GUI
     * <p>
     * Usage:
     * <ul>
     *     <li>buttons[index] = new Button(player, this, itemStack, consumer)</li>
     * </ul>
     *
     * @return optional of weather the tick event failed just in case some error happened, we can prevent any other inventories from being affected
     */
    public abstract Optional<TickResult> onTick();

    /**
     * Called whenever this GUI is opened.
     *
     * @param event inventory open event
     */
    public void onOpen(InventoryOpenEvent event) {

    }

    /**
     * Called whenever this GUI is closed.
     *
     * @param event inventory close event
     */
    public void onClose(InventoryCloseEvent event) {

    }

    /**
     * Updates the inventory for the player.
     * It's final since we don't want anyone that's using the library
     * to accidentally screw up the update sequence and slow down the entire
     * library.
     *
     * <strong>REMINDER: This method is VERY async</strong>
     */
    public final void update() {
        Optional<TickResult> result;
        try {
            result = onTick();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        if (result.isPresent() && result.get() != TickResult.SUCCESS) {
            return;
        }

        final List<Button> array = Arrays.asList(this.buttons);

        //We want to use a parallel stream because we want it to update as fast as possible without any latency
        array.parallelStream().filter(Objects::nonNull).forEachOrdered(button -> inventory.setItem(array.indexOf(button), button.getItem()));
    }

    public long getUpdateTick() {
        return REGULAR_UPDATE_TICK;
    }

    /**
     * Opens the inventory for the player
     */
    public final void open() {
        if (inventory != null) {
            Bukkit.getLogger().severe(GUIHandler.PREFIX + " Tried to open gui twice. (" + getID() + " for player " + player.getName() + ")");
        }

        inventory = createInventory();
        GUIHandler.OPEN_GUIS.put(player.getUniqueId(), this);

        player.openInventory(inventory);
    }

    /**
     * Creates a inventory which we can use for the player
     * This method is not final since we want to be able to support
     * multiple inventory types.
     *
     * @return created inventory
     */
    public Inventory createInventory() {
        return Bukkit.createInventory(null, size, ChatColor.translateAlternateColorCodes('&', title));
    }

    /**
     * Determines weather a GUI should update automatically depending on the
     * update speed.
     *
     * @return weather it should update automatically or not.
     */
    public boolean isAutoUpdating() {
        return false;
    }

}
