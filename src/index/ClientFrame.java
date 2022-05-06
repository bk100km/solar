package index;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import client.ClientUserFrame;
import product.ClientProductFrame;
import rental.ClientRentalFrame;

public class ClientFrame extends JFrame {

	JTabbedPane tab = new JTabbedPane();

	public ClientFrame(IndexFrame indexF, String id) {
		super("케어솔루션 대여 제품 | SOLAR전자");
		ClientProductFrame cpf = new ClientProductFrame(id);
		ClientRentalFrame crf = new ClientRentalFrame(id);
		ClientUserFrame cuf = new ClientUserFrame(indexF, this, id);
		tab.add("제품", cpf.clientProductPanel);
		tab.add("대여정보", crf.clientRentalPanel);
		tab.add("내 정보", cuf.clientUserPanel);
		
		add(tab);
		setSize(1400,700);		setLocation(500,100);		setResizable(false);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}