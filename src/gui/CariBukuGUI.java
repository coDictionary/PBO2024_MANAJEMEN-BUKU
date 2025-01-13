package gui;

import model.Buku;
import model.Perpustakaanimpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CariBukuGUI extends JFrame {
    private JTextField tfIdBuku;
    private JButton btnCari, btnKembali;
    private JTextArea taHasil;
    private Perpustakaanimpl perpustakaan;
    private JFrame parentFrame;

    public CariBukuGUI(Perpustakaanimpl perpustakaan, JFrame parentFrame) {
        this.perpustakaan = perpustakaan;
        this.parentFrame = parentFrame;

        setTitle("Cari Buku");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel atas untuk input ID Buku
        JPanel panelInput = new JPanel(new FlowLayout());
        panelInput.add(new JLabel("ID Buku:"));
        tfIdBuku = new JTextField(20);
        panelInput.add(tfIdBuku);
        btnCari = new JButton("Cari");
        panelInput.add(btnCari);
        add(panelInput, BorderLayout.NORTH);

        // Area tengah untuk menampilkan hasil
        taHasil = new JTextArea();
        taHasil.setEditable(false);
        taHasil.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(taHasil);
        add(scrollPane, BorderLayout.CENTER);

        // Panel bawah untuk tombol navigasi
        JPanel panelTombol = new JPanel(new FlowLayout());
        btnKembali = new JButton("Kembali");
        panelTombol.add(btnKembali);
        add(panelTombol, BorderLayout.SOUTH);

        // Event tombol cari
        btnCari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idBuku = tfIdBuku.getText().trim();
                if (idBuku.isEmpty()) {
                    JOptionPane.showMessageDialog(CariBukuGUI.this, "ID Buku tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Buku buku = perpustakaan.cariBuku(idBuku);
                if (buku != null) {
                    taHasil.setText("Buku ditemukan:\n\n" +
                            "ID Buku   : " + buku.getIdBuku() + "\n" +
                            "Judul     : " + buku.getJudulBuku() + "\n" +
                            "Penulis   : " + buku.getPenulis() + "\n" +
                            "Tahun     : " + buku.getTahunTerbit() + "\n" +
                            "Stok      : " + buku.getStok());
                } else {
                    taHasil.setText("Buku dengan ID \"" + idBuku + "\" tidak ditemukan.");
                }
            }
        });

        // Event tombol kembali
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