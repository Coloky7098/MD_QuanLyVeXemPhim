package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConnectDB.ConnectDB;
import Entity.PhongChieu;

public class PhongChieuDAO {
	Connection conn;
	
	public PhongChieuDAO(Connection conn) {
		this.conn = conn;
	}
	public List<PhongChieu> getAllPhongChieu() {
	    List<PhongChieu> list = new ArrayList<>();
	    String sql = "SELECT * FROM phong_chieu";
	    try (PreparedStatement pst = conn.prepareStatement(sql);
	         ResultSet rs = pst.executeQuery()) {
	        while (rs.next()) {
	            PhongChieu pc = new PhongChieu();
	            pc.setMaPhongChieu(rs.getInt("maPhongChieu"));
	            pc.setTenPhong(rs.getString("tenPhong"));
	            pc.setSoLuongGhe(rs.getInt("soLuongGhe"));
	            list.add(pc);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}

	public PhongChieu layPhongChieuBangMaPhongChieu(int maPhongChieu) {
		String sql = "SELECT * FROM phong_chieu WHERE maPhongChieu = ?";
	    PhongChieu phongChieu = null;

	    try (PreparedStatement pst = conn.prepareStatement(sql)) {

	        pst.setInt(1, maPhongChieu);

	        try (ResultSet rs = pst.executeQuery()) {
	            if (rs.next()) {
	                phongChieu = new PhongChieu();
	                phongChieu.setMaPhongChieu(rs.getInt("maPhongChieu"));
	                phongChieu.setTenPhong(rs.getString("tenPhong"));
	                phongChieu.setSoLuongGhe(rs.getInt("soLuongGhe"));
	                return phongChieu;
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return phongChieu;
	}
	public boolean insertPhong(PhongChieu pc) {
	    String sql = "INSERT INTO phong_chieu (tenPhong, soLuongGhe) VALUES (?, ?)";
	    try (PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, pc.getTenPhong());
	        ps.setInt(2, pc.getSoLuongGhe());
	        return ps.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public boolean updatePhong(PhongChieu pc) {
	    String sql = "UPDATE phong_chieu SET tenPhong=?, soLuongGhe=? WHERE maPhongChieu=?";
	    try (PreparedStatement ps = conn.prepareStatement(sql)) { 
	        ps.setString(1, pc.getTenPhong());
	        ps.setInt(2, pc.getSoLuongGhe());
	        ps.setInt(3, pc.getMaPhongChieu());
	        return ps.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public boolean deletePhong(int maPhong) {
	    String sql = "DELETE FROM phong_chieu WHERE maPhongChieu=?";
	    try (PreparedStatement ps = conn.prepareStatement(sql)) { 
	        ps.setInt(1, maPhong);
	        return ps.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}
