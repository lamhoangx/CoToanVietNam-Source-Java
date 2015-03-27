package cotoan.banco;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JLabel;

import cotoan.ai.AI;
import cotoan.quanco.QuanCo;
import cotoan.util.ManagerBoard;
import cotoan.util.Util;
import cotoan.vanco.VanCo;

public class OCo implements MouseListener {
	public int Hang;
	public int Cot;
	public boolean Trong;
	public String Ten;
	public int Phe;
	public JLabel jCanMove = new JLabel();
	
	public OCo() throws IOException {
		Hang = 0;
		Cot = 0;
		Trong = true;
		Ten = "";
		Phe = 0;
		Util.loadImageCanMove("data\\CanMove.png", jCanMove);
		
		jCanMove.addMouseListener(this);
	}
	
	public static void OCoTrong(int i, int j) {
		BanCo.ViTri[i][j].Trong = true;
		BanCo.ViTri[i][j].Ten = "";
		BanCo.ViTri[i][j].Phe = 2;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		JLabel lbRequest = (JLabel) e.getSource();
		if (lbRequest == jCanMove) {
			ManagerBoard.resetBoardChess();
			VanCo.saveGameLog(QuanCo.getChess(Cot, Hang), Hang, Cot);
			
			OCoTrong(QuanCo.DanhDau.Cot, QuanCo.DanhDau.Hang);
			
			if (QuanCo.getChess(Cot, Hang) != null)
				VanCo.eatChess(QuanCo.getChess(Cot, Hang));
			VanCo.putChess(QuanCo.DanhDau, Cot, Hang);
			
			if (VanCo.isAI == 1) {
				VanCo.changePlayer();
				AI.moveChessAI(VanCo.NguoiChoi);
			}
			VanCo.changePlayer();
			
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		jCanMove.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
