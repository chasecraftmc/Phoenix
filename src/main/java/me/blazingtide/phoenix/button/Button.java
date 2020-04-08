package me.blazingtide.phoenix.button;

import lombok.Getter;
import me.blazingtide.phoenix.GUI;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

@Getter
public class Button<T extends GUI> {

    private final Player player;
    private final T gui;
    private final ItemStack item;
    private final Consumer<InventoryClickEvent> clickConsumer;

    public Button(Player player, T gui, ItemStack item, Consumer<InventoryClickEvent> clickConsumer) {
        this.player = player;
        this.gui = gui;
        this.item = item;
        this.clickConsumer = clickConsumer;
    }

}
