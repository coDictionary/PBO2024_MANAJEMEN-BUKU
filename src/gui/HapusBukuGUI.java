package gui;
import model.Perpustakaanimpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HapusBukuGUI extends JFrame {
    private JTextField tfIdBuku;
    private JButton btnHapus, btnKembali;
    private JTextArea taHasil;
    private Perpustakaanimpl perpustakaan;
    private JFrame parentFrame;

    public HapusBukuGUI(Perpustakaanimpl perpustakaan, JFrame parentFrame) {
        this.perpustakaan = perpustakaan;
        this.parentFrame = parentFrame;

        setTitle("Hapus Buku");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Panel atas untuk input ID Buku
        JLabel lblIdBuku = new JLabel("ID Buku:");
        gbc.gridx = 0; gbc.gridy = 0;
        add(lblIdBuku, gbc);

        tfIdBuku = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 0;
        add(tfIdBuku, gbc);

        // Tombol Hapus
        btnHapus = new JButton("Hapus");
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnHapus, gbc);

        // Area untuk menampilkan hasil
        taHasil = new JTextArea(10, 30);
        taHasil.setEditable(false);
        taHasil.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(taHasil);
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(scrollPane, gbc);

        // Tombol Kembali
        btnKembali = new JButton("Kembali");
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnKembali, gbc);

        // Event tombol Hapus
        btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idBuku = tfIdBuku.getText().trim();
                if (idBuku.isEmpty()) {
                    JOptionPane.showMessageDialog(HapusBukuGUI.this, "ID Buku tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                boolean berhasil = perpustakaan.hapusBuku(idBuku);
                if (berhasil) {
                    taHasil.setText("Buku dengan ID \"" + idBuku + "\" berhasil dihapus.");
                } else {
                    taHasil.setText("Buku dengan ID \"" + idBuku + "\" tidak ditemukan.");
                }
                tfIdBuku.setText(""); // Reset input
            }
        });

        // Event tombol Kembali
        btnKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.setVisible(true);
                dispose();
            }
        });

        setLocationRelativeTo(null); // Posisikan di tengah layar
        setVisible(true);
    }
}