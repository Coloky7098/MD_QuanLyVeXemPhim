package DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import ConnectDB.ConnectDB;
import Entity.Ve;

public class VeDAO {
//	
//	public boolean taoVe(Ve ve) {
//		String sql = "INSERT INTO ve (maSuatChieu, maGhe, giaVe, ngayDat, ngayChieu, gioChieu, tenGhe, tenPhongChieu) " +
//                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//        
//        try (Connection conn = ConnectDB.getConnection();
//        		PreparedStatement pst = conn.prepareStatement(sql)){
//        	
//        	pst.setInt(1, ve.getSuatChieu().getMaSuatChieu());
//        	pst.setInt(2, ve.getGhe().getMaGhe());
//        	pst.setDouble(3, ve.getGiaVe());
//        	pst.setDate(4, Date.valueOf(ve.getNgayDat()));
//        	pst.setDate(5, Date.valueOf(ve.getNgayChieu()));
//        	pst.setTime(6, Time.valueOf(ve.getGioChieu()));
//        	pst.setString(7, ve.getTenGhe());
//        	pst.setString(8, ve.getTenPhongChieu());
//        	
//        	int rowsAffected = pst.executeUpdate();
//            
//            return rowsAffected > 0;
//            
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.err.println("Lỗi khi tạo vé: " + e.getMessage());
//            return false;
//        }
//    }
	
	public boolean taoSetVe(List<Ve> listVe) {
	    
	    // Nếu set rỗng thì không làm gì cả
	    if (listVe == null || listVe.isEmpty()) {
	        return true; // Hoặc false tùy logic của bạn, nhưng không có lỗi
	    }
	    
	    String sql = "INSERT INTO ve (maSuatChieu, maGhe, giaVe, ngayDat, ngayChieu, gioChieu, tenGhe, tenPhongChieu) " +
	                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	    
	    try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement pst = conn.prepareStatement(sql)) {

	        try {
	            conn.setAutoCommit(false);

	            for (Ve ve : listVe) {
	                pst.setInt(1, ve.getSuatChieu().getMaSuatChieu());
	                pst.setInt(2, ve.getGhe().getMaGhe());
	                pst.setDouble(3, ve.getGiaVe());
	                pst.setDate(4, Date.valueOf(ve.getNgayDat()));
	                pst.setDate(5, Date.valueOf(ve.getNgayChieu()));
	                pst.setTime(6, Time.valueOf(ve.getGioChieu()));
	                pst.setString(7, ve.getTenGhe());
	                pst.setString(8, ve.getTenPhongChieu());

	                pst.addBatch();
	            }

	            int[] results = pst.executeBatch();
	            
	            // (Kiểm tra tùy chọn: xem tất cả các hàng có thành công không)
	            // Ví dụ: kiểm tra xem có hàng nào trả về 0 hoặc lỗi không
	            for (int r : results) {
	                if (r <= 0 && r != Statement.SUCCESS_NO_INFO) {
	                    // Nếu một hàng bị lỗi (trả về 0), ném ra lỗi để rollback
	                    throw new SQLException("Một vé trong lô không thể tạo, trả về số hàng: " + r);
	                }
	            }

	            // 5. Nếu mọi thứ thành công: Commit (lưu) Transaction
	            conn.commit();
	            return true;

	        } catch (SQLException e) {
	            // 6. Nếu có bất kỳ lỗi nào xảy ra: Rollback (hoàn tác) Transaction
	            e.printStackTrace();
	            System.err.println("Lỗi khi tạo loạt vé, đang rollback...");
	            if (conn != null) {
	                conn.rollback(); // Hoàn tác tất cả thay đổi
	            }
	            return false;
	        }
	        // (Không cần finally để setAutoCommit(true) 
	        //  vì conn sẽ bị đóng bởi try-with-resources)

	    } catch (SQLException e) {
	        // Lỗi này xảy ra nếu không thể kết nối hoặc chuẩn bị câu lệnh
	        e.printStackTrace();
	        System.err.println("Lỗi khi chuẩn bị tạo vé: " + e.getMessage());
	        return false;
	    }
	}
}
