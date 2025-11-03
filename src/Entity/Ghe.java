package Entity;

public class Ghe {
	private Integer maGhe;
    private String tenGhe;
    private LoaiGhe loaiGhe; // Quan hệ
    private boolean trangThaiDat = true; // true = chua dat, false = da dat
    private PhongChieu phongChieu; // Quan hệ
	
    public Ghe(Integer maGhe, String tenGhe, LoaiGhe loaiGhe, boolean trangThaiDat, PhongChieu phongChieu) {
		super();
		this.maGhe = maGhe;
		this.tenGhe = tenGhe;
		this.loaiGhe = loaiGhe;
		this.trangThaiDat = trangThaiDat;
		this.phongChieu = phongChieu;
	}

	public Ghe() {
		super();
	}

	public Integer getMaGhe() {
		return maGhe;
	}

	public void setMaGhe(Integer maGhe) {
		this.maGhe = maGhe;
	}

	public String getTenGhe() {
		return tenGhe;
	}

	public void setTenGhe(String tenGhe) {
		this.tenGhe = tenGhe;
	}

	public LoaiGhe getLoaiGhe() {
		return loaiGhe;
	}

	public void setLoaiGhe(LoaiGhe loaiGhe) {
		this.loaiGhe = loaiGhe;
	}

	public boolean isTrangThaiDat() {
		return trangThaiDat;
	}

	public void setTrangThaiDat(boolean trangThaiDat) {
		this.trangThaiDat = trangThaiDat;
	}

	public PhongChieu getPhongChieu() {
		return phongChieu;
	}

	public void setPhongChieu(PhongChieu phongChieu) {
		this.phongChieu = phongChieu;
	}
    
    
}
