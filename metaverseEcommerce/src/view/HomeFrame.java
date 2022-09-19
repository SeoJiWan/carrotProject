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
import repository.jdbc.JdbcMemberRepsitory;
import repository.jdbc.JdbcMessageRepository;
import repository.jdbc.JdbcOrderRepository;
import repository.jdbc.JdbcProductRepository;
import repository.jdbc.JdbcSaleRepository;
import repository.jdbc.JdbcSuseongMapRepository;
import service.MemberService;
import service.MessageService;
import service.OrderService;
import service.ProductService;
import service.SaleService;
import service.SuseongMapService;

public class HomeFrame extends JFrame {

	/*
	 * Field
	 */
	protected JFrame frame;
	// 이미지 SRC
	private String mainImgSrc = HomeFrame.class.getResource("").getPath() + "img/homeview.jpg";
	// 컴포넌트 크기
	protected int frameSize[] = { 1440, 960 };
	protected int mainImgSize[] = { 600, 600 };
	protected int sideButtonSize[] = { 150, 60 };
	// 워닝 방지 코드
	protected static final long serialVersionUID = 1L;
	// 로그인 멤버 필드
	protected static Member logInMember;
	// 서비스 로직
	protected static MemberService memberService = new MemberService(JdbcMemberRepsitory.getMemberRepository());
	protected static SaleService saleService = new SaleService(JdbcSaleRepository.getSaleRepository());
	protected static ProductService productService = new ProductService(JdbcProductRepository.getProductRepository());
	protected static OrderService orderService = new OrderService(JdbcOrderRepository.getOrderRepository());
	protected static MessageService messageService = new MessageService(JdbcMessageRepository.getMessageRepository());
	protected static SuseongMapService mapService = new SuseongMapService(JdbcSuseongMapRepository.getMapRepository());

	/*
	 * Constructor
	 */
	public HomeFrame() {
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
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // window 창 닫을 시 프로그램 종료

		// 메인 패널
		ImagePanel bgPanel = new ImagePanel(new ImageIcon(mainImgSrc).getImage());
		bgPanel.setLayout(null);
		bgPanel.setBounds(100, 0, 1000, 600);
//		bgPanel.setBackground(Color.black);

		// 사이드바
		JPanel sidebar = this.drawSidebar();

		// 웰컴메세지
		String welcomeMessage = "Welcome to Carrot World, " + logInMember.getIdentification() + " !";
		JLabel label = new JLabel(welcomeMessage);
		label.setBounds(500, 700, 1000, 100);
		label.setFont(new Font("Arial Black", Font.BOLD, 30));

		// made by 라벨 추가
		JLabel madeBy = new JLabel("@  made by wana");
		madeBy.setBounds(1250, 800, 300, 200);
		madeBy.setFont(new Font("Arial", Font.BOLD, 19));
		madeBy.setForeground(Color.orange);

		// 프레임에 메인패널 추가
		frame.add(madeBy);
		frame.add(label);
		frame.add(sidebar);
		frame.getContentPane().add(bgPanel);
		frame.setVisible(true);

	}

	// 사이드바
	protected JPanel drawSidebar() {

		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(null); // layout : null --> setBounds 를 이용해 위치 직접 지정
		sidePanel.setBounds(0, 0, 150, 1440);
		sidePanel.setBackground(Color.orange);

		// 카테고리 이동 버튼 생성
		RoundedButton btn1 = this.drawButton("HOME", 0, 0);
		RoundedButton btn2 = this.drawButton("SHOP", 0, sideButtonSize[1]);
		RoundedButton btn3 = this.drawButton("MY PAGE", 0, sideButtonSize[1] * 2);
		RoundedButton btn4 = this.drawButton("LOGOUT", 0, 860);

		sidePanel.add(btn1);
		sidePanel.add(btn2);
		sidePanel.add(btn3);
		sidePanel.add(btn4);

		return sidePanel;
	}

	// 카테고리 버튼 메서드
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

		// 각 카테고리 버튼 클릭시 동작
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (category.equals("HOME")) {
					new HomeFrame();
					frame.dispose();
				}

				if (category.equals("SHOP")) {
					new ShopFrame();
					frame.dispose();
				}

				if (category.equals("MY PAGE")) {
					new MyPage();
					frame.dispose();
				}

				if (category.equals("LOGOUT")) {
					logInMember = null;
					new LoginFrame();
					frame.dispose();
				}
			}
		});
		return btn;
	}

	// 이미지 패널
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
