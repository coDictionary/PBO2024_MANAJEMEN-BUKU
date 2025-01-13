package gui;
import model.Perpustakaanimpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuUtamaGUI extends JFrame {
    private Perpustakaanimpl perpustakaan;

    public MenuUtamaGUI() {
        perpustakaan = new Perpustakaanimpl();
        setTitle("Menu Utama - Manajemen Perpustakaan");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        JButton btnTambahBuku = new JButton("Tambah Buku");
        JButton btnCariBuku = new JButton("Cari Buku");
        JButton btnHapusBuku = new JButton("Hapus Buku");
        JButton btnEditBuku = new JButton("Edit Buku");
        JButton btnTampilkanStok = new JButton("Tampilkan Stok Buku");

        add(btnTambahBuku);
        add(btnCariBuku);
        add(btnHapusBuku);
        add(btnEditBuku);
        add(btnTampilkanStok);

        btnTambahBuku.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TambahBukuGUI(perpustakaan, MenuUtamaGUI.this);
            }
        });

        btnCariBuku.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CariBukuGUI(perpustakaan, MenuUtamaGUI.this);
            }
        });

        btnHapusBuku.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HapusBukuGUI(perpustakaan, MenuUtamaGUI.this);
            }
        });

        btnEditBuku.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditBukuGUI(perpustakaan, MenuUtamaGUI.this);
            }
        });

        btnTampilkanStok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TampilkanStokGUI(perpustakaan, MenuUtamaGUI.this);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new MenuUtamaGUI();
    }
}
