package net.zappfire.zappmod.recipe;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.zappfire.zappmod.Zappmod;

public class ModRecipes {
    public static void register() {
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(Zappmod.MOD_ID, AlloySmelterRecipe.Serializer.ID),
                AlloySmelterRecipe.Serializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(Zappmod.MOD_ID, AlloySmelterRecipe.Type.ID),
                AlloySmelterRecipe.Type.INSTANCE);
    }
}
