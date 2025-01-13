package model;

// Class PerpustakaanImpl
import java.util.ArrayList;
import java.util.List;

public class Perpustakaanimpl extends Perpustakaan {
    private List<Buku> koleksiBuku;

    public Perpustakaanimpl() {
        this.koleksiBuku = new ArrayList<>();
    }

    @Override
    public void tambahBuku(Buku buku) {
        koleksiBuku.add(buku);
        System.out.println("Buku berhasil ditambahkan: " + buku);
    }

    @Override
    public Buku cariBuku(String idBuku) {
        for (Buku buku : koleksiBuku) {
            if (buku.getIdBuku().equals(idBuku)) {
                return buku;
            }
        }
        return null; // Jika buku tidak ditemukan
    }

    @Override
    public boolean hapusBuku(String idBuku) {
        Buku buku = cariBuku(idBuku);
        if (buku != null) {
            koleksiBuku.remove(buku);
            System.out.println("Buku berhasil dihapus: " + buku);
            return true;
        } else {
            System.out.println("Buku dengan ID " + idBuku + " tidak ditemukan.");
            return false;
        }
    }

    @Override
    public boolean editBuku(String idBuku, Buku bukuBaru) {
        Buku buku = cariBuku(idBuku);
        if (buku != null) {
            buku.setJudulBuku(bukuBaru.getJudulBuku());
            buku.setPenulis(bukuBaru.getPenulis());
            buku.setTahunTerbit(bukuBaru.getTahunTerbit());
            buku.setStok(bukuBaru.getStok());
            System.out.println("Buku berhasil diperbarui: " + buku);
            return true;
        } else {
            System.out.println("Buku dengan ID " + idBuku + " tidak ditemukan.");
            return false;
        }
    }

    @Override
    public List<Buku> tampilkanStokBuku() {
        return koleksiBuku;
    }

    public List<Buku> getKoleksiBuku() {
        return koleksiBuku;
    }
}
