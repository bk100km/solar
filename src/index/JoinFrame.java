package index;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JoinFrame extends JFrame {
	
	JPanel panW = new JPanel(new GridLayout(8, 1));
	JPanel panC = new JPanel(new GridLayout(8, 1));
	JPanel panS = new JPanel(new FlowLayout());
	
	JLabel id = new JLabel("아 이 디  : ");
	JLabel pw1 = new JLabel("비밀번호 : ");	
	JLabel pw2 = new JLabel("비번확인 : ");
	JLabel name = new JLabel("성    명   : ");
	JLabel birth = new JLabel("생년월일 : ");
	JLabel addr = new JLabel("주    소   : ");
	JLabel tel = new JLabel("연 락 처  : ");
	JLabel mail = new JLabel("이 메 일  : ");
	
	JPanel w1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel w2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel w3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel w4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel w5 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel w6 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel w7 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel w8 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	
	JPanel c1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel c2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel c3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel c4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel c5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel c6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel c7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel c8 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	
	public TextField tf_id = new TextField(16);		JLabel idForm = new JLabel("영문/숫자 1~16자리"); 	
	public TextField tf_pw1 = new TextField(16);	JLabel pwForm = new JLabel("영문/숫자 1~16자리                           ");
	public TextField tf_pw2 = new TextField(16);
	public TextField tf_name = new TextField(9);
	public TextField tf_birth = new TextField(9);	JLabel birthEx = new JLabel("     예)  19920611");
	public TextField tf_addr = new TextField(36);
	public JTextField tf_010 = new JTextField(" 010 ");	
	public TextField tf_tel = new TextField(9);		JLabel telForm = new JLabel("   숫자 8자리");
	public TextField tf_mail = new TextField(36);
	
	JButton idCheck = new JButton("중복확인");
	JButton join = new JButton("가입");
	JButton cancel = new JButton("취소");
	
	public JoinFrame() {
		
		super ("회원가입");
		
		w1.add(id);			w2.add(pw1);		w3.add(pw2);		w4.add(name);
		w5.add(birth);		w6.add(addr);		w7.add(tel);		w8.add(mail);
		panW.add(w1);		panW.add(w2);		panW.add(w3);		panW.add(w4);
		panW.add(w5);		panW.add(w6);		panW.add(w7);		panW.add(w8);
		
		tf_010.setEditable(false);
		c1.add(tf_id);		c1.add(idCheck);	c1.add(idForm);		
		c2.add(tf_pw1);		c2.add(pwForm); 	c3.add(tf_pw2);		
		c4.add(tf_name);	c5.add(tf_birth); 	c5.add(birthEx);	c6.add(tf_addr);	
		c7.add(tf_010);		c7.add(tf_tel);		c7.add(telForm);	c8.add(tf_mail);
		panC.add(c1);	panC.add(c2);	panC.add(c3);	panC.add(c4);
		panC.add(c5);	panC.add(c6);	panC.add(c7);	panC.add(c8);
		
		tf_pw1.setEchoChar('*');	tf_pw2.setEchoChar('*');
		
		panS.add(join);	panS.add(cancel);	
	
		add(panW, "West"); 	add(panC, "Center");	add(panS, "South");	
	
		setSize (400,600);		
		setResizable(false);	
		setFont(new Font("고딕체", Font.BOLD, 15));
		setLocation(600, 150);
		
	}
	
	public static void main(String[] args) {
		JoinFrame jof = new JoinFrame();
	}
}
