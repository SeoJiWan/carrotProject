package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import domain.Message;
import domain.Order;
import domain.Product;
import domain.Sale;
import domain.SaleInfo;

public class ShopFrame_preVersion extends HomeFrame {

	/*
	 * Field
	 */
	private static final long serialVersionUID = 1L;
	private static int quantity;
	private JTextField textCreateTotalPrice;
	private int totalPrice;
	private int saleBtn_X = 0;
	private int saleBtn_Y = 0;
	

	
	/*
	 * Constructor
	 */
	public ShopFrame_preVersion() {

	}

	
	/*
	 * Method
	 */
	@Override
	protected void initialize() {
		// 프레임 구성
		frame = new JFrame("SHOP Form");
		frame.setBackground(Color.white);
		frame.setSize(new Dimension(frameSize[0], frameSize[1]));
		frame.setLayout(null); // layout : null --> setBounds 를 이용해 위치 직접 지정

		// 사이드 메뉴바
		JPanel sidebar = super.drawSidebar();
		
		JLabel label = new JLabel("# SHOWCASE #");
		label.setBounds(360, -10, 300, 200);
		label.setFont(new Font("Arial", Font.BOLD, 23));
		

		// 쇼케이스
		JPanel showcasePanel = this.drawShowcasePanel();

		// 상품등록 버튼
		RoundedButton postButton = this.postSaleButton();
		

		// 프레임에 패널 추가
		frame.add(postButton);
		frame.add(sidebar);
		frame.add(label);
		frame.add(showcasePanel);
		frame.setVisible(true);

	}

	
	private JPanel drawShowcasePanel() {
		// 쇼케이스 담을 패널 - 그리드 레이아웃 - 버튼형식 -> null 로 변경
		JPanel panel = new JPanel();
		panel.setBounds(200, 120, 600, 750);
		panel.setLayout(null);
//		panel.setBackground(Color.green);
	
		List<SaleInfo> saleList = saleService.findAllSales(18);
		
		// 패널에 상품 버튼 추가
		for (SaleInfo saleInfo : saleList) {
			RoundedButton drawSaleButton = this.drawSaleButton(saleInfo.getProductName(), saleInfo, saleList.size());
			panel.add(drawSaleButton);

		}

		return panel;
	}

	// 상품 버튼 생성
	private RoundedButton drawSaleButton(String name, SaleInfo saleInfo, int numOfBtn) { //
		RoundedButton saleBtn = new RoundedButton(name);

		saleBtn.setFont(new Font("Arial", Font.BOLD, 18));
		saleBtn.setSize(100, 100);
		
		saleBtn.setLocation(saleBtn_X, saleBtn_Y);
		saleBtn_X += 130;
		if (saleBtn_X >= 520) {
			saleBtn_X = 0;
			saleBtn_Y += 130;
		}
		
		saleBtn.setBackground(Color.LIGHT_GRAY);

		// 상품 버튼 클리시 디테일창 팝업
		saleBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 디테일창
				JFrame jf = new JFrame("Details");
				jf.setLayout(null);
//				jf.setBackground(Color.BLACK);
				jf.setBounds(850, 90, 500, 700);
				jf.setVisible(true);

				/*
				 * 추후 이미지 삽입
				 */
				JLabel label = new JLabel("image");
				label.setBounds(220, 150, 150, 50);
				label.setFont(new Font("Arial", Font.BOLD, 18));
				jf.add(label);

				// 판매상품의 디테일정보 출력할 text area 생성
				TextArea textArea = new TextArea();
				textArea.setBounds(0, 350, 500, 250);
				textArea.setEditable(false); // 읽기 전용
				textArea.setText(saleInfo.toString()); // 판매상품 정보 쓰기
				jf.add(textArea);

				// 뒤로가기 버튼
				RoundedButton backBtn = new RoundedButton("BACK");
				backBtn.setBounds(300, 610, 60, 40);
				backBtn.setBackground(Color.pink);
				jf.add(backBtn);

				// 뒤로가기 버튼 클릭시 Details 창 끄기
				backBtn.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						jf.dispose();
					}
				});

				// 메세지 버튼
				RoundedButton messageBtn = new RoundedButton("MSG");
				messageBtn.setBounds(25, 610, 60, 40);
				messageBtn.setBackground(Color.pink);
				jf.add(messageBtn);

				// 메세지 버튼 클릭시 메세지 입력 창 팝업
				messageBtn.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						JFrame jf3 = new JFrame("MESSAGE");
						jf3.setLayout(null);
//						jf.setBackground(Color.BLACK);
						jf3.setBounds(850, 120, 500, 350);
						jf3.setVisible(true);

						String title = "Send a message to ' " + saleInfo.getIdentification() + " '";
						JLabel label = new JLabel(title);
						label.setBounds(10, 0, 400, 50);
						label.setFont(new Font("굴림", Font.BOLD, 16));
						jf3.add(label);

						JTextArea textCreateMessage = new JTextArea();
						textCreateMessage.setBounds(10, 70, 460, 225);
						textCreateMessage.setLineWrap(true); // 자동 줄바꿈
						jf3.add(textCreateMessage);

						// 뒤로가기 버튼
						RoundedButton backBtn = new RoundedButton("BACK");
						backBtn.setBounds(300, 10, 60, 40);
						backBtn.setBackground(Color.pink);
						jf3.add(backBtn);

						// 뒤로가기 버튼 클릭시 MESSAGE 창 끄기
						backBtn.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								jf3.dispose();
							}
						});

						// 보내기 버튼
						RoundedButton sendBtn = new RoundedButton("SEND");
						sendBtn.setBounds(400, 10, 60, 40);
						sendBtn.setBackground(Color.pink);
						jf3.add(sendBtn);

						// 보내기 버튼 클릭시 message DB 저장
						sendBtn.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {

								Message message = new Message();
								message.setSenderId(HomeFrame.logInMember.getMemberId());
								message.setReceiverId(saleInfo.getSellerId());
								message.setContent(textCreateMessage.getText());
								message.setProductId(saleInfo.getProduct_id());

//								System.out.println(message.toString());

								// 빈 메세지 예외처리
								if (textCreateMessage.getText().length() != 0) {
									messageService.writeMessage(message);
//									System.out.println("윈도우 - 메세지 전송 선공");
									JOptionPane.showMessageDialog(frame, "Sending message successful !", "Congratulations !", JOptionPane.INFORMATION_MESSAGE);
									jf3.dispose();
								}
								else {
									JOptionPane.showMessageDialog(frame, "Please fill in the message.", "Sending message failed !", JOptionPane.INFORMATION_MESSAGE);
								}

							}
						});

					}
				});

				
				// 주문버튼
				RoundedButton orderBtn = new RoundedButton("ORDER");
				orderBtn.setBounds(400, 610, 60, 40);
				orderBtn.setBackground(Color.pink);
				jf.add(orderBtn);

				// 주문 버튼 클릭시 주문 입력 폼 팝업
				orderBtn.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {

						// 주문 입력폼
						JFrame jf2 = new JFrame("Order");
						jf2.setLayout(null);
						jf2.setBackground(Color.BLACK);
						jf2.setBounds(850, 120, 500, 300);
						jf2.setVisible(true);

						// 구매상품 수량 입력
						JLabel lblNewLabel2_1 = new JLabel("QUANTITY");
						lblNewLabel2_1.setBounds(30, 100, 100, 80);
						lblNewLabel2_1.setFont(new Font("굴림", Font.BOLD, 16));
						jf2.add(lblNewLabel2_1);

						JTextField textCreateQuantity = new JTextField();
						textCreateQuantity.setBounds(180, 125, 231, 27);
						jf2.add(textCreateQuantity);
						textCreateQuantity.setColumns(10);

						// 구매상품 가격 출력
						JLabel lblNewLabel2_2 = new JLabel("TOTAL_PRICE");
						lblNewLabel2_2.setBounds(30, 149, 150, 80);
						lblNewLabel2_2.setFont(new Font("굴림", Font.BOLD, 16));
						jf2.add(lblNewLabel2_2);

						// 선택 수량에따라 가격표시
						textCreateTotalPrice = new JTextField();
						textCreateTotalPrice.setBounds(180, 173, 231, 27);
						textCreateTotalPrice.setEnabled(false); // 읽기전용
						jf2.add(textCreateTotalPrice);
						textCreateTotalPrice.setColumns(10);

						// 수량 입력 후 엔터 치면 total price 텍스트필드에 가격 출력
						textCreateQuantity.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {

								// 구매 수량 과 가격 계산 -> total 가격 텍스트필드에 뿌리기
								quantity = Integer.parseInt(textCreateQuantity.getText());
								totalPrice = saleInfo.getProductPrice() * quantity;
								textCreateTotalPrice.setText(Integer.toString(totalPrice));
								textCreateTotalPrice.setFont(new Font("굴림", Font.BOLD, 16));
							}
						});

						// 뒤로가기 버튼
						RoundedButton backBtn = new RoundedButton("BACK");
						backBtn.setBounds(300, 213, 60, 40);
						backBtn.setBackground(Color.pink);
						jf2.add(backBtn);

						// 뒤로가기 버튼 클릭시 Details 창 끄기
						backBtn.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								jf2.dispose();
							}
						});

						
						// 구매 버튼
						RoundedButton buyBtn = new RoundedButton("BUY");
						buyBtn.setBounds(400, 213, 60, 40);
						buyBtn.setBackground(Color.pink);
						jf2.add(buyBtn);

						// 구매 버튼 클릭시 order DB 저장
						buyBtn.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {

								// 주문수량 0개 or 미기입 예외처리
								if (quantity == 0 || textCreateQuantity.getText().length() == 0) {
									// 예외처리 팝업
									JOptionPane.showMessageDialog(frame, "Zero and empty must not exist.", "Order failed !", JOptionPane.INFORMATION_MESSAGE);
									return;
								}
								// (주문수량 > 상품재고) 인 경우 예외처리
								else if (quantity > saleInfo.getProductQuantity()) {
									JOptionPane.showMessageDialog(frame, "The product is out of stock.", "Order failed !", JOptionPane.INFORMATION_MESSAGE);
								}
								else {
									Order order = new Order();
									order.setBuyerId(HomeFrame.logInMember.getMemberId());
									order.setSaleId(saleInfo.getSaleId());
									order.setOrderQuantity(quantity);
									order.setOrderPrice(totalPrice);
									
									JOptionPane.showMessageDialog(frame, "Order successfully.", "Congratulation !", JOptionPane.INFORMATION_MESSAGE);
									orderService.createOrder(order);
//									System.out.println(order.toString());
									
									// 주문 완료 후 구매, 주문 창 다 닫기
									jf2.dispose();
									jf.dispose();
								
								}
							}
						});
					}
				});
			}
		});

		return saleBtn;
	}


	// 판매등록 버튼
	private RoundedButton postSaleButton() {
		RoundedButton saleRegBtn = new RoundedButton("REGISTER");
		saleRegBtn.setBounds(200, 25, 70, 40);
		saleRegBtn.setBackground(Color.pink);

		// 판매등록 버튼 클릭시 상품등록 폼 팝업
		saleRegBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				// 상품 등록 폼
				JFrame jf = new JFrame("SALE REG");
				jf.setLayout(null);
				jf.setBackground(Color.BLACK);
				jf.setBounds(850, 90, 500, 700);
				jf.setVisible(true);

				JPanel panel = new JPanel();
				panel.setBounds(0, 0, 500, 700);
//				panel.setBackground(Color.blue);
				panel.setLayout(null);

				JLabel lblNewLabe2 = new JLabel("# Register your product #");
				lblNewLabe2.setBounds(120, 20, 300, 35);
				lblNewLabe2.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 20));
				panel.add(lblNewLabe2);

				int yGap = 49;
				int labelStartY = 310;
				int textStartY = 337;
				int lableStartX = 30;
				int textStartX = 180;

				// 상품 이름 입력
				JLabel lblNewLabel2_1 = new JLabel("NAME");
				lblNewLabel2_1.setBounds(lableStartX, labelStartY, 100, 80);
				lblNewLabel2_1.setFont(new Font("굴림", Font.BOLD, 18));
				panel.add(lblNewLabel2_1);

				JTextField textCreateName = new JTextField();
				textCreateName.setBounds(textStartX, textStartY, 231, 25);
				panel.add(textCreateName);
				textCreateName.setColumns(10);

				// 상품 가격 입력
				JLabel lblNewLabel2_2 = new JLabel("PRICE");
				lblNewLabel2_2.setBounds(lableStartX, labelStartY + yGap, 100, 80);
				lblNewLabel2_2.setFont(new Font("굴림", Font.BOLD, 18));
				panel.add(lblNewLabel2_2);

				JTextField textCreatePrice = new JTextField();
				textCreatePrice.setBounds(textStartX, textStartY + yGap, 231, 25);
				panel.add(textCreatePrice);
				textCreatePrice.setColumns(10);

				// 상품 수량 입력
				JLabel lblNewLabel2_3 = new JLabel("QUANTITY");
				lblNewLabel2_3.setBounds(lableStartX, labelStartY + 2 * yGap, 100, 80);
				lblNewLabel2_3.setFont(new Font("굴림", Font.BOLD, 18));
				panel.add(lblNewLabel2_3);

				JTextField textCreateQuantity = new JTextField();
				textCreateQuantity.setBounds(textStartX, textStartY + 2 * yGap, 231, 25);
				panel.add(textCreateQuantity);
				textCreateQuantity.setColumns(10);

				// 상품 설명 입력 - text area
				JLabel lblNewLabel2_4 = new JLabel("DISCRIPTION");
				lblNewLabel2_4.setBounds(lableStartX, labelStartY + 3 * yGap, 120, 80);
				lblNewLabel2_4.setFont(new Font("굴림", Font.BOLD, 18));
				panel.add(lblNewLabel2_4);

				JTextArea textCreateDiscription = new JTextArea();
				textCreateDiscription.setBounds(textStartX, textStartY + 3 * yGap, 231, 100);
				textCreateDiscription.setLineWrap(true); // 자동 줄바꿈
				panel.add(textCreateDiscription);
				textCreateDiscription.setColumns(10);

				// 뒤로가기
				RoundedButton backBtn = new RoundedButton("BACK");
				backBtn.setBounds(290, 600, 60, 40);
				backBtn.setBackground(Color.pink);
				jf.add(backBtn);

				// 뒤로가기 클릭
				backBtn.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						jf.dispose();
					}
				});

				// 등록버튼
				RoundedButton postBtn = new RoundedButton("POST");
				postBtn.setBounds(390, 600, 60, 40);
				postBtn.setBackground(Color.pink);
				jf.add(postBtn);

				// 등록 버튼 클릭시 DB에 product, sale 저장
				postBtn.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						
						// 상품정보 미기입 예외처리
						if (textCreateName.getText().length() != 0 && textCreatePrice.getText().length() != 0 &&
								textCreateQuantity.getText().length() != 0 && textCreateDiscription.getText().length() != 0) {
							// 상품 등록
							Product product = new Product();
							product.setName(textCreateName.getText());
							product.setPrice(Integer.parseInt(textCreatePrice.getText()));
							product.setQuantity(Integer.parseInt(textCreateQuantity.getText()));
							product.setDescription(textCreateDiscription.getText());
							
							JOptionPane.showMessageDialog(frame, "Product resistration successfully", "Congratulations !", JOptionPane.INFORMATION_MESSAGE);
							productService.saveProduct(product);
							
							int productId = productService.findOneProductById(textCreateName.getText()).getProductId();
							// 판매 등록
							Sale sale = new Sale();
							sale.setSellerId(HomeFrame.logInMember.getMemberId());
							sale.setProductId(productId);
							sale.setSaleStatus("판매중");

							saleService.createSale(sale);

							jf.dispose();
						}
						else {
							JOptionPane.showMessageDialog(frame, "Please fill product Info.", "Product resistration failed !", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
					}
				});
				jf.add(panel);
			}
		});
		return saleRegBtn;
	}


	public static void main(String[] args) {
		new ShopFrame_preVersion();
	}

}