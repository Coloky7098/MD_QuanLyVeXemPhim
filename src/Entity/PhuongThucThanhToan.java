package Entity;

import java.util.Objects;

public class PhuongThucThanhToan {
	private int maPTTT;
	private String tenPTTT;
    private String moTa;
    private double phiGiaoDich;
    
	public PhuongThucThanhToan(int maPTTT, String tenPTTT, String moTa, double phiGiaoDich) {
		super();
		this.maPTTT = maPTTT;
		this.tenPTTT = tenPTTT;
		this.moTa = moTa;
		this.phiGiaoDich = phiGiaoDich;
	}
    
	public PhuongThucThanhToan() {
		super();
	}

	public int getMaPTTT() {
		return maPTTT;
	}

	public void setMaPTTT(int maPTTT) {
		this.maPTTT = maPTTT;
	}

	public String getTenPTTT() {
		return tenPTTT;
	}

	public void setTenPTTT(String tenPTTT) {
		this.tenPTTT = tenPTTT;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public double getPhiGiaoDich() {
		return phiGiaoDich;
	}

	public void setPhiGiaoDich(double phiGiaoDich) {
		this.phiGiaoDich = phiGiaoDich;
	}
	
	@Override
	public String toString() {
		return this.tenPTTT;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (!(o instanceof PhuongThucThanhToan)) return false;
	    PhuongThucThanhToan p = (PhuongThucThanhToan) o;
	    return maPTTT == p.maPTTT;
	}

	@Override
	public int hashCode() {
	    return Objects.hash(maPTTT);
	}

}
