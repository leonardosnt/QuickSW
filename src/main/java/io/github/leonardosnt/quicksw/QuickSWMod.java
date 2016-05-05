/*
 * Copyright (C) 2016 Leonardosnt.
 * QuickSW is licensed under CC BY-NC-SA.
 * Read LICENSE.txt for more info.
 */
 
package io.github.leonardosnt.quicksw;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

@Mod(modid = "QuickSW", version = "1.0", acceptedMinecraftVersions = "[1.8,1.8.9]")
public class QuickSWMod {

  private static final Minecraft MINECRAFT = Minecraft.getMinecraft();
  private boolean isOnHypixel;

  @EventHandler
  public void init(FMLInitializationEvent event) {
    MinecraftForge.EVENT_BUS.register(this);
    FMLCommonHandler.instance().bus().register(this);
  }

  @SubscribeEvent
  public void onActionChatGui(GuiScreenEvent.ActionPerformedEvent.Post e) {
    if (!isOnHypixel || !(e.gui instanceof GuiChat)){
      return;
    }
    switch (e.button.id) {
      case 0:
        MINECRAFT.thePlayer.sendChatMessage("/play ranked_normal");
        break;
  
      case 1:
        MINECRAFT.thePlayer.sendChatMessage("/play solo_insane");
        break;
  
      case 2: 
        MINECRAFT.thePlayer.sendChatMessage("/play solo_normal");
        break;
  
      case 3: 
        MINECRAFT.thePlayer.sendChatMessage("/play teams_insane");
        break;
  
      case 4: 
        MINECRAFT.thePlayer.sendChatMessage("/play teams_normal");
        break;
  
      case 5: 
        MINECRAFT.thePlayer.sendChatMessage("/play mega_normal");
        break;
  
      default:
        break;
    }
  }

  @SubscribeEvent
  public void onRenderChatGui(GuiScreenEvent.DrawScreenEvent.Post e) {
    if (!isOnHypixel || !(e.gui instanceof GuiChat)){
      return;
    }
    e.gui.drawCenteredString(MINECRAFT.fontRendererObj, EnumChatFormatting.YELLOW 
        + "QuickSW by Leonardosnt.", e.gui.width - 65, e.gui.height - 25, 0xFF);
    e.gui.drawCenteredString(MINECRAFT.fontRendererObj, EnumChatFormatting.WHITE + 
        "Modes", e.gui.width - 40, 7, 0xFF);
  }

  @SubscribeEvent
  public void onInitChatGui(GuiScreenEvent.InitGuiEvent.Post e) {
    if (!isOnHypixel || !(e.gui instanceof GuiChat)){
      return;
    }
    int y = 20;
    e.buttonList.add(new GuiButton(0, e.gui.width - 75, y, 70, 20, "Ranked"));
    y += 22;
    e.buttonList.add(new GuiButton(1, e.gui.width - 75, y, 70, 20, "Solo Insane"));
    y += 22;
    e.buttonList.add(new GuiButton(2, e.gui.width - 75, y, 70, 20, "Solo Normal"));
    y += 22;
    e.buttonList.add(new GuiButton(3, e.gui.width - 75, y, 70, 20, "Team Insane"));
    y += 22;
    e.buttonList.add(new GuiButton(4, e.gui.width - 75, y, 70, 20, "Team Normal"));
    y += 22;
    e.buttonList.add(new GuiButton(5, e.gui.width - 75, y, 70, 20, "Mega"));
  }

  @SubscribeEvent
  public void onConnect(FMLNetworkEvent.ClientConnectedToServerEvent e) {
    final ServerData data = MINECRAFT.getCurrentServerData();
    if (data != null && data.serverIP.contains("hypixel.net")) {
      isOnHypixel = true;
    }
  }

  @SubscribeEvent
  public void onDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent e) {
    isOnHypixel = false;
  }
}
