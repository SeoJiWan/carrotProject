package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import domain.Member;
import repository.jdbc.JdbcMemberRepsitory;
import service.MemberService;

public class RegisterFrame extends JFrame {

	/*
	 * Field
	 */
	// 워닝 방지코드
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField id;
	private JPasswordField pwd;
	private JTextField address;
	private JTextField phonNum;
	// 이미지 SRC - 집
//	private String mainImgSrc = "";
	// 이미지 SRC - 학교
	private String mainImgSrc = "";
	// 이미지 크기
	private int frameSize[] = { 1440, 960 };
	private int mainImgSize[] = { 300, 500 };
	private int textSize[] = { 260, 40 };
	private int registerButtonSize[] = { 220, 55 };
	// memberService
	private MemberService memberService;

	/*
	 * Constructor
	 */
	public RegisterFrame() {
		initialize();
		memberService = new MemberService(JdbcMemberRepsitory.getMemberRepository());
	}

	/*
	 * Method
	 */
	private void initialize() {
		frame = new JFrame("Register Form");
		frame.setBackground(Color.white);
		frame.setSize(frameSize[0], frameSize[1]);

		// 배경이미지 패널 구성
		ImagePanel bgPanel = new ImagePanel(new ImageIcon(mainImgSrc).getImage());
		bgPanel.setLayout(null);

		// ID, PWD 텍스트 입력창
		this.drawId(bgPanel);
		this.drawPwd(bgPanel);
		this.drawAddress(bgPanel);
		this.drawPhoneNum(bgPanel);

		// REGISTER 버튼 이미지
		this.drawRegisterButton(bgPanel);

		// SIGN UP 버튼
		this.drawCheckIdDuplButton(bgPanel);

		// 프레임에 메인패널 추가
		frame.getContentPane().add(bgPanel);
		frame.setVisible(true);
	}

	// ID 텍스트 입력창
	private void drawId(ImagePanel bgPanel) {
		id = new JTextField();
		id.setBounds(frameSize[0] / 2 - textSize[0] / 2, 280, textSize[0], textSize[1]);
		bgPanel.add(id);
	}

	// PWD 텍스트 입력창
	private void drawPwd(ImagePanel bgPanel) {
		pwd = new JPasswordField();
		pwd.setBounds(frameSize[0] / 2 - textSize[0] / 2, 380, textSize[0], textSize[1]);
		bgPanel.add(pwd);
	}

	// ADDRESS 텍스트 입력창
	private void drawAddress(ImagePanel bgPanel) {
		address = new JTextField();
		address.setBounds(frameSize[0] / 2 - textSize[0] / 2, 480, textSize[0], textSize[1]);
		bgPanel.add(address);
	}

	// PHONE NUMBER 텍스트 입력창
	private void drawPhoneNum(ImagePanel bgPanel) {
		phonNum = new JTextField();
		phonNum.setBounds(frameSize[0] / 2 - textSize[0] / 2, 580, textSize[0], textSize[1]);
		bgPanel.add(phonNum);
	}

	// REGISTER 버튼 이미지로
	private void drawRegisterButton(ImagePanel bgPanel) {
		RoundedButton btn = new RoundedButton("REGISTER");
		btn.setLocation(frameSize[0] / 2 - registerButtonSize[0] / 2, 650);
		btn.setSize(registerButtonSize[0], registerButtonSize[1]);
		btn.setFont(new Font("Arial Black", Font.BOLD, 20));
		btn.setForeground(Color.BLACK);
		btn.setBackground(Color.ORANGE);
		// 버튼 이미지 테두리 없애기
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);

		// REGISTER 버튼 클릭시 동작
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 회원가입
				Member member = new Member();
				
				member.setIdentification(id.getText());
				member.setPassword(Arrays.toString(pwd.getPassword()));
				member.setPhoneNumber(phonNum.getText());
				member.setAddress(address.getText());
				
				int memberId = memberService.joinMember(member);
				
				if (memberId != -1) {
					System.out.println("윈도우 - 회원가입성공");
					new LoginFrame();
					frame.dispose();
					
				}
				else {
					System.out.println("윈도우 - 회원가입실패");
				}
				
			}
		});

		bgPanel.add(btn);
	}

	// Check Duplication 버튼
	public void drawCheckIdDuplButton(ImagePanel bgPanel) {
		RoundedButton btn = new RoundedButton("Check Dupl");
		btn.setLocation(frameSize[0] / 2 + textSize[0] / 2, 280);
		btn.setSize(150, 40);
		btn.setFont(new Font("Arial Black", Font.BOLD, 15));
		btn.setForeground(Color.BLACK);
		btn.setBackground(Color.ORANGE);
		// 버튼 이미지 테두리 없애기
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);

		// Check Duplication 버튼 클릭시 동작
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String getId = id.getText();
				boolean isIdDupl = memberService.checkIdDupl(getId);
				if (isIdDupl) {
					System.out.println("윈도우 - 이미 존재하는 아이디입니다.");
				} else {
					System.out.println("윈도우 - 사용가능한 아이디입니다.");
				}

			}
		});

		bgPanel.add(btn);
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

}
