package com.mycompany.barangayrecords;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            ResidentView view = new ResidentView();
            new ResidentController(view);
            view.setVisible(true);
        });
    }
}
