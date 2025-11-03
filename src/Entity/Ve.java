package Entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Ve {
	private Integer maVe;
    private SuatChieu suatChieu; // Quan hệ
    private Ghe ghe; // Quan hệ
    private Double giaVe;
    private LocalDate ngayDat;
    private LocalDate ngayChieu;
    private LocalTime gioChieu;
    private String tenGhe;
    private String tenPhongChieu;
	
    public Ve(Integer maVe, SuatChieu suatChieu, Ghe ghe, Double giaVe, LocalDate ngayDat, LocalDate ngayChieu,
			LocalTime gioChieu, String tenGhe, String tenPhongChieu) {
		super();
		this.maVe = maVe;
		this.suatChieu = suatChieu;
		this.ghe = ghe;
		this.giaVe = giaVe;
		this.ngayDat = ngayDat;
		this.ngayChieu = ngayChieu;
		this.gioChieu = gioChieu;
		this.tenGhe = tenGhe;
		this.tenPhongChieu = tenPhongChieu;
	}

	public Ve() {
		super();
	}

	public Integer getMaVe() {
		return maVe;
	}

	public void setMaVe(Integer maVe) {
		this.maVe = maVe;
	}

	public SuatChieu getSuatChieu() {
		return suatChieu;
	}

	public void setSuatChieu(SuatChieu suatChieu) {
		this.suatChieu = suatChieu;
	}

	public Ghe getGhe() {
		return ghe;
	}

	public void setGhe(Ghe ghe) {
		this.ghe = ghe;
	}

	public Double getGiaVe() {
		return giaVe;
	}

	public void setGiaVe(Double giaVe) {
		this.giaVe = giaVe;
	}

	public LocalDate getNgayDat() {
		return ngayDat;
	}

	public void setNgayDat(LocalDate ngayDat) {
		this.ngayDat = ngayDat;
	}

	public LocalDate getNgayChieu() {
		return ngayChieu;
	}

	public void setNgayChieu(LocalDate ngayChieu) {
		this.ngayChieu = ngayChieu;
	}

	public LocalTime getGioChieu() {
		return gioChieu;
	}

	public void setGioChieu(LocalTime gioChieu) {
		this.gioChieu = gioChieu;
	}

	public String getTenGhe() {
		return tenGhe;
	}

	public void setTenGhe(String tenGhe) {
		this.tenGhe = tenGhe;
	}

	public String getTenPhongChieu() {
		return tenPhongChieu;
	}

	public void setTenPhongChieu(String tenPhongChieu) {
		this.tenPhongChieu = tenPhongChieu;
	}
    
    
    
}
