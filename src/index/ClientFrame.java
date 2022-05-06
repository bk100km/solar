package index;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import client.ClientUserFrame;
import product.ClientProductFrame;
import rental.ClientRentalFrame;

public class ClientFrame extends JFrame {

	JTabbedPane tab = new JTabbedPane();

	public ClientFrame(IndexFrame indexF, String id) {
		super("�ɾ�ַ�� �뿩 ��ǰ | SOLAR����");
		ClientProductFrame cpf = new ClientProductFrame(id);
		ClientRentalFrame crf = new ClientRentalFrame(id);
		ClientUserFrame cuf = new ClientUserFrame(indexF, this, id);
		tab.add("��ǰ", cpf.clientProductPanel);
		tab.add("�뿩����", crf.clientRentalPanel);
		tab.add("�� ����", cuf.clientUserPanel);
		
		add(tab);
		setSize(1400,700);		setLocation(500,100);		setResizable(false);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}