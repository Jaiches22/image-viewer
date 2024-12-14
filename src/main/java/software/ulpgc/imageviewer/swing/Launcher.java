package software.ulpgc.imageviewer.swing;

import software.ulpgc.imageviewer.ImageHandler;
import javax.swing.*;

public class Launcher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ApplicationFrame appFrame = new ApplicationFrame();
            appFrame.setVisible(true);
            ImageHandler handler = appFrame.getImageHandler();
            handler.render("img1", 0);
            handler.render("img2", 400);
            handler.render("img3", 800);
        });
    }
}
