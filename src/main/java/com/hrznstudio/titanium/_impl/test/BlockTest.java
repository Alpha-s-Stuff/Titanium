/*
 * This file is part of Titanium
 * Copyright (C) 2020, Horizon Studio <contact@hrznstudio.com>.
 *
 * This code is licensed under GNU Lesser General Public License v3.0, the full license text can be found in LICENSE.txt
 */

package com.hrznstudio.titanium._impl.test;

import com.hrznstudio.titanium._impl.test.tile.TileTest;
import com.hrznstudio.titanium.annotation.config.ConfigFile;
import com.hrznstudio.titanium.annotation.config.ConfigVal;
import com.hrznstudio.titanium.api.IFactory;
import com.hrznstudio.titanium.block.BlockRotation;
import com.hrznstudio.titanium.recipe.generator.TitaniumLootTableProvider;
import net.minecraft.block.material.Material;

import javax.annotation.Nonnull;

@ConfigFile.Child(TitaniumConfig.class)
public class BlockTest extends BlockRotation<TileTest> {

    @ConfigVal
    public static int DUMB_VALUE = 135;

    public static BlockTest TEST;

    public BlockTest() {
        super("block_test", Properties.create(Material.ROCK), TileTest.class);
    }

    @Override
    public IFactory<TileTest> getTileEntityFactory() {
        return TileTest::new;
    }

    @Nonnull
    @Override
    public RotationType getRotationType() {
        return RotationType.FOUR_WAY;
    }

    @Override
    public void createLootTable(TitaniumLootTableProvider provider) {
        provider.createEmpty(this);
    }
}
