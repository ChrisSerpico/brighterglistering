package com.prollumsloof.brighterglistering.datagen;

import com.prollumsloof.brighterglistering.BrighterGlistering;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, BrighterGlistering.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(BrighterGlistering.GLISTERING_MELON.get());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(BrighterGlistering.GLISTERING_MELON.get());
        tag(BlockTags.GUARDED_BY_PIGLINS)
                .add(BrighterGlistering.GLISTERING_MELON.get());
        tag(BlockTags.MAINTAINS_FARMLAND)
                .add(BrighterGlistering.ATTACHED_GLISTERING_MELON_STEM.get());
    }
}
