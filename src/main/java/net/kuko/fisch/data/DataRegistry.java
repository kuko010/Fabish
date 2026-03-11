package net.kuko.fisch.data;

import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.server.packs.PackType;

public class DataRegistry {
    public static ResourceManagerHelper myDataListener = ResourceManagerHelper.get(PackType.SERVER_DATA);

    public static void register() {
        myDataListener.registerReloadListener(new MyDataListener());
    }
}
