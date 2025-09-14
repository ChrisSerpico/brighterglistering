package com.prollumsloof.brighterglistering;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.AttachedStemBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.neoforged.neoforge.event.level.block.CropGrowEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

import java.util.Objects;
import java.util.Random;

@Mod(BrighterGlistering.MODID)
public class BrighterGlistering {
    public static final String MODID = "brighterglistering";
//    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static final DeferredBlock<Block> GLISTERING_MELON = BLOCKS.registerSimpleBlock("glistering_melon",
            BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK));
    public static final DeferredBlock<AttachedStemBlock> ATTACHED_GLISTERING_MELON_STEM = BLOCKS.register("attached_glistering_melon_stem",
            () -> new AttachedStemBlock(
                    net.minecraft.references.Blocks.MELON_STEM,
                    Objects.requireNonNull(GLISTERING_MELON.getKey()),
                    net.minecraft.references.Items.MELON_SEEDS,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.ATTACHED_MELON_STEM)
            ));
    public static final DeferredItem<BlockItem> GLISTERING_MELON_ITEM = ITEMS.registerSimpleBlockItem("glistering_melon", GLISTERING_MELON);

    private final Random RANDOM = new Random();

    public BrighterGlistering(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);

        modEventBus.addListener(this::modifyDefaultComponents);
        modEventBus.addListener(this::addCreative);
        NeoForge.EVENT_BUS.addListener(this::onCropGrowEvent);
    }

    public void modifyDefaultComponents(ModifyDefaultComponentsEvent event) {
        event.modify(Items.GLISTERING_MELON_SLICE, builder -> builder.set(DataComponents.FOOD,
                new FoodProperties.Builder().nutrition(4).saturationModifier(1.2f).build()));
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.insertAfter(new ItemStack(Blocks.MELON), new ItemStack(GLISTERING_MELON.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    private void onCropGrowEvent(CropGrowEvent.Post event) {
        if (event.getState().getBlock() == Blocks.ATTACHED_MELON_STEM) {
            Direction facing = event.getState().getValue(HorizontalDirectionalBlock.FACING);
            BlockPos melonPos = event.getPos().relative(facing);
            if (event.getLevel().getBlockState(melonPos).getBlock() == Blocks.MELON && RANDOM.nextInt(64) == 0) {
                event.getLevel().setBlock(event.getPos(), ATTACHED_GLISTERING_MELON_STEM.get().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, facing), 3);
                event.getLevel().setBlock(melonPos, GLISTERING_MELON.get().defaultBlockState(), 3);
            }
        }
    }
}
