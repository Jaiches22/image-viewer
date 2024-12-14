package software.ulpgc.imageviewer.swing;

import software.ulpgc.imageviewer.ImageHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class SwingImagePanel extends JPanel implements ImageHandler {
    private int currentIndex = 0;
    private int dragOffset = 0;
    private int lastDragX = 0;
    private double rotationAngle = 0;
    private final List<String> imageKeys;
    private final Map<String, Image> images;

    public SwingImagePanel(Map<String, String> paths) {
        this.images = loadImages(paths);
        this.imageKeys = new ArrayList<>(images.keySet());
        addMouseMotionListener(buildMouseMotionListener());
        addMouseListener(buildMouseListener());
    }

    private Map<String, Image> loadImages(Map<String, String> paths) {
        Map<String, Image> loadedImages = new HashMap<>();
        paths.forEach((key, path) -> loadedImages.put(key, new ImageIcon(path).getImage()));
        return loadedImages;
    }

    private MouseMotionListener buildMouseMotionListener() {
        return new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                dragOffset = e.getX() - lastDragX;
                repaint();
            }
        };
    }

    private MouseListener buildMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastDragX = e.getX();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (dragOffset > 50) {
                    render("previous", 0);
                } else if (dragOffset < -50) {
                    render("next", 0);
                }
                dragOffset = 0;
                repaint();
            }
        };
    }

    public void rotateImage(double angle) {
        this.rotationAngle = (this.rotationAngle + angle) % 360;
        repaint();
    }

    @Override
    public void render(String action, int offset) {
        if ("next".equals(action)) {
            currentIndex = (currentIndex + 1) % imageKeys.size();
        } else if ("previous".equals(action)) {
            currentIndex = (currentIndex - 1 + imageKeys.size()) % imageKeys.size();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.translate(getWidth() / 2.0, getHeight() / 2.0);
        g2d.rotate(Math.toRadians(rotationAngle));
        g2d.translate(-getWidth() / 2.0, -getHeight() / 2.0);

        int xOffset = dragOffset;
        for (int i = -1; i <= 1; i++) {
            int idx = (currentIndex + i + imageKeys.size()) % imageKeys.size();
            Image img = images.get(imageKeys.get(idx));
            if (img != null) {
                int x = getWidth() * i + xOffset;
                g2d.drawImage(img, x, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
