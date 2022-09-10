package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import repository.jdbc.JdbcMemberRepsitory;
import service.MemberService;

public class LoginFrame extends JFrame{ // 탑레벨 컨테이너, 윈도우 창 1개

	/*
	 * Field
	 */
	// 워닝방지코드
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField id;
	private JPasswordField pwd;
	// 이미지 src
	// LoginFrame.class.getResource("").getPath()
	// --> C:\\Users\\Wana\\dev\\workSpace\\eclipse_workspace\\toyProject\\carrotProject\\metaverseEcommerce\\src\\view\\
	private String mainImgSrc = LoginFrame.class.getResource("").getPath() + "img/carrot.PNG";
	// 이미지 크기
	private int frameSize[] = { 1440, 960 };
	private int mainImgSize[] = { 300, 500 };
	private int textSize[] = { 260, 40 };
	private int loginImgsize[] = { 220, 55 };
	// memberService
	private MemberService memberService = new MemberService(JdbcMemberRepsitory.getMemberRepository());
	
	

	/*
	 * Constructor
	 */
	public LoginFrame() {
		initialize();
	}

	
	/*
	 * Method
	 */
	private void initialize() {
		frame = new JFrame("Login Form");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // window 창 닫을 시 프로그램 종료
		
		// 배경이미지 패널 구성
		ImagePanel bgPanel = new ImagePanel(new ImageIcon(mainImgSrc).getImage());
		bgPanel.setLayout(null);
		frame.setBackground(Color.white);
		frame.setSize(frameSize[0], frameSize[1]);

		// ID, PWD 텍스트 입력창
		this.drawId(bgPanel);
		this.drawPwd(bgPanel);

		// LOGIN 버튼 
		RoundedButton logInButton = this.drawLogInButton();

		// SIGN UP 버튼
		JButton signupButton = this.drawSignupButton();
		
		// made by 라벨 추가
		JLabel madeBy = new JLabel("@  made by wana");
		madeBy.setBounds(1250, 800, 300, 200);
		madeBy.setFont(new Font("Arial", Font.BOLD, 19));
		madeBy.setForeground(Color.orange);

		// 프레임에 메인패널 추가
		frame.add(madeBy);
		frame.add(signupButton);
		frame.add(logInButton);
		frame.getContentPane().add(bgPanel);
		frame.setVisible(true);
	}

	// ID 텍스트 입력창
	private void drawId(ImagePanel bgPanel) {
		id = new JTextField();
		id.setBounds(frameSize[0] / 2 - textSize[0] / 2, 480, textSize[0], textSize[1]);
		bgPanel.add(id);
	}

	// PWD 텍스트 입력창
	private void drawPwd(ImagePanel bgPanel) {
		pwd = new JPasswordField();
		pwd.setBounds(frameSize[0] / 2 - textSize[0] / 2, 580, textSize[0], textSize[1]);
		bgPanel.add(pwd);
	}

	// LOGIN 버튼 이미지로
	private RoundedButton drawLogInButton() {
		RoundedButton btn = new RoundedButton("LOG IN");
		btn.setLocation(frameSize[0] / 2 - loginImgsize[0] / 2, 650);
		btn.setSize(loginImgsize[0], loginImgsize[1]);
		btn.setFont(new Font("Arial Black", Font.BOLD, 20));
		btn.setForeground(Color.BLACK);
		btn.setBackground(Color.orange);

		// LOGIN 버튼 클릭시 동작
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeFrame.logInMember = memberService.logIn(id.getText(), Arrays.toString(pwd.getPassword()));
//				System.out.println("현재 로그인 멤버 : " + HomeFrame.logInMember);
				if (HomeFrame.logInMember == null) {
					JOptionPane.showMessageDialog(frame, "Please check ID and PWD.", "Login Failed.", JOptionPane.INFORMATION_MESSAGE);
					// 입력 창 초기화
					id.setText("");
					pwd.setText("");
				}
				else {
					JOptionPane.showMessageDialog(frame, "Login Successfully.", "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
					
					new HomeFrame();
					frame.dispose();
				}
				
			}
		});

		return btn;
	}

	// SIGN UP 버튼
	public JButton drawSignupButton() {
		JButton btn = new JButton("Sign Up");
		btn.setBounds(730, 720, 150, 50);
		btn.setFont(new Font("Arial Black", Font.BOLD, 22));
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		btn.setForeground(Color.BLACK);
		btn.setBackground(Color.white);

		// sign up 버튼 클릭시 동작
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RegisterFrame();
				frame.dispose();
			}
		});

		return btn;
	}

	public static void main(String[] args) {
		new LoginFrame();
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
