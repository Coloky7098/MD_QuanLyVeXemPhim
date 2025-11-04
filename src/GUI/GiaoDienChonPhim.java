package GUI;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import DAO.PhimDAO;
import Entity.Phim;
import Entity.TheLoai;


public class GiaoDienChonPhim extends JFrame{
	JPanel filmGridPanel;
	PhimDAO phimDAO = new PhimDAO();
	//load Film demo
//    Phim phim1 = new Phim(1, "Avengers: Hồi Kết"
//    		, "Sau sự kiện hủy diệt tàn khốc, vũ trụ chìm trong cảnh hoang tàn. Với sự trợ giúp của những đồng minh còn sống sót, biệt đội siêu anh hùng Avengers tập hợp một lần nữa để đảo ngược hành động của Thanos và khôi phục lại trật tự của vũ trụ."
//    		, "18+", "Âu Mỹ"
//    		, 180, "Anthony Russo"
//    		, LocalDate.now()
//    		, "/ImgPhim/endgame.jpg", new TheLoai(1, "Hành động", "Hành động"));
//    
//    Phim phim2 = new Phim(2, "One Piece"
//    		, "OP"
//    		, "13+", "Nhật Bản"
//    		, 180, "Goro Taniguchi"
//    		, LocalDate.now()
//    		, "/ImgPhim/onepiece.jpg", new TheLoai(2, "Hoạt hình", "Hoạt hình"));
//    
//    Phim phim3 = new Phim(3, "JJK"
//    		, "JJK"
//    		, "13+", "Nhật Bản"
//    		, 180, "Goro Taniguchi"
//    		, LocalDate.now()
//    		, "/ImgPhim/thaycung.jpg", new TheLoai(2, "Hoạt hình", "Hoạt hình"));
    ArrayList<Phim> listPhim = new ArrayList<Phim>(); 
    
	private static final Dimension CARD_SIZE = new Dimension(220, 420);
	
	public GiaoDienChonPhim() {
		super();
		
		listPhim = phimDAO.getAllPhim();
        
		var len = listPhim.size();
		int columns = 1 + len / 4;
		System.out.print(columns);
		filmGridPanel = new JPanel(new GridLayout(columns, 5, 15, 15));
		
        filmGridPanel.setBackground(Color.WHITE);
        filmGridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Xu ly add Card
        for(Phim phim : listPhim) {
        	filmGridPanel.add(CardPhim(phim));
        }
        
        // 3. Đặt filmGridPanel vào một JScrollPane
        // Điều này rất quan trọng để có thể cuộn (scroll) khi có nhiều phim
        JScrollPane scrollPane = new JScrollPane(filmGridPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(8); // Tăng tốc độ cuộn

        // Thêm tiêu đề "Phim Đang Chiếu"
        JLabel lblTitle = new JLabel("Phim Đang Chiếu");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 0));

        // Bố cục chính của JFrame
        setLayout(new BorderLayout());
        add(lblTitle, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1400, 800);
		setLocationRelativeTo(null); // Căn giữa màn hình
	}

	public JPanel CardPhim(Phim phim) {
		JPanel cardPhim = new JPanel();
		cardPhim.setLayout(new BorderLayout());
		cardPhim.setPreferredSize(CARD_SIZE);
		cardPhim.setMaximumSize(CARD_SIZE); // Quan trọng khi dùng với BoxLayout
		cardPhim.setMinimumSize(CARD_SIZE);
		cardPhim.setBackground(Color.WHITE);
        // Thêm đường viền mỏng
//		cardPhim.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		
		ImageIcon poster = loadAndScaleImage(phim.getImg(), 218, 280);
	
        // Img
        JLabel lblPoster = new JLabel(poster);
        lblPoster.setBounds(0, 0, 218, 280);
        
        cardPhim.add(lblPoster, BorderLayout.CENTER);
        cardPhim.add(createInfoPanel(phim), BorderLayout.SOUTH); // Thêm panel info
		return cardPhim;
	}
	
	private ImageIcon loadAndScaleImage(String path, int width, int height) {
	    try {
	        // SỬA: Dùng getResource() để tìm file trong classpath (src)
	        java.net.URL imgURL = getClass().getResource(path);

	        // SỬA: Kiểm tra xem có tìm thấy tài nguyên không
	        if (imgURL == null) {
	            // Ném lỗi nếu không tìm thấy file
	            throw new Exception("Không tìm thấy tài nguyên: " + path);
	        }

	        // Tải ảnh từ URL
	        ImageIcon originalIcon = new ImageIcon(imgURL);

	        // (Phần còn lại giữ nguyên)
	        if (originalIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
	             throw new Exception("Không tải được ảnh");
	        }

	        Image image = originalIcon.getImage();
	        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	        return new ImageIcon(scaledImage);

	    } catch (Exception e) {
	        System.err.println(e.getMessage()); // In lỗi cụ thể
	        // Trả về một ảnh placeholder khi lỗi
	        return null;
	    }
	}
	
	private Component createInfoPanel(Phim phim) {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT); // Căn lề trái

        // Tên Phim
        JLabel lblTenPhim = new JLabel(phim.getTenPhim());
        lblTenPhim.setFont(new Font("Arial", Font.BOLD, 16));
        lblTenPhim.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblDaoDien = new JLabel("Đạo diễn: " + phim.getDaoDien());
        lblDaoDien.setFont(new Font("Arial", Font.PLAIN, 12));
        lblDaoDien.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Thể loại (Giả sử getTheLoai() trả về đối tượng TheLoai
        // và TheLoai có getTenTheLoai())
        String tenTheLoai = (phim.getTheLoai() != null) ? phim.getTheLoai().getTenTheLoai() : "Chưa xác định";
        JLabel lblTheLoai = new JLabel("Thể loại: " + tenTheLoai);
        lblTheLoai.setFont(new Font("Arial", Font.PLAIN, 12));
        lblTheLoai.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblDoTuoi = new JLabel("Độ tuổi: " + phim.getDoTuoi());
        lblDoTuoi.setFont(new Font("Arial", Font.PLAIN, 12));
        lblDoTuoi.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Thời lượng (Giả sử getThoiLuong() trả về số phút (int))
        JLabel lblThoiLuong = new JLabel("Thời lượng: " + phim.getThoiLuong() + " phút");
        lblThoiLuong.setFont(new Font("Arial", Font.PLAIN, 12));
        lblThoiLuong.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Khởi chiếu (Giả sử getNgayKhoiChieu() trả về LocalDate)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String ngayKC = (phim.getNgayKhoiChieu() != null) ? phim.getNgayKhoiChieu().format(formatter) : "N/A";
        JLabel lblKhoiChieu = new JLabel("Khởi chiếu: " + ngayKC);
        lblKhoiChieu.setFont(new Font("Arial", Font.PLAIN, 12));
        lblKhoiChieu.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblQuocGia = new JLabel("Quốc gia: " + phim.getQuocGia());
        lblQuocGia.setFont(new Font("Arial", Font.PLAIN, 12));
        lblQuocGia.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        infoPanel.add(lblTenPhim);
        infoPanel.add(lblTheLoai);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Khoảng cách
        infoPanel.add(lblDaoDien);
        infoPanel.add(lblDoTuoi);
        infoPanel.add(lblThoiLuong);
        infoPanel.add(lblKhoiChieu);
        infoPanel.add(lblQuocGia);
        
        // Thêm khoảng đệm để đẩy nút bấm xuống đáy (nếu cần)
        infoPanel.add(Box.createVerticalGlue());

        return infoPanel;
    }
}
