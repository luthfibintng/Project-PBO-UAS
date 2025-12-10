

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bus busTransKoetaradja = new Bus();
        Scanner scanner = new Scanner(System.in);
        int pilihan = 0;
        int idCounter = 1; // Untuk generate ID otomatis

        System.out.println("===== BUS TRANS KOETARADJA =====");

        while (pilihan != 4) {
            System.out.println("\nMENU:");
            System.out.println("1. Naikkan Penumpang");
            System.out.println("2. Turunkan Penumpang");
            System.out.println("3. Lihat Penumpang");
            System.out.println("4. Keluar");
            System.out.print("Pilihan: ");
            
            // Validasi input agar tidak error jika user input huruf
            if (scanner.hasNextInt()) {
                pilihan = scanner.nextInt();
                scanner.nextLine(); // Konsumsi newline
            } else {
                System.out.println("Input tidak valid.");
                scanner.next();
                continue;
            }

            switch (pilihan) {
                case 1:
                    System.out.print("Nama : ");
                    String nama = scanner.nextLine();
                    
                    System.out.print("Umur : ");
                    int umur = scanner.nextInt();
                    
                    System.out.print("Hamil (y/n) : ");
                    String hamilInput = scanner.next();
                    boolean hamil = hamilInput.equalsIgnoreCase("y");

                    // Buat objek penumpang baru
                    Penumpang p = new Penumpang(nama, idCounter++, umur, hamil);
                    
                    // Coba naikkan penumpang ke bus
                    busTransKoetaradja.naikkanPenumpang(p);
                    break;

                case 2:
                    // Turunkan penumpang 
                    System.out.print("Nama : ");
                    String namaTurun = scanner.nextLine();
                    busTransKoetaradja.turunkanPenumpang(namaTurun);
                    break;

                case 3:
                    // Lihat daftar penumpang 
                    System.out.println(busTransKoetaradja.toString());
                    break;

                case 4:
                    System.out.println("Terima kasih telah menggunakan sistem Trans Koetaradja.");
                    break;

                default:
                    System.out.println("Pilihan tidak tersedia.");
            }
        }
        scanner.close();
    }
}