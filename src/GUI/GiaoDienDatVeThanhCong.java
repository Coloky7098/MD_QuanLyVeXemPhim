package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.*;

import ConnectDB.ConnectDB;
import DAO.ChiTietHoaDonDAO;

import java.util.List;

import Entity.ChiTietHoaDon;
import Entity.HoaDon;

public class GiaoDienDatVeThanhCong extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JLabel lblIcon, lblTitle, lblSubtitle;
    private JLabel lblMaGD, lblPhuongThuc, lblSoTien, lblSuatChieu, lblNgayChieu
    , lblPhongChieu, lblTenKH, lblTenNV, lblSDTKH;
    private JLabel valMaGD, valPhuongThuc, valSoTien, valSuatChieu, valNgayChieu
    , valPhongChieu, valTenKH, valTenNV, valSDTKH;
    private JButton btnInVe, btnHoanTat;
	    
	HoaDon hoaDon;
	List<ChiTietHoaDon> listCTHD;
	private static final String FILE_PATH = "inVe/";
	
	private static final Color PRI_COLOR = new Color(252, 247, 223);
    private static final Color SEC_COLOR = new Color(253, 252, 241);
    private static final Color RED_COLOR = new Color(212, 54, 37);
    
    private static final Color TEXT_COLOR = Color.BLACK;
    private static final Color BTN_COLOR = Color.WHITE;
    
    private static final Font fontChu = new Font("Segoe UI", Font.BOLD, 14);
    
	public GiaoDienDatVeThanhCong(HoaDon hoaDon) {
		super();
		this.hoaDon = hoaDon;
		loadCTHD();
		
		// ======= ICON + TITLE =======
        JPanel pnlTop = new JPanel(new BorderLayout());
        pnlTop.setBackground(PRI_COLOR);
        pnlTop.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));

        lblIcon = new JLabel("✔", SwingConstants.CENTER);
        lblIcon.setFont(new Font("Segoe UI", Font.BOLD, 70));
        lblIcon.setForeground(new Color(0, 200, 100));

        lblTitle = new JLabel("Thanh Toán Thành Công!", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setForeground(TEXT_COLOR);

        lblSubtitle = new JLabel("Vé của bạn đã được xác nhận", SwingConstants.CENTER);
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblSubtitle.setForeground(TEXT_COLOR);

        pnlTop.add(lblIcon, BorderLayout.NORTH);
        pnlTop.add(lblTitle, BorderLayout.CENTER);
        pnlTop.add(lblSubtitle, BorderLayout.SOUTH);

        // ======= THÔNG TIN HÓA ĐƠN =======
        JPanel pnlInfo = new JPanel(new GridBagLayout());
        pnlInfo.setBackground(SEC_COLOR);
        pnlInfo.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        lblMaGD = makeLabel("Mã hóa đơn");
        lblPhuongThuc = makeLabel("Phương thức");
        lblSoTien = makeLabel("Số tiền");
        lblSuatChieu = makeLabel("Suất chiếu");
        lblNgayChieu = makeLabel("Ngày chiếu");
        lblPhongChieu = makeLabel("Phòng");
        lblTenKH = makeLabel("Tên khách hàng");
        lblSDTKH = makeLabel("Số điện thoại khách hàng");
        lblTenNV = makeLabel("Tên nhân viên");
        
        valMaGD = makeValue(hoaDon.getMaHD() + "");
        valPhuongThuc = makeValue(hoaDon.getPhuongThucThanhToan().getTenPTTT());
        valSoTien = makeValue(String.format("%,.0f đ", hoaDon.tinhTong(listCTHD)));
        valSoTien.setForeground(new Color(255, 130, 0));
        
        valSuatChieu = makeValue(listCTHD.get(0).getVe().getGioChieu() + "");
        valNgayChieu = makeValue(listCTHD.get(0).getVe().getNgayChieu() + "");
        valPhongChieu = makeValue(listCTHD.get(0).getVe().getTenPhongChieu() + "");
        valTenKH = makeValue(hoaDon.getKhachHang().getTenKH() + "");
        valSDTKH = makeValue(hoaDon.getKhachHang().getSDT() + "");
        valTenNV = makeValue(hoaDon.getNhanVien().getTenNhanVien());
        
        int viTri = 0;
        
        gbc.gridx = 0; gbc.gridy = viTri++; pnlInfo.add(lblMaGD, gbc);
        gbc.gridx = 1; pnlInfo.add(valMaGD, gbc);
        
        gbc.gridx = 0; gbc.gridy = viTri++; pnlInfo.add(lblTenKH, gbc);
        gbc.gridx = 1; pnlInfo.add(valTenKH, gbc);
        
        gbc.gridx = 0; gbc.gridy = viTri++; pnlInfo.add(lblSDTKH, gbc);
        gbc.gridx = 1; pnlInfo.add(valSDTKH, gbc);
        
        gbc.gridx = 0; gbc.gridy = viTri++; pnlInfo.add(lblPhuongThuc, gbc);
        gbc.gridx = 1; pnlInfo.add(valPhuongThuc, gbc);
        
        gbc.gridx = 0; gbc.gridy = viTri++; pnlInfo.add(lblSuatChieu, gbc);
        gbc.gridx = 1; pnlInfo.add(valSuatChieu, gbc);
        
        gbc.gridx = 0; gbc.gridy = viTri++; pnlInfo.add(lblNgayChieu, gbc);
        gbc.gridx = 1; pnlInfo.add(valNgayChieu, gbc);
        
        gbc.gridx = 0; gbc.gridy = viTri++; pnlInfo.add(lblPhongChieu, gbc);
        gbc.gridx = 1; pnlInfo.add(valPhongChieu, gbc);

        gbc.gridx = 0; gbc.gridy = viTri++; pnlInfo.add(lblTenNV, gbc);
        gbc.gridx = 1; pnlInfo.add(valTenNV, gbc);
        
        viTri = addDongKe(pnlInfo, gbc, viTri);

        gbc.gridx = 0; gbc.gridy = viTri++; pnlInfo.add(makeLabel("Vé "), gbc);
        gbc.gridx = 1; pnlInfo.add(makeValue(" "), gbc);

       
        for(ChiTietHoaDon cthd : listCTHD) {
            String tenGhe = cthd.getVe().getTenGhe();
            double donGia = cthd.getDonGiaBan();
            
        	gbc.gridx = 0; gbc.gridy = viTri++;
            pnlInfo.add(makeLabel("    • " + tenGhe), gbc);
            gbc.gridx = 1;
            pnlInfo.add(makeValue(String.format("%,.0f đ", donGia)), gbc);
        }
        
        if(hoaDon.getSoLuongBap() > 0) {
        	gbc.gridx = 0; gbc.gridy = viTri++; pnlInfo.add(makeLabel("Bắp x" + hoaDon.getSoLuongBap() ), gbc);
            gbc.gridx = 1; pnlInfo.add(makeValue(String.format("%,.0f đ", hoaDon.tinhGiaBap())), gbc);
        }
        
        if(hoaDon.getSoLuongNuoc() > 0) {
        	gbc.gridx = 0; gbc.gridy = viTri++; pnlInfo.add(makeLabel("Nước x" + hoaDon.getSoLuongNuoc() ), gbc);
            gbc.gridx = 1; pnlInfo.add(makeValue(String.format("%,.0f đ", hoaDon.tinhGiaNuoc())), gbc);
        }
        
        viTri = addDongKe(pnlInfo, gbc, viTri);
        
        gbc.gridx = 0; gbc.gridy = viTri++; pnlInfo.add(lblSoTien, gbc);
        gbc.gridx = 1; pnlInfo.add(valSoTien, gbc);
      
        
        
        // ======= BUTTONS =======
        JPanel pnlBottom = new JPanel();
        pnlBottom.setBackground(PRI_COLOR);
        pnlBottom.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        btnInVe = taoBtn("🖨 In Vé");

        btnHoanTat = taoBtn("Hoàn Tất");
        
        pnlBottom.add(btnInVe);
        pnlBottom.add(Box.createRigidArea(new Dimension(15, 0)));
        pnlBottom.add(btnHoanTat);

        btnHoanTat.addActionListener(this);
        btnInVe.addActionListener(this);

        // ======= ADD TO FRAME =======
        add(pnlTop, BorderLayout.NORTH);
        add(pnlInfo, BorderLayout.CENTER);
        add(pnlBottom, BorderLayout.SOUTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1400, 800);
		setLocationRelativeTo(null);
}

    private JLabel makeLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(fontChu);
        lbl.setForeground(TEXT_COLOR);
        return lbl;
    }

    private JLabel makeValue(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(fontChu);
        lbl.setAlignmentX(Component.RIGHT_ALIGNMENT);
        lbl.setForeground(TEXT_COLOR);
        return lbl;
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		var event = e.getSource();
		if(event.equals(btnHoanTat)) {
			GiaoDienChonPhim frmChonPhim = new GiaoDienChonPhim();
			frmChonPhim.setVisible(true);
			dispose();
		}
		else if(event.equals(btnInVe)) {
			System.out.print("in vé");
			JFrame frame = new JFrame("Vé xem phim");
			TicketPanel ve = new TicketPanel(listCTHD);
			frame.add(ve);
	        frame.pack();
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true);
	        try {
				ve.saveAsImage(FILE_PATH + "ticket_" + listCTHD.get(0).getHoaDon().getMaHD() + ".png");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}
	
	private void loadCTHD() {
		Connection conn = null;
		try {
			conn = ConnectDB.getConnection();
			ChiTietHoaDonDAO cthdDAO = new ChiTietHoaDonDAO(conn);
			this.listCTHD = cthdDAO.layChitiethoadon(hoaDon.getMaHD());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private JButton taoBtn(String tenBtn) {
		JButton btn = new JButton(tenBtn);
        
		btn.setBackground(RED_COLOR);
		btn.setForeground(BTN_COLOR);
		btn.setPreferredSize(new Dimension(200, 50));
		btn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        
		btn.setOpaque(true);
		btn.setBorderPainted(false);
		btn.setContentAreaFilled(true);
		
		return btn;
	}
	
	private int addDongKe(JPanel pnl, GridBagConstraints gbc, int viTri) {
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        
        // Lưu lại các cài đặt GBC cũ
        int originalGridWidth = gbc.gridwidth;
        int originalFill = gbc.fill;
        Insets originalInsets = gbc.insets;

        // Cấu hình GBC cho JSeparator
        gbc.gridx = 0;
        gbc.gridy = viTri;
        gbc.gridwidth = 2; // Cho phép JSeparator kéo dài qua 2 cột
        gbc.fill = GridBagConstraints.HORIZONTAL; // Cho phép JSeparator lấp đầy theo chiều ngang
        gbc.insets = new Insets(10, 0, 10, 0); // Thêm một chút đệm trên và dưới

        pnl.add(separator, gbc);

        // Khôi phục lại các cài đặt GBC cho các component tiếp theo
        gbc.gridwidth = originalGridWidth;
        gbc.fill = originalFill;
        gbc.insets = originalInsets;
        
        // Trả về vị trí (gridy) tiếp theo
        return viTri + 1;
    }
}
