import java.sql.*;
import java.util.Scanner;

public class Main {
    static final String URL = "jdbc:mysql://localhost:3306/db_kasir";
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n=== SISTEM KASIR ===");
            System.out.println("1. Tampilkan Produk");
            System.out.println("2. Tambah Produk");
            System.out.println("3. Tambah User");
            System.out.println("4. Tambah Transaksi");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1:
                    tampilkanProduk();
                    break;
                case 2:
                    tambahProduk(input);
                    break;
                case 3:
                    tambahUser(input);
                    break;
                case 4:
                    tambahTransaksi(input);
                    break;
                case 5:
                    System.out.println("Terima kasih");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 5);
    }

    public static void tampilkanProduk() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM produk");

            System.out.println("\n--- Daftar Produk ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("produk_id") +
                                   ", Nama: " + rs.getString("nama_produk") +
                                   ", Harga: " + rs.getDouble("harga") +
                                   ", Stok: " + rs.getInt("stok"));
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Gagal menampilkan produk: " + e.getMessage());
        }
    }

    public static void tambahProduk(Scanner input) {
        try {
            System.out.print("Nama produk: ");
            String nama = input.nextLine();
            System.out.print("Harga: ");
            double harga = input.nextDouble();
            System.out.print("Stok: ");
            int stok = input.nextInt();

            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            String sql = "INSERT INTO produk (nama_produk, harga, stok) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nama);
            ps.setDouble(2, harga);
            ps.setInt(3, stok);
            ps.executeUpdate();

            System.out.println("Produk berhasil ditambahkan!");
            conn.close();
        } catch (Exception e) {
            System.out.println("Gagal menambah produk: " + e.getMessage());
        }
    }

    
    public static void tambahUser(Scanner input) {
        try {
            System.out.print("Nama: ");
            String nama = input.nextLine();
            System.out.print("Username: ");
            String username = input.nextLine();
            System.out.print("Password: ");
            String password = input.nextLine();

            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            String sql = "INSERT INTO user (nama, username, password) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nama);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.executeUpdate();

            System.out.println("User berhasil ditambahkan!");
            conn.close();
        } catch (Exception e) {
            System.out.println("Gagal tambah user: " + e.getMessage());
        }
    }

    public static void tambahTransaksi(Scanner input) {
        try {
            System.out.print("Masukkan ID User: ");
            int userId = input.nextInt();
            input.nextLine();

            System.out.print("Jumlah produk yang dibeli: ");
            int jumlahItem = input.nextInt();
            double total = 0;

            Connection conn = DriverManager.getConnection(URL, USER, PASS);

            String sqlTrans = "INSERT INTO transaksi (user_id, total) VALUES (?, ?)";
            PreparedStatement psTrans = conn.prepareStatement(sqlTrans, Statement.RETURN_GENERATED_KEYS);
            psTrans.setInt(1, userId);
            psTrans.setDouble(2, 0);
            psTrans.executeUpdate();

            ResultSet generatedKeys = psTrans.getGeneratedKeys();
            int transaksiId = 0;
            if (generatedKeys.next()) {
                transaksiId = generatedKeys.getInt(1);
            }

            for (int i = 0; i < jumlahItem; i++) {
                System.out.print("ID Produk ke-" + (i+1) + ": ");
                int produkId = input.nextInt();
                System.out.print("Jumlah: ");
                int qty = input.nextInt();

                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT harga FROM produk WHERE produk_id = " + produkId);
                double harga = 0;
                if (rs.next()) harga = rs.getDouble("harga");

                double subtotal = harga * qty;
                total += subtotal;

                String sqlDetail = "INSERT INTO detail_transaksi (transaksi_id, produk_id, jumlah, subtotal) VALUES (?, ?, ?, ?)";
                PreparedStatement psDetail = conn.prepareStatement(sqlDetail);
                psDetail.setInt(1, transaksiId);
                psDetail.setInt(2, produkId);
                psDetail.setInt(3, qty);
                psDetail.setDouble(4, subtotal);
                psDetail.executeUpdate();
            }

            String sqlUpdate = "UPDATE transaksi SET total = ? WHERE transaksi_id = ?";
            PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);
            psUpdate.setDouble(1, total);
            psUpdate.setInt(2, transaksiId);
            psUpdate.executeUpdate();

            System.out.println("Transaksi berhasil disimpan dengan total: Rp" + total);
            conn.close();
        } catch (Exception e) {
            System.out.println("Gagal tambah transaksi: " + e.getMessage());
        }
    }
}


