import java.util.Scanner;
import java.util.ArrayList;

class mataKuliah {
    String Kode, Nama;
    int sks;

    public mataKuliah(String Kode, String Nama, int sks) {
        this.Kode = Kode;
        this.Nama = Nama;
        this.sks = sks;
    }
}

class Mahasiswa {
    String Nama, Nim;
    ArrayList<mataKuliah> daftarMataKuliah = new ArrayList<>();

    public Mahasiswa(String Nama, String Nim) {
        this.Nama = Nama;
        this.Nim = Nim;
    }

    public int hitungTotalSKS() {
        int total = 0;
        for (mataKuliah mk : daftarMataKuliah) {
            total += mk.sks;
        }
        return total;
    }
}

class SistemPemantauanKRS {
    static ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("=== Sistem Pemantauan KRS Mahasiswa ===");
            System.out.println("1. Tambah Data KRS");
            System.out.println("2. Tampilkan Daftar KRS Mahasiswa");
            System.out.println("3. Analisis Data KRS");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");
            int menu = sc.nextInt();
            sc.nextLine();

            switch (menu) {
                case 1:
                    tambahDataKRS();
                    break;
                case 2:
                    tampilkanDaftarKRS();
                    break;
                case 3:
                    analisisDataKRS();
                    break;
                case 4:
                    System.out.println("Program selesai");
                    System.out.println("Terima Kasih!");
                    return;
                default:
                    System.out.println("Pilihan tidak valid");
            }
        }
    }

    public static void tambahDataKRS() {
        System.out.println("\n--- Tambah data KRS ---");
        System.out.print("Nama Mahasiswa: ");
        String Nama = sc.nextLine();
        System.out.print("NIM : ");
        String Nim = sc.nextLine();

        Mahasiswa mahasiswa = new Mahasiswa(Nama, Nim);
        tambahMataKuliah(mahasiswa);

        daftarMahasiswa.add(mahasiswa);
        System.out.println("Total SKS yang diambil: " + mahasiswa.hitungTotalSKS());
    }

    public static void tambahMataKuliah(Mahasiswa mahasiswa) {
        System.out.print("Kode Mata Kuliah: ");
        String kode = sc.nextLine();
        System.out.print("Nama Mata Kuliah: ");
        String nama = sc.nextLine();

        int sks;

        while (true) {
            System.out.print("Jumlah sks (1-3): ");
            sks = sc.nextInt();
            sc.nextLine();
            if (sks >= 1 && sks <= 3) {
                break;
            } else {
                System.out.println("Jumlah sks yang anda masukkan tidak valid. Masukkan angka antara 1-3!");
            }

        }


        mataKuliah mataKuliah = new mataKuliah(kode, nama, sks);
        mahasiswa.daftarMataKuliah.add(mataKuliah);
        System.out.println("Data mata kuliah berhasil ditambahkan.");
        System.out.print("Tambah mata kuliah lain? (y/t): ");
        String pilihan = sc.nextLine();

        if (pilihan.equalsIgnoreCase("y")) {
            tambahMataKuliah(mahasiswa);
        }
    }

    public static void tampilkanDaftarKRS() {
        System.out.println("\n--- Daftar KRS Mahasiswa ---");
        if (daftarMahasiswa.isEmpty()) {
            System.out.println("Belum ada data mahasiswa.");
            return;
        }

        // Menampilkan header tabel
        System.out.printf("%-10s %-15s %-40s %-10s\n", "NIM", "Nama", "Mata Kuliah", "SKS");

        // Menampilkan data mahasiswa dan mata kuliah
        for (Mahasiswa m : daftarMahasiswa) {
            for (mataKuliah mk : m.daftarMataKuliah) {
                // Menampilkan setiap mata kuliah yang diambil mahasiswa dalam format tabel
                System.out.printf("%-10s %-15s %-40s %-10d\n", m.Nim, m.Nama, mk.Nama, mk.sks);
            }
        }
    }

    public static void analisisDataKRS() {
        System.out.println("\n--- Analisis Data KRS ---");
        int count = 0;
        for (Mahasiswa m : daftarMahasiswa) {
            if (m.hitungTotalSKS() < 20) {
                count++;
            }
            System.out.println("Jumlah mahasiswa yang mengambil SKS kurang dari 20: " + count);
        }
    }
}

