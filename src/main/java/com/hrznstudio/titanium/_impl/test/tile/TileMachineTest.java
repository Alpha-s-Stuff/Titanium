/*
 * This file is part of Titanium
 * Copyright (C) 2020, Horizon Studio <contact@hrznstudio.com>.
 *
 * This code is licensed under GNU Lesser General Public License v3.0, the full license text can be found in LICENSE.txt
 */

package com.hrznstudio.titanium._impl.test.tile;

import com.hrznstudio.titanium._impl.test.BlockMachine;
import com.hrznstudio.titanium.annotation.Save;
import com.hrznstudio.titanium.api.filter.FilterSlot;
import com.hrznstudio.titanium.block.tile.TileMachine;
import com.hrznstudio.titanium.filter.ItemstackFilter;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;

public class TileMachineTest extends TileMachine {

    @Save
    private ItemstackFilter filter;

    public TileMachineTest() {
        super(BlockMachine.TEST);
        addFilter(this.filter = new ItemstackFilter("filter", 12));
        int pos = 0;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 3; x++) {
                FilterSlot slot = new FilterSlot<>(20 + x * 18, 20 + y * 18, pos, ItemStack.EMPTY);
                slot.setColor(DyeColor.CYAN);
                this.filter.setFilter(pos, slot);
                ++pos;
            }
        }
    }
}
