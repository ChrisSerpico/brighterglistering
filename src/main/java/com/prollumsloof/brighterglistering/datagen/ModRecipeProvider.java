package com.prollumsloof.brighterglistering.datagen;

import com.prollumsloof.brighterglistering.BrighterGlistering;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BrighterGlistering.GLISTERING_MELON)
                .pattern("GGG")
                .pattern("GGG")
                .pattern("GGG")
                .define('G', Items.GLISTERING_MELON_SLICE)
                .unlockedBy("has_glistering_melon_slice", has(Items.GLISTERING_MELON_SLICE))
                .unlockedBy("has_glistering_melon", has(BrighterGlistering.GLISTERING_MELON))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BrighterGlistering.GLISTERING_MELON)
                .pattern("GGG")
                .pattern("GMG")
                .pattern("GGG")
                .define('G', Items.GOLD_INGOT)
                .define('M', Blocks.MELON)
                .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT))
                .unlockedBy("has_melon", has(Blocks.MELON))
                .unlockedBy("has_glistering_melon", has(BrighterGlistering.GLISTERING_MELON))
                .save(recipeOutput, "glistering_melon_from_melon");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.GLISTERING_MELON_SLICE, 9)
                .requires(BrighterGlistering.GLISTERING_MELON)
                .unlockedBy("has_glistering_melon", has(BrighterGlistering.GLISTERING_MELON))
                .unlockedBy("has_glistering_melon_slice", has(Items.GLISTERING_MELON_SLICE))
                .save(recipeOutput, "glistering_melon_slice_from_glistering_melon");
    }
}
