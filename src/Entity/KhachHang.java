package Entity;


public class KhachHang {
	private Integer maKH;
    private String tenKH;
    private String email;
    private String sdt;
    
	public KhachHang(Integer maKH, String tenKH, String email, String sdt) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.email = email;
		this.sdt = sdt;
	}

	public KhachHang() {
		super();
	}

	public Integer getMaKH() {
		return maKH;
	}

	public void setMaKH(Integer maKH) {
		this.maKH = maKH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
    
    
}
