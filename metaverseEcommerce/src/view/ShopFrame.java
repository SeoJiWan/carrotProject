package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

public class ShopFrame extends HomeFrame {

	/*
	 * Field
	 */
	private static final long serialVersionUID = 1L;
	private static int quantity;
	private JTextField textCreateTotalPrice;
	private int totalPrice;
	private int saleBtn_X = 15;
	private int saleBtn_Y = 70;
	private JFrame searchFrame;

	/*
	 * Constructor
	 */
	public ShopFrame() {
		// 부모인 HomeFrame 생성자 호출로 initialize 메서드 자동 호출
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
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // window 창 닫을 시 프로그램 종료

		// 사이드 메뉴바
		JPanel sidebar = super.drawSidebar();

		// search condition 라벨
		JLabel label = new JLabel("SEARCH CONDITION   :");
		label.setBounds(200, -45, 300, 200);
		label.setFont(new Font("Arial", Font.BOLD, 19));
		label.setForeground(Color.orange);
		// 쇼케이스1 - 전체 검색
		RoundedButton frame1 = this.drawShowcaseFrame1();
		// 쇼케이스2 - 키워드로 검색
		RoundedButton frame2 = this.drawShowcaseFrame2();
		// 쇼케이스3 - 주변지역 검색
		RoundedButton frame3 = this.drawShowcaseFrame3();

		// made by 라벨 추가
		JLabel madeBy = new JLabel("@  made by wana");
		madeBy.setBounds(1250, 800, 300, 200);
		madeBy.setFont(new Font("Arial", Font.BOLD, 19));
		madeBy.setForeground(Color.orange);

		// 프레임에 패널 추가
		frame.add(madeBy);
		frame.add(frame3);
		frame.add(frame2);
		frame.add(frame1);
		frame.add(label);
		frame.add(sidebar);
		frame.setVisible(true);
	}

	/*
	 * 전체, 키워드, 주변지역 검색 처리
	 */
	// 전체 검색
	private RoundedButton drawShowcaseFrame1() {

		// 상품 전체 검색 버튼
		RoundedButton allSearchBtn = new RoundedButton("ALL");
		allSearchBtn.setBounds(430, 35, 70, 40);
		allSearchBtn.setBackground(Color.yellow);

		allSearchBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				searchFrame = new JFrame("ALL SEARCH");
				searchFrame.setLayout(null);
				searchFrame.setBounds(200, 120, 665, 800);
				searchFrame.setVisible(true);

				JLabel label = new JLabel("# SHOWCASE #");
				label.setBounds(230, -65, 300, 200);
				label.setFont(new Font("Arial", Font.BOLD, 23));
				label.setForeground(Color.orange);
				searchFrame.add(label);

				// 판매중인 상품 전체 검색
				List<SaleInfo> saleList = saleService.findAllSales(HomeFrame.logInMember.getMemberId());

				// 패널에 상품 버튼 추가
				for (SaleInfo saleInfo : saleList) {
					RoundedButton drawSaleButton = drawProductButton(saleInfo.getProductName(), saleInfo, saleList.size());
					searchFrame.add(drawSaleButton);
				}

				// 상품 버튼 추가 후 초기값으로 리셋
				saleBtn_X = 15;
				saleBtn_Y = 70;

				// 상품 등록 버튼
				RoundedButton postButton = postSaleButton();
				searchFrame.add(postButton);

				// 뒤로가기 버튼
				RoundedButton backBtn = new RoundedButton("BACK");
				backBtn.setBounds(16, 20, 70, 40);
				backBtn.setBackground(Color.pink);
				searchFrame.add(backBtn);

				// 뒤로가기 버튼 클릭시 ALL SEARCH 창 끄기
				backBtn.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						searchFrame.dispose();
					}
				});
			}
		});

		return allSearchBtn;
	}

	// 키워드로 검색
	private RoundedButton drawShowcaseFrame2() {

		// 상품 키워드로 검색 버튼
		RoundedButton keywordSearchBtn = new RoundedButton("KEYWORD");
		keywordSearchBtn.setBounds(530, 35, 70, 40);
		keywordSearchBtn.setBackground(Color.yellow);

		keywordSearchBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				// 키워드 입력할 기본 입력창 팝업
				String keyword = null;
				keyword = JOptionPane.showInputDialog(frame, "Input keyword !");

				searchFrame = new JFrame("KEYWORD SEARCH");
				searchFrame.setLayout(null);
				searchFrame.setBounds(200, 120, 665, 800);
				searchFrame.setVisible(true);

				JLabel label = new JLabel("# SHOWCASE #");
				label.setBounds(230, -65, 300, 200);
				label.setFont(new Font("Arial", Font.BOLD, 23));
				label.setForeground(Color.orange);
				searchFrame.add(label);

				// 판매중인 상품 키워드로 검색
				List<SaleInfo> saleList = saleService.findAllSalesByKeyword(HomeFrame.logInMember.getMemberId(), keyword);

				// 패널에 상품 버튼 추가
				for (SaleInfo saleInfo : saleList) {
					RoundedButton drawSaleButton = drawProductButton(saleInfo.getProductName(), saleInfo, saleList.size());
					searchFrame.add(drawSaleButton);
				}

				// 상품 버튼 추가 후 초기값으로 리셋
				saleBtn_X = 15;
				saleBtn_Y = 70;

				// 상품 등록 버튼
				RoundedButton postButton = postSaleButton();
				searchFrame.add(postButton);

				// 뒤로가기 버튼
				RoundedButton backBtn = new RoundedButton("BACK");
				backBtn.setBounds(16, 20, 70, 40);
				backBtn.setBackground(Color.pink);
				searchFrame.add(backBtn);

				// 뒤로가기 버튼 클릭시 KEYWORD SEARCH 창 끄기
				backBtn.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						searchFrame.dispose();
					}
				});

			}
		});

		return keywordSearchBtn;
	}

	// 주변지역검색
	private RoundedButton drawShowcaseFrame3() {

		// 상품 전체 검색 버튼
		RoundedButton neighborSearchBtn = new RoundedButton("NEIGHBOR");
		neighborSearchBtn.setBounds(630, 35, 70, 40);
		neighborSearchBtn.setBackground(Color.yellow);

		neighborSearchBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				searchFrame = new JFrame("NEIGHBOR SEARCH");
				searchFrame.setLayout(null);
				searchFrame.setBounds(200, 120, 665, 800);
				searchFrame.setVisible(true);

				JLabel label = new JLabel("# SHOWCASE #");
				label.setBounds(230, -65, 300, 200);
				label.setFont(new Font("Arial", Font.BOLD, 23));
				label.setForeground(Color.orange);
				searchFrame.add(label);

				//// 주변지역 검색
				// 현재 로그인된 유저의 지역 검색
				int loginMemberEmdCd = mapService.findOneByName(HomeFrame.logInMember.getAddress()).getEmdCd();
				// 주변지역 코드 및 동명 출력
				System.out.println(
						"loginMemberEmdCd = " + loginMemberEmdCd + "(" + HomeFrame.logInMember.getAddress() + ")");
				System.out.println("neighborEmdCd = " + (loginMemberEmdCd - 1) + "("
						+ mapService.findOneByCode(loginMemberEmdCd - 1).getEmdNn() + "), " + (loginMemberEmdCd + 1)
						+ "(" + mapService.findOneByCode(loginMemberEmdCd + 1).getEmdNn() + ") ");
				System.out.println();

				// 판매중인 상품 주변 지역으로 검색
				List<SaleInfo> saleList = saleService.findAllSalesByNeighbor(HomeFrame.logInMember.getMemberId(), loginMemberEmdCd - 1, loginMemberEmdCd + 1);

				// 패널에 상품 버튼 추가
				for (SaleInfo saleInfo : saleList) {
					RoundedButton drawSaleButton = drawProductButton(saleInfo.getProductName(), saleInfo, saleList.size());
					searchFrame.add(drawSaleButton);
				}

				// 상품 버튼 추가 후 초기값으로 리셋
				saleBtn_X = 15;
				saleBtn_Y = 70;

				// 상품 등록 버튼
				RoundedButton postButton = postSaleButton();
				searchFrame.add(postButton);

				// 뒤로가기
				// 뒤로가기 버튼
				RoundedButton backBtn = new RoundedButton("BACK");
				backBtn.setBounds(16, 20, 70, 40);
				backBtn.setBackground(Color.pink);
				searchFrame.add(backBtn);

				// 뒤로가기 버튼 클릭시 NEIGHBOR SEARCH 창 끄기
				backBtn.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						searchFrame.dispose();
					}
				});
			}
		});

		return neighborSearchBtn;
	}

	// 상품 버튼 생성
	private RoundedButton drawProductButton(String name, SaleInfo saleInfo, int numOfBtn) { //
		RoundedButton saleBtn = new RoundedButton(name);

		saleBtn.setFont(new Font("Arial", Font.BOLD, 18));
		saleBtn.setSize(100, 100);

		saleBtn.setLocation(saleBtn_X, saleBtn_Y);
		// 상품 버튼 그리드 형식으로 출력
		saleBtn_X += 130;
		if (saleBtn_X >= 650) {
			saleBtn_X = 15;
			saleBtn_Y += 130;
		}

		saleBtn.setBackground(Color.LIGHT_GRAY);

		// 상품 버튼 클리시 디테일창 팝업
		saleBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 디테일창
				JFrame jf = new JFrame("Details");
				jf.setLayout(null);
				jf.setBounds(900, 120, 500, 700);
				jf.setVisible(true);

				// 상품 이미지 출력
				Icon icon = new ImageIcon(ShopFrame.class.getResource("").getPath() + "img/productImg/" + saleInfo.getProductImage());
				// 디폴트 이미지 설정 - 아이콘에 이미지를 담지 못하면 width 가 -1 이 나옴
				if (icon.getIconWidth() == -1) {
					icon = new ImageIcon(ShopFrame.class.getResource("").getPath() + "img/productImg/homeview.jpg");
				}

				JButton imgBtn = new JButton();
				imgBtn.setIcon(icon);
				imgBtn.setBounds(0, 0, 500, 300);
				jf.add(imgBtn);

				// 판매상품의 디테일정보 출력할 text area 생성
				TextArea textArea = new TextArea();
				textArea.setBounds(0, 300, 500, 300);
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
						jf3.setBounds(900, 120, 500, 350);
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
									JOptionPane.showMessageDialog(frame, "Sending message successful !",
											"Congratulations !", JOptionPane.INFORMATION_MESSAGE);
									jf3.dispose();
									jf.dispose();
									searchFrame.dispose();
								} else {
									JOptionPane.showMessageDialog(frame, "Please fill in the message.",
											"Sending message failed !", JOptionPane.INFORMATION_MESSAGE);
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
						JFrame jf2 = new JFrame("ORDER");
						jf2.setLayout(null);
						jf2.setBackground(Color.BLACK);
						jf2.setBounds(900, 120, 500, 300);
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
									JOptionPane.showMessageDialog(frame, "Zero and empty must not exist.",
											"Order failed !", JOptionPane.INFORMATION_MESSAGE);
									return;
								}
								// (주문수량 > 상품재고) 인 경우 예외처리
								else if (quantity > saleInfo.getProductQuantity()) {
									JOptionPane.showMessageDialog(frame, "The product is out of stock.",
											"Order failed !", JOptionPane.INFORMATION_MESSAGE);
								} else {
									Order order = new Order();
									order.setBuyerId(HomeFrame.logInMember.getMemberId());
									order.setSaleId(saleInfo.getSaleId());
									order.setOrderQuantity(quantity);
									order.setOrderPrice(totalPrice);

									JOptionPane.showMessageDialog(frame, "Order successfully.", "Congratulation !",
											JOptionPane.INFORMATION_MESSAGE);
									orderService.createOrder(order);

									// 주문 완료 후 구매, 주문 창 다 닫기
									jf2.dispose();
									jf.dispose();
									searchFrame.dispose();

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
		saleRegBtn.setBounds(565, 20, 70, 40);
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
				panel.setLayout(null);

				JLabel lblNewLabe2 = new JLabel("# Register your product #");
				lblNewLabe2.setBounds(120, 20, 300, 35);
				lblNewLabe2.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 20));
				panel.add(lblNewLabe2);
				
				// 입력 란 시작 위치 및 간격
				int yGap = 49;
				int labelStartY = 260;
				int textStartY = 287;
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

				// 이미지 입력
				JLabel lblNewLabel2_4 = new JLabel("IMAGE");
				lblNewLabel2_4.setBounds(lableStartX, labelStartY + 3 * yGap, 100, 80);
				lblNewLabel2_4.setFont(new Font("굴림", Font.BOLD, 18));
				panel.add(lblNewLabel2_4);

				JTextField textCreateImage = new JTextField();
				textCreateImage.setBounds(textStartX, textStartY + 3 * yGap, 231, 25);
				panel.add(textCreateImage);
				textCreateImage.setColumns(10);
				
				// 상품 이름에서 이름 입력 후 엔터 치면 이미지 이름 자동 완성
				textCreateName.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {					
						textCreateImage.setText(textCreateName.getText() + ".jpg");
					}
				});

				// 상품 설명 입력 - text area
				JLabel lblNewLabel2_5 = new JLabel("DISCRIPTION");
				lblNewLabel2_5.setBounds(lableStartX, labelStartY + 4 * yGap, 120, 80);
				lblNewLabel2_5.setFont(new Font("굴림", Font.BOLD, 18));
				panel.add(lblNewLabel2_5);

				JTextArea textCreateDiscription = new JTextArea();
				textCreateDiscription.setBounds(textStartX, textStartY + 4 * yGap, 231, 100);
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
						if (textCreateName.getText().length() != 0 && textCreatePrice.getText().length() != 0
								&& textCreateQuantity.getText().length() != 0
								&& textCreateDiscription.getText().length() != 0) {
							// 상품 등록
							Product product = new Product();
							product.setName(textCreateName.getText());
							product.setPrice(Integer.parseInt(textCreatePrice.getText()));
							product.setQuantity(Integer.parseInt(textCreateQuantity.getText()));
							product.setImage(textCreateImage.getText());
							product.setDescription(textCreateDiscription.getText());

							JOptionPane.showMessageDialog(frame, "Product resistration successfully",
									"Congratulations !", JOptionPane.INFORMATION_MESSAGE);
							productService.saveProduct(product);
							
							// 상품 고유 번호(시퀀스) 받아서 Sales 테이블에 저장 
							int productId = productService.findOneProductById(textCreateName.getText()).getProductId();
							// 판매 등록
							Sale sale = new Sale();
							sale.setSellerId(HomeFrame.logInMember.getMemberId());
							sale.setProductId(productId);
							sale.setSaleStatus("판매중");
							
							// 판매 내역 DB에 저장
							saleService.createSale(sale);

							jf.dispose();
							searchFrame.dispose();
							
						// 상품 정보 한 개라도 미기입 
						} else {
							JOptionPane.showMessageDialog(frame, "Please fill product Info.",
									"Product resistration failed !", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
					}
				});
				jf.add(panel);
			}
		});
		return saleRegBtn;
	}

	// 패널에 그림을 올려주는 클래스
	class ImagePanel extends JPanel {

		private static final long serialVersionUID = 1L;
		private Image img;

		public ImagePanel(Image img) {
			this.img = img;
		}

		public void paintComponent(Graphics g) {
			g.drawImage(img, 0, 0, 1000, 1000, null);
		}
	}

//	public static void main(String[] args) {
//		new ShopFrame();
//	}

}