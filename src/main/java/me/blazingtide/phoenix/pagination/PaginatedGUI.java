package me.blazingtide.phoenix.pagination;

import com.google.common.collect.Lists;
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

    protected int maxPage;
    protected int maxElements;

    private List<Button> elements;

    protected int page = 1;

    public PaginatedGUI(Player player, String title) {
        this(player, title, 18);
    }

    public PaginatedGUI(Player player, String title, int size) {
        super(player, title, size);

        elements = getPageButtons();
        maxElements = size - 9;

        maxPage = elements.size() / maxElements + 1;
    }

    public abstract List<Button> getPageButtons();

    @Override
    public Optional<TickResult> onTick() {
        int start = (page - 1) * maxElements;
        int end = start + maxElements;

        int index = 0;
        for (int i = start; i < end; i++) {
            if (elements.size() <= i) {
                continue;
            }
            buttons[index] = elements.get(i);
            index++;
        }

        for (int i = size - 9; i < size; i++) {
            buttons[i] = new ButtonBuilder().withGUI(this).withItem(PAGINATED_GUI_FILLER).build(player);
        }

        buttons[size - 9] = new PaginatedButton(player, this, PaginationType.PREVIOUS_PAGE);
        buttons[size - 1] = new PaginatedButton(player, this, PaginationType.NEXT_PAGE);

        return Optional.empty();
    }
}