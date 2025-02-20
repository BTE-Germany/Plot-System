/*
 * The MIT License (MIT)
 *
 *  Copyright © 2021-2022, Alps BTE <bte.atchli@gmail.com>
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

package com.alpsbte.plotsystem.commands.review;

import com.alpsbte.plotsystem.commands.BaseCommand;
import com.alpsbte.plotsystem.core.menus.ReviewMenu;
import com.alpsbte.plotsystem.core.menus.ReviewPlotMenu;
import com.alpsbte.plotsystem.core.system.Builder;
import com.alpsbte.plotsystem.core.system.plot.Plot;
import com.alpsbte.plotsystem.core.system.plot.PlotManager;
import com.alpsbte.plotsystem.utils.Utils;
import com.alpsbte.plotsystem.utils.enums.Status;
import com.alpsbte.plotsystem.utils.io.language.LangPaths;
import com.alpsbte.plotsystem.utils.io.language.LangUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.logging.Level;

public class CMD_Review extends BaseCommand {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (getPlayer(sender) != null) {
            try {
                if (sender.hasPermission(getPermission())) {
                    Builder.Reviewer builder = Builder.byUUID(getPlayer(sender).getUniqueId()).getAsReviewer();
                    Player player = (Player) sender;
                    Plot plot = PlotManager.getCurrentPlot(Builder.byUUID(player.getUniqueId()), Status.unreviewed);
                    if (plot != null) {
                        int countryID = plot.getCity().getCountry().getID();
                        if (plot.getStatus() == Status.unreviewed && builder.getCountries().stream().anyMatch(c -> c.getID() == countryID)) {
                            new ReviewPlotMenu(player, plot);
                            return true;
                        }
                    }
                    new ReviewMenu(player);
                } else {
                    sender.sendMessage(Utils.getErrorMessageFormat(LangUtil.get(sender, LangPaths.Message.Error.PLAYER_HAS_NO_PERMISSIONS)));
                }
            } catch (SQLException ex) {
                sender.sendMessage(Utils.getErrorMessageFormat(LangUtil.get(sender, LangPaths.Message.Error.ERROR_OCCURRED)));
                Bukkit.getLogger().log(Level.SEVERE, "A SQL error occurred!", ex);
            }
        } else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "This command can only be used as a player!");
        }
        return true;
    }

    @Override
    public String[] getNames() {
        return new String[] { "review" };
    }

    @Override
    public String getDescription() {
        return "Opens the review menu or review plot menu.";
    }

    @Override
    public String[] getParameter() {
        return new String[0];
    }

    @Override
    public String getPermission() {
        return "plotsystem.review";
    }
}
