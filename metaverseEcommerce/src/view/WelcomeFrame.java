package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import domain.Member;

public class WelcomeFrame extends JFrame {

	/*
	 * Field
	 */
	protected JFrame frame;
	// 이미지 SRC - 집
//	private String mainImgSrc = "";
	// 이미지 SRC - 학교
	private String mainImgSrc = "D:\\dev\\workspace\\eclipse_workspace\\carrotProject\\metaverseEcommerce\\src\\view\\img\\homeview.jpg";
	// 컴포넌트 크기
	protected int frameSize[] = { 1440, 960 };
	protected int mainImgSize[] = { 600, 600 };
	protected int sideButtonSize[] = {150, 60}; 

	protected static final long serialVersionUID = 1L;
	protected static Member logInMember;

	/*
	 * Constructor
	 */
	public WelcomeFrame() {
		initialize();
	}

	/*
	 * Method
	 */
	protected void initialize() {
		// 프레임 구성
		frame = new JFrame("Welcome Form");
		frame.setBackground(Color.white);
		frame.setSize(new Dimension(frameSize[0], frameSize[1]));
		frame.setLayout(null); // layout : null --> setBounds 를 이용해 위치 직접 지정
		
		// 메인 패널
		ImagePanel bgPanel = new ImagePanel(new ImageIcon(mainImgSrc).getImage());
		bgPanel.setLayout(null);
		bgPanel.setBounds(100, 0, 1000, 1000);
//		bgPanel.setBackground(Color.black);
		
		// 사이드바
		JPanel sidebar = this.drawSidebar();
		
		
//		// 웰컴메세지
//		JLabel label = this.drawWelcomMessageLabel();
//		frame.add(label);

		// 프레임에 메인패널 추가
		frame.add(sidebar);
		frame.getContentPane().add(bgPanel);
		frame.setVisible(true);

	}

	protected JPanel drawSidebar() {
		
		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(null); // layout : null --> setBounds 를 이용해 위치 직접 지정
		sidePanel.setBounds(0, 0, 150, 1440);
		sidePanel.setBackground(Color.orange);
				
		RoundedButton btn1 = this.drawButton("SHOP", 0, 0);
		RoundedButton btn2 = this.drawButton("COMMUNITY", 0, sideButtonSize[1]);
		RoundedButton btn3 = this.drawButton("PAGE", 0, sideButtonSize[1]*2);
		RoundedButton btn4 = this.drawButton(".....", 0, sideButtonSize[1]*3);
		
		sidePanel.add(btn1);
		sidePanel.add(btn2);
		sidePanel.add(btn3);
		sidePanel.add(btn4);
		
		return sidePanel;
	}

	protected RoundedButton drawButton(String category, int x, int y) {
		RoundedButton btn = new RoundedButton(category);
		btn.setSize(sideButtonSize[0], sideButtonSize[1]);
		btn.setLocation(x, y);
		btn.setFont(new Font("Arial", Font.BOLD, 15));
		btn.setForeground(Color.BLACK);
		btn.setBackground(Color.ORANGE);
		// 버튼 이미지 테두리 없애기
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);

		// REGISTER 버튼 클릭시 동작
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (category.equals("SHOP")) {
					new ShopFrame();
					frame.dispose();
				}

			}
		});

		return btn;
	}
	
	// 수정 필요
	protected JLabel drawWelcomMessageLabel() {
//		String welcomMessage = "Welcome to Carrot World!, " + logInMember.getIdentification();
		String welcomMessage = "Welcome to Carrot World!, ";
		
		JLabel label = new JLabel(welcomMessage);
		
		label.setPreferredSize(new Dimension(200, 200));
		
		return label;
	}
	
		
	
	class ImagePanel extends JPanel {
		// 워닝 방지 코드
		private static final long serialVersionUID = 1L;
		private Image img;

		public ImagePanel(Image img) {
			this.img = img;
		}

		public void paintComponent(Graphics g) {
			g.drawImage(img, (frameSize[0] / 2) - (mainImgSize[0] / 2), (frameSize[1] / 2) - (mainImgSize[1] / 2 + 150),
					mainImgSize[0], mainImgSize[1], null);
		}
	}
	
	
	
	public static void main(String[] args) {
		new WelcomeFrame();
	}

}
