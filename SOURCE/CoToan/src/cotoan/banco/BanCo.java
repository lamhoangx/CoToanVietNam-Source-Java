package cotoan.banco;

import game.NewGame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import cotoan.util.ManagerBoard;
import cotoan.vanco.VanCo;

public class BanCo extends JPanel implements MouseListener, ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3264375414903194229L;
	public static JLabel jBanCo = new JLabel();
	private static BanCo instanceBanCo;
	public static OCo[][] ViTri = new OCo[10][12];
	public JLabel btnNewGame, btnUndo, btnOpen, btnSave, btnInfo;
	// diem so
	public static JLabel lbDiemQuanTrang, lbDiemQuanDen;
	public static String strDiemQuanTrang = VanCo.iDiemQuanTrang + "",
			strDiemQuanDen = VanCo.iDiemQuanDen + "";
	// ten nguoi choi
	public static JLabel lbTenNguoiChoi1, lbTenNguoiChoi2;
	public static String strTenNguoiChoi1 = "", strTenNguoiChoi2 = "";
	
	// Thoi gian suy nghi
	public static JLabel lbTimer;
	public static String strTimer = "15";
	// Diem cuoc
	public static JLabel lbScore;
	public static String strScore = "30";
	
	// Timer
	public static Timer timer;
	public static JLabel lbTimerPlayer0;
	public static String strTimerPlayer0 = "";
	public static JLabel lbTimerPlayer1;
	public static String strTimerPlayer1 = "";
	public static int iTimerPlayer0 = 15, iTimerPlayer1 = 15;
	
	// Use FileDialog
	JFileChooser fileChooser;
	
	public BanCo() {
		timer = new Timer(1000, this);
		try {
			addControl();
			// Load hinh anh ban co len form
			BufferedImage image = ImageIO.read(new File("data\\Banco.jpg"));
			ImageIcon imgBanCo = new ImageIcon(image.getScaledInstance(753,
					650, Image.SCALE_SMOOTH));
			jBanCo.setIcon(imgBanCo);
			jBanCo.setVisible(true);
			// load cac hinh anh jCanMove len ban co
			for (int i = 0; i <= 8; i++) {
				for (int j = 0; j <= 10; j++) {
					ViTri[i][j] = new OCo();
					ViTri[i][j].Hang = j;
					ViTri[i][j].Cot = i;
					ViTri[i][j].Trong = true;
					ViTri[i][j].Ten = "";
					ViTri[i][j].Phe = 0;
					ViTri[i][j].jCanMove.setBounds(i * 54 + 40, j * 54 + 43,
							28, 28);
					jBanCo.add(ViTri[i][j].jCanMove);
					// ViTri[i][j].jCanMove.setVisible(true);
				}
			}
			this.add(jBanCo);
			VanCo.addChess();
			
			// AddChess();
			VanCo.setVisibleChess(false);
			
			jBanCo.addMouseListener(this);
			btnNewGame.addMouseListener(this);
			btnOpen.addMouseListener(this);
			btnSave.addMouseListener(this);
			btnUndo.addMouseListener(this);
			
			VanCo.isPlaying = 1;
			new Runnable() {
				
				@Override
				public void run() {
					VanCo.isWin();
				}
			};
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		VanCo.changePlayer();
	}
	
	/**
	 * Them cac ten nguoi choi, so diem cuoc ... vao ban co
	 */
	public void addControl() {
		
		// Timer
		Font font = new Font("Courier", Font.BOLD, 18);
		lbTimerPlayer0 = new JLabel();
		lbTimerPlayer0.setBounds(676, 65, 40, 20);
		lbTimerPlayer0.setForeground(Color.WHITE);
		lbTimerPlayer0.setFont(font);
		lbTimerPlayer0.setText(strTimerPlayer0);
		lbTimerPlayer1 = new JLabel();
		lbTimerPlayer1.setBounds(676, 435, 40, 20);
		lbTimerPlayer1.setForeground(Color.WHITE);
		lbTimerPlayer1.setFont(font);
		lbTimerPlayer1.setText(strTimerPlayer1);
		// File Dialog
		fileChooser = new JFileChooser();
		
		// Button
		btnNewGame = new JLabel();
		btnUndo = new JLabel();
		btnOpen = new JLabel();
		btnSave = new JLabel();
		btnInfo = new JLabel();
		
		// diem so cua ban co
		lbDiemQuanDen = new JLabel();
		lbDiemQuanTrang = new JLabel();
		lbDiemQuanDen.setBounds(600, 65, 40, 20);
		lbDiemQuanTrang.setBounds(600, 435, 40, 20);
		lbDiemQuanDen.setForeground(Color.WHITE);
		lbDiemQuanDen.setFont(font);
		lbDiemQuanTrang.setForeground(Color.WHITE);
		lbDiemQuanTrang.setFont(font);
		lbDiemQuanDen.setText(strDiemQuanDen);
		lbDiemQuanTrang.setText(strDiemQuanTrang);
		
		// Ten
		Font fontName = new Font("Courier", Font.BOLD, 20);
		lbTenNguoiChoi1 = new JLabel();
		lbTenNguoiChoi2 = new JLabel();
		lbTenNguoiChoi1.setBounds(630, 40, 50, 20);
		lbTenNguoiChoi2.setBounds(630, 410, 50, 20);
		lbTenNguoiChoi1.setForeground(Color.WHITE);
		lbTenNguoiChoi1.setFont(fontName);
		lbTenNguoiChoi2.setForeground(Color.WHITE);
		lbTenNguoiChoi2.setFont(fontName);
		lbTenNguoiChoi1.setText(strTenNguoiChoi1);
		lbTenNguoiChoi2.setText(strTenNguoiChoi2);
		
		// Timer
		
		lbTimer = new JLabel();
		lbTimer.setBounds(666, 293, 50, 20);
		lbTimer.setForeground(Color.WHITE);
		lbTimer.setFont(fontName);
		lbScore = new JLabel();
		lbScore.setBounds(666, 323, 50, 20);
		lbScore.setForeground(Color.WHITE);
		lbScore.setFont(font);
		drawControl();
		
		jBanCo.add(btnNewGame);
		jBanCo.add(btnOpen);
		jBanCo.add(btnSave);
		jBanCo.add(btnUndo);
		// jBanCo.add(btnInfo);
		
		// Score
		jBanCo.add(lbDiemQuanDen);
		jBanCo.add(lbDiemQuanTrang);
		// name
		jBanCo.add(lbTenNguoiChoi1);
		jBanCo.add(lbTenNguoiChoi2);
		jBanCo.add(lbTimer);
		jBanCo.add(lbScore);
		//
		jBanCo.add(lbTimerPlayer0);
		jBanCo.add(lbTimerPlayer1);
		
	}
	
	/**
	 * Thay doi diem so cua van co
	 */
	public static void changeScore() {
		strDiemQuanDen = VanCo.iDiemQuanDen + "";
		strDiemQuanTrang = VanCo.iDiemQuanTrang + "";
		lbDiemQuanDen.setText(strDiemQuanDen);
		lbDiemQuanTrang.setText(strDiemQuanTrang);
	}
	
	/**
	 * ve cac button newgame, undo, save, open, info vao ban co
	 */
	public void drawControl() {
		try {
			BufferedImage imgCm = ImageIO.read(new File(
					"data\\Newgame_MouseOver.png"));
			ImageIcon imNewGame = new ImageIcon(imgCm.getScaledInstance(30, 30,
					Image.SCALE_SMOOTH));
			btnNewGame.setIcon(imNewGame);
			btnNewGame.setBounds(540, 350, 30, 30);
			// btnNewGame.setLocation(500, 500);
			btnNewGame.setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// btnUndo
		try {
			BufferedImage imgCm = ImageIO.read(new File(
					"data\\Undo_MouseOver.png"));
			ImageIcon CanMove = new ImageIcon(imgCm.getScaledInstance(30, 30,
					Image.SCALE_SMOOTH));
			btnUndo.setIcon(CanMove);
			btnUndo.setBounds(580, 350, 30, 30);
			btnUndo.setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// btnOpen
		try {
			BufferedImage imgCm = ImageIO.read(new File(
					"data\\Open_MouseOver.png"));
			ImageIcon CanMove = new ImageIcon(imgCm.getScaledInstance(30, 30,
					Image.SCALE_SMOOTH));
			btnOpen.setIcon(CanMove);
			btnOpen.setBounds(620, 350, 30, 30);
			btnOpen.setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// btnSave
		try {
			BufferedImage imgCm = ImageIO.read(new File(
					"data\\Save_MouseOver.png"));
			ImageIcon CanMove = new ImageIcon(imgCm.getScaledInstance(30, 30,
					Image.SCALE_SMOOTH));
			btnSave.setIcon(CanMove);
			btnSave.setBounds(660, 350, 30, 30);
			btnSave.setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// // btnInfo
		// try {
		// BufferedImage imgCm = ImageIO.read(new File("data\\Thongtin.png"));
		// ImageIcon CanMove = new ImageIcon(imgCm.getScaledInstance(50, 50,
		// Image.SCALE_SMOOTH));
		// btnNewGame.setIcon(CanMove);
		// btnNewGame.setBounds(54 + 28, 54 + 32, 30, 30);
		// btnNewGame.setVisible(true);
		// jBanCo.add(btnInfo);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}
	
	/**
	 * ve cac button newgame, undo, save, open, info vao ban co luc click chuot
	 */
	public void drawControlMouseEnter(JLabel temp) {
		if (temp == btnNewGame) {
			try {
				BufferedImage imgCm = ImageIO
						.read(new File("data\\Newgame.png"));
				ImageIcon imNewGame = new ImageIcon(imgCm.getScaledInstance(30,
						30, Image.SCALE_SMOOTH));
				btnNewGame.setIcon(imNewGame);
				btnNewGame.setBounds(540, 350, 30, 30);
				// btnNewGame.setLocation(500, 500);
				// btnNewGame.setVisible(true);
				// jBanCo.add(btnNewGame);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// btnUndo
		if (temp == btnUndo) {
			try {
				BufferedImage imgCm = ImageIO.read(new File("data\\Undo.png"));
				ImageIcon CanMove = new ImageIcon(imgCm.getScaledInstance(30,
						30, Image.SCALE_SMOOTH));
				btnUndo.setIcon(CanMove);
				btnUndo.setBounds(580, 350, 30, 30);
				// btnUndo.setVisible(true);
				// jBanCo.add(btnUndo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// btnOpen
		if (temp == btnOpen) {
			try {
				BufferedImage imgCm = ImageIO.read(new File("data\\Open.png"));
				ImageIcon CanMove = new ImageIcon(imgCm.getScaledInstance(30,
						30, Image.SCALE_SMOOTH));
				btnOpen.setIcon(CanMove);
				btnOpen.setBounds(620, 350, 30, 30);
				// btnOpen.setVisible(true);
				// jBanCo.add(btnOpen);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// btnSave
		if (temp == btnSave) {
			try {
				BufferedImage imgCm = ImageIO.read(new File("data\\Save.png"));
				ImageIcon CanMove = new ImageIcon(imgCm.getScaledInstance(30,
						30, Image.SCALE_SMOOTH));
				btnSave.setIcon(CanMove);
				btnSave.setBounds(660, 350, 30, 30);
				// btnSave.setVisible(true);
				// jBanCo.add(btnSave);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// // btnInfo
		// try {
		// BufferedImage imgCm = ImageIO.read(new File("data\\Thongtin.png"));
		// ImageIcon CanMove = new ImageIcon(imgCm.getScaledInstance(50, 50,
		// Image.SCALE_SMOOTH));
		// btnNewGame.setIcon(CanMove);
		// btnNewGame.setBounds(54 + 28, 54 + 32, 30, 30);
		// btnNewGame.setVisible(true);
		// jBanCo.add(btnInfo);
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}
	
	/**
	 * Them cac hinh anh cua quan co vao ban co
	 */
	public void addChess() {
		
		jBanCo.add(VanCo.NguoiChoi[0].q0.jLpicQuanCo);
		jBanCo.add(VanCo.NguoiChoi[0].q1.jLpicQuanCo);
		jBanCo.add(VanCo.NguoiChoi[0].q2.jLpicQuanCo);
		jBanCo.add(VanCo.NguoiChoi[0].q3.jLpicQuanCo);
		jBanCo.add(VanCo.NguoiChoi[0].q4.jLpicQuanCo);
		jBanCo.add(VanCo.NguoiChoi[0].q5.jLpicQuanCo);
		jBanCo.add(VanCo.NguoiChoi[0].q6.jLpicQuanCo);
		jBanCo.add(VanCo.NguoiChoi[0].q7.jLpicQuanCo);
		jBanCo.add(VanCo.NguoiChoi[0].q8.jLpicQuanCo);
		jBanCo.add(VanCo.NguoiChoi[0].q9.jLpicQuanCo);
		jBanCo.add(VanCo.NguoiChoi[1].q0.jLpicQuanCo);
		jBanCo.add(VanCo.NguoiChoi[1].q1.jLpicQuanCo);
		jBanCo.add(VanCo.NguoiChoi[1].q2.jLpicQuanCo);
		jBanCo.add(VanCo.NguoiChoi[1].q3.jLpicQuanCo);
		jBanCo.add(VanCo.NguoiChoi[1].q4.jLpicQuanCo);
		jBanCo.add(VanCo.NguoiChoi[1].q5.jLpicQuanCo);
		jBanCo.add(VanCo.NguoiChoi[1].q6.jLpicQuanCo);
		jBanCo.add(VanCo.NguoiChoi[1].q7.jLpicQuanCo);
		jBanCo.add(VanCo.NguoiChoi[1].q8.jLpicQuanCo);
		jBanCo.add(VanCo.NguoiChoi[1].q9.jLpicQuanCo);
		
	}
	
	public static BanCo getInstance() {
		if (instanceBanCo == null) {
			return instanceBanCo = new BanCo();
		} else
			return instanceBanCo;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JLabel lbRequest = (JLabel) e.getSource();
		if (lbRequest == jBanCo)
			ManagerBoard.resetBoardChess();
		if (lbRequest == btnNewGame) {
			// VanCo.isPlaying = 1;
			// Reset();
			//
			timer.stop();
			NewGame newGame = new NewGame();
			// newGame.setLocation(this.getWidth() / 2 - newGame.getWidth() / 2,
			// (this.getHeight() - 20) / 2 - newGame.getHeight() / 2 - 20);
			// jBanCo.add(newGame);
			newGame.setVisible(true);
			// newGame.setLocationRelativeTo(null);
			// VanCo.AddChess();
			// VanCo.AddChess();
			// VanCo.SetVisibleChess(true);
			ManagerBoard.resetBoardChess();
			// VanCo.AddChess();
			// VanCo.SetVisibleChess(true);
		}
		if (lbRequest == btnUndo) {
			VanCo.undo();
		}
		if (lbRequest == btnSave) {
			ManagerBoard.resetBoardChess();
			timer.stop();
			int returnValue = fileChooser.showSaveDialog(this);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				VanCo.save(file);
			}
			timer.start();
		}
		if (lbRequest == btnOpen) {
			ManagerBoard.reset();
			timer.stop();
			int returnValue = fileChooser.showOpenDialog(this);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				VanCo.open(file.toString());
			}
			timer.start();
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel temp = (JLabel) e.getSource();
		drawControlMouseEnter(temp);
		
		btnNewGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnOpen.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnUndo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnInfo.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		drawControl();
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() instanceof Timer) {
			if (VanCo.LuotDi == 0) {
				iTimerPlayer0--;
				BanCo.lbTimerPlayer0.setText(iTimerPlayer0 + "");
				BanCo.lbTimerPlayer1.setText(iTimerPlayer1 + "");
			}
			if (VanCo.LuotDi == 1) {
				iTimerPlayer1--;
				BanCo.lbTimerPlayer1.setText(iTimerPlayer1 + "");
				BanCo.lbTimerPlayer0.setText(iTimerPlayer0 + "");
			}
			VanCo.isWin();
		}
	}
	
}
