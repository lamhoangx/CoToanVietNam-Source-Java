package cotoan.ai;

import java.util.ArrayList;

import cotoan.banco.BanCo;
import cotoan.banco.OCo;
import cotoan.player.NguoiChoi;
import cotoan.quanco.QuanCo;
import cotoan.quanco.QuanCo.TryTestMove;
import cotoan.quanco.QuanCo._MoveAI;
import cotoan.util.ManagerBoard;
import cotoan.vanco.VanCo;
import cotoan.vanco.VanCo.NuocDi;

public class AI {
	public static QuanCo tempAB = null;// chua quan co an duoc trong nuoc di thu
	public static int x = 0, y = 0; // luu vi tri cot-hang cua quan bi an
	private static TryTestMove bestMoveAI;
	
	/**
	 * Ham luu nuoc di AI cua quan co
	 * 
	 * @param q
	 *            quan co di
	 * @param p
	 *            quan co bi an
	 * @param i
	 *            xac dinh la nuoc di an hay khong ( 0/khong, 1/co)
	 * @param x
	 *            vi tri cot cua quan co di den
	 * @param y
	 *            vi tri hang cua quan co di den
	 */
	public static void saveGameLogAI(QuanCo q, QuanCo p, int i, int x, int y) {
		VanCo.Log = new NuocDi();
		// nuoc di binh thuong
		if (i == 0) {
			VanCo.turn++;
			VanCo.Log.Dau = q;
			VanCo.Log.Hang_Dau = q.Hang;
			VanCo.Log.Cot_Dau = q.Cot;
			VanCo.Log.Hang_Cuoi = p.Hang;
			VanCo.Log.Cot_Cuoi = p.Cot;
			VanCo.GameLog.add(VanCo.Log);
		}
		// an quan
		if (i == 1) {
			VanCo.turn++;
			VanCo.Log.Dau = p;
			VanCo.Log.Hang_Dau = p.Hang;
			VanCo.Log.Cot_Dau = p.Cot;
			VanCo.Log.Cuoi = q;
			VanCo.Log.Hang_Cuoi = x;
			VanCo.Log.Cot_Cuoi = y;
			VanCo.GameLog.add(VanCo.Log);
		}
	}
	
	/**
	 * Ham an quan co cua doi phuong trong nuoc di AI
	 * 
	 * @param q
	 *            quan co di
	 * @param i
	 *            vi tri cot di den
	 * @param j
	 *            vi tri hang di den
	 */
	public static void moveEatChessAI(QuanCo q, int i, int j,
			NguoiChoi[] NguoiChoi) {
		int index = 1; // phe cua doi phuong (1)
		QuanCo temp = null; // quan co bi an
		// lay quan co dang tro den trong nuoc di AI
		if (BanCo.ViTri[i][j].Ten == "0")
			temp = NguoiChoi[index].q0;
		if (BanCo.ViTri[i][j].Ten == "1")
			temp = NguoiChoi[index].q1;
		if (BanCo.ViTri[i][j].Ten == "2")
			temp = NguoiChoi[index].q2;
		if (BanCo.ViTri[i][j].Ten == "3")
			temp = NguoiChoi[index].q3;
		if (BanCo.ViTri[i][j].Ten == "4")
			temp = NguoiChoi[index].q4;
		if (BanCo.ViTri[i][j].Ten == "5")
			temp = NguoiChoi[index].q5;
		if (BanCo.ViTri[i][j].Ten == "6")
			temp = NguoiChoi[index].q6;
		if (BanCo.ViTri[i][j].Ten == "7")
			temp = NguoiChoi[index].q7;
		if (BanCo.ViTri[i][j].Ten == "8")
			temp = NguoiChoi[index].q8;
		if (BanCo.ViTri[i][j].Ten == "9")
			temp = NguoiChoi[index].q9;
		
		if (temp.Phe != q.Phe) {
			// marked = false;
			saveGameLogAI(temp, temp, 0, i, j);
			saveGameLogAI(q, q, 1, i, j);
			VanCo.eatChess(temp);
			
			OCo.OCoTrong(q.Cot, q.Hang);
			
			q.Hang = j;
			q.Cot = i;
			BanCo.ViTri[i][j].Trong = false;
			BanCo.ViTri[i][j].Phe = q.Phe;
			BanCo.ViTri[i][j].Ten = q.Ten;
			q.drawChessStart();
		}
	}
	
	/**
	 * Ham di chuyen quan co trong nuoc di AI
	 * 
	 * @param q
	 *            quan co duoc di
	 * @param i
	 *            hang ma quan co di toi
	 * @param j
	 *            cot ma quan co di toi
	 */
	public static void moveAI(QuanCo q, int i, int j, NguoiChoi[] NguoiChoi) {
		// neu vi tri di den la vi tri trong
		if (BanCo.ViTri[i][j].Trong == true) {
			OCo.OCoTrong(q.Cot, q.Hang); // tra lai o trong
			saveGameLogAI(q, q, 0, i, j);// luu vao GameLog
			// Dat quan co
			q.Hang = j;
			q.Cot = i;
			BanCo.ViTri[i][j].Trong = false;
			BanCo.ViTri[i][j].Phe = q.Phe;
			BanCo.ViTri[i][j].Ten = q.Ten;
			q.drawChessStart();
		}
		// vi tri chua quan cua doi phuong
		if (BanCo.ViTri[i][j].Trong == false) {
			if (BanCo.ViTri[i][j].Phe == 1)
				moveEatChessAI(q, i, j, NguoiChoi);
		}
		ManagerBoard.resetBoardChess();
	}
	
	/**
	 * Ham sinh nuoc di AI cua quan co
	 */
	public static void moveChessAI(NguoiChoi[] player) {
		int iCheckEat = 0; // bien kiem tra co the an duoc quan
		ArrayList<QuanCo> arrQuanCoE = QuanCo.getListChess();
		for (int k = 1; k < 10; k++) {
			QuanCo temp = arrQuanCoE.get(k);
			temp.moveAI = new ArrayList<>();
			temp.cout_MoveAI = 0;
			temp.moveAIQ(); // sinh tap toan bo cac nuoc co the di de duyet
			int x = temp.cout_MoveAI;
			for (int i = 0; i < x; i++) {
				// xac dinh co the an duoc quan cua doi phuong
				if (BanCo.ViTri[temp.moveAI.get(i).movei][temp.moveAI.get(i).movej].Trong == false
						&& BanCo.ViTri[temp.moveAI.get(i).movei][temp.moveAI
								.get(i).movej].Phe == 1) {
					moveAI(temp, temp.moveAI.get(i).movei,
							temp.moveAI.get(i).movej, player);
					iCheckEat = 1;
					break;
				}
			}
			if (iCheckEat == 1)
				break;
		}
		// neu khong an duoc quan cua doi phuong thi dung thuat toan de sinh
		// nuoc di tiep theo
		if (iCheckEat == 0) {
			// tao nuoc di thu
			QuanCo.TryTestMove test = new QuanCo.TryTestMove();
			// thuat toan
			alphaBeta(test, 0, 1, -100, 100);
			// di nuoc di tot nhat ma thuat toan tim duoc
			moveAI(bestMoveAI.q, bestMoveAI.movei, bestMoveAI.movej, player);
		}
		// kiem tra dieu kien thang sau khi di
		// IsWin();
		
	}
	
	/**
	 * Ham tinh gia tri do tot cua nuoc di
	 * 
	 * @return gia tri do tot
	 */
	public static int evaluate() {
		int value = 0;
		ArrayList<QuanCo> arrQuanCoE = QuanCo.getListChess();
		// dem so quan
		int m = 0, nc = 0;// dem so quan tren ban co cua may va nguoi choi
		for (int i = 0; i <= 8; i++) {
			for (int j = 0; j <= 10; j++) {
				if (BanCo.ViTri[i][j].Phe == 0)
					m++;
				if (BanCo.ViTri[i][j].Phe == 1)
					nc++;
			}
		}
		if (m > nc)
			value += 30;
		// kiem tra cac nuoc di co the bat duoc quan cua doi phuong
		for (int k = 1; k < 10; k++) {
			QuanCo temp = arrQuanCoE.get(k);
			int iTempCoutMoveAI = temp.cout_MoveAI;
			temp.moveAIQ();
			for (int i = iTempCoutMoveAI; i < temp.moveAI.size(); i++) {
				if (BanCo.ViTri[temp.moveAI.get(i).movei][temp.moveAI.get(i).movej].Trong == false
						&& BanCo.ViTri[temp.moveAI.get(i).movei][temp.moveAI
								.get(i).movej].Phe == 1) {
					if (BanCo.ViTri[temp.moveAI.get(i).movei][temp.moveAI
							.get(i).movej].Ten == "0")
						value += 1000;
					if (BanCo.ViTri[temp.moveAI.get(i).movei][temp.moveAI
							.get(i).movej].Ten == "9")
						value += 100;
					if (BanCo.ViTri[temp.moveAI.get(i).movei][temp.moveAI
							.get(i).movej].Ten == "8")
						value += 90;
					if (BanCo.ViTri[temp.moveAI.get(i).movei][temp.moveAI
							.get(i).movej].Ten == "7")
						value += 80;
					if (BanCo.ViTri[temp.moveAI.get(i).movei][temp.moveAI
							.get(i).movej].Ten == "6")
						value += 70;
					if (BanCo.ViTri[temp.moveAI.get(i).movei][temp.moveAI
							.get(i).movej].Ten == "5")
						value += 60;
					if (BanCo.ViTri[temp.moveAI.get(i).movei][temp.moveAI
							.get(i).movej].Ten == "4")
						value += 50;
					if (BanCo.ViTri[temp.moveAI.get(i).movei][temp.moveAI
							.get(i).movej].Ten == "3")
						value += 40;
					if (BanCo.ViTri[temp.moveAI.get(i).movei][temp.moveAI
							.get(i).movej].Ten == "2")
						value += 30;
					if (BanCo.ViTri[temp.moveAI.get(i).movei][temp.moveAI
							.get(i).movej].Ten == "1")
						value += 20;
				}
			}
		}
		
		return value;
	}
	
	/**
	 * Ham sinh nuoc di thu cua nuoc co
	 * 
	 * @param q
	 *            Quan di di thu
	 * @param phe
	 *            phe cua quan co
	 * @param tryMove
	 *            nuoc co di thu
	 * @param temp
	 *            quan bi an trong nuoc di thu
	 * @param i
	 *            gia tri cot di chuyen den
	 * @param j
	 *            gia tri hang di chuyen den
	 */
	public static void tryMove(QuanCo q, int phe, QuanCo._MoveAI tryMove,
			QuanCo temp, int i, int j) {
		OCo.OCoTrong(q.Cot, q.Hang);
		
		i = q.Cot;
		j = q.Hang;
		x = i;
		y = j;
		// di thu
		if (BanCo.ViTri[tryMove.movei][tryMove.movej].Trong == true) {
			q.Hang = tryMove.movej;
			q.Cot = tryMove.movei;
			BanCo.ViTri[q.Cot][q.Hang].Trong = false;
			BanCo.ViTri[q.Cot][q.Hang].Phe = q.Phe;
			BanCo.ViTri[q.Cot][q.Hang].Ten = q.Ten;
		} else {
			int index = 2;
			if (QuanCo.DanhDau.Phe == 0)
				index = 1;
			else
				index = 0;
			
			if (BanCo.ViTri[tryMove.movei][tryMove.movej].Ten == "1")
				temp = VanCo.NguoiChoi[index].q1;
			if (BanCo.ViTri[tryMove.movei][tryMove.movej].Ten == "2")
				temp = VanCo.NguoiChoi[index].q2;
			if (BanCo.ViTri[tryMove.movei][tryMove.movej].Ten == "3")
				temp = VanCo.NguoiChoi[index].q3;
			if (BanCo.ViTri[tryMove.movei][tryMove.movej].Ten == "4")
				temp = VanCo.NguoiChoi[index].q4;
			if (BanCo.ViTri[tryMove.movei][tryMove.movej].Ten == "5")
				temp = VanCo.NguoiChoi[index].q5;
			if (BanCo.ViTri[tryMove.movei][tryMove.movej].Ten == "6")
				temp = VanCo.NguoiChoi[index].q6;
			if (BanCo.ViTri[tryMove.movei][tryMove.movej].Ten == "7")
				temp = VanCo.NguoiChoi[index].q7;
			if (BanCo.ViTri[tryMove.movei][tryMove.movej].Ten == "8")
				temp = VanCo.NguoiChoi[index].q8;
			if (BanCo.ViTri[tryMove.movei][tryMove.movej].Ten == "9")
				temp = VanCo.NguoiChoi[index].q9;
			
			temp.TrangThai = 0;
			tempAB = temp;
		}
		ManagerBoard.resetCanMove();
	}
	
	/**
	 * Ham xoa nuoc di thu
	 * 
	 * @param q
	 *            quan co di thu
	 * @param temp
	 *            quan co an duoc trong nuoc di thu
	 * @param i
	 *            vi tri cot cua nuoc an
	 * @param j
	 *            vi tri hang cua nuoc an
	 */
	public static void removeTryMove(QuanCo q, QuanCo temp, int i, int j) {
		
		OCo.OCoTrong(q.Cot, q.Hang);
		
		q.Hang = j;
		q.Cot = i;
		if (temp != null)
			temp.TrangThai = 1;
		BanCo.ViTri[q.Cot][q.Hang].Trong = false;
		BanCo.ViTri[q.Cot][q.Hang].Phe = q.Phe;
		BanCo.ViTri[q.Cot][q.Hang].Ten = q.Ten;
		
		// BanCo.ResetCanMove();
	}
	
	// Thuat toan Minimax ket hop cat tia AlphaBeta
	
	/**
	 * Ham Minimax ket hop cat tia AlphaBeta
	 * 
	 * @param tryTestMove
	 *            nuoc di thu
	 * @param phe
	 *            phe di thu (0)
	 * @param depth
	 *            do sau duyet cua thuat toan
	 * @param alpha
	 *            gia tri alpha
	 * @param beta
	 *            gia tri beta
	 * @return
	 */
	public static int alphaBeta(QuanCo.TryTestMove tryTestMove, int phe,
			int depth, int alpha, int beta) {
		int iCheckCountRun = 0;
		int iDepthRun = 1000;
		if (depth == 0) {
			return evaluate();
		}
		int bestValue = alpha;
		if (phe == 1) {
			bestValue = beta;
		}
		while (alpha < beta) {
			if (phe == 0) {
				for (int i = 1; i < 10; i++) {
					ArrayList<QuanCo> arrChess = QuanCo.getListChess();
					QuanCo p = arrChess.get(i);
					if (p.Phe == 0) {// duyet quan cua may
						if (p.TrangThai == 1) {
							int iIndexChessMove = 0;
							p.moveAI = new ArrayList<>();
							p.cout_MoveAI = 0;
							p.moveAIQ();// sinh cac nuoc co the di cua quan co p
							iIndexChessMove = p.cout_MoveAI;
							// duyet toan bo nuoc co the di cua quan co p dang
							// xet
							
							for (int j = 0; j < iIndexChessMove; j++) {
								// QuanCo temp = null;
								// int x = 0, y = 0;// luu diem hien tai de quay
								// ve
								// tao nuoc di thu
								TryTestMove nextMove = new TryTestMove();
								
								nextMove.q = p;
								nextMove.movei = p.moveAI.get(j).movei;
								nextMove.movej = p.moveAI.get(j).movej;
								
								// di thu
								tryMove(p, phe, p.moveAI.get(j), tempAB, x, y);
								// de quy AlphaBeta de tim gia tri tot nhat co
								// the tim duoc trong bai toan
								int value = alphaBeta(nextMove, 1 - phe,
										depth - 1, alpha, beta);
								// huy nuoc di thu
								removeTryMove(p, tempAB, x, y);
								if (value == bestValue) {
									iCheckCountRun++;
								}
								// neu tim duoc nuoc di tot hon nuoc hien tai
								if (value > bestValue) {
									alpha = bestValue = value;
									
									// luu nuoc di tot nhat cua quan co khi nhan
									// duoc gia tri di tot nhat
									tryTestMove.q = nextMove.q;
									tryTestMove.movei = nextMove.movei;
									tryTestMove.movej = nextMove.movej;
									iCheckCountRun = 0; // cap nhat lai bo dem
														// so lan chay cua thuat
														// toan
								}
								if (iCheckCountRun == iDepthRun) {
									break;
								}
							}
						}
					}
					if (iCheckCountRun == iDepthRun) {
						break;
					}
				}
			}
			if (phe == 1) {
				for (int i = 11; i < 20; i++) {
					ArrayList<QuanCo> arrChess = QuanCo.getListChess();
					QuanCo p = arrChess.get(i);
					if (p.Phe == 1) {
						p.cout_MoveAI = 0;
						p.moveAIQ();
						int k = p.cout_MoveAI;
						for (int j = 0; j < k; j++) {
							QuanCo temp = null;
							int x = 0, y = 0;// luu diem hien tai de quay ve
							QuanCo.TryTestMove nextMove = new QuanCo.TryTestMove();
							_MoveAI tempIndex = p.moveAI.get(j);
							nextMove.q = p;
							nextMove.movei = tempIndex.movei;
							nextMove.movej = tempIndex.movej;
							
							tryMove(p, phe, tempIndex, temp, x, y);// di thu
																	// quan co
							
							int value = alphaBeta(nextMove, 1 - phe, depth - 1,
									alpha, beta);
							removeTryMove(p, temp, x, y);// huy nuoc di thu
							
							if (value < bestValue) {
								beta = bestValue = value;
							}
							
						}
					}
				}
			}
			if (iCheckCountRun == iDepthRun) {
				break;
			}
		}
		bestMoveAI = tryTestMove;
		return bestValue;
	}
	
}
