package game;

import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import cotoan.banco.BanCo;

public class Game extends JFrame implements MouseListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	public JFrame jF = this;
	
	private final Container cont;
	private final BanCo board;
	public JFrame jFgame = this;
	
	public Game() {
		super("Cờ Toán Việt Nam");
		cont = this.getContentPane();
		cont.setLayout(null);
		//
		
		board = BanCo.getInstance();
		this.cont.add(this.board);
		this.board.setBounds(1, 1, 750, 685);
		this.board.setVisible(true);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
