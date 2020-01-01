/*
 * This file is part of Titanium
 * Copyright (C) 2020, Horizon Studio <contact@hrznstudio.com>.
 *
 * This code is licensed under GNU Lesser General Public License v3.0, the full license text can be found in LICENSE.txt
 */

package com.hrznstudio.titanium.client.gui;

import com.hrznstudio.titanium.api.client.IGuiAddon;

import java.util.Collection;

public interface IGuiAddonConsumer {
    Collection<IGuiAddon> getAddons();
}
