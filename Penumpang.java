public class Penumpang {
    private String nama; // Ditambahkan untuk keperluan simulasi & method turunkanPenumpang
    private int id;
    private int umur;
    private boolean hamil;
    private int saldo;

    // Constructor
    public Penumpang(String nama, int id, int umur, boolean hamil) {
        this.nama = nama;
        this.id = id;
        this.umur = umur;
        this.hamil = hamil;
        this.saldo = 10000; // Saldo awal default 10.000
    }

    // Accessor Methods (Getters)
    public String getNama() {
        return nama;
    }

    public int getId() {
        return id;
    }

    public int getUmur() {
        return umur;
    }

    public boolean getHamil() {
        return hamil;
    }

    public int getSaldo() {
        return saldo;
    }

    // Method untuk menambah saldo
    public void tambahSaldo(int saldoBaru) {
        this.saldo += saldoBaru;
    }

    // Method untuk mengurangi saldo saat bayar ongkos
    public void kurangiSaldo(int ongkos) {
        this.saldo -= ongkos;
    }
}