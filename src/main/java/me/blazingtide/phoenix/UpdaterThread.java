package me.blazingtide.phoenix;

import org.bukkit.scheduler.BukkitRunnable;

public class UpdaterThread extends BukkitRunnable {

    private final Phoenix phoenix;

    UpdaterThread(Phoenix phoenix) {
        this.phoenix = phoenix;
        this.runTaskTimerAsynchronously(phoenix.getPlugin(), 1L, 1L);
    }

    @Override
    public void run() {
        //Made it a parallel stream to prevent any latency with updating GUIs just in case you got a server with like 2k GUIs open.
        phoenix.getOpenGUIS().values().parallelStream().filter(gui -> gui.isAutoUpdating() && (System.currentTimeMillis() - gui.getLastTick() + gui.getUpdateTick() <= 0)).forEach(GUI::update);
    }
}
