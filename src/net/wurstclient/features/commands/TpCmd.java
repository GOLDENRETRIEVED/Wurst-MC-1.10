/*
 * Copyright � 2014 - 2016 | Wurst-Imperium | All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package net.wurstclient.features.commands;

import net.wurstclient.features.commands.Cmd.Info;
import net.wurstclient.utils.EntityUtils.TargetSettings;

@Info(
	description = "Teleports you up to 100 blocks away.\nOnly works on vanilla servers!",
	name = "tp",
	syntax = {"<x> <y> <z>", "<entity>"},
	help = "Commands/tp")
public class TpCmd extends Cmd
{
	private TargetSettings targetSettings = new TargetSettings()
	{
		@Override
		public boolean targetFriends()
		{
			return true;
		}
		
		@Override
		public boolean targetBehindWalls()
		{
			return true;
		};
	};
	
	@Override
	public void execute(String[] args) throws Error
	{
		int[] pos = argsToPos(targetSettings, args);
		mc.thePlayer.setPosition(pos[0], pos[1], pos[2]);
	}
}
