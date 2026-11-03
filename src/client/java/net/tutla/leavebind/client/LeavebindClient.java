package net.tutla.leavebind.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class LeavebindClient implements ClientModInitializer {
    public static KeyMapping leaveKey;

    @Override
    public void onInitializeClient() {
        leaveKey = KeyMappingHelper.registerKeyMapping(
                new KeyMapping(
                        "key.leavebind.leaveKey",
                        InputConstants.Type.KEYBOARD,
                        InputConstants.KEY_P,
                        KeyMapping.Category.MISC
                )
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (leaveKey.consumeClick()) {
                Minecraft mc = Minecraft.getInstance();
                if (mc.getSingleplayerServer() != null || mc.getConnection() != null) {
                    mc.disconnectFromWorld(Component.literal("Left using button"));
                }
            }
        });
    }
}
