/*
 * The MIT License (MIT)
 *
 *  Copyright © 2021, Alps BTE <bte.atchli@gmail.com>
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package com.alpsbte.plotsystem.utils.items;

import com.alpsbte.plotsystem.utils.Utils;
import com.alpsbte.plotsystem.utils.io.language.LangPaths;
import com.alpsbte.plotsystem.utils.io.language.LangUtil;
import com.alpsbte.plotsystem.utils.items.builder.ItemBuilder;
import com.alpsbte.plotsystem.utils.items.builder.LoreBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MenuItems {

    public static ItemStack closeMenuItem(Player player) {
        return new ItemBuilder(Material.BARRIER)
                .setName("§c§l" + LangUtil.get(player, LangPaths.MenuTitle.CLOSE))
                .build();
    }

    public static ItemStack backMenuItem(Player player) {
        return new ItemBuilder(Utils.getItemHead(Utils.CustomHead.BACK_BUTTON))
                .setName("§6§l" + LangUtil.get(player, LangPaths.MenuTitle.BACK))
                .build();
    }

    public static ItemStack nextPageItem(Player player) {
        return new ItemBuilder(Utils.getItemHead(Utils.CustomHead.NEXT_BUTTON))
                .setName("§6§l" + LangUtil.get(player, LangPaths.MenuTitle.NEXT_PAGE))
                .build();
    }

    public static ItemStack previousPageItem(Player player) {
        return new ItemBuilder(Utils.getItemHead(Utils.CustomHead.PREVIOUS_BUTTON))
                .setName("§6§l" + LangUtil.get(player, LangPaths.MenuTitle.PREVIOUS_PAGE))
                .build();
    }

    public static ItemStack errorItem(Player player) {
        return new ItemBuilder(Material.BARRIER)
                .setName("§c§l" + LangUtil.get(player, LangPaths.MenuTitle.ERROR))
                .setLore(new LoreBuilder()
                    .addLine(LangUtil.get(player, LangPaths.MenuDescription.ERROR)).build())
                .build();
    }

    public static ItemStack loadingItem(Material material, Player player) {
        return new ItemBuilder(material)
                .setName("§6§l" + LangUtil.get(player, LangPaths.MenuTitle.LOADING))
                .build();
    }

    public static ItemStack loadingItem(Material material, byte subId, Player player) {
        return new ItemBuilder(material, 1, subId)
                .setName("§6§l" + LangUtil.get(player, LangPaths.MenuTitle.LOADING))
                .build();
    }

    public static ItemStack filterItem(Player langPlayer) {
        return new ItemBuilder(Material.HOPPER, 1)
                .setName("§6§l" + LangUtil.get(langPlayer, LangPaths.MenuTitle.FILTER_BY_COUNTRY))
                .build();
    }
}
