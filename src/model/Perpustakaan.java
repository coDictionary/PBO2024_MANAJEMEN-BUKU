package model;

import java.util.List;

// Abstract Class Perpustakaan
abstract class Perpustakaan {
    public abstract void tambahBuku(Buku buku);
    public abstract Buku cariBuku(String idBuku);
    public abstract boolean hapusBuku(String idBuku);
    public abstract boolean editBuku(String idBuku, Buku bukuBaru);
    public abstract List<Buku> tampilkanStokBuku();
}
