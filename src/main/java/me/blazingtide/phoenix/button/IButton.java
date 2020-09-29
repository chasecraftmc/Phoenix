package me.blazingtide.phoenix.button;

import me.blazingtide.phoenix.GUI;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public interface IButton {

    Player getPlayer();

    GUI getGui();

    ItemStack getItem();

    void onClick(InventoryClickEvent event);

    boolean isAutoCancelEvent();

}
