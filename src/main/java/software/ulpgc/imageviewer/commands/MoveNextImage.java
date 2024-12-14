package software.ulpgc.imageviewer.commands;

import software.ulpgc.imageviewer.ImageHandler;

public class MoveNextImage implements Action {
    private final ImageHandler handler;

    public MoveNextImage(ImageHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute() {
        handler.render("next", 0);
    }
}
