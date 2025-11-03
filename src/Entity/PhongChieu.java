package Entity;

import java.util.List;

public class PhongChieu {
	private Integer maPhongChieu;
    private String tenPhong;
    private Integer soLuongGhe;
    private List<Ghe> ghes;
    private List<SuatChieu> suatChieus;
    
	public PhongChieu(Integer maPhongChieu, String tenPhong, Integer soLuongGhe, List<Ghe> ghes,
			List<SuatChieu> suatChieus) {
		super();
		this.maPhongChieu = maPhongChieu;
		this.tenPhong = tenPhong;
		this.soLuongGhe = soLuongGhe;
		this.ghes = ghes;
		this.suatChieus = suatChieus;
	}

	public PhongChieu() {
		super();
	}

	public Integer getMaPhongChieu() {
		return maPhongChieu;
	}

	public void setMaPhongChieu(Integer maPhongChieu) {
		this.maPhongChieu = maPhongChieu;
	}

	public String getTenPhong() {
		return tenPhong;
	}

	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}

	public Integer getSoLuongGhe() {
		return soLuongGhe;
	}

	public void setSoLuongGhe(Integer soLuongGhe) {
		this.soLuongGhe = soLuongGhe;
	}

	public List<Ghe> getGhes() {
		return ghes;
	}

	public void setGhes(List<Ghe> ghes) {
		this.ghes = ghes;
	}

	public List<SuatChieu> getSuatChieus() {
		return suatChieus;
	}

	public void setSuatChieus(List<SuatChieu> suatChieus) {
		this.suatChieus = suatChieus;
	}
    
    
}
