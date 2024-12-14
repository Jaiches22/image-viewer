package software.ulpgc.imageviewer.commands;

import software.ulpgc.imageviewer.ImageHandler;
import javax.swing.*;

public class AutoPlayAction implements Action {
    private final ImageHandler imageHandler;
    private Timer timer;

    public AutoPlayAction(ImageHandler imageHandler) {
        this.imageHandler = imageHandler;
    }

    @Override
    public void execute() {
        if (isRunning()) {
            stopAutoPlay();
        } else {
            startAutoPlay();
        }
    }

    public boolean isRunning() {
        return timer != null && timer.isRunning();
    }

    private void startAutoPlay() {
        timer = new Timer(2000, event -> imageHandler.render("next", 0));
        timer.start();
    }

    private void stopAutoPlay() {
        if (timer != null) {
            timer.stop();
        }
    }
}
