import gui.MenuUtamaGUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Set Look and Feel untuk tampilan lebih baik (opsional)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Inisialisasi dan tampilkan Menu Utama
        SwingUtilities.invokeLater(MenuUtamaGUI::new);
    }
}

