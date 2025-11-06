package Entity;

import java.time.LocalDateTime;
import java.util.List;

public class HoaDon {
	private Integer maHD;
    private LocalDateTime ngayLapHoaDon;
    private KhachHang khachHang; // Quan hệ
    private NhanVien nhanVien; // Quan hệ
    private Integer soLuongBap;
    private Integer soLuongNuoc;
	
    private final Double giaBap = 40.0;
    private final Double giaNuoc = 20.0;
    
    
	public HoaDon(Integer maHD, LocalDateTime ngayLapHoaDon, KhachHang khachHang, NhanVien nhanVien, Integer soLuongBap,
			Integer soLuongNuoc) {
		super();
		this.maHD = maHD;
		this.ngayLapHoaDon = ngayLapHoaDon;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.soLuongBap = soLuongBap;
		this.soLuongNuoc = soLuongNuoc;
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

	public Integer getSoLuongBap() {
		return soLuongBap;
	}

	public void setSoLuongBap(Integer soLuongBap) {
		this.soLuongBap = soLuongBap;
	}

	public Integer getSoLuongNuoc() {
		return soLuongNuoc;
	}

	public void setSoLuongNuoc(Integer soLuongNuoc) {
		this.soLuongNuoc = soLuongNuoc;
	}
	
	
}
