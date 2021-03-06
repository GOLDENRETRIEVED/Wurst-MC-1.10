/*
 * Copyright � 2014 - 2016 | Wurst-Imperium | All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package net.wurstclient.features.mods;

import net.minecraft.client.Minecraft;
import net.wurstclient.events.listeners.UpdateListener;
import net.wurstclient.features.mods.Mod.Bypasses;
import net.wurstclient.features.mods.Mod.Info;

@Info(
	description = "Automatically jumps whenever you walk.\n"
		+ "Tip: Jumping while sprinting is a faster way to move.",
	name = "BunnyHop",
	tags = "AutoJump, BHop, bunny hop, auto jump",
	help = "Mods/BunnyHop")
@Bypasses
public class BunnyHopMod extends Mod implements UpdateListener
{
	@Override
	public void onEnable()
	{
		wurst.events.add(UpdateListener.class, this);
	}
	
	@Override
	public void onUpdate()
	{
		if((mc.thePlayer.moveForward != 0
			|| Minecraft.getMinecraft().thePlayer.moveStrafing != 0)
			&& !mc.thePlayer.isSneaking() && mc.thePlayer.onGround)
			mc.thePlayer.jump();
	}
	
	@Override
	public void onDisable()
	{
		wurst.events.remove(UpdateListener.class, this);
	}
}
