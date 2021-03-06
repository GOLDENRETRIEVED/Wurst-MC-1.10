/*
 * Copyright � 2014 - 2016 | Wurst-Imperium | All rights reserved.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package net.wurstclient.features.mods;

import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.wurstclient.events.PacketOutputEvent;
import net.wurstclient.events.listeners.PacketOutputListener;
import net.wurstclient.features.mods.Mod.Bypasses;
import net.wurstclient.features.mods.Mod.Info;

@Info(
	description = "Allows you walk through walls.\n"
		+ "Not all servers support this.",
	name = "NoWalls",
	help = "Mods/NoWalls")
@Bypasses(ghostMode = false, latestNCP = false, olderNCP = false)
public class NoWallsMod extends Mod implements PacketOutputListener
{
	
	@Override
	public void onEnable()
	{
		wurst.events.add(PacketOutputListener.class, this);
		
		if(!wurst.mods.noClipMod.isEnabled())
			wurst.mods.noClipMod.setEnabled(true);
	}
	
	@Override
	public void onSentPacket(PacketOutputEvent event)
	{
		Packet packet = event.getPacket();
		if(packet instanceof CPacketPlayer)
			event.cancel();
	}
	
	@Override
	public void onDisable()
	{
		wurst.events.remove(PacketOutputListener.class, this);
		
		mc.thePlayer.connection.sendPacket(new CPacketPlayer.PositionRotation(
			mc.thePlayer.posX, mc.thePlayer.getEntityBoundingBox().minY,
			mc.thePlayer.posZ, mc.thePlayer.cameraYaw, mc.thePlayer.cameraPitch,
			mc.thePlayer.onGround));
		
		wurst.mods.noClipMod.setEnabled(false);
	}
}
