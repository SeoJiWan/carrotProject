package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame{ // 탑레벨 컨테이너, 윈도우 창 1개

	/*
	 * Field
	 */
	private JFrame frame;
	private JTextField id;
	private JPasswordField pwd;
	// 이미지 SRC
	private String mainImgSrc = "C:\\Users\\Wana\\dev\\workSpace\\eclipse-workspace\\carrotProject\\metaverseEcommerce\\src\\view\\img\\carrot.png";
	private String loginImgSrc = "C:\\Users\\Wana\\dev\\workSpace\\eclipse-workspace\\carrotProject\\metaverseEcommerce\\src\\view\\img\\login1.PNG";
	private String loginHoverImgSrc = "C:\\Users\\Wana\\dev\\workSpace\\eclipse-workspace\\carrotProject\\metaverseEcommerce\\src\\view\\img\\login2.PNG";
	// 이미지 크기
	private int mainImgSize[] = {300, 500};
	private int frameSize[] = {1440, 960};
	private int textSize[] = {260, 40};
	private int loginImgsize[] = {220, 55};
 
	
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
		
		ImagePanel bgPanel = new ImagePanel(new ImageIcon(mainImgSrc).getImage());
		bgPanel.setLayout(null);
		frame.setBackground(Color.white);
		frame.setSize(frameSize[0], frameSize[1]);
		
		this.drawId(bgPanel, new int[] {frameSize[0]/2 - textSize[0]/2, 480, textSize[0], textSize[1]});
		this.drawPwd(bgPanel, new int[] {frameSize[0]/2 - textSize[0]/2, 580, textSize[0], textSize[1]});
		
		JButton btn_login = this.makeImageButton(loginImgSrc, loginHoverImgSrc);
		btn_login.setBounds(frameSize[0]/2 - loginImgsize[0]/2, 650, loginImgsize[0], loginImgsize[1]);
		bgPanel.add(btn_login);
		
		this.drawSignupButton(bgPanel);
		
		
		
		frame.getContentPane().add(bgPanel);
		frame.setVisible(true);
	}
	
	
	private void drawId(ImagePanel bgPanel, int[] bounds) {
		id = new JTextField();
		id.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
		bgPanel.add(id);
	}
	
	private void drawPwd(ImagePanel bgPanel, int[] bounds) {
		pwd = new JPasswordField();
		pwd.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
		bgPanel.add(pwd);
	}
	
	public JButton makeImageButton(String img, String hoverImg) {
		Icon IMG = new ImageIcon(img);
		Icon IMG_HOVER = new ImageIcon(hoverImg);
		JButton btn = new JButton();

		btn.setIcon(IMG);
		// hover
		btn.setRolloverIcon(IMG_HOVER);
		// 버튼 이미지 테두리 없애기
		btn.setBorderPainted(false);
		btn.setContentAreaFilled(false);
		btn.setFocusPainted(false);

		return btn;
	}
	
	public void drawSignupButton(ImagePanel bgPanel) {
		JButton btn = new JButton("Sign Up");
		btn.setBounds(730, 720, 150, 50);
		btn.setFont(new Font("Arial Black", Font.BOLD, 22));
		btn.setBorderPainted(false);
		btn.setFocusPainted(false);
		btn.setForeground(Color.BLACK);
		btn.setBackground(Color.white);

		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				registerFrame r = new registerFrame();
//				r.setVisible(true);
				frame.dispose();
			}
		});

		bgPanel.add(btn);
	}
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		new LoginFrame();
	}
	
	
	
	
	
	class ImagePanel extends JPanel {
		private static final long serialVersionUID = 1L;
		  private Image img;
		  
		  public ImagePanel(Image img) {
		      this.img = img;
//		      setSize(200, 200);
//		      setPreferredSize(new Dimension(img.getWidth(null), img.getHeight(null)));
//		      setLayout(null);
		  }
		  
		  public void paintComponent(Graphics g) {
		      g.drawImage(img, (frameSize[0]/2) - (mainImgSize[0]/2), (frameSize[1]/2) - (mainImgSize[1]/2 + 150), 
		    		  mainImgSize[0], mainImgSize[1], null);
		  }
		}

}
