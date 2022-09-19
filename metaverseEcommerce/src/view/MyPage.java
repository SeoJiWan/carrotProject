package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import domain.Message;
import domain.MessageInfo;
import domain.MyTrade;
import domain.TopInfo;

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
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // window 창 닫을 시 프로그램 종료

		// 사이드 메뉴바
		JPanel sidebar = super.drawSidebar();

		// 메세지창 팝업 버튼
		RoundedButton mgPopButton = this.drawMsgPopButton();
//
		// 내 거래내역 조회 버튼
		RoundedButton trdPopButton = this.drawMyTradeButton();

		// 구매왕, 판매왕 그래프 조회 버튼
		RoundedButton graphPopBtn = this.drawGraphButton();

		// made by 라벨 추가
		JLabel madeBy = new JLabel("@  made by wana");
		madeBy.setBounds(1250, 800, 300, 200);
		madeBy.setFont(new Font("Arial", Font.BOLD, 19));
		madeBy.setForeground(Color.orange);

		// 프레임에 패널 추가
		frame.add(madeBy);
		frame.add(graphPopBtn);
		frame.add(trdPopButton);
		frame.add(mgPopButton);
		frame.add(sidebar);
		frame.setVisible(true);
	}

	// 메세지 버튼 생성
	private RoundedButton drawMsgPopButton() {
		// 메세지 버튼
		RoundedButton msgBtn = new RoundedButton("MSG");
		msgBtn.setBounds(200, 50, 100, 60);
		msgBtn.setFont(new Font("Arial", Font.BOLD, 16));
		msgBtn.setBackground(Color.green);

		// 메세지 버튼 클릭시 메세지상세창 팝업
		msgBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JFrame jf1 = new JFrame("MESSAGE BOX");
				jf1.setLayout(null);
				jf1.setBounds(200, 150, 500, 700);
				jf1.setVisible(true);

				JLabel label1 = new JLabel("' " + HomeFrame.logInMember.getIdentification() + " '  message box.");
				label1.setBounds(20, 10, 500, 50);
				label1.setFont(new Font("Arial", Font.BOLD, 18));
				jf1.add(label1);

				JPanel panel1 = new JPanel();
				panel1.setLayout(null);
				panel1.setBounds(20, 70, 445, 570);
				panel1.setBackground(Color.white);
				jf1.add(panel1);

				// 메시지 DB에서 받기
				List<MessageInfo> messages = messageService
						.findAllMessageByReceiver(HomeFrame.logInMember.getMemberId());

				//// DB에서 받은 메세지를 라벨에 추가
				// 메세지 카테고리
				JLabel label2 = new JLabel("SENDER");
				label2.setBounds(0, 0, 70, 20);
				label2.setOpaque(true);
				label2.setBackground(Color.yellow);
				panel1.add(label2);

				JLabel label3 = new JLabel("CONTENT");
				label3.setBounds(70, 0, 185, 20);
				label3.setOpaque(true);
				label3.setBackground(Color.yellow);
				panel1.add(label3);

				JLabel label4 = new JLabel("PRODUCT");
				label4.setBounds(255, 0, 100, 20);
				label4.setOpaque(true);
				label4.setBackground(Color.yellow);
				panel1.add(label4);

				JLabel label5 = new JLabel("SEND DATE");
				label5.setBounds(355, 0, 100, 20);
				label5.setOpaque(true);
				label5.setBackground(Color.yellow);
				panel1.add(label5);

				// 카테고리별로 메세지 패널에 뿌리기
				int posY = 5;
				for (MessageInfo msi : messages) {
					JLabel label6 = new JLabel(msi.getSenderidentification());
					label6.setBounds(0, 20 + posY, 70, 20);
					label6.setOpaque(true);
					label6.setBackground(Color.white);
					panel1.add(label6);

					JLabel label7 = new JLabel(msi.getContent());
					label7.setBounds(70, 20 + posY, 175, 20);
					label7.setOpaque(true);
					label7.setBackground(Color.white);
					panel1.add(label7);

					// 내용 클릭시 메세지 디테일창 팝업
					label7.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							JFrame jf2 = new JFrame("MESSAGE DETAILS");
							jf2.setLayout(null);
							jf2.setBounds(700, 150, 500, 500);
							jf2.setVisible(true);

							JLabel label1 = new JLabel("SENDER  :  " + msi.getSenderidentification());
							label1.setBounds(20, 30, 150, 20);
							label1.setOpaque(true);
							jf2.add(label1);

							JLabel label2 = new JLabel("PRODUCT  :  " + msi.getProductName());
							label2.setBounds(20, 50, 150, 20);
							label2.setOpaque(true);
							jf2.add(label2);

							JLabel label3 = new JLabel("SEND DATE  :  " + (msi.getSendDate()).toString());
							label3.setBounds(20, 70, 150, 20);
							label3.setOpaque(true);
							jf2.add(label3);

							JLabel label4 = new JLabel("CONTENT  :  ");
							label4.setBounds(20, 90, 150, 20);
							label4.setOpaque(true);
							jf2.add(label4);

							// 메세지내용 출력할 text area 생성
							JTextArea textArea1 = new JTextArea();
							textArea1.setBounds(20, 120, 440, 320);
							textArea1.setEditable(false); // 읽기 전용
							textArea1.setText(msi.getContent());
							textArea1.setFont(new Font("굴림", Font.BOLD, 16));
							textArea1.setLineWrap(true); // 자동 줄바꿈
							jf2.add(textArea1);

							// 뒤로가기 버튼
							RoundedButton backBtn = new RoundedButton("BACK");
							backBtn.setBounds(220, 30, 60, 40);
							backBtn.setBackground(Color.pink);
							jf2.add(backBtn);

							// 뒤로가기 클릭
							backBtn.addMouseListener(new MouseAdapter() {
								public void mouseClicked(MouseEvent e) {
									jf2.dispose();
								}
							});

							// 메세지 삭제 버튼
							RoundedButton deleteBtn = new RoundedButton("DELETE");
							deleteBtn.setBounds(310, 30, 60, 40);
							deleteBtn.setBackground(Color.pink);
							jf2.add(deleteBtn);

							// 메세지 삭제 버튼 클릭
							deleteBtn.addMouseListener(new MouseAdapter() {
								public void mouseClicked(MouseEvent e) {

									messageService.deleteMessage(msi.getMessageId());
									JOptionPane.showMessageDialog(frame, "Delete message successful !",
											"Congratulations !", JOptionPane.INFORMATION_MESSAGE);
									jf2.dispose();
									jf1.dispose();
								}
							});

							// 답장 버튼
							RoundedButton replyBtn = new RoundedButton("REPLY");
							replyBtn.setBounds(400, 30, 60, 40);
							replyBtn.setBackground(Color.pink);
							jf2.add(replyBtn);

							// 답장 버튼 클릭시 메세지창 팝업
							replyBtn.addMouseListener(new MouseAdapter() {
								public void mouseClicked(MouseEvent e) {
									// 메세지창
									JFrame jf3 = new JFrame("MESSAGE");
									jf3.setLayout(null);
									jf3.setBounds(700, 300, 500, 350);
									jf3.setVisible(true);

									// 메세지 창 라벨
									String title = "Send a message to ' " + msi.getSenderidentification() + " '";
									JLabel label = new JLabel(title);
									label.setBounds(10, 10, 400, 50);
									label.setFont(new Font("굴림", Font.BOLD, 16));
									jf3.add(label);

									// 메세지 입력 창
									JTextArea textCreateMessage = new JTextArea();
									textCreateMessage.setBounds(10, 70, 460, 225);
									textCreateMessage.setLineWrap(true); // 자동 줄바꿈
									jf3.add(textCreateMessage);

									// 뒤로가기 버튼
									RoundedButton backBtn = new RoundedButton("BACK");
									backBtn.setBounds(300, 20, 60, 40);
									backBtn.setBackground(Color.pink);
									jf3.add(backBtn);

									// 뒤로가기 클릭
									backBtn.addMouseListener(new MouseAdapter() {
										public void mouseClicked(MouseEvent e) {
											jf3.dispose();
										}
									});

									// 보내기 버튼
									RoundedButton sendBtn = new RoundedButton("SEND");
									sendBtn.setBounds(400, 20, 60, 40);
									sendBtn.setBackground(Color.pink);
									jf3.add(sendBtn);

									// 답장 버튼 클릭시 DB에 message 저장
									sendBtn.addMouseListener(new MouseAdapter() {
										public void mouseClicked(MouseEvent e) {

											Message message = new Message();
											message.setSenderId(HomeFrame.logInMember.getMemberId());
											message.setReceiverId(msi.getSenderId());
											message.setContent(textCreateMessage.getText());
											message.setProductId(msi.getProductId());

											// 빈 메세지 예외처리
											if (textCreateMessage.getText().length() != 0) {
												messageService.writeMessage(message);
												JOptionPane.showMessageDialog(frame, "Sending message successful !",
														"Congratulations !", JOptionPane.INFORMATION_MESSAGE);
												jf3.dispose();
												jf2.dispose();
												jf1.dispose();

											} else {
												JOptionPane.showMessageDialog(frame, "Please fill in the message.",
														"Sending message failed !", JOptionPane.INFORMATION_MESSAGE);
											}
										}
									});

								}
							});
						}
					});

					JLabel label8 = new JLabel(msi.getProductName());
					label8.setBounds(255, 20 + posY, 100, 20);
					label8.setOpaque(true);
					label8.setBackground(Color.white);
					panel1.add(label8);

					JLabel label9 = new JLabel((msi.getSendDate()).toString());
					label9.setBounds(355, 20 + posY, 100, 20);
					label9.setOpaque(true);
					label9.setBackground(Color.white);
					panel1.add(label9);

					posY += 23;
				}

				// 뒤로가기 버튼
				RoundedButton backBtn = new RoundedButton("BACK");
				backBtn.setBounds(400, 15, 60, 40);
				backBtn.setBackground(Color.pink);
				jf1.add(backBtn);

				// 뒤로가기 클릭
				backBtn.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						jf1.dispose();
					}
				});

			}
		});

		return msgBtn;
	}

	// 내 거래내역 조회 버튼
	private RoundedButton drawMyTradeButton() {
		// 거래내역 조회 버튼
		RoundedButton trdBtn = new RoundedButton("MY TRADE");
		trdBtn.setFont(new Font("Arial", Font.BOLD, 16));
		trdBtn.setBounds(330, 50, 100, 60);
		trdBtn.setBackground(Color.green);

		// 거래내역 조회 버튼 클릭시 거래내역 창 팝업
		trdBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// 거래내역 창
				JFrame jf1 = new JFrame("MY TRADE");
				jf1.setLayout(null);
				jf1.setBounds(200, 150, 1000, 700);
				jf1.setVisible(true);

				// 뒤로가기 버튼
				RoundedButton backBtn = new RoundedButton("BACK");
				backBtn.setBounds(870, 30, 60, 40);
				backBtn.setBackground(Color.pink);
				jf1.add(backBtn);

				// 뒤로가기 클릭
				backBtn.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						jf1.dispose();
					}
				});

				// 구매내역
				RoundedButton orderBtn = new RoundedButton("ORDER LIST");
				orderBtn.setFont(new Font("굴림", Font.BOLD, 12));
				orderBtn.setBounds(50, 30, 90, 40);
				orderBtn.setBackground(Color.WHITE);
				jf1.add(orderBtn);

				// 구매내역 클릭시 주문내역 출력
				orderBtn.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {

						// order list 담을 프레임
						JFrame jf2 = new JFrame("ORDER LIST");
						jf2.setLayout(null);
						jf2.setBounds(250, 260, 900, 550);
						jf2.setVisible(true);

						JLabel label1 = new JLabel(
								"' " + HomeFrame.logInMember.getIdentification() + " '  order list.");
						label1.setBounds(20, 10, 500, 50);
						label1.setFont(new Font("Arial", Font.BOLD, 18));
						jf2.add(label1);

						// 뒤로가기 버튼
						RoundedButton backBtn = new RoundedButton("BACK");
						backBtn.setBounds(800, 10, 60, 40);
						backBtn.setBackground(Color.pink);
						jf2.add(backBtn);

						// 뒤로가기 클릭
						backBtn.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								jf2.dispose();
							}
						});

						//// DB에서 받은 구매내역을 라벨에 추가
						// order list 담을 패널 생성
						JPanel panel1 = new JPanel();
						panel1.setLayout(null);
						panel1.setBounds(20, 60, 843, 430);
						panel1.setBackground(Color.white);
						jf2.add(panel1);

						// order list 카테고리
						JLabel label2 = new JLabel("ORDER.NO");
						label2.setBounds(0, 0, 180, 20);
						label2.setOpaque(true);
						label2.setBackground(Color.yellow);
						panel1.add(label2);

						JLabel label3 = new JLabel("SELLER");
						label3.setBounds(180, 0, 180, 20);
						label3.setOpaque(true);
						label3.setBackground(Color.yellow);
						panel1.add(label3);

						JLabel label4 = new JLabel("PRODUCT");
						label4.setBounds(360, 0, 180, 20);
						label4.setOpaque(true);
						label4.setBackground(Color.yellow);
						panel1.add(label4);

						JLabel label5 = new JLabel("ORDER QUANTITY");
						label5.setBounds(540, 0, 180, 20);
						label5.setOpaque(true);
						label5.setBackground(Color.yellow);
						panel1.add(label5);

						JLabel label6 = new JLabel("TOTAL PRICE");
						label6.setBounds(720, 0, 180, 20);
						label6.setOpaque(true);
						label6.setBackground(Color.yellow);
						panel1.add(label6);

						// DB에서 구매내역 조회
						List<MyTrade> myOrders = orderService.findMyOrders(HomeFrame.logInMember.getMemberId());

						// 카테고리별로 구매내역 패널에 뿌리기
						int posY = 5;
						for (MyTrade myOrder : myOrders) {
							JLabel label7 = new JLabel(Integer.toString(myOrder.getOrderId()));
							label7.setBounds(0, 20 + posY, 180, 20);
							label7.setOpaque(true);
							label7.setBackground(Color.white);
							panel1.add(label7);

							JLabel label8 = new JLabel(myOrder.getIdentification());
							label8.setBounds(180, 20 + posY, 180, 20);
							label8.setOpaque(true);
							label8.setBackground(Color.white);
							panel1.add(label8);

							JLabel label9 = new JLabel(myOrder.getProductName());
							label9.setBounds(360, 20 + posY, 180, 20);
							label9.setOpaque(true);
							label9.setBackground(Color.white);
							panel1.add(label9);

							JLabel label10 = new JLabel(Integer.toString(myOrder.getOrderQuatity()));
							label10.setBounds(540, 20 + posY, 180, 20);
							label10.setOpaque(true);
							label10.setBackground(Color.white);
							panel1.add(label10);

							JLabel label11 = new JLabel(Integer.toString(myOrder.getOrderPrice()));
							label11.setBounds(720, 20 + posY, 180, 20);
							label11.setOpaque(true);
							label11.setBackground(Color.white);
							panel1.add(label11);

							posY += 23;
						}

					}
				});

				// 판매내역 버튼 생성
				RoundedButton saleBtn = new RoundedButton("SALE LIST");
				saleBtn.setFont(new Font("굴림", Font.BOLD, 12));
				saleBtn.setBounds(142, 30, 90, 40);
				saleBtn.setBackground(Color.WHITE);
				jf1.add(saleBtn);

				// 판매내역 버튼 클릭
				saleBtn.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {

						// sale list 담을 프레임
						JFrame jf2 = new JFrame("SALE LIST");
						jf2.setLayout(null);
						jf2.setBounds(250, 260, 900, 550);
//						jf1.getContentPane().setBackground(Color.white);
						jf2.setVisible(true);

//						JLabel label1 = new JLabel("' wana1997 '  sale list.");
						JLabel label1 = new JLabel("' " + HomeFrame.logInMember.getIdentification() + " '  sale list.");
						label1.setBounds(20, 10, 500, 50);
						label1.setFont(new Font("Arial", Font.BOLD, 18));
						jf2.add(label1);

						// 뒤로가기 버튼
						RoundedButton backBtn = new RoundedButton("BACK");
						backBtn.setBounds(800, 10, 60, 40);
						backBtn.setBackground(Color.pink);
						jf2.add(backBtn);

						// 뒤로가기 클릭
						backBtn.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								jf2.dispose();
							}
						});

						//// DB에서 받은 판매내역을 라벨에 추가
						// sale list 담을 패널 생성
						JPanel panel1 = new JPanel();
						panel1.setLayout(null);
						panel1.setBounds(20, 60, 843, 430);
						panel1.setBackground(Color.white);
						jf2.add(panel1);

						// sale list 카테고리
						JLabel label2 = new JLabel("SALE.NO");
						label2.setBounds(0, 0, 180, 20);
						label2.setOpaque(true);
						label2.setBackground(Color.yellow);
						panel1.add(label2);

						JLabel label3 = new JLabel("BUYER");
						label3.setBounds(180, 0, 180, 20);
						label3.setOpaque(true);
						label3.setBackground(Color.yellow);
						panel1.add(label3);

						JLabel label4 = new JLabel("PRODUCT");
						label4.setBounds(360, 0, 180, 20);
						label4.setOpaque(true);
						label4.setBackground(Color.yellow);
						panel1.add(label4);

						JLabel label5 = new JLabel("ORDER QUANTITY");
						label5.setBounds(540, 0, 180, 20);
						label5.setOpaque(true);
						label5.setBackground(Color.yellow);
						panel1.add(label5);

						JLabel label6 = new JLabel("TOTAL PRICE");
						label6.setBounds(720, 0, 180, 20);
						label6.setOpaque(true);
						label6.setBackground(Color.yellow);
						panel1.add(label6);

						// DB에서 판매내역 조회
						List<MyTrade> mySales = orderService.findMySales(HomeFrame.logInMember.getMemberId());

						// 카테고리별로 판매내역 패널에 뿌리기
						int posY = 5;
						for (MyTrade mySale : mySales) {
							JLabel label7 = new JLabel(Integer.toString(mySale.getSaleId()));
							label7.setBounds(0, 20 + posY, 180, 20);
							label7.setOpaque(true);
							label7.setBackground(Color.white);
							panel1.add(label7);

							JLabel label8 = new JLabel(mySale.getIdentification());
							label8.setBounds(180, 20 + posY, 180, 20);
							label8.setOpaque(true);
							label8.setBackground(Color.white);
							panel1.add(label8);

							JLabel label9 = new JLabel(mySale.getProductName());
							label9.setBounds(360, 20 + posY, 180, 20);
							label9.setOpaque(true);
							label9.setBackground(Color.white);
							panel1.add(label9);

							JLabel label10 = new JLabel(Integer.toString(mySale.getOrderQuatity()));
							label10.setBounds(540, 20 + posY, 180, 20);
							label10.setOpaque(true);
							label10.setBackground(Color.white);
							panel1.add(label10);

							JLabel label11 = new JLabel(Integer.toString(mySale.getOrderPrice()));
							label11.setBounds(720, 20 + posY, 180, 20);
							label11.setOpaque(true);
							label11.setBackground(Color.white);
							panel1.add(label11);

							posY += 23;
						}
					}
				});

			}
		});
		return trdBtn;
	}

	// 구매, 판매 빈도수 조회 버튼 - 그래프
	private RoundedButton drawGraphButton() {
		// 구매왕, 판매왕 조회 버튼
		RoundedButton topBtn = new RoundedButton("TOP USER");
		topBtn.setFont(new Font("Arial", Font.BOLD, 16));
		topBtn.setBounds(460, 50, 100, 60);
		topBtn.setBackground(Color.green);

		// 구매, 판매 빈도수 조회 버튼 클릭시 구매, 판매 그래프 창 팝업
		topBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				JFrame jf1 = new JFrame("TOP USER");
				jf1.setLayout(null);
				jf1.setBounds(200, 150, 1000, 700);
//				jf1.getContentPane().setBackground(Color.white);
				jf1.setVisible(true);

				// 뒤로가기 버튼
				RoundedButton backBtn = new RoundedButton("BACK");
				backBtn.setBounds(870, 30, 60, 40);
				backBtn.setBackground(Color.pink);
				jf1.add(backBtn);

				// 뒤로가기 클릭
				backBtn.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						jf1.dispose();
					}
				});

				// 멤버별 구매실적 조회 버튼 생성
				RoundedButton orderBtn = new RoundedButton("ORDER FREQ");
				orderBtn.setFont(new Font("굴림", Font.BOLD, 12));
				orderBtn.setBounds(50, 30, 90, 40);
				orderBtn.setBackground(Color.WHITE);
				jf1.add(orderBtn);

				// 멤버별 구매실적 조회 버튼 클릭시 그래프 출력
				orderBtn.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {

						// 구매실적 그래프 뿌릴 프레임
						JFrame jf2 = new JFrame("ORDER FREQ GRAPH");
						jf2.setLayout(null);
						jf2.setBounds(250, 260, 900, 550);
//						jf1.getContentPane().setBackground(Color.white);
						jf2.setVisible(true);

						JLabel label1 = new JLabel("ORDER FREQUENCY GRAPH");
						label1.setBounds(20, 10, 500, 50);
						label1.setFont(new Font("Arial", Font.BOLD, 18));
						jf2.add(label1);

						//// DB에서 받은 판매내역을 라벨에 추가
						// sale list 담을 패널 생성
						JPanel panel1 = new JPanel();
						panel1.setLayout(null);
						panel1.setBounds(20, 60, 843, 430);
						panel1.setBackground(Color.black);
						jf2.add(panel1);

						JPanel panel2 = new JPanel();
						panel2.setLayout(null);
						panel2.setBounds(0, 0, 843, 370);
						panel2.setBackground(Color.white);
						panel1.add(panel2);

						// 구매왕 데이터 받아서 그래프 그리기
						List<TopInfo> topBuyer = orderService.findTopBuyer();

						int gapX = 843 / topBuyer.size();
						int posX = 843 / (topBuyer.size() * 2);

						for (TopInfo topInfo : topBuyer) {
							// x좌표 - 유저이름
							JLabel label = new JLabel(topInfo.getIdentification());
							label.setBounds(posX, 370, 150, 50);
							label.setFont(new Font("Arial", Font.BOLD, 13));
							label.setForeground(Color.white);
							panel1.add(label);

							// 막대 그래프 그리기
							JButton btn = new JButton();
							btn.setBounds(posX, 370 - (topInfo.getSaleOrBuyCnt() * 15), 20,
									topInfo.getSaleOrBuyCnt() * 15);
							btn.setBackground(Color.MAGENTA);
							panel2.add(btn);

							// 구매 빈도수
							JLabel label2 = new JLabel(Integer.toString(topInfo.getSaleOrBuyCnt()) + "times");
							label2.setBounds(posX - 15, 370 - (topInfo.getSaleOrBuyCnt() * 15) - 40, 150, 50);
							label2.setFont(new Font("Arial", Font.BOLD, 15));
							label2.setForeground(Color.black);
							panel2.add(label2);

							posX += gapX;
						}

						// 뒤로가기 버튼
						RoundedButton backBtn = new RoundedButton("BACK");
						backBtn.setBounds(800, 10, 60, 40);
						backBtn.setBackground(Color.pink);
						jf2.add(backBtn);

						// 뒤로가기 클릭
						backBtn.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								jf2.dispose();
							}
						});

					}
				});

				// 멤버별 판매실적 조회 버튼 생성
				RoundedButton saleBtn = new RoundedButton("SALE FREQ");
				saleBtn.setFont(new Font("굴림", Font.BOLD, 12));
				saleBtn.setBounds(142, 30, 90, 40);
				saleBtn.setBackground(Color.WHITE);
				jf1.add(saleBtn);

				// 멤버별 판매실적 조회 버튼 클릭시 그래프 출력
				saleBtn.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {

						// 판매실적 그래프 뿌릴 프레임
						JFrame jf2 = new JFrame("SALE FREQ GRAPH");
						jf2.setLayout(null);
						jf2.setBounds(250, 260, 900, 550);
//						jf1.getContentPane().setBackground(Color.white);
						jf2.setVisible(true);

						JLabel label1 = new JLabel("SALE FREQUENCY GRAPH");
						label1.setBounds(20, 10, 500, 50);
						label1.setFont(new Font("Arial", Font.BOLD, 18));
						jf2.add(label1);

						//// DB에서 받은 판매내역을 라벨에 추가
						JPanel panel1 = new JPanel();
						panel1.setLayout(null);
						panel1.setBounds(20, 60, 843, 430);
						panel1.setBackground(Color.black);
						jf2.add(panel1);

						JPanel panel2 = new JPanel();
						panel2.setLayout(null);
						panel2.setBounds(0, 0, 843, 370);
						panel2.setBackground(Color.white);
						panel1.add(panel2);

						// 판매왕 데이터 받아서 그래프 그리기
						List<TopInfo> topSeller = orderService.findTopSeller();

						int gapX = 843 / topSeller.size();
						int posX = 843 / (topSeller.size() * 2);

						for (TopInfo topInfo : topSeller) {
							// x좌표 - 유저이름
							JLabel label = new JLabel(topInfo.getIdentification());
							label.setBounds(posX, 370, 150, 50);
							label.setFont(new Font("Arial", Font.BOLD, 13));
							label.setForeground(Color.white);
							panel1.add(label);

							// 막대 그래프 그리기
							JButton btn = new JButton();
							btn.setBounds(posX, 370 - (topInfo.getSaleOrBuyCnt() * 15), 20,
									topInfo.getSaleOrBuyCnt() * 15);
							btn.setBackground(Color.blue);
							panel2.add(btn);

							// 판매 빈도수
							JLabel label2 = new JLabel(Integer.toString(topInfo.getSaleOrBuyCnt()) + "times");
							label2.setBounds(posX - 15, 370 - (topInfo.getSaleOrBuyCnt() * 15) - 40, 150, 50);
							label2.setFont(new Font("Arial", Font.BOLD, 15));
							label2.setForeground(Color.black);
							panel2.add(label2);

							posX += gapX;
						}

						// 뒤로가기 버튼
						RoundedButton backBtn = new RoundedButton("BACK");
						backBtn.setBounds(800, 10, 60, 40);
						backBtn.setBackground(Color.pink);
						jf2.add(backBtn);

						// 뒤로가기 클릭
						backBtn.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								jf2.dispose();
							}
						});
					}
				});

			}
		});

		return topBtn;
	}
}
