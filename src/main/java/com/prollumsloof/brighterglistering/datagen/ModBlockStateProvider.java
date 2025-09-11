package com.prollumsloof.brighterglistering.datagen;

import com.prollumsloof.brighterglistering.BrighterGlistering;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, BrighterGlistering.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(BrighterGlistering.GLISTERING_MELON.get(), models().cubeColumn(BrighterGlistering.GLISTERING_MELON.getRegisteredName(),
                modLoc("block/glistering_melon_side"), modLoc("block/glistering_melon_top")));
    }
}
