/*
 * Copyright � 2014 - 2016 | Wurst-Imperium | All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package net.wurstclient.features.mods;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.wurstclient.events.listeners.RenderListener;
import net.wurstclient.features.Feature;
import net.wurstclient.features.mods.Mod.Bypasses;
import net.wurstclient.features.mods.Mod.Info;
import net.wurstclient.utils.RenderUtils;

@Info(description = "Allows you to see mobs through walls.",
	name = "MobESP",
	tags = "mob esp",
	help = "Mods/MobESP")
@Bypasses
public class MobEspMod extends Mod implements RenderListener
{
	@Override
	public Feature[] getSeeAlso()
	{
		return new Feature[]{wurst.mods.playerEspMod,
			wurst.mods.prophuntEspMod};
	}
	
	@Override
	public void onEnable()
	{
		wurst.events.add(RenderListener.class, this);
	}
	
	@Override
	public void onRender()
	{
		for(Object entity : mc.theWorld.loadedEntityList)
			if(entity instanceof EntityLiving)
				RenderUtils.entityESPBox((Entity)entity, 0);
	}
	
	@Override
	public void onDisable()
	{
		wurst.events.remove(RenderListener.class, this);
	}
}
