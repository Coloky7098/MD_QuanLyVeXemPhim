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
import Entity.Ve;

public class GiaoDienDatVeThanhCong extends JFrame implements ActionListener{
	private JLabel lblIcon, lblTitle, lblSubtitle;
    private JLabel lblMaGD, lblPhuongThuc, lblSoTien, lblGhe, lblSuatChieu, lblNgayChieu, lblPhongChieu;
    private JLabel valMaGD, valPhuongThuc, valSoTien, valGhe, valSuatChieu, valNgayChieu, valPhongChieu;
    private JButton btnInVe, btnHoanTat;
	    
	HoaDon hoaDon;
	List<ChiTietHoaDon> listCTHD;
	
	private static final Color PRI_COLOR = new Color(252, 247, 223);
    private static final Color SEC_COLOR = new Color(253, 252, 241);
    private static final Color RED_COLOR = new Color(212, 54, 37);
    
    private static final Color TEXT_COLOR = Color.BLACK;
    private static final Color BTN_COLOR = Color.WHITE;
    
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

        lblMaGD = makeLabel("Mã giao dịch");
        lblPhuongThuc = makeLabel("Phương thức");
        lblSoTien = makeLabel("Số tiền");
        lblSuatChieu = makeLabel("Suất chiếu");
        lblNgayChieu = makeLabel("Ngày chiếu");
        lblPhongChieu = makeLabel("Phòng");
        lblGhe = makeLabel("Ghế");
        
                
        valMaGD = makeValue(hoaDon.getMaHD() + "");
        valPhuongThuc = makeValue(hoaDon.getPhuongThucThanhToan().getTenPTTT());
        valSoTien = makeValue(String.format("%,.0f đ", hoaDon.tinhTong(listCTHD)));
        valSoTien.setForeground(new Color(255, 130, 0));
        
        valGhe = makeValue(layDanhSachGhe(listCTHD.stream().map(ChiTietHoaDon::getVe).toList()));
        valSuatChieu = makeValue(listCTHD.get(0).getVe().getGioChieu() + "");
        valNgayChieu = makeValue(listCTHD.get(0).getVe().getNgayChieu() + "");
        valPhongChieu = makeValue(listCTHD.get(0).getVe().getTenPhongChieu() + "");
        
        gbc.gridx = 0; gbc.gridy = 0; pnlInfo.add(lblMaGD, gbc);
        gbc.gridx = 1; pnlInfo.add(valMaGD, gbc);
        gbc.gridx = 0; gbc.gridy = 1; pnlInfo.add(lblPhuongThuc, gbc);
        gbc.gridx = 1; pnlInfo.add(valPhuongThuc, gbc);
        gbc.gridx = 0; gbc.gridy = 3; pnlInfo.add(lblGhe, gbc);
        gbc.gridx = 1; pnlInfo.add(valGhe, gbc);
        gbc.gridx = 0; gbc.gridy = 4; pnlInfo.add(lblSuatChieu, gbc);
        gbc.gridx = 1; pnlInfo.add(valSuatChieu, gbc);
        gbc.gridx = 0; gbc.gridy = 5; pnlInfo.add(lblNgayChieu, gbc);
        gbc.gridx = 1; pnlInfo.add(valNgayChieu, gbc);
        gbc.gridx = 0; gbc.gridy = 6; pnlInfo.add(lblPhongChieu, gbc);
        gbc.gridx = 1; pnlInfo.add(valPhongChieu, gbc);
        gbc.gridx = 0; gbc.gridy = 7; pnlInfo.add(lblSoTien, gbc);
        gbc.gridx = 1; pnlInfo.add(valSoTien, gbc);
        

        // ======= BUTTONS =======
        JPanel pnlBottom = new JPanel();
        pnlBottom.setBackground(PRI_COLOR);
        pnlBottom.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        btnInVe = new JButton("🖨 In Vé");
        
        btnInVe.setBackground(RED_COLOR);
        btnInVe.setForeground(BTN_COLOR);
        btnInVe.setPreferredSize(new Dimension(200, 50));
        btnInVe.setFont(new Font("Segoe UI", Font.BOLD, 20));
        
        btnInVe.setOpaque(true);
        btnInVe.setBorderPainted(false);
        btnInVe.setContentAreaFilled(true);

        btnHoanTat = new JButton("Hoàn Tất");
        
        btnHoanTat.setBackground(RED_COLOR);
        btnHoanTat.setForeground(BTN_COLOR);
        btnHoanTat.setPreferredSize(new Dimension(200, 50));
        btnHoanTat.setFont(new Font("Segoe UI", Font.BOLD, 20));
        
        btnHoanTat.setOpaque(true);
        btnHoanTat.setBorderPainted(false);
        btnHoanTat.setContentAreaFilled(true);
//        btnHoanTat.setFocusPainted(false);

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
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lbl.setForeground(TEXT_COLOR);
        return lbl;
    }

    private JLabel makeValue(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lbl.setAlignmentX(Component.RIGHT_ALIGNMENT);
        lbl.setForeground(TEXT_COLOR);
        return lbl;
    }

    private String layDanhSachGhe(List<Ve> listVe) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listVe.size(); i++) {
            sb.append(listVe.get(i).getTenGhe());
            if (i < listVe.size() - 1) sb.append(", ");
        }
        return sb.toString();
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
		if(event.equals(btnInVe)) {
			System.out.print("in vé");
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
    
}
