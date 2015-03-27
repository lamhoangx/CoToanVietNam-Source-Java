package cotoan.quanco;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;

import cotoan.banco.BanCo;
import cotoan.util.ManagerBoard;
import cotoan.util.Util;
import cotoan.vanco.VanCo;

public abstract class QuanCo implements MouseListener {
	public int Hang;
	public int Cot;
	public String Ten;
	public int Phe;
	public int Giatri;
	public int TrangThai;
	public JLabel jLpicQuanCo = new JLabel();
	public boolean Khoa = false;
	public static boolean turn = false;
	// Cac gia tri de tinh toan nuoc di
	private int ch = 0, c = 0, t = 0, n = 0, d = 0; // chia, cong, tru, nhan, du
	// gia tri quan dem, gia tri quan tham chieu den, gia tri det qua cong,
	// chia, nhan, tru, du
	public int index1 = 0, index2 = 0, result_Add = 0, result_Add_Div = 0,
			result_Add_Mul = 0, result_Sub = 0, result_Du = 0;
	/**
	 * Quan co dang duoc thao tac
	 */
	public static QuanCo DanhDau;
	
	/**
	 * Nuoc di cua quan co
	 * 
	 * @author LamHX
	 * 
	 */
	public static class _MoveAI {
		public int movei;
		public int movej;
		
		public _MoveAI() {
			movei = movej = 0;
		}
	}
	
	/**
	 * Nuoc di thu cua quan co
	 * 
	 * @author LamHX
	 * 
	 */
	public static class TryTestMove {
		public QuanCo q;
		public int movei, movej;
		
		public TryTestMove() {
			q = null;
			movei = movej = 0;
		}
	}
	
	public ArrayList<_MoveAI> moveAI = new ArrayList<>();// mang chua cac
	// nuoc di cua
	// quan co
	public _MoveAI moveai;
	public int cout_MoveAI = 0; // so nuoc phat sinh
	public int coutMoveAll = 0;// dem toan bo nuoc co sinh ra tu 1 the co
	
	public QuanCo() {
		Hang = 9;
		Cot = 11;
		Ten = "";
		Phe = 2;
		Giatri = 0;
		TrangThai = 0;
		Khoa = true;
		jLpicQuanCo.addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (this.Khoa == false) {// kiem tra dieu kien khoa cua quan co
			if (this.TrangThai == 1) { // Kiem tra quan co click vao dang con
										// song
										// (chua bi an)
				// DrawChessSelect();
				// System.out.println("Su kien click vao quan co " + this.Ten
				// + " Phe " + this.Phe);
				DanhDau = this;
				
				System.out.println("Quan Co duoc danh dau : " + DanhDau.Ten
						+ " Phe " + this.Phe + "Vi tri: " + this.Cot + "Hang "
						+ this.Hang);
				
				for (int i = 0; i <= 8; i++) {
					for (int j = 0; j <= 10; j++) {
						// if (BanCo.ViTri[i][j].Trong == true) {
						// System.out.println("O[" + i + "][" + j + "] trong ");
						// }
						// if (BanCo.ViTri[i][j].Trong == false) {
						// System.out.println("O[" + i + "][" + j +
						// "] khong trong ");
						// }
						if (i != Cot || j != Hang)
							if (checkChess(i, j) == 1) {
								
								BanCo.ViTri[i][j].jCanMove.setVisible(true);
							}
					}
				}
			}
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		if (this.Khoa == false)
			jLpicQuanCo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		// if (this.TrangThai == 1)
		// DrawChessStart();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		ManagerBoard.resetBoardChess();
		// System.out.println("Pressed Moussed");
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// OCo.ResetBanCo();
		// System.out.println("Pressed Released.");
		
	}
	
	/**
	 * Ham lay mang cac quan co tren ban co
	 * 
	 * @return
	 */
	public static ArrayList<QuanCo> getListChess() {
		ArrayList<QuanCo> listChess1 = new ArrayList<>();
		listChess1.add(VanCo.NguoiChoi[0].q0);
		listChess1.add(VanCo.NguoiChoi[0].q1);
		listChess1.add(VanCo.NguoiChoi[0].q2);
		listChess1.add(VanCo.NguoiChoi[0].q3);
		listChess1.add(VanCo.NguoiChoi[0].q4);
		listChess1.add(VanCo.NguoiChoi[0].q5);
		listChess1.add(VanCo.NguoiChoi[0].q6);
		listChess1.add(VanCo.NguoiChoi[0].q7);
		listChess1.add(VanCo.NguoiChoi[0].q8);
		listChess1.add(VanCo.NguoiChoi[0].q9);
		
		listChess1.add(VanCo.NguoiChoi[1].q0);
		listChess1.add(VanCo.NguoiChoi[1].q1);
		listChess1.add(VanCo.NguoiChoi[1].q2);
		listChess1.add(VanCo.NguoiChoi[1].q3);
		listChess1.add(VanCo.NguoiChoi[1].q4);
		listChess1.add(VanCo.NguoiChoi[1].q5);
		listChess1.add(VanCo.NguoiChoi[1].q6);
		listChess1.add(VanCo.NguoiChoi[1].q7);
		listChess1.add(VanCo.NguoiChoi[1].q8);
		listChess1.add(VanCo.NguoiChoi[1].q9);
		
		return listChess1;
		
	}
	
	/**
	 * Xac dinh quan co tai vi tri[i][j]
	 * 
	 * @param hang
	 * @param cot
	 * @return
	 */
	public static QuanCo getChess(int cot, int hang) {
		int index = 2;
		if (DanhDau.Phe == 0)
			index = 1;
		else
			index = 0;
		QuanCo temp = null;
		
		if (BanCo.ViTri[cot][hang].Ten == "0")
			temp = VanCo.NguoiChoi[index].q0;
		if (BanCo.ViTri[cot][hang].Ten == "1")
			temp = VanCo.NguoiChoi[index].q1;
		if (BanCo.ViTri[cot][hang].Ten == "2")
			temp = VanCo.NguoiChoi[index].q2;
		if (BanCo.ViTri[cot][hang].Ten == "3")
			temp = VanCo.NguoiChoi[index].q3;
		if (BanCo.ViTri[cot][hang].Ten == "4")
			temp = VanCo.NguoiChoi[index].q4;
		if (BanCo.ViTri[cot][hang].Ten == "5")
			temp = VanCo.NguoiChoi[index].q5;
		if (BanCo.ViTri[cot][hang].Ten == "6")
			temp = VanCo.NguoiChoi[index].q6;
		if (BanCo.ViTri[cot][hang].Ten == "7")
			temp = VanCo.NguoiChoi[index].q7;
		if (BanCo.ViTri[cot][hang].Ten == "8")
			temp = VanCo.NguoiChoi[index].q8;
		if (BanCo.ViTri[cot][hang].Ten == "9")
			temp = VanCo.NguoiChoi[index].q9;
		
		return temp;
	}
	
	/**
	 * Khoi tao quan co
	 * 
	 * @param phe
	 * @param giatri
	 * @param name
	 * @param stt
	 * @param khoa
	 * @param hang
	 * @param cot
	 */
	public void createChess(int phe, int giatri, String name, int stt,
			boolean khoa, int hang, int cot) {
		Hang = hang;
		Cot = cot;
		Ten = name;
		TrangThai = stt;
		Phe = phe;
		Giatri = giatri;
		Khoa = khoa;
		
	}
	
	/**
	 * Thiet lap quan co len ban co
	 */
	public void drawChess() {
		
		drawChessStart();
		
		// thiet lap quan tren ban co
		BanCo.ViTri[Cot][Hang].Hang = Hang;
		BanCo.ViTri[Cot][Hang].Cot = Cot;
		BanCo.ViTri[Cot][Hang].Trong = false;
		BanCo.ViTri[Cot][Hang].Ten = Ten;
		BanCo.ViTri[Cot][Hang].Phe = Phe;
	}
	
	/**
	 * Ve quan co
	 */
	public void drawChessStart() {
		if (Phe == 0) {
			if (Ten == "0") {
				Util.loadImageChess("data\\Trang0.png", jLpicQuanCo, Cot, Hang);
			}
			if (Ten == "1") {
				
				Util.loadImageChess("data\\Trang1.png", jLpicQuanCo, Cot, Hang);
			}
			if (Ten == "2") {
				Util.loadImageChess("data\\Trang2.png", jLpicQuanCo, Cot, Hang);
			}
			if (Ten == "3") {
				Util.loadImageChess("data\\Trang3.png", jLpicQuanCo, Cot, Hang);
			}
			if (Ten == "4") {
				Util.loadImageChess("data\\Trang4.png", jLpicQuanCo, Cot, Hang);
			}
			if (Ten == "5") {
				Util.loadImageChess("data\\Trang5.png", jLpicQuanCo, Cot, Hang);
			}
			if (Ten == "6") {
				Util.loadImageChess("data\\Trang6.png", jLpicQuanCo, Cot, Hang);
			}
			if (Ten == "7") {
				Util.loadImageChess("data\\Trang7.png", jLpicQuanCo, Cot, Hang);
			}
			if (Ten == "8") {
				Util.loadImageChess("data\\Trang8.png", jLpicQuanCo, Cot, Hang);
			}
			if (Ten == "9") {
				Util.loadImageChess("data\\Trang9.png", jLpicQuanCo, Cot, Hang);
			}
		}
		if (Phe == 1) {
			if (Ten == "0") {
				Util.loadImageChess("data\\Den0.png", jLpicQuanCo, Cot, Hang);
			}
			if (Ten == "1") {
				Util.loadImageChess("data\\Den1.png", jLpicQuanCo, Cot, Hang);
			}
			if (Ten == "2") {
				Util.loadImageChess("data\\Den2.png", jLpicQuanCo, Cot, Hang);
			}
			if (Ten == "3") {
				Util.loadImageChess("data\\Den3.png", jLpicQuanCo, Cot, Hang);
			}
			if (Ten == "4") {
				Util.loadImageChess("data\\Den4.png", jLpicQuanCo, Cot, Hang);
			}
			if (Ten == "5") {
				Util.loadImageChess("data\\Den5.png", jLpicQuanCo, Cot, Hang);
			}
			if (Ten == "6") {
				Util.loadImageChess("data\\Den6.png", jLpicQuanCo, Cot, Hang);
			}
			if (Ten == "7") {
				Util.loadImageChess("data\\Den7.png", jLpicQuanCo, Cot, Hang);
			}
			if (Ten == "8") {
				Util.loadImageChess("data\\Den8.png", jLpicQuanCo, Cot, Hang);
			}
			if (Ten == "9") {
				Util.loadImageChess("data\\Den9.png", jLpicQuanCo, Cot, Hang);
			}
		}
	}
	
	/**
	 * Ve quan co luc click vao Select
	 */
	public void drawChessSelect() {
		if (Phe == 0) {
			if (Ten == "0") {
				Util.loadImageChess("data\\Trang0_Select.png", jLpicQuanCo,
						Cot, Hang);
			}
			if (Ten == "1") {
				
				Util.loadImageChess("data\\Trang1_Select.png", jLpicQuanCo,
						Cot, Hang);
			}
			if (Ten == "2") {
				Util.loadImageChess("data\\Trang2_Select.png", jLpicQuanCo,
						Cot, Hang);
			}
			if (Ten == "3") {
				Util.loadImageChess("data\\Trang3_Select.png", jLpicQuanCo,
						Cot, Hang);
			}
			if (Ten == "4") {
				Util.loadImageChess("data\\Trang4_Select.png", jLpicQuanCo,
						Cot, Hang);
			}
			if (Ten == "5") {
				Util.loadImageChess("data\\Trang5_Select.png", jLpicQuanCo,
						Cot, Hang);
			}
			if (Ten == "6") {
				Util.loadImageChess("data\\Trang6_Select.png", jLpicQuanCo,
						Cot, Hang);
			}
			if (Ten == "7") {
				Util.loadImageChess("data\\Trang7_Select.png", jLpicQuanCo,
						Cot, Hang);
			}
			if (Ten == "8") {
				Util.loadImageChess("data\\Trang8_Select.png", jLpicQuanCo,
						Cot, Hang);
			}
			if (Ten == "9") {
				Util.loadImageChess("data\\Trang9_Select.png", jLpicQuanCo,
						Cot, Hang);
			}
		}
		if (Phe == 1) {
			if (Ten == "0") {
				Util.loadImageChess("data\\Den0_Select.png", jLpicQuanCo, Cot,
						Hang);
			}
			if (Ten == "1") {
				Util.loadImageChess("data\\Den1_Select.png", jLpicQuanCo, Cot,
						Hang);
			}
			if (Ten == "2") {
				Util.loadImageChess("data\\Den2_Select.png", jLpicQuanCo, Cot,
						Hang);
			}
			if (Ten == "3") {
				Util.loadImageChess("data\\Den3_Select.png", jLpicQuanCo, Cot,
						Hang);
			}
			if (Ten == "4") {
				Util.loadImageChess("data\\Den4_Select.png", jLpicQuanCo, Cot,
						Hang);
			}
			if (Ten == "5") {
				Util.loadImageChess("data\\Den5_Select.png", jLpicQuanCo, Cot,
						Hang);
			}
			if (Ten == "6") {
				Util.loadImageChess("data\\Den6_Select.png", jLpicQuanCo, Cot,
						Hang);
			}
			if (Ten == "7") {
				Util.loadImageChess("data\\Den7_Select.png", jLpicQuanCo, Cot,
						Hang);
			}
			if (Ten == "8") {
				Util.loadImageChess("data\\Den8_Select.png", jLpicQuanCo, Cot,
						Hang);
			}
			if (Ten == "9") {
				Util.loadImageChess("data\\Den9_Select.png", jLpicQuanCo, Cot,
						Hang);
			}
		}
	}
	
	/**
	 * Kiem tra tai vi tri [i][j] quan co di chuyen duoc hay khong
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public abstract int checkChess(int i, int j);
	
	/**
	 * Xac dinh vi tri di chuyen duoc cua quan co
	 * 
	 * @param i
	 *            cot
	 * @param j
	 *            hang
	 * @param n
	 *            so buoc di chuyen duoc cua quan co
	 */
	private void move(int i, int j, int n) {
		
		if (i >= 0 && i <= 8 && j >= 0 && j <= 10)
			turn = false;
		for (int x = 1; x <= n; x++) {
			
			if ((j == Hang - x && i == Cot - x)
					|| (j == Hang - x && i == Cot + x)) {
				if (BanCo.ViTri[i][j].Trong == true)
					turn = true;
				// huong phia tren ben trai
				if (j < Hang && i < Cot) {
					int k, l;
					for (k = Hang - 1, l = Cot - 1; k >= j && l >= i; k--, l--) {
						if (BanCo.ViTri[l][k].Trong == false) {
							turn = false;
							break;
						}
					}
				}
				// huong phia tren ben phai
				if (j < Hang && i > Cot) {
					int k, l;
					for (k = Hang - 1, l = Cot + 1; k >= j && l <= i; k--, l++) {
						if (BanCo.ViTri[l][k].Trong == false) {
							turn = false;
							break;
						}
					}
				}
			}
			
			if ((j == Hang + x && i == Cot - x)
					|| (j == Hang + x && i == Cot + x)) {
				if (BanCo.ViTri[i][j].Trong == true)
					turn = true;
				// cheo duoi trai
				if (j > Hang && i > Cot) {
					int k, l;
					for (k = Hang + 1, l = Cot + 1; k <= j && l <= i; k++, l++) {
						if (BanCo.ViTri[l][k].Trong == false) {
							turn = false;
							break;
						}
					}
				}
				// cheo duoi phai
				if (j > Hang && i < Cot) {
					int k, l;
					for (k = Hang + 1, l = Cot - 1; k <= j && l >= i; k++, l--) {
						if (BanCo.ViTri[l][k].Trong == false) {
							turn = false;
							break;
						}
					}
				}
			}
			// xet huong di thang theo hang
			if ((j == Hang - x && i == Cot) || (j == Hang + x && i == Cot)) {
				if (BanCo.ViTri[i][j].Trong == true)
					turn = true;
				if (j > Hang)
					for (int k = Hang + 1; k <= j; k++) {
						
						if (BanCo.ViTri[i][k].Trong == false) {
							turn = false;
							break;
						}
					}
				if (j < Hang)
					for (int k = j + 1; k <= Hang - 1; k++) {
						if (BanCo.ViTri[i][k].Trong == false) {
							turn = false;
							break;
						}
					}
				
			}
			// xet huong di thang theo cot
			if ((j == Hang && i == Cot - x) || (j == Hang && i == Cot + x)) {
				//
				if (BanCo.ViTri[i][j].Trong == true)
					turn = true;
				//
				if (i > Cot)
					for (int k = Cot + 1; k <= i; k++) {
						if (BanCo.ViTri[k][j].Trong == false) {
							turn = false;
							break;
						}
					}
				if (i < Cot)
					for (int k = i + 1; k <= Cot - 1; k++) {
						if (BanCo.ViTri[k][j].Trong == false) {
							turn = false;
							break;
						}
					}
			}
			
		}
	}
	
	/**
	 * Lay gia tri cua quan co trai vi tri[i][j]
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public int GiaTri(int i, int j) {
		if (BanCo.ViTri[i][j].Ten == "0")
			return 0;
		if (BanCo.ViTri[i][j].Ten == "1")
			return 1;
		if (BanCo.ViTri[i][j].Ten == "2")
			return 2;
		if (BanCo.ViTri[i][j].Ten == "3")
			return 3;
		if (BanCo.ViTri[i][j].Ten == "4")
			return 4;
		if (BanCo.ViTri[i][j].Ten == "5")
			return 5;
		if (BanCo.ViTri[i][j].Ten == "6")
			return 6;
		if (BanCo.ViTri[i][j].Ten == "7")
			return 7;
		if (BanCo.ViTri[i][j].Ten == "8")
			return 8;
		if (BanCo.ViTri[i][j].Ten == "9")
			return 9;
		else
			return 0;
	}
	
	/**
	 * Xac dinh an quan theo huong cheo len trai
	 * 
	 * @param i
	 *            cot
	 * @param j
	 *            hang
	 */
	private void XDAnQuan1(int i, int j) {
		if (j == Hang - 2 && i == Cot - 2) {
			turn = true;
		} else {
			// kiem tra khong co quan chan trong nuoc an quan
			for (int k = Hang - 2, l = Cot - 2; k > j && l > i; k--, l--) {
				if (BanCo.ViTri[l][k].Trong == false) {
					turn = false;
					break;
				} else
					turn = true;
			}
		}
	}
	
	/**
	 * Xac dinh an quan theo huong cheo len phai
	 * 
	 * @param i
	 *            cot
	 * @param j
	 *            hang
	 */
	private void XDAnQuan2(int i, int j) {
		if (j == Hang - 2 && i == Cot + 2) {
			turn = true;
		} else {
			for (int k = Hang - 2, l = Cot + 2; k > j && l < i; k--, l++) {
				if (BanCo.ViTri[l][k].Trong == false) {
					turn = false;
					break;
				} else
					turn = true;
			}
		}
	}
	
	/**
	 * Xac dinh an quan theo huong cheo xuong phai
	 * 
	 * @param i
	 *            cot
	 * @param j
	 *            hang
	 */
	private void XDAnQuan3(int i, int j) {
		if (j == Hang + 2 && i == Cot + 2) {
			turn = true;
		} else {
			for (int k = Hang + 2, l = Cot + 2; k < j && l < i; k++, l++) {
				if (BanCo.ViTri[l][k].Trong == false) {
					turn = false;
					break;
				} else
					turn = true;
			}
		}
	}
	
	/**
	 * Xac dinh an quan theo huong cheo xuong trai
	 * 
	 * @param i
	 *            cot
	 * @param j
	 *            hang
	 */
	private void XDAnQuan4(int i, int j) {
		if (j == Hang + 2 && i == Cot - 2) {
			turn = true;
		} else {
			for (int k = Hang + 2, l = Cot - 2; k < j && l > i; k++, l--) {
				if (BanCo.ViTri[l][k].Trong == false) {
					turn = false;
					break;
				} else
					turn = true;
			}
		}
	}
	
	/**
	 * Xac dinh an quan theo huong cheo xuong duoi
	 * 
	 * @param i
	 *            cot
	 * @param j
	 *            hang
	 */
	private void XDAnQuan5(int i, int j) {
		if (j == Hang + 2 && i == Cot) {
			turn = true;
		} else {
			for (int k = Hang + 2; k < j; k++) {
				if (BanCo.ViTri[i][k].Trong == false) {
					turn = false;
					break;
				} else
					turn = true;
			}
		}
	}
	
	/**
	 * Xac dinh an quan theo huong len tren
	 * 
	 * @param i
	 *            cot
	 * @param j
	 *            hang
	 */
	private void XDAnQuan6(int i, int j) {
		if (j == Hang - 2 && i == Cot) {
			turn = true;
		} else {
			for (int k = j + 1; k <= Hang - 2; k++) {
				if (BanCo.ViTri[i][k].Trong == false) {
					turn = false;
					break;
				} else
					turn = true;
			}
		}
	}
	
	/**
	 * Xac dinh an quan theo huong sang phai
	 * 
	 * @param i
	 *            cot
	 * @param j
	 *            hang
	 */
	private void XDAnQuan7(int i, int j) {
		if (j == Hang && i == Cot + 2) {
			turn = true;
		} else {
			for (int k = Cot + 2; k < i; k++) {
				if (BanCo.ViTri[k][j].Trong == false) {
					turn = false;
					break;
				} else
					turn = true;
			}
		}
	}
	
	/**
	 * Xac dinh an quan theo huong sang trai
	 * 
	 * @param i
	 *            cot
	 * @param j
	 *            hang
	 */
	private void XDAnQuan8(int i, int j) {
		if (j == Hang && i == Cot - 2) {
			turn = true;
		} else {
			for (int k = i + 1; k <= Cot - 2; k++) {
				if (BanCo.ViTri[k][j].Trong == false) {
					turn = false;
					break;
				} else
					turn = true;
			}
		}
	}
	
	/**
	 * Vi tri an duoc cua quan co
	 * 
	 * @param i
	 *            cot
	 * @param j
	 *            hang
	 * @param result_Add
	 *            ket qua phep cong
	 * @param result_Add_Div
	 *            ket qua phep chia
	 * @param result_Add_Mul
	 *            ket qua phep nhan
	 * @param result_Sub
	 *            ket qua phep tru
	 * @param result_Du
	 *            ket qua phep du
	 */
	private void pointEatChess(int i, int j, int result_Add,
			int result_Add_Div, int result_Add_Mul, int result_Sub,
			int result_Du) {
		int pc = 0, pt = 0, pn = 0, pch = 0, pd = 0;
		// xac dinh vi tri duoc an cua quan co
		if ((j == Hang - result_Add && i == Cot)
				|| (j == Hang + result_Add && i == Cot)
				|| (j == Hang && i == Cot - result_Add)
				|| (j == Hang && i == Cot + result_Add)
				|| (j == Hang - result_Add && i == Cot - result_Add)
				|| (j == Hang - result_Add && i == Cot + result_Add)
				|| (j == Hang + result_Add && i == Cot - result_Add)
				|| (j == Hang + result_Add && i == Cot + result_Add)
				|| (j == Hang - result_Add_Div && i == Cot)
				|| (j == Hang + result_Add_Div && i == Cot)
				|| (j == Hang && i == Cot - result_Add_Div)
				|| (j == Hang && i == Cot + result_Add_Div)
				|| (j == Hang - result_Add_Div && i == Cot - result_Add_Div)
				|| (j == Hang - result_Add_Div && i == Cot + result_Add_Div)
				|| (j == Hang + result_Add_Div && i == Cot - result_Add_Div)
				|| (j == Hang + result_Add_Div && i == Cot + result_Add_Div)
				|| (j == Hang - result_Add_Mul && i == Cot)
				|| (j == Hang + result_Add_Mul && i == Cot)
				|| (j == Hang && i == Cot - result_Add_Mul)
				|| (j == Hang && i == Cot + result_Add_Mul)
				|| (j == Hang - result_Add_Mul && i == Cot - result_Add_Mul)
				|| (j == Hang - result_Add_Mul && i == Cot + result_Add_Mul)
				|| (j == Hang + result_Add_Mul & i == Cot - result_Add_Mul)
				|| (j == Hang + result_Add_Mul && i == Cot + result_Add_Mul)
				|| (j == Hang - result_Sub && i == Cot)
				|| (j == Hang + result_Sub && i == Cot)
				|| (j == Hang && i == Cot - result_Sub)
				|| (j == Hang && i == Cot + result_Sub)
				|| (j == Hang - result_Sub && i == Cot - result_Sub)
				|| (j == Hang - result_Sub && i == Cot + result_Sub)
				|| (j == Hang + result_Sub && i == Cot - result_Sub)
				|| (j == Hang + result_Sub && i == Cot + result_Sub)
				|| (j == Hang - result_Du && i == Cot)
				|| (j == Hang + result_Du && i == Cot)
				|| (j == Hang && i == Cot - result_Du)
				|| (j == Hang && i == Cot + result_Du)
				|| (j == Hang - result_Du && i == Cot - result_Du)
				|| (j == Hang - result_Du && i == Cot + result_Du)
				|| (j == Hang + result_Du && i == Cot - result_Du)
				|| (j == Hang + result_Du && i == Cot + result_Du)) {
			// kiem ra dieu kien duoc an
			if (BanCo.ViTri[i][j].Trong == false) {
				if (BanCo.ViTri[i][j].Phe != this.Phe) {
					if ((j == Hang - result_Add && i == Cot)
							|| (j == Hang + result_Add && i == Cot)
							|| (j == Hang && i == Cot - result_Add)
							|| (j == Hang && i == Cot + result_Add)
							|| (j == Hang - result_Add && i == Cot - result_Add)
							|| (j == Hang - result_Add && i == Cot + result_Add)
							|| (j == Hang + result_Add && i == Cot - result_Add)
							|| (j == Hang + result_Add && i == Cot + result_Add))
						pc = 1;
					if ((j == Hang - result_Add_Div && i == Cot)
							|| (j == Hang + result_Add_Div && i == Cot)
							|| (j == Hang && i == Cot - result_Add_Div)
							|| (j == Hang && i == Cot + result_Add_Div)
							|| (j == Hang - result_Add_Div && i == Cot
									- result_Add_Div)
							|| (j == Hang - result_Add_Div && i == Cot
									+ result_Add_Div)
							|| (j == Hang + result_Add_Div && i == Cot
									- result_Add_Div)
							|| (j == Hang + result_Add_Div && i == Cot
									+ result_Add_Div))
						pch = 1;
					if ((j == Hang - result_Add_Mul && i == Cot)
							|| (j == Hang + result_Add_Mul && i == Cot)
							|| (j == Hang && i == Cot - result_Add_Mul)
							|| (j == Hang && i == Cot + result_Add_Mul)
							|| (j == Hang - result_Add_Mul && i == Cot
									- result_Add_Mul)
							|| (j == Hang - result_Add_Mul && i == Cot
									+ result_Add_Mul)
							|| (j == Hang + result_Add_Mul & i == Cot
									- result_Add_Mul)
							|| (j == Hang + result_Add_Mul && i == Cot
									+ result_Add_Mul))
						pn = 1;
					if ((j == Hang - result_Sub && i == Cot)
							|| (j == Hang + result_Sub && i == Cot)
							|| (j == Hang && i == Cot - result_Sub)
							|| (j == Hang && i == Cot + result_Sub)
							|| (j == Hang - result_Sub && i == Cot - result_Sub)
							|| (j == Hang - result_Sub && i == Cot + result_Sub)
							|| (j == Hang + result_Sub && i == Cot - result_Sub)
							|| (j == Hang + result_Sub && i == Cot + result_Sub))
						pt = 1;
					if ((j == Hang - result_Du && i == Cot)
							|| (j == Hang + result_Du && i == Cot)
							|| (j == Hang && i == Cot - result_Du)
							|| (j == Hang && i == Cot + result_Du)
							|| (j == Hang - result_Du && i == Cot - result_Du)
							|| (j == Hang - result_Du && i == Cot + result_Du)
							|| (j == Hang + result_Du && i == Cot - result_Du)
							|| (j == Hang + result_Du && i == Cot + result_Du))
						pd = 1;
					
					// huong xet
					// cheo len trai
					if (j < Hang && i < Cot) {
						if (BanCo.ViTri[Cot - 1][Hang - 1].Trong == false) {
							if (BanCo.ViTri[Cot - 1][Hang - 1].Phe == this.Phe) {
								resultEat(this.Giatri,
										GiaTri(Cot - 1, Hang - 1));
								if (pc == 1 && c == result_Add && c > 1)
									XDAnQuan1(i, j);
								if (pt == 1 && t == result_Sub && t > 1)
									XDAnQuan1(i, j);
								if (pn == 1 && n == result_Add_Mul && n > 1)
									XDAnQuan1(i, j);
								if (pch == 1 && ch == result_Add_Div && ch > 1)
									XDAnQuan1(i, j);
								if (pd == 1 && d == result_Du && d > 1)
									XDAnQuan1(i, j);
							}
						}
					}
					// cheo len phai
					if (j < Hang && i > Cot) {
						if (BanCo.ViTri[Cot + 1][Hang - 1].Trong == false) {
							if (BanCo.ViTri[Cot + 1][Hang - 1].Phe == this.Phe) {
								resultEat(this.Giatri,
										GiaTri(Cot + 1, Hang - 1));
								if (pc == 1 && c == result_Add && c > 1)
									XDAnQuan2(i, j);
								if (pt == 1 && t == result_Sub && t > 1)
									XDAnQuan2(i, j);
								if (pn == 1 && n == result_Add_Mul && n > 1)
									XDAnQuan2(i, j);
								if (pch == 1 && ch == result_Add_Div && ch > 1)
									XDAnQuan2(i, j);
								if (pd == 1 && d == result_Du && d > 1)
									XDAnQuan2(i, j);
							}
						}
					}
					// cheo xuong phai
					if (j > Hang && i > Cot) {
						if (BanCo.ViTri[Cot + 1][Hang + 1].Trong == false) {
							if (BanCo.ViTri[Cot + 1][Hang + 1].Phe == this.Phe) {
								resultEat(this.Giatri,
										GiaTri(Cot + 1, Hang + 1));
								if (pc == 1 && c == result_Add && c > 1)
									XDAnQuan3(i, j);
								if (pt == 1 && t == result_Sub && t > 1)
									XDAnQuan3(i, j);
								if (pn == 1 && n == result_Add_Mul && n > 1)
									XDAnQuan3(i, j);
								if (pch == 1 && ch == result_Add_Div && ch > 1)
									XDAnQuan3(i, j);
								if (pd == 1 && d == result_Du && d > 1)
									XDAnQuan3(i, j);
							}
						}
					}
					// cheo xuong trai
					if (j > Hang && i < Cot) {
						if (BanCo.ViTri[Cot - 1][Hang + 1].Trong == false) {
							if (BanCo.ViTri[Cot - 1][Hang + 1].Phe == this.Phe) {
								resultEat(this.Giatri,
										GiaTri(Cot - 1, Hang + 1));
								if (pc == 1 && c == result_Add && c > 1)
									XDAnQuan4(i, j);
								if (pt == 1 && t == result_Sub && t > 1)
									XDAnQuan4(i, j);
								if (pn == 1 && n == result_Add_Mul && n > 1)
									XDAnQuan4(i, j);
								if (pch == 1 && ch == result_Add_Div && ch > 1)
									XDAnQuan4(i, j);
								if (pd == 1 & d == result_Du && d > 1)
									XDAnQuan4(i, j);
							}
						}
					}
					// xuong duoi
					if (j > Hang && i == Cot) {
						if (BanCo.ViTri[Cot][Hang + 1].Trong == false) {
							if (BanCo.ViTri[Cot][Hang + 1].Phe == this.Phe) {
								resultEat(this.Giatri, GiaTri(Cot, Hang + 1));
								if (pc == 1 && c == result_Add && c > 1)
									XDAnQuan5(i, j);
								if (pt == 1 && t == result_Sub && t > 1)
									XDAnQuan5(i, j);
								if (pn == 1 && n == result_Add_Mul && n > 1)
									XDAnQuan5(i, j);
								if (pch == 1 && ch == result_Add_Div && ch > 1)
									XDAnQuan5(i, j);
								if (pd == 1 && d == result_Du && d > 1)
									XDAnQuan5(i, j);
							}
						}
					}
					// len tren
					if (j < Hang && i == Cot) {
						if (BanCo.ViTri[Cot][Hang - 1].Trong == false) {
							if (BanCo.ViTri[Cot][Hang - 1].Phe == this.Phe) {
								resultEat(this.Giatri, GiaTri(Cot, Hang - 1));
								if (pc == 1 && c == result_Add && c > 1)
									XDAnQuan6(i, j);
								if (pt == 1 && t == result_Sub && t > 1)
									XDAnQuan6(i, j);
								if (pn == 1 && n == result_Add_Mul && n > 1)
									XDAnQuan6(i, j);
								if (pch == 1 && ch == result_Add_Div && ch > 1)
									XDAnQuan6(i, j);
								if (pd == 1 && d == result_Du && d > 1)
									XDAnQuan6(i, j);
							}
						}
					}
					// sang phai
					if (j == Hang && i > Cot) {
						if (BanCo.ViTri[Cot + 1][Hang].Trong == false) {
							if (BanCo.ViTri[Cot + 1][Hang].Phe == this.Phe) {
								resultEat(this.Giatri, GiaTri(Cot + 1, Hang));
								if (pc == 1 && c == result_Add && c > 1)
									XDAnQuan7(i, j);
								if (pt == 1 && t == result_Sub && t > 1)
									XDAnQuan7(i, j);
								if (pn == 1 && n == result_Add_Mul && n > 1)
									XDAnQuan7(i, j);
								if (pch == 1 && ch == result_Add_Div && ch > 1)
									XDAnQuan7(i, j);
								if (pd == 1 && d == result_Du && d > 1)
									XDAnQuan7(i, j);
							}
						}
					}
					// sang trai
					if (j == Hang && i < Cot) {
						if (BanCo.ViTri[Cot - 1][Hang].Trong == false) {
							if (BanCo.ViTri[Cot - 1][Hang].Phe == this.Phe) {
								// int ch = 0, c = 0, t = 0, n = 0, d = 0;
								resultEat(this.Giatri, GiaTri(Cot - 1, Hang));
								if (pc == 1 && c == result_Add && c > 1)
									XDAnQuan8(i, j);
								if (pt == 1 && t == result_Sub && t > 1)
									XDAnQuan8(i, j);
								if (pn == 1 && n == result_Add_Mul && n > 1)
									XDAnQuan8(i, j);
								if (pch == 1 && ch == result_Add_Div && ch > 1)
									XDAnQuan8(i, j);
								if (pd == 1 && d == result_Du && d > 1)
									XDAnQuan8(i, j);
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Xac dinh cac gia tri cong, tru, nhan, chia, phep du
	 * 
	 * @param x
	 *            gia tri 1 (quan co tham chieu den)
	 * @param y
	 *            gia tri 2 (quan co lam buoc dem)
	 */
	private void resultEat(int x, int y) {
		if (x > y) {
			t = (x - y) + 1;
			c = (x + y);
			{
				if (c >= 10) {
					c = (c - 10) + 1;
				} else
					c = c + 1;
			}
			if (y != 0) {
				ch = ((x / y)) + 1;
				d = (x % y) + 1;
			}
			n = ((x * y));
			if (n > 10) {
				n = (n % 10) + 1;
			} else
				n = n + 1;
		}
		if (x < y) {
			int temp = y + x;
			if (temp > 10) {
				c = (temp - 10) + 1;
			} else
				c = temp + 1;
			if (y != 0) {
				ch = ((x / y)) + 1;
				d = (x % y) + 1;
			}
			n = ((x * y));
			if (n > 10) {
				n = (n % 10) + 1;
			} else
				n = n + 1;
		}
	}
	
	/**
	 * Xac dinh toan bo vi tri [i][j] di duoc cua quan co ke ca nuoc di an quan
	 * do
	 * 
	 * @param i
	 *            cot
	 * @param j
	 *            hang
	 * @param gtri
	 *            so buoc co the di
	 */
	public void valueChess(int i, int j, int gtri) {
		move(i, j, gtri);
		for (int m = 0; m <= 8; m++) {
			for (int n = 0; n <= 10; n++) {
				// xac dinh co quan ke vs this.quanco
				if ((n == Hang - 1 && m == Cot) || (n == Hang + 1 && m == Cot)
						|| (n == Hang && m == Cot - 1)
						|| (n == Hang && m == Cot + 1)
						|| (n == Hang - 1 && m == Cot - 1)
						|| (n == Hang - 1 && m == Cot + 1)
						|| (n == Hang + 1 && m == Cot - 1)
						|| (n == Hang + 1 && m == Cot + 1)) {
					if (BanCo.ViTri[m][n].Trong == false) {
						if (BanCo.ViTri[m][n].Phe == this.Phe) {
							
							index2 = gtri;
							if (BanCo.ViTri[m][n].Ten == "0")
								index1 = 0;
							if (BanCo.ViTri[m][n].Ten == "1")
								index1 = 1;
							if (BanCo.ViTri[m][n].Ten == "2")
								index1 = 2;
							if (BanCo.ViTri[m][n].Ten == "3")
								index1 = 3;
							if (BanCo.ViTri[m][n].Ten == "4")
								index1 = 4;
							if (BanCo.ViTri[m][n].Ten == "5")
								index1 = 5;
							if (BanCo.ViTri[m][n].Ten == "6")
								index1 = 6;
							if (BanCo.ViTri[m][n].Ten == "7")
								index1 = 7;
							if (BanCo.ViTri[m][n].Ten == "8")
								index1 = 8;
							if (BanCo.ViTri[m][n].Ten == "9")
								index1 = 9;
							
							if (index2 > index1) {
								result_Sub = (index2 - index1) + 1;
								
								result_Add = (index2 + index1);
								{
									if (result_Add >= 10) {
										result_Add = (result_Add - 10) + 1;
									} else
										result_Add = result_Add + 1;
								}
								if (index1 != 0) {
									result_Add_Div = (index2 / index1) + 1;
									result_Du = (index2 % index1) + 1;
								}
								result_Add_Mul = ((index2 * index1));
								if (result_Add_Mul > 10) {
									result_Add_Mul = (result_Add_Mul % 10) + 1;
								} else
									result_Add_Mul = result_Add_Mul + 1;
								pointEatChess(i, j, result_Add, result_Add_Div,
										result_Add_Mul, result_Sub, result_Du);
							}
							if (index2 < index1) {
								int temp = index1 + index2;
								if (temp > 10) {
									result_Add = (temp - 10) + 1;
								} else
									result_Add = temp + 1;
								if (index1 != 0) {
									result_Add_Div = (index2 / index1) + 1;
									result_Du = (index2 % index1) + 1;
								}
								result_Add_Mul = ((index2 * index1));
								if (result_Add_Mul > 10) {
									result_Add_Mul = (result_Add_Mul % 10) + 1;
								} else
									result_Add_Mul = result_Add_Mul + 1;
								pointEatChess(i, j, result_Add, result_Add_Div,
										result_Add_Mul, result_Sub, result_Du);
							}
							
						}
					}
				}
			}
		}
	}
	
	/**
	 * Ham sinh moi nuoc di cua 1 quan co
	 */
	public void moveAIQ() {
		if (this.TrangThai == 1) {
			for (int i = 0; i <= 8; i++) {
				for (int j = 0; j <= 10; j++) {
					if (j != Hang || i != Cot) {
						if (checkChess(i, j) == 1) {
							moveai = new _MoveAI();
							moveai.movei = i;
							moveai.movej = j;
							moveAI.add(moveai);
							cout_MoveAI++;
						}
					}
				}
			}
		}
	}
}
