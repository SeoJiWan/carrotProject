package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyPage extends HomeFrame {

	/*
	 * Field
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Constructor
	 */
	public MyPage() {

	}

	/*
	 * Method
	 */
	@Override
	protected void initialize() {
		// 프레임 구성
		frame = new JFrame("MyPage Form");
		frame.setBackground(Color.white);
		frame.setSize(new Dimension(frameSize[0], frameSize[1]));
		frame.setLayout(null); // layout : null --> setBounds 를 이용해 위치 직접 지정

		// 사이드 메뉴바
		JPanel sidebar = super.drawSidebar();

		// 메세지창 팝업 버튼
		RoundedButton mgPopButton = this.drawMsgPopButton();

		// 프레임에 패널 추가
		frame.add(mgPopButton);
		frame.add(sidebar);
		frame.setVisible(true);
	}

	// 메세지 버튼 생성
	private RoundedButton drawMsgPopButton() {
		// 메세지 버튼
		RoundedButton msgBtn = new RoundedButton("MSG");
		msgBtn.setBounds(200, 50, 100, 60);
		msgBtn.setBackground(Color.green);

		// 메세지 버튼 클릭시 메세지상세창 팝업
		msgBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JFrame jf1 = new JFrame();
				jf1.setLayout(null);
				jf1.setBounds(200, 150, 500, 700);
				jf1.setVisible(true);
				
				JLabel label1 = new JLabel("' wana1997 ' message box.");
//				JLabel label1 = new JLabel("' " + HomeFrame.logInMember.getIdentification() + " ' message box.");
				label1.setBounds(20, 10, 500, 50);
				label1.setFont(new Font("Arial", Font.BOLD, 18));
				jf1.add(label1);
				
				JPanel panel1 = new JPanel();
				panel1.setLayout(null);
				panel1.setBounds(20, 70, 445, 570);
				panel1.setBackground(Color.white);
				jf1.add(panel1);
				
				
				
			}
		});

		return msgBtn;
	}

	
	
	
	public static void main(String[] args) {
		new MyPage();
	}

}
