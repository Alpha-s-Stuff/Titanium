/*
 * This file is part of Titanium
 * Copyright (C) 2020, Horizon Studio <contact@hrznstudio.com>.
 *
 * This code is licensed under GNU Lesser General Public License v3.0, the full license text can be found in LICENSE.txt
 */

package com.hrznstudio.titanium._impl.test.tile;

import com.hrznstudio.titanium._impl.test.BlockTest;
import com.hrznstudio.titanium.annotation.Save;
import com.hrznstudio.titanium.api.IFactory;
import com.hrznstudio.titanium.api.IItemStackQuery;
import com.hrznstudio.titanium.api.client.AssetTypes;
import com.hrznstudio.titanium.api.client.IGuiAddon;
import com.hrznstudio.titanium.block.tile.TilePowered;
import com.hrznstudio.titanium.block.tile.button.PosButton;
import com.hrznstudio.titanium.block.tile.fluid.PosFluidTank;
import com.hrznstudio.titanium.block.tile.inventory.SidedInvHandler;
import com.hrznstudio.titanium.block.tile.progress.PosProgressBar;
import com.hrznstudio.titanium.client.gui.addon.EnergyBarGuiAddon;
import com.hrznstudio.titanium.client.gui.addon.StateButtonAddon;
import com.hrznstudio.titanium.client.gui.addon.StateButtonInfo;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;

import java.util.Collections;
import java.util.List;

public class TileTest extends TilePowered {

    @Save
    private PosProgressBar bar;
    @Save
    private SidedInvHandler first;
    @Save
    private SidedInvHandler second;
    @Save
    private PosFluidTank third;

    private PosButton button;
    @Save
    private int state;

    public TileTest() {
        super(BlockTest.TEST);
        this.addInventory(first = (SidedInvHandler) new SidedInvHandler("test", 80, 60, 1, 0).setTile(this).setInputFilter((stack, integer) -> IItemStackQuery.ANYTHING.test(stack)));
        this.addInventory(second = (SidedInvHandler) new SidedInvHandler("test2", 80, 30, 1, 1).setTile(this).setInputFilter((stack, integer) -> IItemStackQuery.ANYTHING.test(stack)));
        this.addGuiAddonFactory(() -> new EnergyBarGuiAddon(4, 10, getEnergyStorage()));
        this.addProgressBar(bar = new PosProgressBar(40, 20, 500).setCanIncrease(tileEntity -> true).setOnFinishWork(() -> System.out.println("WOWOOW")).setBarDirection(PosProgressBar.BarDirection.HORIZONTAL_RIGHT).setColor(DyeColor.LIME));
        this.addTank(third = new PosFluidTank("testTank", 8000, 130, 30));
        this.addButton(button = new PosButton(-13, 1, 14, 14) {
            @Override
            public List<IFactory<? extends IGuiAddon>> getGuiAddons() {
                return Collections.singletonList(() -> new StateButtonAddon(button, new StateButtonInfo(0, AssetTypes.BUTTON_SIDENESS_DISABLED), new StateButtonInfo(1, AssetTypes.BUTTON_SIDENESS_ENABLED), new StateButtonInfo(2, AssetTypes.BUTTON_SIDENESS_PULL), new StateButtonInfo(3, AssetTypes.BUTTON_SIDENESS_PUSH)) {
                    @Override
                    public int getState() {
                        return state;
                    }
                });
            }
        }.setId(0).setPredicate((playerEntity, compoundNBT) -> {
            System.out.println(":pepeD:");
            ++state;
            if (state >= 4) state = 0;
            markForUpdate();
        }));
        first.setColor(DyeColor.LIME);
        second.setColor(DyeColor.CYAN);
    }

    @Override
    public void tick() {
        bar.tickBar();
        if (getWorld().isRaining()) {
            getWorld().getWorldInfo().setRaining(false);
        }
    }

    @Override
    public ActionResultType onActivated(PlayerEntity playerIn, Hand hand, Direction facing, double hitX, double hitY, double hitZ) {
        if (super.onActivated(playerIn, hand, facing, hitX, hitY, hitZ) == ActionResultType.PASS) {
            openGui(playerIn);
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }

}
