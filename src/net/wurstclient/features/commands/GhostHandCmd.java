/*
 * Copyright � 2014 - 2016 | Wurst-Imperium | All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package net.wurstclient.features.commands;

import net.minecraft.block.Block;
import net.wurstclient.utils.ChatUtils;
import net.wurstclient.utils.MiscUtils;

@Cmd.Info(description = "Changes the settings of GhostHand or toggles it.",
	name = "ghosthand",
	syntax = {"id <block_id>", "name <block_name>"},
	help = "Commands/ghosthand")
public class GhostHandCmd extends Cmd
{
	@Override
	public void execute(String[] args) throws Error
	{
		if(args.length == 0)
		{
			wurst.mods.ghostHandMod.toggle();
			ChatUtils.message("GhostHand turned "
				+ (wurst.mods.ghostHandMod.isEnabled() ? "on" : "off") + ".");
		}else if(args.length == 2)
		{
			if(args[0].equalsIgnoreCase("id") && MiscUtils.isInteger(args[1]))
			{
				wurst.options.ghostHandID = Integer.valueOf(args[1]);
				wurst.files.saveOptions();
				ChatUtils.message("GhostHand ID set to " + args[1] + ".");
			}else if(args[0].equalsIgnoreCase("name"))
			{
				int newID =
					Block.getIdFromBlock(Block.getBlockFromName(args[1]));
				if(newID == -1)
				{
					ChatUtils.message(
						"The block \"" + args[1] + "\" could not be found.");
					return;
				}
				wurst.options.ghostHandID = newID;
				wurst.files.saveOptions();
				ChatUtils.message(
					"GhostHand ID set to " + newID + " (" + args[1] + ").");
			}else
				syntaxError();
		}else
			syntaxError();
	}
}
