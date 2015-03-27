package cotoan.vanco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import cotoan.banco.BanCo;
import cotoan.banco.OCo;
import cotoan.player.NguoiChoi;
import cotoan.quanco.QuanCo;
import cotoan.util.ManagerBoard;
import cotoan.util.Util;

public class VanCo {
	public static NguoiChoi[] NguoiChoi = new NguoiChoi[2];
	// diem so
	public static int iDiemQuanDen = 0, iDiemQuanTrang = 0;
	public static int iDiemCuoc = 0;
	// Quan ly quan bi an
	public static int soQuanAn_Den = 0;
	public static int soQuanAn_Trang = 0;
	
	// bat dau choi
	public static int isPlaying = 0;// dang choi
	public static int isAI = 0; // bien kiem tra che do choi voi may ( = 1)
	public static int isSingle = 0; // bien kiem tra che do choi voi nguoi ( =
									// 1)
	public static int LuotDi = 1;
	public static int iTimer = -1;
	
	public static int iCuoc = 0;
	public static int iDiem = 0;
	
	// class NuocDi
	public static class NuocDi {
		public QuanCo Dau;
		public QuanCo Cuoi;
		
		public int Hang_Dau, Cot_Dau;
		
		public int Hang_Cuoi, Cot_Cuoi;
		
		public NuocDi() {
			Dau = Cuoi = null;
			Hang_Dau = Hang_Cuoi = Cot_Dau = Cot_Cuoi = 0;
		}
	}
	
	// Quan ly tap nuoc di
	public static NuocDi Log;
	public static ArrayList<NuocDi> GameLog = new ArrayList<>();
	public static int turn = 0; // tong so luot di cua van co
	public static QuanCo.TryTestMove bestMoveAI = new QuanCo.TryTestMove();// luu
																			// nuoc
																			// di
																			// tot
																			// nhat
																			// cua
	// thuat toan
	// Luot di
	public static int player = 0;
	
	// bien kiem tra ton tai quan co duoc chon
	public static boolean marked = false;
	
	// **************************************
	/**
	 * Thay doi luot di
	 */
	public static void changePlayer() {
		// VanCo.isWin();
		if (iTimer >= 0) {
			BanCo.timer.stop();
			BanCo.iTimerPlayer0 = iTimer;
			BanCo.iTimerPlayer1 = iTimer;
		}
		if (LuotDi == 0) {
			LuotDi = 1;
			
		} else if (LuotDi == 1) {
			LuotDi = 0;
		}
		if (LuotDi == 0) {
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
			
			NguoiChoi[1].q0.Khoa = true;
			NguoiChoi[1].q1.Khoa = true;
			NguoiChoi[1].q2.Khoa = true;
			NguoiChoi[1].q3.Khoa = true;
			NguoiChoi[1].q4.Khoa = true;
			NguoiChoi[1].q5.Khoa = true;
			NguoiChoi[1].q6.Khoa = true;
			NguoiChoi[1].q7.Khoa = true;
			NguoiChoi[1].q8.Khoa = true;
			NguoiChoi[1].q9.Khoa = true;
			
		}
		if (LuotDi == 1) {
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
			
			NguoiChoi[0].q0.Khoa = true;
			NguoiChoi[0].q1.Khoa = true;
			NguoiChoi[0].q2.Khoa = true;
			NguoiChoi[0].q3.Khoa = true;
			NguoiChoi[0].q4.Khoa = true;
			NguoiChoi[0].q5.Khoa = true;
			NguoiChoi[0].q6.Khoa = true;
			NguoiChoi[0].q7.Khoa = true;
			NguoiChoi[0].q8.Khoa = true;
			NguoiChoi[0].q9.Khoa = true;
			
		}
		if (iTimer >= 0)
			BanCo.timer.start();
	}
	
	/**
	 * Ham them quan co vao ban co
	 */
	public static void addChess() {
		
		switch (isPlaying) {
			case 1:
				setVisibleChess(false);
				ManagerBoard.reset();
				BanCo.timer.stop();
				NguoiChoi[0] = new NguoiChoi(0);
				
				NguoiChoi[0].q0.drawChess();
				NguoiChoi[0].q1.drawChess();
				NguoiChoi[0].q2.drawChess();
				NguoiChoi[0].q3.drawChess();
				NguoiChoi[0].q4.drawChess();
				NguoiChoi[0].q5.drawChess();
				NguoiChoi[0].q6.drawChess();
				NguoiChoi[0].q7.drawChess();
				NguoiChoi[0].q8.drawChess();
				NguoiChoi[0].q9.drawChess();
				
				NguoiChoi[1] = new NguoiChoi(1);
				NguoiChoi[1].q0.drawChess();
				NguoiChoi[1].q1.drawChess();
				NguoiChoi[1].q2.drawChess();
				NguoiChoi[1].q3.drawChess();
				NguoiChoi[1].q4.drawChess();
				NguoiChoi[1].q5.drawChess();
				NguoiChoi[1].q6.drawChess();
				NguoiChoi[1].q7.drawChess();
				NguoiChoi[1].q8.drawChess();
				NguoiChoi[1].q9.drawChess();
				break;
			case 0:
				NguoiChoi[0] = new NguoiChoi(0);
				
				NguoiChoi[0].q0.drawChess();
				NguoiChoi[0].q1.drawChess();
				NguoiChoi[0].q2.drawChess();
				NguoiChoi[0].q3.drawChess();
				NguoiChoi[0].q4.drawChess();
				NguoiChoi[0].q5.drawChess();
				NguoiChoi[0].q6.drawChess();
				NguoiChoi[0].q7.drawChess();
				NguoiChoi[0].q8.drawChess();
				NguoiChoi[0].q9.drawChess();
				
				NguoiChoi[1] = new NguoiChoi(1);
				NguoiChoi[1].q0.drawChess();
				NguoiChoi[1].q1.drawChess();
				NguoiChoi[1].q2.drawChess();
				NguoiChoi[1].q3.drawChess();
				NguoiChoi[1].q4.drawChess();
				NguoiChoi[1].q5.drawChess();
				NguoiChoi[1].q6.drawChess();
				NguoiChoi[1].q7.drawChess();
				NguoiChoi[1].q8.drawChess();
				NguoiChoi[1].q9.drawChess();
				break;
		}
	}
	
	/**
	 * Ham dat quan co den vi tri [i][j]
	 * 
	 * @param q
	 *            quan co duoc di
	 * @param i
	 *            cot
	 * @param j
	 *            hang
	 */
	public static void putChess(QuanCo q, int i, int j) {
		q.Hang = j;
		q.Cot = i;
		q.drawChess();
	}
	
	/**
	 * Ham an quan co
	 * 
	 * @param q
	 *            quan co bi an
	 */
	public static void eatChess(QuanCo q) {
		int hang = 0, cot = 0;
		q.TrangThai = 0;
		if (q.Phe == 1) {
			if (soQuanAn_Den >= 0 && soQuanAn_Den <= 2) {
				hang = 0;
				cot = soQuanAn_Den;
			}
			if (soQuanAn_Den >= 3 && soQuanAn_Den <= 5) {
				hang = 1;
				cot = soQuanAn_Den - 3;
			}
			if (soQuanAn_Den >= 6 && soQuanAn_Den <= 9) {
				hang = 2;
				cot = soQuanAn_Den - 6;
			}
			soQuanAn_Den++;
			iDiemQuanDen += q.Giatri; // cap nhat diem so cho quan trang
			BanCo.changeScore();
			q.jLpicQuanCo.setBounds(cot * 54 + 540, hang * 54 + 90, 50, 50); // dua
																				// quan
																				// bi
																				// an
																				// ra
																				// vi
																				// tri
																				// chua
																				// quan
																				// bi
																				// an
		}
		if (q.Phe == 0) {
			if (soQuanAn_Trang >= 0 && soQuanAn_Trang <= 2) {
				hang = 0;
				cot = soQuanAn_Trang;
			}
			if (soQuanAn_Trang >= 3 && soQuanAn_Trang <= 5) {
				hang = 1;
				cot = soQuanAn_Trang - 3;
			}
			if (soQuanAn_Trang >= 6 && soQuanAn_Trang <= 9) {
				hang = 2;
				cot = soQuanAn_Trang - 6;
			}
			soQuanAn_Trang++;
			iDiemQuanTrang += q.Giatri; // cap nhat diem so cho quan den
			BanCo.changeScore();
			q.jLpicQuanCo.setBounds(cot * 54 + 540, hang * 54 + 463, 50, 50);
		}
	}
	
	/**
	 * Ham kiem tra thang tran
	 * 
	 * @return
	 */
	public static void isWin() {
		if (VanCo.NguoiChoi[0].q0.TrangThai == 0) {
			Util.showWin(0);
		} else if (iDiemQuanDen >= iDiemCuoc && iDiem == 1) {
			Util.showWin(0);
		} else if (BanCo.iTimerPlayer0 <= 0 && iCuoc == 1) {
			Util.showWin(0);
		} else if (VanCo.NguoiChoi[1].q0.TrangThai == 0) {
			Util.showWin(1);
		} else if (iDiemQuanTrang >= iDiemCuoc && iDiem == 1) {
			Util.showWin(1);
		} else if (BanCo.iTimerPlayer1 <= 0 && iCuoc == 1) {
			Util.showWin(1);
		}
	}
	
	/**
	 * set Visible cho quan co (true/false)
	 * 
	 * @param check
	 */
	public static void setVisibleChess(boolean check) {
		NguoiChoi[0].q0.jLpicQuanCo.setVisible(check);
		NguoiChoi[0].q1.jLpicQuanCo.setVisible(check);
		NguoiChoi[0].q2.jLpicQuanCo.setVisible(check);
		NguoiChoi[0].q3.jLpicQuanCo.setVisible(check);
		NguoiChoi[0].q4.jLpicQuanCo.setVisible(check);
		NguoiChoi[0].q5.jLpicQuanCo.setVisible(check);
		NguoiChoi[0].q6.jLpicQuanCo.setVisible(check);
		NguoiChoi[0].q7.jLpicQuanCo.setVisible(check);
		NguoiChoi[0].q8.jLpicQuanCo.setVisible(check);
		NguoiChoi[0].q9.jLpicQuanCo.setVisible(check);
		
		NguoiChoi[1].q0.jLpicQuanCo.setVisible(check);
		NguoiChoi[1].q1.jLpicQuanCo.setVisible(check);
		NguoiChoi[1].q2.jLpicQuanCo.setVisible(check);
		NguoiChoi[1].q3.jLpicQuanCo.setVisible(check);
		NguoiChoi[1].q4.jLpicQuanCo.setVisible(check);
		NguoiChoi[1].q5.jLpicQuanCo.setVisible(check);
		NguoiChoi[1].q6.jLpicQuanCo.setVisible(check);
		NguoiChoi[1].q7.jLpicQuanCo.setVisible(check);
		NguoiChoi[1].q8.jLpicQuanCo.setVisible(check);
		NguoiChoi[1].q9.jLpicQuanCo.setVisible(check);
	}
	
	/**
	 * Ham luu nuoc di cua nguoi choi
	 * 
	 * @param q
	 *            quan co bi an
	 * @param hang
	 * @param cot
	 */
	public static void saveGameLog(QuanCo q, int hang, int cot) {
		Log = new NuocDi();
		// nuoc di binh thuong
		if (q == null) {
			turn++;
			Log.Dau = QuanCo.DanhDau;
			Log.Hang_Dau = QuanCo.DanhDau.Hang;
			Log.Cot_Dau = QuanCo.DanhDau.Cot;
			Log.Hang_Cuoi = hang;
			Log.Cot_Cuoi = cot;
			GameLog.add(Log);
		}
		// an quan
		if (q != null) {
			turn++;
			Log.Dau = QuanCo.DanhDau;
			Log.Hang_Dau = QuanCo.DanhDau.Hang;
			Log.Cot_Dau = QuanCo.DanhDau.Cot;
			Log.Cuoi = q;
			Log.Hang_Cuoi = q.Hang;
			Log.Cot_Cuoi = q.Cot;
			GameLog.add(Log);
		}
	}
	
	/**
	 * Ham tro ve nuoc di truoc do
	 * */
	public static void undo() {
		int iSwitch;
		QuanCo temp_d = null, temp_c = null;
		
		if (turn > 0) {
			NuocDi tempLog = GameLog.get(turn - 1);
			
			// System.out.println("Nuoc co vua luu : \n\t Quan Co :"
			// + tempLog.Dau.Ten + "\n\t Phe : " + tempLog.Dau.Phe
			// + "\n\t Vi Tri : " + tempLog.Dau.Hang + ","
			// + tempLog.Dau.Cot + "\n\t Vi tri hien tai : "
			// + tempLog.Cot_Dau + "," + tempLog.Hang_Dau);
			
			// tham chieu temp_d den quan tren ban co
			if (tempLog.Dau.Ten == "0")
				temp_d = NguoiChoi[tempLog.Dau.Phe].q0;
			if (tempLog.Dau.Ten == "1")
				temp_d = NguoiChoi[tempLog.Dau.Phe].q1;
			if (tempLog.Dau.Ten == "2")
				temp_d = NguoiChoi[tempLog.Dau.Phe].q2;
			if (tempLog.Dau.Ten == "3")
				temp_d = NguoiChoi[tempLog.Dau.Phe].q3;
			if (tempLog.Dau.Ten == "4")
				temp_d = NguoiChoi[tempLog.Dau.Phe].q4;
			if (tempLog.Dau.Ten == "5")
				temp_d = NguoiChoi[tempLog.Dau.Phe].q5;
			if (tempLog.Dau.Ten == "6")
				temp_d = NguoiChoi[tempLog.Dau.Phe].q6;
			if (tempLog.Dau.Ten == "7")
				temp_d = NguoiChoi[tempLog.Dau.Phe].q7;
			if (tempLog.Dau.Ten == "8")
				temp_d = NguoiChoi[tempLog.Dau.Phe].q8;
			if (tempLog.Dau.Ten == "9")
				temp_d = NguoiChoi[tempLog.Dau.Phe].q9;
			
			// kiem tra nuoc di co phai la nuoc di an quan hay khong
			if (tempLog.Cuoi == null)
				iSwitch = 0; // khong an quan
			else
				iSwitch = 1; // an quan
			switch (iSwitch) {
				case 0: // neu la nuoc di khong an quan co cua doi phuong
					// tra lai o co trong
					OCo.OCoTrong(tempLog.Cot_Cuoi, tempLog.Hang_Cuoi);
					// dat quan co vua di tro lai vi tri cu
					temp_d.Hang = tempLog.Hang_Dau;
					temp_d.Cot = tempLog.Cot_Dau;
					// temp_d.Hang = 10;
					// temp_d.Cot = 8;
					temp_d.drawChessStart();
					// thiet lap quan co tai vi tri cu vua dat o tren
					BanCo.ViTri[temp_d.Cot][temp_d.Hang].Trong = false;
					BanCo.ViTri[temp_d.Cot][temp_d.Hang].Phe = temp_d.Phe;
					BanCo.ViTri[temp_d.Cot][temp_d.Hang].Ten = temp_d.Ten;
					
					// Xoa nuoc di vua khoi phuc ra khoi GameLog
					if (turn > 0) {
						GameLog.remove(turn - 1);
						turn--;
					}
					
					// Doi luot di
					changePlayer();
					break;
				case 1:// neu la nuoc di an quan co cua doi phuong
					
					// tham chieu temp_c den quan tren ban co
					if (tempLog.Cuoi.Ten == "0")
						temp_c = NguoiChoi[tempLog.Cuoi.Phe].q0;
					if (tempLog.Cuoi.Ten == "1")
						temp_c = NguoiChoi[tempLog.Cuoi.Phe].q1;
					if (tempLog.Cuoi.Ten == "2")
						temp_c = NguoiChoi[tempLog.Cuoi.Phe].q2;
					if (tempLog.Cuoi.Ten == "3")
						temp_c = NguoiChoi[tempLog.Cuoi.Phe].q3;
					if (tempLog.Cuoi.Ten == "4")
						temp_c = NguoiChoi[tempLog.Cuoi.Phe].q4;
					if (tempLog.Cuoi.Ten == "5")
						temp_c = NguoiChoi[tempLog.Cuoi.Phe].q5;
					if (tempLog.Cuoi.Ten == "6")
						temp_c = NguoiChoi[tempLog.Cuoi.Phe].q6;
					if (tempLog.Cuoi.Ten == "7")
						temp_c = NguoiChoi[tempLog.Cuoi.Phe].q7;
					if (tempLog.Cuoi.Ten == "8")
						temp_c = NguoiChoi[tempLog.Cuoi.Phe].q8;
					if (tempLog.Cuoi.Ten == "9")
						temp_c = NguoiChoi[tempLog.Cuoi.Phe].q9;
					
					// thiet lap lai quan co o vi tri vua bi an tren ban co
					BanCo.ViTri[tempLog.Dau.Cot][tempLog.Dau.Hang].Trong = false;
					BanCo.ViTri[tempLog.Dau.Cot][tempLog.Dau.Hang].Phe = tempLog.Cuoi.Phe;
					BanCo.ViTri[tempLog.Dau.Cot][tempLog.Dau.Hang].Ten = tempLog.Cuoi.Ten;
					
					// dat quan co vua bi an vao vi tri cu trong ban co
					temp_c.TrangThai = 1;
					temp_c.drawChessStart();
					if (temp_c.Phe == 0) {
						soQuanAn_Den--;
					}
					if (temp_c.Phe == 1) {
						soQuanAn_Trang--;
					}
					
					// dat quan vua di vao vi tri cu
					temp_d.Hang = tempLog.Hang_Dau;
					temp_d.Cot = tempLog.Cot_Dau;
					temp_d.drawChessStart();
					if (temp_d.Phe == 0) {
						iDiemQuanDen -= temp_c.Giatri;
						soQuanAn_Den--;
						BanCo.changeScore();
					}
					if (temp_d.Phe == 1) {
						iDiemQuanTrang -= temp_c.Giatri;
						soQuanAn_Trang--;
						BanCo.changeScore();
					}
					BanCo.ViTri[temp_d.Cot][temp_d.Hang].Trong = false;
					BanCo.ViTri[temp_d.Cot][temp_d.Hang].Phe = temp_d.Phe;
					BanCo.ViTri[temp_d.Cot][temp_d.Hang].Ten = temp_d.Ten;
					
					// xoa nuoc vua khoi phuc ra khoi GameLog
					if (turn > 0) {
						GameLog.remove(turn - 1);
						turn--;
					}
					// doi luot di
					changePlayer();
					break;
			}
			
		}
	}
	
	/**
	 * Luu van co
	 */
	public static void save(File file) {
		try {
			// File file = new File(path + ".ctvn");
			file.createNewFile();
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(VanCo.isSingle + "\n");
			bw.write(VanCo.isAI + "\n");
			bw.write(VanCo.LuotDi + "\n");
			bw.write(VanCo.iDiemCuoc + "\n");
			bw.write(VanCo.iTimer + "\n");
			bw.write(VanCo.iDiemQuanDen + "\n");
			bw.write(VanCo.iDiemQuanTrang + "\n");
			bw.write(VanCo.iCuoc + "\n");
			bw.write(VanCo.iDiem + "\n");
			
			// ghi ten 2 nguoi choi
			bw.write(BanCo.strTenNguoiChoi1 + "\n");
			bw.write(BanCo.strTenNguoiChoi2 + "\n");
			//
			
			for (int i = 0; i <= 8; i++) {
				for (int j = 0; j <= 10; j++) {
					if (QuanCo.getChess(i, j) != null
							&& QuanCo.getChess(i, j).TrangThai == 1) {
						if (BanCo.ViTri[i][j].Trong == false) {
							bw.write(BanCo.ViTri[i][j].Phe
									+ BanCo.ViTri[i][j].Ten
									+ BanCo.ViTri[i][j].Cot
									+ BanCo.ViTri[i][j].Hang + "\n");
						}
					}
				}
			}
			bw.close();
			fw.close();
			JOptionPane.showMessageDialog(BanCo.jBanCo, "Ván cờ đã được lưu!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Mo van co da luu
	 */
	public static void open(String path) {
		VanCo.setVisibleChess(false);
		ManagerBoard.reset();
		BanCo.timer.stop();
		QuanCo temp = null;
		int may, nguoi, hang, cot, phe, luotdi, diem, diem1, diem2, quanco = 0, time, isTime, isDiem;
		String ten = "", ten1, ten2;
		
		try {
			File file = new File(path);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			String line = "";
			// doc nguoi - may
			nguoi = Integer.parseInt(line = br.readLine());
			may = Integer.parseInt(line = br.readLine());
			// doc gia tri luot di
			luotdi = Integer.parseInt(line = br.readLine());
			
			diem = Integer.parseInt(line = br.readLine());
			time = Integer.parseInt(line = br.readLine());
			
			diem2 = Integer.parseInt(line = br.readLine());
			diem1 = Integer.parseInt(line = br.readLine());
			isTime = Integer.parseInt(line = br.readLine());
			isDiem = Integer.parseInt(line = br.readLine());
			
			ten1 = br.readLine();
			ten2 = br.readLine();
			while ((line = br.readLine()) != null) {
				phe = Integer.parseInt(line.substring(0, 1));
				quanco = Integer.parseInt(line.substring(1, 2));
				cot = Integer.parseInt(line.substring(2, 3));
				hang = Integer.parseInt(line.substring(3));
				if (quanco == 0)
					temp = VanCo.NguoiChoi[phe].q0;
				if (quanco == 1)
					temp = VanCo.NguoiChoi[phe].q1;
				if (quanco == 2)
					temp = VanCo.NguoiChoi[phe].q2;
				if (quanco == 3)
					temp = VanCo.NguoiChoi[phe].q3;
				if (quanco == 4)
					temp = VanCo.NguoiChoi[phe].q4;
				if (quanco == 5)
					temp = VanCo.NguoiChoi[phe].q5;
				if (quanco == 6)
					temp = VanCo.NguoiChoi[phe].q6;
				if (quanco == 7)
					temp = VanCo.NguoiChoi[phe].q7;
				if (quanco == 8)
					temp = VanCo.NguoiChoi[phe].q8;
				if (quanco == 9)
					temp = VanCo.NguoiChoi[phe].q9;
				
				// Thiết lập quân cờ trên bàn cờ
				BanCo.ViTri[cot][hang].Trong = false;
				BanCo.ViTri[cot][hang].Phe = phe;
				BanCo.ViTri[cot][hang].Ten = ten;
				
				// dat quan co vao vi tri
				if (luotdi != phe)
					temp.Khoa = true;
				else
					temp.Khoa = false;
				temp.Hang = hang;
				temp.Cot = cot;
				temp.TrangThai = 1;
				temp.drawChessStart();
				temp.jLpicQuanCo.setVisible(true);
				
				// tra lai gia tri null de tiep tuc lay thong tin quan co khac
				ten = "";
			}
			
			// thiet lap luot di, ten nguoi choi, thoi gian
			VanCo.isSingle = nguoi;
			VanCo.isAI = may;
			BanCo.strTenNguoiChoi1 = ten1;
			BanCo.strTenNguoiChoi2 = ten2;
			VanCo.isPlaying = luotdi;
			VanCo.iDiemCuoc = diem;
			VanCo.iTimer = time;
			VanCo.iDiemQuanDen = diem2;
			VanCo.iDiemQuanTrang = diem1;
			VanCo.LuotDi = luotdi;
			VanCo.iDiem = isDiem;
			VanCo.iCuoc = isTime;
			
			turn = 0;
			Util.updateInfo();
			
			br.close();
			fr.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	// **********************AI****************************//
	
}
