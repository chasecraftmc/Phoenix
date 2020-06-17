package me.blazingtide.phoenix.pagination;

import lombok.Getter;
import lombok.Setter;
import me.blazingtide.phoenix.GUI;
import me.blazingtide.phoenix.button.Button;
import me.blazingtide.phoenix.button.builder.ButtonBuilder;
import me.blazingtide.phoenix.enums.TickResult;
import me.blazingtide.phoenix.pagination.button.PaginatedButton;
import me.blazingtide.phoenix.pagination.button.PaginationType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
public abstract class PaginatedGUI extends GUI {

    public static ItemStack PAGINATED_GUI_FILLER = new ItemStack(Material.STAINED_GLASS_PANE);

    private int page = 1;

    public PaginatedGUI(Player player, String title) {
        super(player, title, 18);
    }

    public abstract List<Button> getPageButtons();

    @Override
    public Optional<TickResult> onTick() {
        final List<Button> objects = getPageButtons();

        for (int i = 0; i < 9; i++) {
            if (objects.size() <= (i * page)) {
                break;
            }
            buttons[i] = objects.get(i * page);
        }

        for (int i = 9; i < 18; i++) {
            buttons[i] = new ButtonBuilder().withGUI(this).withItem(PAGINATED_GUI_FILLER).build(player);
        }

        buttons[9] = new PaginatedButton(player, this, PaginationType.NEXT_PAGE);
        buttons[17] = new PaginatedButton(player, this, PaginationType.PREVIOUS_PAGE);

        return Optional.empty();
    }
}