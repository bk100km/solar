package client;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ClientUserDialog extends JDialog implements ActionListener{
	
	JPanel panW = new JPanel(new GridLayout(8, 1));
	JLabel id = new JLabel("�� �� ��  : ");
	JLabel pw1 = new JLabel("��й�ȣ : ");
	JLabel pw2 = new JLabel("���Ȯ�� : ");
	JLabel name = new JLabel("��    ��   : ");
	JLabel birth = new JLabel("������� : ");
	JLabel addr = new JLabel("��    ��   : ");
	JLabel tel = new JLabel("�� �� ó : ");
	JLabel mail = new JLabel("�� �� ��  : ");
	JPanel w1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel w2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel w3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel w4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel w5 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel w6 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel w7 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	JPanel w8 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	
	JPanel panC = new JPanel(new GridLayout(8, 1));
	JPanel c1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel c2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel c3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel c4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel c5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel c6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel c7 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JPanel c8 = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JTextField tf_id = new JTextField(16);			JLabel idForm = new JLabel("����/���� 1~16�ڸ�");
	JPasswordField tf_pw1 = new JPasswordField(16);	JLabel pwForm = new JLabel("����/���� 1~16�ڸ�                           ");
	JPasswordField tf_pw2 = new JPasswordField(16);
	JTextField tf_name = new JTextField(9);
	JTextField tf_birth = new JTextField(9);		JLabel birthEx = new JLabel("     ��)  19920611");
	JTextField tf_addr = new JTextField(26);
	JTextField tf_010 = new JTextField(" 010 ");	
	JTextField tf_tel = new JTextField(9);			JLabel telForm = new JLabel("   ���� 8�ڸ�");
	JTextField tf_mail = new JTextField(26);
	
	JPanel panS = new JPanel(new FlowLayout());
	JButton idCheck = new JButton("�ߺ�Ȯ��");
	JButton update = new JButton("����");
	JButton cancel = new JButton("���");
	ClientDAO dao = new ClientDAO();
	ClientUserFrame cuf;

	public ClientUserDialog(ClientUserFrame cuf) {
		super(cuf, "������", true);
		this.cuf = cuf;
		w1.add(id);			w2.add(pw1);		w3.add(pw2);		w4.add(name);
		w5.add(birth);		w6.add(addr);		w7.add(tel);		w8.add(mail);
		panW.add(w1);		panW.add(w2);		panW.add(w3);		panW.add(w4);
		panW.add(w5);		panW.add(w6);		panW.add(w7);		panW.add(w8);
		
		tf_pw1.setEchoChar('*');		tf_pw2.setEchoChar('*');
		tf_010.setEditable(false);
		c1.add(tf_id);		c1.add(idCheck);	c1.add(idForm);		
		c2.add(tf_pw1);		c2.add(pwForm); 	c3.add(tf_pw2);		
		c4.add(tf_name);	c5.add(tf_birth); 	c5.add(birthEx);	c6.add(tf_addr);	
		c7.add(tf_010);		c7.add(tf_tel);		c7.add(telForm);	c8.add(tf_mail);
		panC.add(c1);	panC.add(c2);	panC.add(c3);	panC.add(c4);
		panC.add(c5);	panC.add(c6);	panC.add(c7);	panC.add(c8);
		
		tf_pw1.setEchoChar('*');	tf_pw2.setEchoChar('*');
		
		panS.add(update);	panS.add(cancel);	
		
		add(panW, "West"); add(panC, "Center");
		add(panS, "South");
		setSize(400,600);	setLocation(1400,250);
		setVisible(false);	setResizable(false);
		
		update.addActionListener(this);
		cancel.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == update) {
				if (dao.updateByClient(this) > 0) {
					JOptionPane.showMessageDialog(this, "���� �Ϸ�");
					dispose();
					dao.clientShow(cuf, cuf.id);
					
				} else {
					JOptionPane.showMessageDialog(this, "���� ����");
				}
		} else if (e.getSource() == cancel) {
			dispose();
		}
	}
}
