package net.tutla.leavebind.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class LeavebindClient implements ClientModInitializer {
    public static KeyBinding leaveKey;

    @Override
    public void onInitializeClient() {
        leaveKey = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.leavebind.leaveKey",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_P,
                        KeyBinding.Category.MISC
                )
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (leaveKey.wasPressed()) {
                MinecraftClient mc = MinecraftClient.getInstance();
                if (mc.getServer() != null || mc.getNetworkHandler() != null) {
                    mc.disconnect(Text.literal("Left using button"));
                }
            }
        });
    }
}
