package Entity;

import java.time.LocalDateTime;
import java.util.List;

public class HoaDon {
	private Integer maHD;
    private LocalDateTime ngayLapHoaDon;
    private KhachHang khachHang; // Quan hệ
    private NhanVien nhanVien; // Quan hệ
    private List<ChiTietHoaDon> chiTietHoaDons;
    
	public HoaDon(Integer maHD, LocalDateTime ngayLapHoaDon, KhachHang khachHang, NhanVien nhanVien,
			List<ChiTietHoaDon> chiTietHoaDons) {
		super();
		this.maHD = maHD;
		this.ngayLapHoaDon = ngayLapHoaDon;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.chiTietHoaDons = chiTietHoaDons;
	}
	
	public HoaDon() {
		super();
	}

	public Integer getMaHD() {
		return maHD;
	}

	public void setMaHD(Integer maHD) {
		this.maHD = maHD;
	}

	public LocalDateTime getNgayLapHoaDon() {
		return ngayLapHoaDon;
	}

	public void setNgayLapHoaDon(LocalDateTime ngayLapHoaDon) {
		this.ngayLapHoaDon = ngayLapHoaDon;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public List<ChiTietHoaDon> getChiTietHoaDons() {
		return chiTietHoaDons;
	}

	public void setChiTietHoaDons(List<ChiTietHoaDon> chiTietHoaDons) {
		this.chiTietHoaDons = chiTietHoaDons;
	}
    
    
}
