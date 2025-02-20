package com.alpsbte.plotsystem.core.menus;

import com.alpsbte.plotsystem.utils.Utils;
import com.alpsbte.plotsystem.utils.io.language.LangPaths;
import com.alpsbte.plotsystem.utils.io.language.LangUtil;
import com.alpsbte.plotsystem.utils.items.MenuItems;
import com.alpsbte.plotsystem.utils.items.builder.ItemBuilder;
import com.alpsbte.plotsystem.utils.items.builder.LoreBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.ipvp.canvas.mask.BinaryMask;
import org.ipvp.canvas.mask.Mask;

import java.util.function.Consumer;

public class SettingsMenu extends AbstractMenu {
    private Consumer<Player> onBack = (player) -> player.performCommand("companion");

    public SettingsMenu(Player player) {
        super(3, LangUtil.get(player, LangPaths.MenuTitle.SETTINGS), player);
    }
    public SettingsMenu(Player player, Consumer<Player> onBack) {
        this(player);
        this.onBack = onBack;
    }

    @Override
    protected void setMenuItemsAsync() {
        // Set language item
        getMenu().getSlot(11).setItem(
                new ItemBuilder(Utils.CustomHead.GLOBE.getAsItemStack())
                        .setName("§6§l" + LangUtil.get(getMenuPlayer(), LangPaths.MenuTitle.SELECT_LANGUAGE))
                        .setLore(new LoreBuilder()
                                .addLine(LangUtil.get(getMenuPlayer(), LangPaths.MenuDescription.SELECT_LANGUAGE))
                                .build())
                        .build());

        // Set Plot type item
        getMenu().getSlot(15).setItem(
                new ItemBuilder(Utils.CustomHead.PLOT_TYPE.getAsItemStack())
                        .setName("§6§l" + LangUtil.get(getMenuPlayer(), LangPaths.MenuTitle.SELECT_PLOT_TYPE))
                        .setLore(new LoreBuilder()
                                .addLine(LangUtil.get(getMenuPlayer(), LangPaths.MenuDescription.SELECT_PLOT_TYPE))
                                .build())
                        .build());

        // Set back item
        getMenu().getSlot(22).setItem(MenuItems.backMenuItem(getMenuPlayer()));
    }

    @Override
    protected void setItemClickEventsAsync() {
        // Set click event for language item
        getMenu().getSlot(11).setClickHandler(((clickPlayer, clickInformation) -> {
            getMenuPlayer().closeInventory();
            new SelectLanguageMenu(clickPlayer);
        }));

        // Set click event for plot type item
        getMenu().getSlot(15).setClickHandler(((clickPlayer, clickInformation) -> {
            getMenuPlayer().closeInventory();
            new SelectPlotTypeMenu(clickPlayer);
        }));

        // Set click event for back item
        getMenu().getSlot(22).setClickHandler((clickPlayer, clickInformation) -> onBack.accept(clickPlayer));
    }

    @Override
    protected Mask getMask() {
        return BinaryMask.builder(getMenu())
                .item(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 7).setName(" ").build())
                .pattern("111111111")
                .pattern("000000000")
                .pattern("111101111")
                .build();
    }
}
