package sistemkasir;

import java.util.Date;
import java.util.List;

public class Transaksi {
    private int id;
    private User user;
    private Date tanggal;
    private double totalHarga;
    private List<DetailTransaksi> listDetail;

    public Transaksi() {}

    public Transaksi(int id, User user, Date tanggal, double totalHarga, List<DetailTransaksi> listDetail) {
        this.id = id;
        this.user = user;
        this.tanggal = tanggal;
        this.totalHarga = totalHarga;
        this.listDetail = listDetail;
    }

   
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Date getTanggal() { return tanggal; }
    public void setTanggal(Date tanggal) { this.tanggal = tanggal; }
    public double getTotalHarga() { return totalHarga; }
    public void setTotalHarga(double totalHarga) { this.totalHarga = totalHarga; }
    public List<DetailTransaksi> getListDetail() { return listDetail; }
    public void setListDetail(List<DetailTransaksi> listDetail) { this.listDetail = listDetail; }
}
