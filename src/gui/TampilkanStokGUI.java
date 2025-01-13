package gui;
import model.Buku;
import model.Perpustakaanimpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TampilkanStokGUI extends JFrame {
    private JTable tableBuku;
    private JButton btnKembali;
    private Perpustakaanimpl perpustakaan;
    private JFrame parentFrame;

    public TampilkanStokGUI(Perpustakaanimpl perpustakaan, JFrame parentFrame) {
        this.perpustakaan = perpustakaan;
        this.parentFrame = parentFrame;

        setTitle("Tampilkan Stok Buku");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header untuk tabel
        String[] columnNames = {"ID Buku", "Judul Buku", "Penulis", "Tahun Terbit", "Stok"};

        // Data tabel diambil dari koleksi buku
        List<Buku> bukuList = perpustakaan.tampilkanStokBuku();
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (Buku buku : bukuList) {
            Object[] rowData = {
                    buku.getIdBuku(),
                    buku.getJudulBuku(),
                    buku.getPenulis(),
                    buku.getTahunTerbit(),
                    buku.getStok()
            };
            model.addRow(rowData);
        }

        // Tabel untuk menampilkan data buku
        tableBuku = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tableBuku);
        add(scrollPane, BorderLayout.CENTER);

        // Tombol kembali
        btnKembali = new JButton("Kembali");
        btnKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.setVisible(true);
                dispose();
            }
        });
        JPanel panelBawah = new JPanel();
        panelBawah.add(btnKembali);
        add(panelBawah, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // Posisikan di tengah layar
        setVisible(true);
    }
}
