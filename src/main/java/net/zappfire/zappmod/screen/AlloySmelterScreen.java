package net.zappfire.zappmod.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.zappfire.zappmod.Zappmod;

public class AlloySmelterScreen extends HandledScreen<AlloySmelterScreenHandler> {
    private static final Identifier TEXTURE =
            new Identifier(Zappmod.MOD_ID, "textures/gui/alloy_smelter.png");

    public AlloySmelterScreen(AlloySmelterScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        // Center the title
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
        int progress = handler.getScaledProgress();
        int a = (progress / 8);


        if(handler.isCrafting()) {
            this.drawTexture(matrices, x + 27, y + 36, 0, 184, progress, 16);
            this.drawTexture(matrices, x + 64, y + 54 + a, 0, 166 + a, 24,18 - a);
            this.drawTexture(matrices, x + 107, y + 54 + a, 43, 166 + a, 19,18 - a);
        }

    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
}
