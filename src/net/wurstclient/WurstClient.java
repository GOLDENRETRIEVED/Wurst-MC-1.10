/*
 * Copyright � 2014 - 2016 | Wurst-Imperium | All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package net.wurstclient;

import net.wurstclient.analytics.AnalyticsManager;
import net.wurstclient.chat.ChatManager;
import net.wurstclient.events.EventManager;
import net.wurstclient.features.commands.CmdManager;
import net.wurstclient.features.mods.ModManager;
import net.wurstclient.features.special_features.SpfManager;
import net.wurstclient.files.FileManager;
import net.wurstclient.font.Fonts;
import net.wurstclient.hooks.FrameHook;
import net.wurstclient.navigator.Navigator;
import net.wurstclient.options.FriendsList;
import net.wurstclient.options.KeybindManager;
import net.wurstclient.options.OptionsManager;
import net.wurstclient.update.Updater;

public enum WurstClient
{
	INSTANCE;
	
	public static final String VERSION = "4.7.1";
	public static final String MINECRAFT_VERSION = "1.10";
	
	public AnalyticsManager analytics;
	public ChatManager chat;
	public CmdManager commands;
	public EventManager events;
	public FileManager files;
	public FriendsList friends;
	public ModManager mods;
	public Navigator navigator;
	public KeybindManager keybinds;
	public OptionsManager options;
	public SpfManager special;
	public Updater updater;
	
	private boolean enabled = true;
	
	public void startClient()
	{
		events = new EventManager();
		mods = new ModManager();
		commands = new CmdManager();
		special = new SpfManager();
		files = new FileManager();
		updater = new Updater();
		chat = new ChatManager();
		keybinds = new KeybindManager();
		options = new OptionsManager();
		friends = new FriendsList();
		navigator = new Navigator();
		
		files.init();
		navigator.sortFeatures();
		Fonts.loadFonts();
		updater.checkForUpdate();
		analytics =
			new AnalyticsManager("UA-52838431-5", "client.wurst-client.tk");
		files.saveOptions();
		
		FrameHook.maximize();
	}
	
	public boolean isEnabled()
	{
		return enabled;
	}
	
	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
		if(!enabled)
		{
			mods.panicMod.setEnabled(true);
			mods.panicMod.onUpdate();
		}
	}
}