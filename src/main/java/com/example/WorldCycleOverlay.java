package com.example;

import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.overlay.components.ComponentOrientation;
import net.runelite.client.ui.overlay.components.TitleComponent;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;

import javax.inject.Inject;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Color;

public class WorldCycleOverlay extends OverlayPanel {
    private final WorldCyclePlugin plugin;
    private Graphics2D lastGraphics2D;
    @Inject
    private WorldCycleOverlay(WorldCyclePlugin plugin) {
        this.plugin = plugin;
        setPriority(OverlayPriority.HIGH);
        setPosition(OverlayPosition.TOP_RIGHT);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        BuildComponent(plugin.configDisplayPreviousWorld && plugin.previousWorldId != -1,
                plugin.previousWorldId,
                plugin.configPreviousWorldColor);

        BuildComponent(plugin.configDisplayCurrentWorld,
                plugin.currentWorldId,
                plugin.configCurrentWorldColor);

        BuildComponent(plugin.configDisplayNextWorld && plugin.nextWorldId != -1,
                plugin.nextWorldId,
                plugin.configNextWorldColor);

        panelComponent.setBackgroundColor(plugin.configWorldPanelColor);
        panelComponent.setOrientation(ComponentOrientation.VERTICAL);
        panelComponent.setBorder(new Rectangle(0,0,0,9));

        graphics.setFont(new Font(FontManager.getRunescapeFont().toString(), plugin.configBoldFont ? Font.BOLD : Font.PLAIN, plugin.configFontSize));

        lastGraphics2D = graphics;
        return super.render(graphics);
    }

    /**
     * Draws singular dimmer frame over the client to indicate a hop is in progress
     */
    public void DrawDimmer(Dimension size){
        if(lastGraphics2D == null)
            return;
        lastGraphics2D.setColor(plugin.configDimmerColor);
        lastGraphics2D.fillRect(0, 0, size.width, size.height);
        lastGraphics2D = null;
    }

    public void BuildComponent(boolean qualifier, int worldId, Color color){
        if(!qualifier)
            return;
        panelComponent.getChildren().add(TitleComponent.builder()
                .text(String.valueOf(worldId))
                .color(color)
                .build());
    }

}