/*
 * Copyright � 2014 - 2016 | Wurst-Imperium | All rights reserved.
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package net.wurstclient.features.commands;

import java.util.Iterator;

import net.wurstclient.features.Feature;
import net.wurstclient.settings.CheckboxSetting;
import net.wurstclient.settings.Setting;

@Cmd.Info(
	description = "Changes a checkbox setting of a feature. Allows you\n"
		+ "to toggle checkboxes through keybinds.",
	name = "setcheckbox",
	syntax = {"<feature> <checkbox_setting> (on|off|toggle)"},
	help = "Commands/setcheckbox")
public class SetCheckboxCmd extends Cmd
{
	@Override
	public void execute(String[] args) throws Error
	{
		if(args.length != 3)
			syntaxError();
		
		// find feature
		Feature feature = null;
		String featureName = args[0];
		for(Iterator itr = wurst.navigator.iterator(); itr.hasNext();)
		{
			Feature item = (Feature)itr.next();
			if(featureName.equalsIgnoreCase(item.getName()))
			{
				feature = item;
				break;
			}
		}
		if(feature == null)
			error(
				"A feature named \"" + featureName + "\" could not be found.");
		
		// find setting
		Setting setting = null;
		String settingName = args[1].replace("_", " ");
		for(Setting featureSetting : feature.getSettings())
		{
			if(featureSetting.getName().equalsIgnoreCase(settingName))
			{
				setting = featureSetting;
				break;
			}
		}
		if(setting == null)
			error("A setting named \"" + settingName
				+ "\" could not be found in " + feature.getName() + ".");
		
		// check that setting is checkbox setting
		if(!(setting instanceof CheckboxSetting))
			error(feature.getName() + " " + setting.getName()
				+ " is not a checkbox setting.");
		CheckboxSetting checkboxSetting = (CheckboxSetting)setting;
		
		// set check
		String valueName = args[2];
		if(valueName.equalsIgnoreCase("on"))
			checkboxSetting.setChecked(true);
		else if(valueName.equalsIgnoreCase("off"))
			checkboxSetting.setChecked(false);
		else if(valueName.equalsIgnoreCase("toggle"))
			checkboxSetting.toggle();
		else
			syntaxError();
	}
}
