package net.zappfire.zappmod.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.zappfire.zappmod.Zappmod;

public class ModScreenHandlers {
    public static ScreenHandlerType<AlloySmelterScreenHandler> ALLOY_SMELTER_SCREEN_HANDLER =
            ScreenHandlerRegistry.registerSimple(new Identifier(Zappmod.MOD_ID, "alloy_smelter"),
                    AlloySmelterScreenHandler::new);
}

