/*
 * Copyright � 2014 - 2016 | Wurst-Imperium | All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package net.wurstclient.features.commands;

import net.wurstclient.features.commands.Cmd.Info;

@Info(description = "Toggles RemoteView or makes it target a specific entity.",
	name = "rv",
	syntax = {"[<player>]"},
	help = "Commands/rv")
public class RvCmd extends Cmd
{
	@Override
	public void execute(String[] args) throws Error
	{
		if(args.length == 0)
		{
			wurst.mods.remoteViewMod.onToggledByCommand(null);
			return;
		}else if(args.length == 1)
			wurst.mods.remoteViewMod.onToggledByCommand(args[0]);
		else
			syntaxError("Too many arguments.");
	}
}
