package Entity;

public class ChiTietHoaDon {
	private HoaDon hoaDon; // Quan hệ
    private Ve ve; // Quan hệ
    private Double donGiaBan;
    private Integer soLuong = 1;
    
	public ChiTietHoaDon(HoaDon hoaDon, Ve ve, Double donGiaBan, Integer soLuong) {
		super();
		this.hoaDon = hoaDon;
		this.ve = ve;
		this.donGiaBan = donGiaBan;
		this.soLuong = soLuong;
	}

	public ChiTietHoaDon() {
		super();
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public Ve getVe() {
		return ve;
	}

	public void setVe(Ve ve) {
		this.ve = ve;
	}

	public Double getDonGiaBan() {
		return donGiaBan;
	}

	public void setDonGiaBan(Double donGiaBan) {
		this.donGiaBan = donGiaBan;
	}

	public Integer getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(Integer soLuong) {
		this.soLuong = soLuong;
	}
    
    
}
