package gui;

import model.Buku;
import model.Perpustakaanimpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TambahBukuGUI extends JFrame {
    private JTextField tfIdBuku, tfJudulBuku, tfPenulis, tfTahunTerbit, tfStok;
    private JButton btnTambah, btnKembali;
    private Perpustakaanimpl perpustakaan;
    private JFrame parentFrame;

    public TambahBukuGUI(Perpustakaanimpl perpustakaan, JFrame parentFrame) {
        this.perpustakaan = perpustakaan;
        this.parentFrame = parentFrame;

        setTitle("Tambah Buku");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Form input
        JLabel lblIdBuku = new JLabel("ID Buku:");
        gbc.gridx = 0; gbc.gridy = 0;
        add(lblIdBuku, gbc);

        tfIdBuku = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 0;
        add(tfIdBuku, gbc);

        JLabel lblJudulBuku = new JLabel("Judul Buku:");
        gbc.gridx = 0; gbc.gridy = 1;
        add(lblJudulBuku, gbc);

        tfJudulBuku = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 1;
        add(tfJudulBuku, gbc);

        JLabel lblPenulis = new JLabel("Penulis:");
        gbc.gridx = 0; gbc.gridy = 2;
        add(lblPenulis, gbc);

        tfPenulis = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 2;
        add(tfPenulis, gbc);

        JLabel lblTahunTerbit = new JLabel("Tahun Terbit:");
        gbc.gridx = 0; gbc.gridy = 3;
        add(lblTahunTerbit, gbc);

        tfTahunTerbit = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 3;
        add(tfTahunTerbit, gbc);

        JLabel lblStok = new JLabel("Stok:");
        gbc.gridx = 0; gbc.gridy = 4;
        add(lblStok, gbc);

        tfStok = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 4;
        add(tfStok, gbc);

        btnTambah = new JButton("Tambah");
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnTambah, gbc);

        btnKembali = new JButton("Kembali");
        gbc.gridy = 6;
        add(btnKembali, gbc);

        // Event tombol Tambah
        btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String idBuku = tfIdBuku.getText().trim();
                    String judulBuku = tfJudulBuku.getText().trim();
                    String penulis = tfPenulis.getText().trim();
                    int tahunTerbit = Integer.parseInt(tfTahunTerbit.getText().trim());
                    int stok = Integer.parseInt(tfStok.getText().trim());

                    if (idBuku.isEmpty() || judulBuku.isEmpty() || penulis.isEmpty()) {
                        JOptionPane.showMessageDialog(TambahBukuGUI.this, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Cek apakah ID Buku sudah ada
                    if (perpustakaan.cariBuku(idBuku) != null) {
                        JOptionPane.showMessageDialog(TambahBukuGUI.this, "ID Buku sudah digunakan. Masukkan ID yang berbeda.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Buku buku = new Buku(idBuku, judulBuku, penulis, tahunTerbit, stok);
                    perpustakaan.tambahBuku(buku);

                    JOptionPane.showMessageDialog(TambahBukuGUI.this, "Buku berhasil ditambahkan.", "Sukses", JOptionPane.INFORMATION_MESSAGE);

                    // Reset form setelah berhasil menambahkan buku
                    tfIdBuku.setText("");
                    tfJudulBuku.setText("");
                    tfPenulis.setText("");
                    tfTahunTerbit.setText("");
                    tfStok.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(TambahBukuGUI.this, "Tahun Terbit dan Stok harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
                }
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


