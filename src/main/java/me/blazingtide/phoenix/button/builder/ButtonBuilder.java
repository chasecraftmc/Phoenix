package me.blazingtide.phoenix.button.builder;

import me.blazingtide.phoenix.GUI;
import me.blazingtide.phoenix.button.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class ButtonBuilder<T extends GUI> {

    private ItemStack item;
    private T gui;
    private Consumer<InventoryClickEvent> clickConsumer;

    public ButtonBuilder<?> withItem(ItemStack item) {
        this.item = item;
        return this;
    }

    public ButtonBuilder<?> withGUI(T gui) {
        this.gui = gui;
        return this;
    }

    public ButtonBuilder<?> onClick(Consumer<InventoryClickEvent> consumer) {
        clickConsumer = consumer;

        return this;
    }

    public Button<T> build(Player player) {
        return new Button<T>(player, gui, item, clickConsumer);
    }

}
