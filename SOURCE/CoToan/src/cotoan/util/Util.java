package cotoan.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import cotoan.banco.BanCo;
import cotoan.player.NguoiChoi;
import cotoan.vanco.VanCo;

public class Util {
	
	/**
	 * Load anh cua CanMove
	 * 
	 * @param url
	 * @param lbImage
	 */
	public static void loadImageCanMove(String url, JLabel lbImage) {
		BufferedImage imgCm;
		try {
			imgCm = ImageIO.read(new File(url));
			ImageIcon CanMove = new ImageIcon(imgCm.getScaledInstance(28, 28,
					Image.SCALE_SMOOTH));
			lbImage.setIcon(CanMove);
			lbImage.setVisible(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Load anh cua quan co
	 * 
	 * @param url
	 *            duong dan
	 * @param lbImage
	 *            Jlable chua anh
	 * @param Cot
	 * @param Hang
	 */
	public static void loadImageChess(String url, JLabel lbImage, int Cot,
			int Hang) {
		try {
			BufferedImage imgCm = ImageIO.read(new File(url));
			ImageIcon CanMove = new ImageIcon(imgCm.getScaledInstance(50, 50,
					Image.SCALE_SMOOTH));
			lbImage.setIcon(CanMove);
			lbImage.setBounds(Cot * 54 + 28, Hang * 54 + 32, 50, 50);
			// jLpicQuanCo.setVisible(false);
			BanCo.jBanCo.add(lbImage);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Hien thong tin thang cuoc
	 * 
	 * @param phe
	 */
	public static void showWin(int phe) {
		if (phe == 0) {
			JOptionPane.showMessageDialog(BanCo.jBanCo, BanCo.strTenNguoiChoi2
					+ " thắng !");
		}
		if (phe == 1) {
			JOptionPane.showMessageDialog(BanCo.jBanCo, BanCo.strTenNguoiChoi1
					+ " thắng !");
		}
		ManagerBoard.lockBoard(VanCo.NguoiChoi);
		BanCo.timer.stop();
	}
	
	/**
	 * Cap nhat lai thong tin cua van co
	 */
	public static void updateInfo() {
		BanCo.strScore = VanCo.iDiemCuoc + "";
		BanCo.lbScore.setText(BanCo.strScore);
		
		BanCo.strTimer = VanCo.iTimer + "";
		BanCo.lbTimer.setText(BanCo.strTimer);
		
		BanCo.lbTenNguoiChoi1.setText(BanCo.strTenNguoiChoi1);
		BanCo.lbTenNguoiChoi2.setText(BanCo.strTenNguoiChoi2);
		
		BanCo.lbDiemQuanDen.setText(VanCo.iDiemQuanDen + "");
		BanCo.lbDiemQuanTrang.setText(VanCo.iDiemQuanTrang + "");
		
		BanCo.lbTimerPlayer0.setText("");
		BanCo.lbTimerPlayer1.setText("");
	}
	
	/**
	 * Khoa toan bo quan co
	 */
	public static void lockChess(NguoiChoi[] NguoiChoi) {
		NguoiChoi[0].q0.Khoa = false;
		NguoiChoi[0].q1.Khoa = false;
		NguoiChoi[0].q2.Khoa = false;
		NguoiChoi[0].q3.Khoa = false;
		NguoiChoi[0].q4.Khoa = false;
		NguoiChoi[0].q5.Khoa = false;
		NguoiChoi[0].q6.Khoa = false;
		NguoiChoi[0].q7.Khoa = false;
		NguoiChoi[0].q8.Khoa = false;
		NguoiChoi[0].q9.Khoa = false;
		
		NguoiChoi[1].q0.Khoa = false;
		NguoiChoi[1].q1.Khoa = false;
		NguoiChoi[1].q2.Khoa = false;
		NguoiChoi[1].q3.Khoa = false;
		NguoiChoi[1].q4.Khoa = false;
		NguoiChoi[1].q5.Khoa = false;
		NguoiChoi[1].q6.Khoa = false;
		NguoiChoi[1].q7.Khoa = false;
		NguoiChoi[1].q8.Khoa = false;
		NguoiChoi[1].q9.Khoa = false;
	}
}
