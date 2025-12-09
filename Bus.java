package UAS;

import java.util.ArrayList;

public class Bus {
    // List untuk menyimpan objek penumpang 
    private ArrayList<Penumpang> penumpangBiasa;
    private ArrayList<Penumpang> penumpangPrioritas;
    private ArrayList<Penumpang> penumpangBerdiri;

    // Konstanta dan variabel pendapatan 
    private static final int ONGKOS_BUS = 2000;
    private int totalPendapatan;

    // Kapasitas Kursi
    // 16 Kursi Biasa, 4 Kursi Prioritas, Sisa (20) Berdiri = Total 40
    private final int MAX_PENUMPANG_BIASA = 16;
    private final int MAX_PENUMPANG_PRIORITAS = 4;
    private final int MAX_PENUMPANG_BERDIRI = 20;

    // Constructor
    public Bus() {
        this.penumpangBiasa = new ArrayList<>();
        this.penumpangPrioritas = new ArrayList<>();
        this.penumpangBerdiri = new ArrayList<>();
        this.totalPendapatan = 0;
    }

    // Getter methods untuk list penumpang
    public ArrayList<Penumpang> getPenumpangBiasa() {
        return penumpangBiasa;
    }

    public ArrayList<Penumpang> getPenumpangPrioritas() {
        return penumpangPrioritas;
    }

    public ArrayList<Penumpang> getPenumpangBerdiri() {
        return penumpangBerdiri;
    }

    // Getter methods untuk jumlah penumpang
    public int getJumlahPenumpangBiasa() {
        return penumpangBiasa.size();
    }

    public int getJumlahPenumpangPrioritas() {
        return penumpangPrioritas.size();
    }

    public int getJumlahPenumpangBerdiri() {
        return penumpangBerdiri.size();
    }
    
    // Method Helper: Cek apakah penumpang masuk kategori prioritas
    private boolean isPrioritas(Penumpang p) {
        return (p.getUmur() < 10 || p.getUmur() > 60 || p.getHamil());
    }

    // Method Naikkan Penumpang 
    public boolean naikkanPenumpang(Penumpang p) {
        // 1. Cek apakah saldo mencukupi 
        if (p.getSaldo() < ONGKOS_BUS) {
            System.out.println("Gagal: Saldo tidak mencukupi.");
            return false;
        }

        // 2. Cek apakah bus sudah penuh total (40 orang)
        int totalPenumpang = getJumlahPenumpangBiasa() + getJumlahPenumpangPrioritas() + getJumlahPenumpangBerdiri();
        if (totalPenumpang >= (MAX_PENUMPANG_BIASA + MAX_PENUMPANG_PRIORITAS + MAX_PENUMPANG_BERDIRI)) {
            System.out.println("Gagal: Bus Penuh.");
            return false;
        }

        boolean berhasilNaik = false;

        // 3. Logika Penempatan Kursi
        if (isPrioritas(p)) {
            // Jika Prioritas: Coba Kursi Prioritas -> Kursi Biasa -> Berdiri 
            if (getJumlahPenumpangPrioritas() < MAX_PENUMPANG_PRIORITAS) {
                penumpangPrioritas.add(p);
                berhasilNaik = true;
            } else if (getJumlahPenumpangBiasa() < MAX_PENUMPANG_BIASA) {
                penumpangBiasa.add(p); // Penumpang prioritas boleh duduk di kursi biasa 
                berhasilNaik = true;
            } else if (getJumlahPenumpangBerdiri() < MAX_PENUMPANG_BERDIRI) {
                penumpangBerdiri.add(p);
                berhasilNaik = true;
            }
        } else {
            // Jika Biasa: Coba Kursi Biasa -> Berdiri (TIDAK BOLEH Kursi Prioritas) 
            if (getJumlahPenumpangBiasa() < MAX_PENUMPANG_BIASA) {
                penumpangBiasa.add(p);
                berhasilNaik = true;
            } else if (getJumlahPenumpangBerdiri() < MAX_PENUMPANG_BERDIRI) {
                penumpangBerdiri.add(p);
                berhasilNaik = true;
            }
        }

        // 4. Jika berhasil naik, potong saldo dan tambah pendapatan 
        if (berhasilNaik) {
            p.kurangiSaldo(ONGKOS_BUS);
            this.totalPendapatan += ONGKOS_BUS;
            System.out.println("Penumpang Berhasil ditambahkan!"); 
            return true;
        } else {
            System.out.println("Gagal: Tidak ada tempat yang sesuai.");
            return false;
        }
    }

    // Method Turunkan Penumpang 
    public boolean turunkanPenumpang(String nama) {
        // Cek di list Biasa
        for (int i = 0; i < penumpangBiasa.size(); i++) {
            if (penumpangBiasa.get(i).getNama().equalsIgnoreCase(nama)) {
                penumpangBiasa.remove(i);
                System.out.println("Penumpang Berhasil Turun!");
                return true;
            }
        }
        // Cek di list Prioritas
        for (int i = 0; i < penumpangPrioritas.size(); i++) {
            if (penumpangPrioritas.get(i).getNama().equalsIgnoreCase(nama)) {
                penumpangPrioritas.remove(i);
                System.out.println("Penumpang Berhasil Turun!");
                return true;
            }
        }
        // Cek di list Berdiri
        for (int i = 0; i < penumpangBerdiri.size(); i++) {
            if (penumpangBerdiri.get(i).getNama().equalsIgnoreCase(nama)) {
                penumpangBerdiri.remove(i);
                System.out.println("Penumpang Berhasil Turun!");
                return true;
            }
        }
        
        System.out.println("Penumpang Tidak ditemukan!");
        return false;
    }

    // Method toString untuk menampilkan info
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Penumpang Biasa : ");
        if (penumpangBiasa.isEmpty()) sb.append("<kosong>");
        else {
            for (Penumpang p : penumpangBiasa) sb.append(p.getNama()).append(", ");
        }
        sb.append("\n");

        sb.append("Penumpang Prioritas : ");
        if (penumpangPrioritas.isEmpty()) sb.append("<kosong>");
        else {
            for (Penumpang p : penumpangPrioritas) sb.append(p.getNama()).append(", ");
        }
        sb.append("\n");

        sb.append("Penumpang Berdiri : ");
        if (penumpangBerdiri.isEmpty()) sb.append("<kosong>");
        else {
            for (Penumpang p : penumpangBerdiri) sb.append(p.getNama()).append(", ");
        }
        sb.append("\n");

        int total = getJumlahPenumpangBiasa() + getJumlahPenumpangPrioritas() + getJumlahPenumpangBerdiri();
        sb.append("Jumlah Penumpang: ").append(total).append("\n");
        sb.append("Total Pendapatan: Rp").append(totalPendapatan).append("\n"); // Tambahan info pendapatan
        
        return sb.toString();
    }
}