import java.util.Scanner;

class MataKuliah {
    String Kode, Nama;
    int sks;

    public MataKuliah(String Kode, String Nama, int sks) {
        this.Kode = Kode;
        this.Nama = Nama;
        this.sks = sks;
    }
}

class Mahasiswa {
    String Nama, Nim;
    MataKuliah[] daftarMataKuliah = new MataKuliah[10]; // Maksimal 10 mata kuliah
    int jumlahMataKuliah = 0;

    public Mahasiswa(String Nama, String Nim) {
        this.Nama = Nama;
        this.Nim = Nim;
    }

    public int hitungTotalSKS() {
        int total = 0;
        for (int i = 0; i < jumlahMataKuliah; i++) {
            total += daftarMataKuliah[i].sks;
        }
        return total;
    }
}

class SistemPemantauanKRS {
    static Mahasiswa[] daftarMahasiswa = new Mahasiswa[100]; // Maksimal 100 mahasiswa
    static int jumlahMahasiswa = 0;
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
        if (jumlahMahasiswa >= 100) {
            System.out.println("Kapasitas maksimum mahasiswa tercapai!");
            return;
        }

        System.out.println("\n--- Tambah data KRS ---");
        System.out.print("Nama Mahasiswa: ");
        String Nama = sc.nextLine();
        System.out.print("NIM : ");
        String Nim = sc.nextLine();

        Mahasiswa mahasiswa = new Mahasiswa(Nama, Nim);
        tambahMataKuliah(mahasiswa);

        daftarMahasiswa[jumlahMahasiswa++] = mahasiswa;
        System.out.println("Total SKS yang diambil: " + mahasiswa.hitungTotalSKS());
    }

    public static void tambahMataKuliah(Mahasiswa mahasiswa) {
        if (mahasiswa.jumlahMataKuliah >= 10) {
            System.out.println("Kapasitas maksimum mata kuliah tercapai!");
            return;
        }

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

        mahasiswa.daftarMataKuliah[mahasiswa.jumlahMataKuliah++] = new MataKuliah(kode, nama, sks);
        System.out.println("Data mata kuliah berhasil ditambahkan.");
        System.out.print("Tambah mata kuliah lain? (y/t): ");
        String pilihan = sc.nextLine();

        if (pilihan.equalsIgnoreCase("y")) {
            tambahMataKuliah(mahasiswa);
        }
    }

    public static void tampilkanDaftarKRS() {
        System.out.println("\n--- Daftar KRS Mahasiswa ---");
        if (jumlahMahasiswa == 0) {
            System.out.println("Belum ada data mahasiswa.");
            return;
        }

        System.out.printf("%-10s %-15s %-40s %-10s\n", "NIM", "Nama", "Mata Kuliah", "SKS");

        for (int i = 0; i < jumlahMahasiswa; i++) {
            Mahasiswa m = daftarMahasiswa[i];
            for (int j = 0; j < m.jumlahMataKuliah; j++) {
                MataKuliah mk = m.daftarMataKuliah[j];
                System.out.printf("%-10s %-15s %-40s %-10d\n", m.Nim, m.Nama, mk.Nama, mk.sks);
            }
        }
    }

    public static void analisisDataKRS() {
        System.out.println("\n--- Analisis Data KRS ---");
        int count = 0;
        for (int i = 0; i < jumlahMahasiswa; i++) {
            if (daftarMahasiswa[i].hitungTotalSKS() < 20) {
                count++;
            }
        }
        System.out.println("Jumlah mahasiswa yang mengambil SKS kurang dari 20: " + count);
    }
}
