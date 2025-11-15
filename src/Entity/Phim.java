package Entity;

import java.time.LocalDate;
import java.util.List;

public class Phim {
	private Integer maPhim;
    private String tenPhim;
    private String moTa;
    private String doTuoi;
    private String quocGia;
    private Integer thoiLuong;
    private String daoDien;
    private LocalDate ngayKhoiChieu;
    private String img;
    private TheLoai theLoai; // Quan hệ
    private List<SuatChieu> listSuatChieu;
    
	public Phim(Integer maPhim, String tenPhim, String moTa, String doTuoi, String quocGia, Integer thoiLuong,
			String daoDien, LocalDate ngayKhoiChieu, String img, TheLoai theLoai) {
		super();
		this.maPhim = maPhim;
		this.tenPhim = tenPhim;
		this.moTa = moTa;
		this.doTuoi = doTuoi;
		this.quocGia = quocGia;
		this.thoiLuong = thoiLuong;
		this.daoDien = daoDien;
		this.ngayKhoiChieu = ngayKhoiChieu;
		this.img = img;
		this.theLoai = theLoai;
	}
	public Phim(Integer maPhim, String tenPhim, List<SuatChieu> listSuatChieu) {
        this.maPhim = maPhim;
        this.tenPhim = tenPhim;
        this.listSuatChieu = listSuatChieu;
    }
    
	public Phim() {
		super();
	}

	public Integer getMaPhim() {
		return maPhim;
	}

	public void setMaPhim(Integer maPhim) {
		this.maPhim = maPhim;
	}

	public String getTenPhim() {
		return tenPhim;
	}

	public void setTenPhim(String tenPhim) {
		this.tenPhim = tenPhim;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public String getDoTuoi() {
		return doTuoi;
	}

	public void setDoTuoi(String doTuoi) {
		this.doTuoi = doTuoi;
	}

	public String getQuocGia() {
		return quocGia;
	}

	public void setQuocGia(String quocGia) {
		this.quocGia = quocGia;
	}

	public Integer getThoiLuong() {
		return thoiLuong;
	}

	public void setThoiLuong(Integer thoiLuong) {
		this.thoiLuong = thoiLuong;
	}

	public String getDaoDien() {
		return daoDien;
	}

	public void setDaoDien(String daoDien) {
		this.daoDien = daoDien;
	}

	public LocalDate getNgayKhoiChieu() {
		return ngayKhoiChieu;
	}

	public void setNgayKhoiChieu(LocalDate ngayKhoiChieu) {
		this.ngayKhoiChieu = ngayKhoiChieu;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public TheLoai getTheLoai() {
		return theLoai;
	}

	public void setTheLoai(TheLoai theLoai) {
		this.theLoai = theLoai;
	}
	
	public List<SuatChieu> getListSuatChieu() {
		return listSuatChieu;
	}
	public void setListSuatChieu(List<SuatChieu> listSuatChieu) {
		this.listSuatChieu = listSuatChieu;
	}
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (!(obj instanceof Phim)) return false;
	    Phim other = (Phim) obj;
	    return this.maPhim != null && this.maPhim.equals(other.maPhim);
	}

	@Override
	public int hashCode() {
	    return maPhim != null ? maPhim.hashCode() : 0;
	}
	@Override
	public String toString() {
	    return tenPhim; 
	}
}
