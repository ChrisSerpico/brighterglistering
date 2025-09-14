package com.prollumsloof.brighterglistering.datagen;

import com.prollumsloof.brighterglistering.BrighterGlistering;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class ModBlockLootSubProvider extends BlockLootSubProvider {
    protected ModBlockLootSubProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(BrighterGlistering.GLISTERING_MELON.get());
        add(BrighterGlistering.ATTACHED_GLISTERING_MELON_STEM.get(), createAttachedStemDrops(BrighterGlistering.ATTACHED_GLISTERING_MELON_STEM.get(), Items.MELON_SEEDS));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BrighterGlistering.BLOCKS.getEntries().stream().map(b -> (Block) b.get()).toList();
    }
}
