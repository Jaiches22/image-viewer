package software.ulpgc.imageviewer.commands;

import software.ulpgc.imageviewer.ImageHandler;

public class MovePreviousImage implements Action {
    private final ImageHandler handler;

    public MovePreviousImage(ImageHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute() {
        handler.render("previous", 0);
    }
}
