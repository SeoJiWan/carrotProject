package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import domain.SaleInfo;
import oracle.net.aso.l;
import repository.jdbc.JdbcSaleRepository;
import service.SaleService;

public class ShopFrame extends WelcomeFrame {

	/*
	 * Field
	 */
	private static final long serialVersionUID = 1L;
	private static SaleService saleService = new SaleService(JdbcSaleRepository.getSaleRepository());

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
		JPanel showcasePanel = this.drawShowcasePanel();

//		JLabel label = new JLabel("이건 한글이 왜 되냐...");
//		label.setBounds(0, 32, 600, 100);
//		;
//		showcasePanel.add(label);

		List<SaleInfo> saleList = this.getSaleList();
//		int y = 0;
//		for (SaleInfo saleInfo : saleList) {
//			JLabel drawSaleLabel = this.drawSaleLabel(saleInfo.toString(), y);
//			showcasePanel.add(drawSaleLabel);
//			y += 32;
//		}

		for (SaleInfo saleInfo : saleList) {
			RoundedButton drawSaleButton = this.drawSaleButton(saleInfo.getProductName(), saleInfo.toString());
			showcasePanel.add(drawSaleButton);
		}

		// 상품디테일

		// 프레임에 메인패널 추가

		frame.add(sidebar);
		frame.add(showcasePanel);
		frame.setVisible(true);

	}

	private JPanel drawShowcasePanel() {
//		// 쇼케이스 담을 패널
//		JPanel panel = new JPanel();
//		panel.setBounds(200, 70, 600, 750);
//		panel.setLayout(null);
//		panel.setBackground(Color.green);
//
//		return panel;

		// 쇼케이스 담을 패널 - 그리드 레이아웃 - 버튼형식
		JPanel panel = new JPanel();
		panel.setBounds(200, 70, 600, 750);
		panel.setLayout(new GridLayout(6, 6, 30, 30));
//		panel.setBackground(Color.green);

		return panel;
	}

//	private JLabel drawSaleLabel(String info, int y) { //
//		JLabel label = new JLabel(info);
//		label.setBounds(0, y, 600, 100);
//		label.setFont(new Font("Arial", Font.BOLD, 12));
//
//		label.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				JFrame jf = new JFrame("Details");
//				jf.setLayout(null);
//				jf.setBackground(Color.BLACK);
//				jf.setBounds(850, 90, 500, 700);
//				jf.setVisible(true);
//
//				TextArea textArea = new TextArea();
//				textArea.setBounds(0, 350, 500, 350);
//
//				textArea.setEditable(false);
//				textArea.setText(info);
//
//				jf.add(textArea);
//			}
//		});
//
//		return label;
//	}
	
	private RoundedButton drawSaleButton(String name, String info) { //
		RoundedButton btn = new RoundedButton(name);
		
		btn.setFont(new Font("Arial", Font.BOLD, 18));
		btn.setBackground(Color.LIGHT_GRAY);

		btn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JFrame jf = new JFrame("Details");
				jf.setLayout(null);
				jf.setBackground(Color.BLACK);
				jf.setBounds(850, 90, 500, 700);
				jf.setVisible(true);

				TextArea textArea = new TextArea();
				textArea.setBounds(0, 350, 500, 350);

				textArea.setEditable(false);
				textArea.setText(info);

				jf.add(textArea);
			}
		});

		return btn;
	}

	private List<SaleInfo> getSaleList() {
		return saleService.findAllSales();
	}

	public static void main(String[] args) {
		new ShopFrame();
	}

}
