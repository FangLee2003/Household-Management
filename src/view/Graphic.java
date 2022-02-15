package view;

import javax.swing.*;
import java.awt.*;

public class Graphic {
    public static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Home.class.getResource(path);

        if (imgURL != null) {
            return new ImageIcon(new ImageIcon(imgURL).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        }
        System.err.println("Couldn't find file: " + path);
        return null;
    }

    public static ImageIcon getSearchImageIcon() {
        return createImageIcon("../assets/search.png");
    }

    public static ImageIcon getAddImageIcon() {
        return createImageIcon("../assets/add.png");
    }

    public static ImageIcon getEditImageIcon() {
        return createImageIcon("../assets/edit.png");
    }

    public static ImageIcon getDeleteImageIcon() {
        return createImageIcon("../assets/delete.png");
    }

    public static ImageIcon getNameImageIcon() {
        return createImageIcon("../assets/name.png");
    }

    public static ImageIcon getSaveImageIcon() {
        return createImageIcon("../assets/save.png");
    }

    public static ImageIcon getLoadImageIcon(String path) {
        java.net.URL imgURL = Home.class.getResource(path);

        if (imgURL != null) {
            return new ImageIcon(imgURL);
        }
        System.err.println("Couldn't find file: " + path);
        return null;
    }
}

