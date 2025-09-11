package com.prollumsloof.brighterglistering;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(BrighterGlistering.MODID)
public class BrighterGlistering {
    public static final String MODID = "brighterglistering";

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static final DeferredBlock<Block> GLISTERING_MELON = BLOCKS.registerSimpleBlock("glistering_melon",
            BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK));
    public static final DeferredItem<BlockItem> GLISTERING_MELON_ITEM = ITEMS.registerSimpleBlockItem("glistering_melon", GLISTERING_MELON);

    public BrighterGlistering(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);

        modEventBus.addListener(this::modifyDefaultComponents);
        modEventBus.addListener(this::addCreative);
    }

    public void modifyDefaultComponents(ModifyDefaultComponentsEvent event) {
        event.modify(Items.GLISTERING_MELON_SLICE, builder -> builder.set(DataComponents.FOOD,
                new FoodProperties.Builder().nutrition(4).saturationModifier(1.2f).build()));
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(GLISTERING_MELON_ITEM);
        }
    }
}
