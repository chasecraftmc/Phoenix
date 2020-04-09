package me.blazingtide.phoenix;

import lombok.SneakyThrows;

public class GUIUpdater extends Thread {

    public GUIUpdater() {
        super("GUI Updater Thread");
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            //Made it a parallel stream to prevent any latency with updating GUIs just in case you got a server with like 2k GUIs open.
            GUIHandler.OPEN_GUIS.values().parallelStream().filter(GUI::isAutoUpdating).filter(gui -> System.currentTimeMillis() - gui.getLastTick() + gui.getUpdateTick() <= 0).forEach(GUI::update);

            Thread.sleep(1); //Yeah probs a bad thing but y not LOl
        }
    }
}
