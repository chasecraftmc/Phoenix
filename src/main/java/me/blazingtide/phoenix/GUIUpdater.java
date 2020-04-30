package me.blazingtide.phoenix;

public class GUIUpdater extends Thread {

    public GUIUpdater() {
        super("GUI Updater Thread");
    }

    @Override
    public void run() {
        while (true) {
            //Made it a parallel stream to prevent any latency with updating GUIs just in case you got a server with like 2k GUIs open.
            GUIHandler.OPEN_GUIS.values().parallelStream().filter(gui -> gui.isAutoUpdating() && (System.currentTimeMillis() - gui.getLastTick() + gui.getUpdateTick() <= 0)).forEach(GUI::update);

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
