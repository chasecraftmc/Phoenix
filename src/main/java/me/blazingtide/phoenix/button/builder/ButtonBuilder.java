package me.blazingtide.phoenix.button.builder;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class ButtonBuilder {

    private ItemStack item;
    private Consumer<InventoryClickEvent> clickConsumer;

    public ButtonBuilder withItem(ItemStack item) {
        this.item = item;
        return this;
    }

    public ButtonBuilder onClick(Consumer<InventoryClickEvent> consumer) {
        clickConsumer = consumer;

        return this;
    }

}
