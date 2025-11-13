package GUI;
import javax.swing.*;

import Entity.ChiTietHoaDon;
import Entity.Ve;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SuppressWarnings("serial")
public class TicketPanel extends JPanel {
    private List<ChiTietHoaDon> listCTHD;

    public TicketPanel(List<ChiTietHoaDon> listCTHD) {
        this.listCTHD = listCTHD;
        setPreferredSize(new Dimension(400, 350)); // kích thước vé
    }
    
	private static final Color PRI_COLOR = new Color(252, 247, 223);
    private static final Color SEC_COLOR = new Color(253, 252, 241);
    private static final Color RED_COLOR = new Color(212, 54, 37);
    
    private static final Color TEXT_COLOR = Color.BLACK;
    private static final Color BTN_COLOR = Color.WHITE;
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Khử răng cưa, làm mịn
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Nền vé
        g2.setColor(SEC_COLOR);
        g2.fillRoundRect(10, 10, 380, 320, 30, 30);

        // Viền
        g2.setColor(Color.DARK_GRAY);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(10, 10, 380, 320, 30, 30);

        // Tiêu đề
        g2.setFont(new Font("SansSerif", Font.BOLD, 20));
        g2.setColor(RED_COLOR);
        g2.drawString("🎬 DK CINEMA", 120, 40);

        // Nội dung vé
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("SansSerif", Font.PLAIN, 14));
        int x = 40, y = 70, line = 25;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        String tenGhe = layDanhSachGhe(listCTHD.stream().map(ChiTietHoaDon::getVe).toList());
        Double giaVe = tinhGiaVe(listCTHD);
        
        g2.drawString("Mã: " + listCTHD.get(0).getHoaDon().getMaHD(), x, y);
        
        g2.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{6}, 0));
        g2.drawLine(30, 75, 370, 75);
        
        g2.drawString("Phim: " + listCTHD.get(0).getVe().getSuatChieu().getPhim().getTenPhim(), x, y += line);
        g2.drawString("Ngày chiếu: " + listCTHD.get(0).getVe().getNgayChieu().format(df), x, y += line);
        g2.drawString("Giờ chiếu: " + listCTHD.get(0).getVe().getGioChieu().toString(), x, y += line);
        g2.drawString("Ghế: " + tenGhe, x, y += line);
        g2.drawString("Rạp : " + listCTHD.get(0).getVe().getTenPhongChieu(), x, y += line);
        
        g2.drawString("Giá vé: " + String.format("%,.0f VND", giaVe), x, y += line);

        // Đường gạch đứt (giữa vé)
        g2.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{6}, 0));
        g2.drawLine(30, 200, 370, 200);

        // QR / Mã vạch giả lập
        g2.setColor(Color.GRAY);
        g2.fillRect(280, 230, 90, 90);
    }

    // Xuất panel ra ảnh PNG
    public void saveAsImage(String filePath) throws Exception {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        paint(g2);
        g2.dispose();
        javax.imageio.ImageIO.write(image, "png", new java.io.File(filePath));
    }

    private String layDanhSachGhe(List<Ve> listVe) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listVe.size(); i++) {
            sb.append(listVe.get(i).getTenGhe());
            if (i < listVe.size() - 1) sb.append(", ");
        }
        return sb.toString();
    }
    
    private Double tinhGiaVe(List<ChiTietHoaDon> listCTHD) { 
    	Double tong = 0.0;
    	for(ChiTietHoaDon chiTietHoaDon : listCTHD) {
    		tong += chiTietHoaDon.getDonGiaBan();
    	}
    	return tong;
    }
}
