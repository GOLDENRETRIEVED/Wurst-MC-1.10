/*
 * Copyright � 2014 - 2016 | Wurst-Imperium | All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package net.wurstclient.features.mods;

import net.wurstclient.features.mods.Mod.Bypasses;
import net.wurstclient.features.mods.Mod.Info;

@Info(
	description = "Hides all player names.\n"
		+ "Some YouTubers like to censor out all names in their\n" + "videos.",
	name = "NameProtect",
	tags = "name protect",
	help = "Mods/NameProtect")
@Bypasses
public class NameProtectMod extends Mod
{
	
}
