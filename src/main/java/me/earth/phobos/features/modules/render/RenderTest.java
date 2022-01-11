package me.earth.phobos.features.modules.render;

import me.earth.phobos.event.events.Render2DEvent;
import me.earth.phobos.features.modules.Module;
import me.earth.phobos.features.modules.client.ClickGui;
import me.earth.phobos.features.setting.Setting;
import me.earth.phobos.util.ColorUtil;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class RenderTest extends Module {
    public static final ResourceLocation mark = new ResourceLocation("textures/nft.png");
    public Setting<Integer> imageX;
    public Setting<Integer> imageY;
    public Setting<Integer> imageWidth;
    public Setting<Integer> imageHeight;
    private int color;

    public RenderTest() {
        super("Nft", "Puts a nft in ur screen", Category.RENDER, false, false, false);
        this.imageX = this.register(new Setting("WatermarkX", 0, 0, 300));
        this.imageY = this.register(new Setting("WatermarkY", 0, 0, 300));
        this.imageWidth = this.register(new Setting("WatermarkWidth", 97, 0, 1000));
        this.imageHeight = this.register(new Setting("WatermarkHeight", 97, 0, 1000));

    }

    public void renderLogo() {
        int width = (Integer) this.imageWidth.getValue();
        int height = (Integer) this.imageHeight.getValue();
        int x = (Integer) this.imageX.getValue();
        int y = (Integer) this.imageY.getValue();
        mc.renderEngine.bindTexture(this.mark);
        GlStateManager.color(255.0F, 255.0F, 255.0F);
        Gui.drawScaledCustomSizeModalRect(x - 2, y - 36, 7.0F, 7.0F, width - 7, height - 7, width, height, (float) width, (float) height);
    }

    public void onRender2D(Render2DEvent event) {
        if (!fullNullCheck()) {
            int width = this.renderer.scaledWidth;
            int height = this.renderer.scaledHeight;
            this.color = ColorUtil.toRGBA((Integer) ClickGui.getInstance().red.getValue(), (Integer) ClickGui.getInstance().green.getValue(), (Integer) ClickGui.getInstance().blue.getValue());
            if ((Boolean) this.enabled.getValue()) {
                this.renderLogo();
            }

        }

    }
}