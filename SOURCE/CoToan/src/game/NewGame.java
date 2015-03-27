package game;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.JTextField;

import cotoan.banco.BanCo;
import cotoan.util.ManagerBoard;
import cotoan.util.Util;
import cotoan.vanco.VanCo;

public class NewGame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel lbName1, lbName2, lbTime, lbScore;
	public JTextField tfName1, tfName2, tfTime, tfScore;
	public CheckboxGroup cbgPlay;
	public Checkbox cbSingle, cbAI;
	public Container newGame;
	public JButton btnStart, btnCancel;
	public JCheckBox isTimer, isScore;
	
	public NewGame() {
		
		newGame = this.getContentPane();
		newGame.setLayout(null);
		setSize(290, 290);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		// setBackground(new Color(1, 1, 1, 0.7f));
		//
		// JLable
		lbName1 = new JLabel("Tên người chơi 1 : ");
		lbName2 = new JLabel("Tên người chơi 2 : ");
		lbTime = new JLabel("Thời gian cược : ");
		lbScore = new JLabel("Điểm cược : ");
		
		// CheckBoxGroup
		cbgPlay = new CheckboxGroup();
		cbSingle = new Checkbox("Solo", cbgPlay, true);
		cbAI = new Checkbox("AI", cbgPlay, false);
		
		// JTextField
		tfName1 = new JTextField();
		tfName2 = new JTextField();
		tfTime = new JTextField();
		tfScore = new JTextField();
		
		// setBounds
		cbSingle.setBounds(90, 50, 50, 20);
		cbAI.setBounds(150, 50, 50, 20);
		
		lbName1.setBounds(30, 70, 120, 20);
		lbName2.setBounds(30, 100, 120, 20);
		lbTime.setBounds(30, 130, 120, 20);
		lbScore.setBounds(30, 160, 120, 20);
		
		tfName1.setBounds(150, 70, 90, 20);
		tfName2.setBounds(150, 100, 90, 20);
		tfTime.setBounds(200, 130, 40, 20);
		tfScore.setBounds(200, 160, 40, 20);
		tfTime.setVisible(false);
		tfScore.setVisible(false);
		
		// Button
		btnStart = new JButton("Bắt đầu");
		btnStart.setBounds(30, 200, 100, 20);
		
		btnCancel = new JButton("Hủy");
		btnCancel.setBounds(150, 200, 100, 20);
		
		// JCheckBox
		isTimer = new JCheckBox();
		isTimer.setBounds(150, 128, 20, 20);
		isScore = new JCheckBox();
		isScore.setBounds(150, 158, 20, 20);
		
		// add
		newGame.add(cbAI);
		newGame.add(cbSingle);
		newGame.add(lbName1);
		newGame.add(lbName2);
		newGame.add(lbTime);
		newGame.add(lbScore);
		newGame.add(tfName1);
		newGame.add(tfName2);
		newGame.add(tfTime);
		newGame.add(tfScore);
		newGame.add(btnStart);
		newGame.add(btnCancel);
		newGame.add(isScore);
		newGame.add(isTimer);
		
		// Action btnStart
		btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ManagerBoard.reset();
				
				VanCo.iCuoc = 0;
				VanCo.iDiem = 0;
				String strName1 = tfName1.getText().toString() + "";
				String strName2 = tfName2.getText().toString() + "";
				if (strName1 != "" && strName2 != "") {
					
					BanCo.strTenNguoiChoi1 = strName1;
					BanCo.strTenNguoiChoi2 = strName2;
					if (cbAI.getState()) {
						VanCo.isAI = 1;
						VanCo.isSingle = 0;
						VanCo.LuotDi = 1;
						VanCo.changePlayer();
					} else {
						VanCo.isAI = 0;
						VanCo.isSingle = 1;
						VanCo.LuotDi = 0;
					}
					if (isScore.isSelected()
							&& tfScore.getText().toString() != null) {
						VanCo.iDiem = 1;
						VanCo.iDiemCuoc = Integer.parseInt(tfScore.getText()
								.toString());
						BanCo.strScore = VanCo.iDiemCuoc + "";
						BanCo.lbScore.setText(BanCo.strScore);
					} else {
						VanCo.iDiem = 0;
					}
					if (isTimer.isSelected()
							&& tfTime.getText().toString() != null) {
						VanCo.iCuoc = 1;
						VanCo.iTimer = Integer.parseInt(tfTime.getText()
								.toString());
						BanCo.strTimer = VanCo.iTimer + "";
						BanCo.lbTimer.setText(BanCo.strTimer);
					} else {
						VanCo.iCuoc = 0;
					}
					BanCo.iTimerPlayer0 = 0;
					BanCo.iTimerPlayer1 = 0;
					VanCo.iDiemQuanDen = 0;
					VanCo.iDiemQuanTrang = 0;
					BanCo.changeScore();
					Util.updateInfo();
					ManagerBoard.reset();
					VanCo.addChess();
					VanCo.addChess();
					VanCo.changePlayer();
					dispose();
				}
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				BanCo.timer.start();
				
			}
		});
		isTimer.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if (isTimer.isSelected()) {
					tfTime.setVisible(true);
				} else
					tfTime.setVisible(false);
				
			}
		});
		isScore.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (isScore.isSelected()) {
					tfScore.setVisible(true);
				} else
					tfScore.setVisible(false);
				
			}
		});
	}
}
