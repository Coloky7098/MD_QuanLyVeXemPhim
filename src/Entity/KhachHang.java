package Entity;

import java.util.Objects;

public class KhachHang {
	private Integer maKH;
    private String tenKH;
    private String SDT;
    
	public KhachHang(Integer maKH, String tenKH, String SDT) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.SDT = SDT;
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

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String SDT) {
		this.SDT = SDT;
	}

	@Override
	public String toString() {
		return this.tenKH;
	}
    
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;
	    KhachHang that = (KhachHang) o;
	    return maKH == that.maKH;
	}

	@Override
	public int hashCode() {
	    return Objects.hash(maKH);
	}

}
