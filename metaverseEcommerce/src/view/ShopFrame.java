package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import repository.jdbc.JdbcProductRepository;
import service.ProductService;

public class ShopFrame extends WelcomeFrame {

	/*
	 * Field
	 */
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService(JdbcProductRepository.getProductRepository());
	

	/*
	 * Constructor
	 */
	public ShopFrame() {
		
	}


	/*
	 * Method
	 */
	@Override
	protected void initialize() {
		// 프레임 구성
		frame = new JFrame("Welcome Form");
		frame.setBackground(Color.white);
		frame.setSize(new Dimension(frameSize[0], frameSize[1]));
		frame.setLayout(null); // layout : null --> setBounds 를 이용해 위치 직접 지정
		
		// 사이드 메뉴바
		JPanel sidebar = this.drawSidebar();
		
		// 쇼케이스
		JPanel showcase = this.drawShowcase();
		
		// 프레임에 메인패널 추가
		frame.add(sidebar);
		frame.add(showcase);
		frame.setVisible(true);


	}
	
	private JPanel drawShowcase() {
		// 쇼케이스 담을 패널
		JPanel panel = new JPanel();
		panel.setBounds(160, 10, 700, 700);
		panel.setLayout(null);
		panel.setBackground(Color.green);
		
		// TEXT AREA 생성
		TextArea textArea = new TextArea();
		textArea.setEditable(false); // 읽기 전용 설정
		
		// 스크롤 기능
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(textArea);
		scrollPane.setBounds(0, 0, 700, 700);
		
		panel.add(scrollPane);
		
		
		// sales 테이블 전체조회
		for (int i = 0; i < 100; i++) {
			textArea.append("asdfasfsafsdafsadfasdfsadsdaf\n");
			
		}
		
		
		return panel;
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		new ShopFrame();
	}

	
	

	

}
