package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.LayoutManager;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import Entity.Phim;
import Entity.SuatChieu;

public class GiaoDienThanhToan extends JFrame{
	JPanel pNorth, pWest, pCen;
	JLabel lblThanhToan, lblThongTinVe, lblThongTinKH, lblBapNuoc;
	JTextField txtTenKH, txtSDT, txtBap, txtNuoc;
	JButton btnTao, btnQuayLai;
	SuatChieu suatChieuDaChon;
	Set<String> gheDaChon;
	
	LoadHinhAnh load = new LoadHinhAnh();
	
	private final String imgBap = "/img/bap.jpg";
	private final String imgNuoc = "/img/nuoc.jpg";
	
	private final Color COLOR_BG = Color.WHITE;
	private final Font fontTieuDe = new Font("Arial", Font.BOLD, 19);
	private final Dimension POSTER_SIZE = new Dimension(260, 350);
	private final Dimension BAP_NUOC_SIZE = new Dimension(50, 50);
	
	public GiaoDienThanhToan(Set<String> ghe, SuatChieu suatChieu) {
		this.suatChieuDaChon = suatChieu;
		this.gheDaChon = ghe;
		
		MenuChinh menuBar = new MenuChinh(this);
		this.setJMenuBar(menuBar);
		MenuToggleUtil.addToggleSupport(this, menuBar);
		
		
		pNorth = new JPanel();
		lblThanhToan = new JLabel("Thanh toán");
		pNorth.add(lblThanhToan);
		
		pWest = createInfoPanel();
		
		pCen = panelThanhToan();
		pCen.setBackground(COLOR_BG);
		
		add(pNorth, BorderLayout.NORTH);
		add(pWest, BorderLayout.WEST);
		add(pCen, BorderLayout.CENTER);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1400, 800);
		setLocationRelativeTo(null); // Căn giữa màn hình
	}
	
	private JPanel createInfoPanel() {
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(320, 0));
        p.setBackground(COLOR_BG);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(new EmptyBorder(10, 20, 10, 12));

        lblThongTinVe = new JLabel("Thông tin vé:");
        lblThongTinVe.setFont(fontTieuDe);
        p.add(lblThongTinVe);
        p.add(Box.createRigidArea(new Dimension(0, 10)));

        // ======= Poster phim =======
        JLabel poster = new JLabel("<html><center>Poster<br>không có</center></html>", JLabel.CENTER);
        poster.setPreferredSize(new Dimension(260, 350));
        poster.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        poster.setHorizontalAlignment(SwingConstants.CENTER);

        if (suatChieuDaChon != null && suatChieuDaChon.getPhim() != null && suatChieuDaChon.getPhim().getImg() != null) {
        	poster.setIcon(load.taiHinhAnh(suatChieuDaChon.getPhim().getImg(), POSTER_SIZE.width, POSTER_SIZE.height));
        	poster.setText("");
        }
        
        p.add(poster);
        p.add(Box.createRigidArea(new Dimension(0, 12)));

        // ======= Thông tin phim =======
        Phim phim = (suatChieuDaChon != null) ? suatChieuDaChon.getPhim() : null;
        String tenPhim = (phim != null && phim.getTenPhim() != null) ? phim.getTenPhim() : "Tên phim";
        String daoDien = (phim != null && phim.getDaoDien() != null) ? phim.getDaoDien() : "—";
        String doTuoi = (phim != null && phim.getDoTuoi() != null) ? phim.getDoTuoi() : "—";
        int thoiLuong = (phim != null && phim.getThoiLuong() > 0) ? phim.getThoiLuong() : 0;
        String gioChieu = (suatChieuDaChon != null && suatChieuDaChon.getGioChieu() != null)
                ? suatChieuDaChon.getGioChieu().toString()
                : "";

        JLabel lblTitle = new JLabel(tenPhim);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(lblTitle);
        p.add(Box.createRigidArea(new Dimension(0, 6)));

        JLabel lblSuat = new JLabel("Suất: " + suatChieuDaChon.getGioChieu().toString()+ "   " + suatChieuDaChon.getNgayChieu().toString());
        lblSuat.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(lblSuat);

        JLabel lblDaoDien = new JLabel("Đạo diễn: " + daoDien);
        lblDaoDien.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(lblDaoDien);

        JLabel lblDoTuoi = new JLabel("Độ tuổi: " + doTuoi);
        lblDoTuoi.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(lblDoTuoi);

        if (thoiLuong > 0) {
            JLabel lblThoiLuong = new JLabel("Thời lượng: " + thoiLuong + " phút");
            lblThoiLuong.setAlignmentX(Component.LEFT_ALIGNMENT);
            p.add(lblThoiLuong);
        }
        
        String tenGhe = String.join(", ", gheDaChon);
        JLabel lblGhe = new JLabel("Ghế: " + tenGhe);
        lblGhe.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(lblGhe);
        
        p.add(Box.createRigidArea(new Dimension(0, 10))); // Khoảng cách trước

	     // Tạo panel chứa đường kẻ
	    JPanel separatorPanel = new JPanel();
	    separatorPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
	    separatorPanel.setBackground(Color.LIGHT_GRAY);
	    separatorPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    p.add(separatorPanel);
	
	    p.add(Box.createRigidArea(new Dimension(0, 10))); // Khoảng cách sau

        var tongTien = gheDaChon.size()*suatChieuDaChon.getGiaVeCoBan();
        JLabel lblTotal = new JLabel("Tổng cộng: " + String.valueOf(Math.round(tongTien)));
        lblTotal.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblTotal.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        
        p.add(lblTotal);

        p.add(Box.createVerticalGlue());

        return p;
    }

	private JPanel panelThanhToan() {
		JPanel pnlThanhToan = new JPanel();
		pnlThanhToan.setLayout(new BoxLayout(pnlThanhToan, BoxLayout.Y_AXIS));
		pnlThanhToan.setBorder(new EmptyBorder(10, 12, 10, 12));
//		pnlThanhToan.setSize(Integer.MAX_VALUE, 500);
		
		lblThongTinKH = new JLabel("Thông tin khách hàng: ");
		lblThongTinKH.setFont(fontTieuDe);
		lblThongTinKH.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		Box boxTenKH = Box.createHorizontalBox();
		JLabel lblTenKH = new JLabel("Tên khách hàng: ");
		txtTenKH = new JTextField(10);
		boxTenKH.add(lblTenKH);
		boxTenKH.add(txtTenKH);
		
		Box boxSDT = Box.createHorizontalBox();
		JLabel lblSDT = new JLabel("Số điện thoại khách hàng: ");
		txtSDT = new JTextField(10);
		boxSDT.add(lblSDT);
		boxSDT.add(txtSDT);
		
		lblBapNuoc = new JLabel("Bắp nước:");
		lblBapNuoc.setFont(fontTieuDe);
		lblBapNuoc.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		Box boxBap = Box.createHorizontalBox();
		JLabel lblImgBap = new JLabel(load.taiHinhAnh(imgBap, BAP_NUOC_SIZE.width, BAP_NUOC_SIZE.height));
		JLabel lblBap = new JLabel("Số lượng bắp: ");
		txtBap = new JTextField(10);
		boxBap.add(lblImgBap);
		boxBap.add(lblBap);
		boxBap.add(txtBap);
		
		Box boxNuoc = Box.createHorizontalBox();
		JLabel lblImgNuoc = new JLabel(load.taiHinhAnh(imgNuoc, BAP_NUOC_SIZE.width, BAP_NUOC_SIZE.height));
		JLabel lblNuoc = new JLabel("Số lượng nước: ");
		txtNuoc = new JTextField(10);
		boxNuoc.add(lblImgNuoc);
		boxNuoc.add(lblNuoc);
		boxNuoc.add(txtNuoc);
		
		
		JPanel pnlNut = new JPanel();
		btnTao = new JButton("Tạo đơn hàng");
		btnQuayLai = new JButton("Hủy");
		pnlNut.add(btnQuayLai);
		pnlNut.add(btnTao);
		
		
		

		Dimension size = lblSDT.getPreferredSize();
		lblTenKH.setPreferredSize(size);
		lblBap.setPreferredSize(size);
		lblNuoc.setPreferredSize(size);
		
		pnlThanhToan.add(lblThongTinKH);     
		pnlThanhToan.add(Box.createRigidArea(new Dimension(0, 10)));
		pnlThanhToan.add(boxTenKH);
		pnlThanhToan.add(boxSDT);
		pnlThanhToan.add(Box.createRigidArea(new Dimension(0, 10)));
		pnlThanhToan.add(lblBapNuoc);
		pnlThanhToan.add(Box.createRigidArea(new Dimension(0, 10)));
		pnlThanhToan.add(boxBap);
		pnlThanhToan.add(boxNuoc);
		pnlThanhToan.add(pnlNut);
		pnlThanhToan.add(Box.createRigidArea(new Dimension(0, 300))); // Khoảng cách sau
		
		pnlThanhToan.add(Box.createVerticalGlue());
		return pnlThanhToan;
	}
}
