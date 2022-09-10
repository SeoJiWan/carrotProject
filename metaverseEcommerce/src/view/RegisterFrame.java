package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import domain.Member;
import domain.SuseongMap;
import repository.jdbc.JdbcMemberRepsitory;
import repository.jdbc.JdbcSuseongMapRepository;
import service.MemberService;
import service.SuseongMapService;

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
	private JComboBox<String> box;
	int isIdDupl = 1; // 1: 중복, 0: 중복X
	// 이미지 SRC
	private String mainImgSrc = RegisterFrame.class.getResource("").getPath() + "img/reg.PNG";
	// 이미지 크기
	private int frameSize[] = { 1440, 960 };
	private int mainImgSize[] = { 1200, 800 };
	private int textSize[] = { 260, 40 };
	private int registerButtonSize[] = { 220, 55 };
	// memberService
	private MemberService memberService = new MemberService(JdbcMemberRepsitory.getMemberRepository());
	private SuseongMapService mapService = new SuseongMapService(JdbcSuseongMapRepository.getMapRepository());

	/*
	 * Constructor
	 */
	public RegisterFrame() {
		initialize();
	}

	/*
	 * Method
	 */
	private void initialize() {
		frame = new JFrame("Register Form");
		frame.setBackground(Color.white);
		frame.setSize(frameSize[0], frameSize[1]);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // window 창 닫을 시 프로그램 종료

		// 배경이미지 패널 구성
		ImagePanel bgPanel = new ImagePanel(new ImageIcon(mainImgSrc).getImage());
		bgPanel.setLayout(null);
		bgPanel.setLocation(0, 100);

		// ID, PWD 텍스트 입력창
		this.drawId(bgPanel);
		this.drawPwd(bgPanel);
		this.drawAddress(bgPanel);
		this.drawPhoneNum(bgPanel);

		// REGISTER 버튼 이미지
		RoundedButton registerButton = this.drawRegisterButton();

		// 중복체크 버튼
		RoundedButton checkIdDuplButton = this.drawCheckIdDuplButton();

		// 뒤로가기 버튼
		RoundedButton backBtn = new RoundedButton("BACK");
		backBtn.setBounds(16, 20, 70, 40);
		backBtn.setBackground(Color.pink);
		frame.add(backBtn);

		// 뒤로가기 버튼 클릭시 ALL SEARCH 창 끄기
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginFrame();
				frame.dispose();
			}
		});

		// made by 라벨 추가
		JLabel madeBy = new JLabel("@  made by wana");
		madeBy.setBounds(1250, 800, 300, 200);
		madeBy.setFont(new Font("Arial", Font.BOLD, 19));
		madeBy.setForeground(Color.orange);

		// 프레임에 메인패널 추가
		frame.add(madeBy);
		frame.add(checkIdDuplButton);
		frame.add(registerButton);
		frame.getContentPane().add(bgPanel);
		frame.setVisible(true);
	}

	// ID 텍스트 입력창
	private void drawId(ImagePanel bgPanel) {
		id = new JTextField();
		id.setBounds(frameSize[0] / 2 - textSize[0] / 2, 320, textSize[0], textSize[1]);
		bgPanel.add(id);
	}

	// PWD 텍스트 입력창
	private void drawPwd(ImagePanel bgPanel) {
		pwd = new JPasswordField();
		pwd.setBounds(frameSize[0] / 2 - textSize[0] / 2, 400, textSize[0], textSize[1]);
		bgPanel.add(pwd);
	}

	// PHONE NUMBER 텍스트 입력창
	private void drawPhoneNum(ImagePanel bgPanel) {
		phonNum = new JTextField();
		phonNum.setBounds(frameSize[0] / 2 - textSize[0] / 2, 490, textSize[0], textSize[1]);
		bgPanel.add(phonNum);
	}

	// ADDRESS 텍스트 입력창
	private void drawAddress(ImagePanel bgPanel) {
		address = new JTextField();
		address.setBounds(frameSize[0] / 2 - textSize[0] / 2, 570, textSize[0], textSize[1]);

		// 지역 기입 콤보박스
		List<SuseongMap> allRegion = mapService.findAllRegion();
		List<String> regionName = new ArrayList<String>();
		for (SuseongMap region : allRegion) {
			regionName.add(region.getEmdNn());
		}
		box = new JComboBox<String>(regionName.toArray(new String[regionName.size()]));
		box.setBounds(frameSize[0] / 2 + textSize[0] / 2, 570, textSize[0] - 150, textSize[1]);

		// 콤보박스 클릭시 address에 세팅
		box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				address.setText(box.getSelectedItem().toString());
			}
		});

		bgPanel.add(box);
		bgPanel.add(address);
	}

	// REGISTER 버튼 이미지로
	private RoundedButton drawRegisterButton() {

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

//				System.out.println(id.getText().length());
//				System.out.println(pwd.getPassword().length);
//				System.out.println(phonNum.getText().length());
//				System.out.println(address.getText().length());

				// 회원가입 유저정보 미기입 예외처리
				if (isIdDupl != 1) {
					if (id.getText().length() != 0 && pwd.getPassword().length != 0 && phonNum.getText().length() != 0
							&& address.getText().length() != 0) {
						int memberId = memberService.joinMember(member);
						if (memberId != -1) {
//						System.out.println("윈도우 - 회원가입성공");
							// 알림 팝업창
							JOptionPane.showMessageDialog(frame, "Registration Successfully.", "Congratulations !",
									JOptionPane.INFORMATION_MESSAGE);
							new LoginFrame();
							frame.dispose();
						}
					} else {
						// 알림 팝업창
						JOptionPane.showMessageDialog(frame, "Please fill your Info.", "Registration Failed.",
								JOptionPane.INFORMATION_MESSAGE);
						return;

					}
				} else {
					JOptionPane.showMessageDialog(frame, "Check the duplication first.", "Registration Failed.",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});

		return btn;
	}

	// Check Duplication 버튼
	public RoundedButton drawCheckIdDuplButton() {
		RoundedButton btn = new RoundedButton("Check Dupl");
		btn.setLocation(frameSize[0] / 2 + textSize[0] / 2, 320);
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
				// 중복확인 예외처리
				isIdDupl = memberService.checkIdDupl(getId);
				if (isIdDupl == 1) {
//					System.out.println("윈도우 - 이미 존재하는 아이디입니다.");
					JOptionPane.showMessageDialog(frame, "This ID already exists.", "Duplicate check failed !",
							JOptionPane.INFORMATION_MESSAGE);
					id.setText("");

				} else {
//					System.out.println("윈도우 - 사용가능한 아이디입니다.");
					JOptionPane.showMessageDialog(frame, "This ID is available.", "Congratulations !",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});

		return btn;
	}

	class ImagePanel extends JPanel {
		// 워닝 방지 코드
		private static final long serialVersionUID = 1L;
		private Image img;

		public ImagePanel(Image img) {
			this.img = img;
		}

		public void paintComponent(Graphics g) {
			g.drawImage(img, (frameSize[0] / 2) - (mainImgSize[0] / 2 + 20),
					(frameSize[1] / 2) - (mainImgSize[1] / 2 + 150), mainImgSize[0], mainImgSize[1], null);
		}
	}

}
