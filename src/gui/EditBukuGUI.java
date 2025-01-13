package gui;
import model.Buku;
import model.Perpustakaanimpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditBukuGUI extends JFrame {
    private JTextField tfIdBuku, tfJudulBuku, tfPenulis, tfTahunTerbit, tfStok;
    private JButton btnCari, btnSimpan, btnKembali;
    private Perpustakaanimpl perpustakaan;
    private JFrame parentFrame;

    public EditBukuGUI(Perpustakaanimpl perpustakaan, JFrame parentFrame) {
        this.perpustakaan = perpustakaan;
        this.parentFrame = parentFrame;

        setTitle("Edit Buku");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Bagian input ID Buku
        JLabel lblIdBuku = new JLabel("Masukkan ID Buku:");
        gbc.gridx = 0; gbc.gridy = 0;
        add(lblIdBuku, gbc);

        tfIdBuku = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 0;
        add(tfIdBuku, gbc);

        btnCari = new JButton("Cari");
        gbc.gridx = 2; gbc.gridy = 0;
        add(btnCari, gbc);

        // Bagian form edit (disembunyikan dulu)
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

        btnSimpan = new JButton("Simpan");
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnSimpan, gbc);

        btnKembali = new JButton("Kembali");
        gbc.gridy = 6;
        add(btnKembali, gbc);

        // Awal form edit di-disable
        toggleEditForm(false);

        // Event tombol Cari
        btnCari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idBuku = tfIdBuku.getText().trim();
                Buku buku = perpustakaan.cariBuku(idBuku);

                if (buku != null) {
                    tfJudulBuku.setText(buku.getJudulBuku());
                    tfPenulis.setText(buku.getPenulis());
                    tfTahunTerbit.setText(String.valueOf(buku.getTahunTerbit()));
                    tfStok.setText(String.valueOf(buku.getStok()));
                    toggleEditForm(true);
                } else {
                    JOptionPane.showMessageDialog(EditBukuGUI.this, "Buku dengan ID \"" + idBuku + "\" tidak ditemukan.", "Error", JOptionPane.ERROR_MESSAGE);
                    toggleEditForm(false);
                }
            }
        });

        // Event tombol Simpan
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String idBuku = tfIdBuku.getText().trim();
                    String judulBuku = tfJudulBuku.getText().trim();
                    String penulis = tfPenulis.getText().trim();
                    int tahunTerbit = Integer.parseInt(tfTahunTerbit.getText().trim());
                    int stok = Integer.parseInt(tfStok.getText().trim());

                    if (judulBuku.isEmpty() || penulis.isEmpty()) {
                        JOptionPane.showMessageDialog(EditBukuGUI.this, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Buku buku = new Buku(idBuku, judulBuku, penulis, tahunTerbit, stok);
                    perpustakaan.editBuku(idBuku, buku);

                    JOptionPane.showMessageDialog(EditBukuGUI.this, "Data buku berhasil diperbarui.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    toggleEditForm(false);
                    tfIdBuku.setText(""); // Reset ID Buku
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(EditBukuGUI.this, "Tahun Terbit dan Stok harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
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

    // Method untuk mengaktifkan/menonaktifkan form edit
    private void toggleEditForm(boolean enable) {
        tfJudulBuku.setEnabled(enable);
        tfPenulis.setEnabled(enable);
        tfTahunTerbit.setEnabled(enable);
        tfStok.setEnabled(enable);
        btnSimpan.setEnabled(enable);
    }
}