package index;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IndexFrame extends JFrame implements KeyListener {
	
	JPanel panN = new JPanel();
	JPanel panC = new JPanel(new FlowLayout());
	JPanel panS = new JPanel(new FlowLayout());
	
	JLabel label_img= new JLabel();
	JLabel lid= new JLabel("아이디 :");
	JLabel lpw= new JLabel("     비밀번호 :");
	
	ImageIcon img = new ImageIcon(
	IndexFrame.class.getResource("/index/image/가전제품.jpg"));

	
	public TextField tf_id = new TextField(16);
	public TextField tf_pw = new TextField(16);
	
	
	JButton btnLogin = new JButton("로  그  인");
	JButton btnJoin = new JButton("회원가입");
		
	public IndexFrame() {
		
		super ("SOLAR전자 대여 서비스");
		
		add(panN, "North");	 add(panC, "Center");	add(panS, "South");
		
		label_img.setIcon(img);
		panN.add(label_img);
		panC.add(lid);	panC.add(tf_id);	panC.add(lpw);	panC.add(tf_pw);
		
		panS.setBackground(Color.BLACK);
		panS.add(btnLogin);	panS.add(btnJoin);	
		
		tf_pw.setEchoChar('*');
		
		setSize (1400,700);
//		setVisible(true);
		setResizable(false);
		setLocation(500, 100);
		tf_pw.addKeyListener(this);
	}	

	public static void main(String[] args) {
		IndexFrame lf = new IndexFrame();
		lf.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	}

	@Override
	public void keyPressed(KeyEvent e) {	
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ENTER) {
		Toolkit.getDefaultToolkit().beep();
		btnLogin.doClick();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}