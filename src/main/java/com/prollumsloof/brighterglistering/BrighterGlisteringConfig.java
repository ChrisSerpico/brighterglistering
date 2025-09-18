package com.prollumsloof.brighterglistering;

import net.neoforged.neoforge.common.ModConfigSpec;

public class BrighterGlisteringConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.IntValue GLISTERING_MELON_CHANCE = BUILDER
            .comment("The chance (1/n) that a Melon Stem will grow a Glistering Melon instead of a standard Melon.\nSet to 0 to disable.")
            .defineInRange("glisteringMelonChance", 64, 0, 1024);

    public static final ModConfigSpec SPEC = BUILDER.build();
}
