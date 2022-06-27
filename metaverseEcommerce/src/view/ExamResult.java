package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ExamResult extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExamResult() {
		super("성적분포표");
		
		buildGUI();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(500,200);
		setSize(400,400);
		
		setVisible(true);
	}
	
	private void buildGUI() {
		setLayout(new BorderLayout());

		Container contentpane = getContentPane();
		ResultPanel resultPanel = new ResultPanel();
        //그래프를 그릴 패널
		
		contentpane.add(resultPanel, BorderLayout.CENTER);
		
		JPanel p = new JPanel();
		JTextField e1 = new JTextField(3);
		JTextField e2 = new JTextField(3);
		JTextField e3 = new JTextField(3);
		
		JButton button = new JButton("성적나와라뿅");
		
		p.add(new JLabel("국어"));
		p.add(e1);
		p.add(new JLabel("영어"));
		p.add(e2);
		p.add(new JLabel("수학"));
		p.add(e3);
		p.add(button);
		
		contentpane.add(p, BorderLayout.SOUTH);
		button.addActionListener(new DrawingActionListener(e1, e2, e3, resultPanel));
		//버튼을 누를 시 작동할 리스너
	}

	public static void main(String[] args) {
		new ExamResult();
		
	}

	//결과물(그래프)이 나타날 패널
	class ResultPanel extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		int kor, eng, math;
		
		public void paint(Graphics g) {
			g.clearRect(0,0,getWidth(),getHeight());
			
			
			
			g.drawString("국", 100, 270);
			g.drawString("영",  200, 270);
			g.drawString("수", 300, 270);
			g.setColor(Color.BLUE);
			
			if(kor > 0)
				g.fillRect(110, 0, 10, kor);	
			if(eng > 0)
				g.fillRect(210, 0, 10, eng);	
			if(math > 0)
				g.fillRect(310, 0, 10, math);
		}
		
		void setScore(int kor, int eng, int math) {
			this.kor = kor;
			this.eng = eng;
			this.math = math;
		}
	}

	//버튼을 눌렀을 때 동작할 리스너
	class DrawingActionListener implements ActionListener{
		JTextField e1, e2, e3;
		ResultPanel resultPanel;
		
		DrawingActionListener(JTextField e1, JTextField e2, JTextField e3, ResultPanel resultPanel){
			this.e1 = e1;
			this.e2 = e2;
			this.e3 = e3;
			this.resultPanel = resultPanel;
		}
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
        //숫자를 입력하지 않았을 시 오류 해결 & 에러 메세지
			try{
				int kor = Integer.parseInt(e1.getText());
				int eng = Integer.parseInt(e2.getText());
				int math = Integer.parseInt(e3.getText());
				resultPanel.setScore(kor, eng, math);
				resultPanel.repaint();
			}
			catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(resultPanel, "다시 입력하세요", "에러 메세지", JOptionPane.ERROR_MESSAGE);
			}
		}

	}
}