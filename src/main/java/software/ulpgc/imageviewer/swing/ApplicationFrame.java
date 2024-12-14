package software.ulpgc.imageviewer.swing;

import software.ulpgc.imageviewer.commands.AutoPlayAction;
import software.ulpgc.imageviewer.commands.MoveNextImage;
import software.ulpgc.imageviewer.commands.MovePreviousImage;
import software.ulpgc.imageviewer.ImageHandler;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ApplicationFrame extends JFrame {
    private ImageHandler handler;

    public ApplicationFrame() {
        setTitle("Custom Image Viewer");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(buildImageHandler(), BorderLayout.CENTER);
        add(buildControls(), BorderLayout.SOUTH);
    }

    public ImageHandler getImageHandler() {
        return handler;
    }

    private Component buildImageHandler() {
        Map<String, String> paths = Map.of(
                "img1", "src/main/resources/software/ulpgc/imageviewer/images/1.jpg",
                "img2", "src/main/resources/software/ulpgc/imageviewer/images/2.jpg",
                "img3", "src/main/resources/software/ulpgc/imageviewer/images/3.jpg"
        );
        SwingImagePanel imagePanel = new SwingImagePanel(paths);
        this.handler = imagePanel;
        return imagePanel;
    }

    private JPanel buildControls() {
        JPanel panel = new JPanel();
        MoveNextImage nextAction = new MoveNextImage(handler);
        MovePreviousImage prevAction = new MovePreviousImage(handler);
        AutoPlayAction autoPlay = new AutoPlayAction(handler);

        JButton prevBtn = new JButton("Previous");
        prevBtn.addActionListener(e -> prevAction.execute());

        JButton nextBtn = new JButton("Next");
        nextBtn.addActionListener(e -> nextAction.execute());

        JToggleButton toggleAutoPlay = new JToggleButton("AutoPlay");
        toggleAutoPlay.addItemListener(e -> {
            autoPlay.execute();
            toggleAutoPlay.setText(autoPlay.isRunning() ? "Stop AutoPlay" : "AutoPlay");
        });

        JButton rotateBtn = new JButton("Rotate");
        rotateBtn.addActionListener(e -> {
            if (handler instanceof SwingImagePanel) {
                ((SwingImagePanel) handler).rotateImage(90);
            }
        });

        panel.add(prevBtn);
        panel.add(toggleAutoPlay);
        panel.add(rotateBtn);
        panel.add(nextBtn);
        return panel;
    }

}
