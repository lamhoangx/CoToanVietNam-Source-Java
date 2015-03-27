package cotoan.util;

import cotoan.banco.BanCo;
import cotoan.player.NguoiChoi;

public class ManagerBoard {
	/**
	 * Dinh dang lai ban co
	 */
	public static void resetBoardChess() {
		for (int i = 0; i <= 8; i++) {
			for (int j = 0; j <= 10; j++) {
				if (BanCo.ViTri[i][j].Trong == true) {
					BanCo.ViTri[i][j].Hang = j;
					BanCo.ViTri[i][j].Cot = i;
					BanCo.ViTri[i][j].Trong = true;
					BanCo.ViTri[i][j].Ten = "";
					BanCo.ViTri[i][j].Phe = 0;
					
				}
				BanCo.ViTri[i][j].jCanMove.setVisible(false);
			}
		}
	}
	
	/**
	 * gan visible cac hinh anh jCanMove la false
	 */
	public static void resetCanMove() {
		for (int i = 0; i <= 8; i++) {
			for (int j = 0; j <= 10; j++) {
				BanCo.ViTri[i][j].jCanMove.setVisible(false);
			}
		}
	}
	
	/**
	 * Khoi tao lai ban co tu dau
	 */
	public static void reset() {
		for (int i = 0; i <= 8; i++) {
			for (int j = 0; j <= 10; j++) {
				BanCo.ViTri[i][j].Hang = j;
				BanCo.ViTri[i][j].Cot = i;
				BanCo.ViTri[i][j].Trong = true;
				BanCo.ViTri[i][j].Ten = "";
				BanCo.ViTri[i][j].Phe = 0;
				
				// }
				BanCo.ViTri[i][j].jCanMove.setVisible(false);
			}
		}
		// VanCo.iCuoc = 0;
		// VanCo.iDiem = 0;
		// VanCo.iTimer = -1;
		// timer.stop();
	}
	
	/**
	 * Khoa Ban Co
	 */
	public static void lockBoard(NguoiChoi[] NguoiChoi) {
		for (int i = 0; i <= 8; i++) {
			for (int j = 0; j <= 10; j++) {
				BanCo.ViTri[i][j].Hang = j;
				BanCo.ViTri[i][j].Cot = i;
				BanCo.ViTri[i][j].Trong = false;
				BanCo.ViTri[i][j].Ten = "";
				BanCo.ViTri[i][j].Phe = 0;
				BanCo.ViTri[i][j].jCanMove.setVisible(false);
			}
		}
		Util.lockChess(NguoiChoi);
		
	}
	
}
