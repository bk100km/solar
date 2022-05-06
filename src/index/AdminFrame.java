package index;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import client.AdminUserFrame;
import product.AdminProductFrame;
import rental.AdminRentalFrame;

public class AdminFrame extends JFrame {

	JTabbedPane tab = new JTabbedPane();
	
	AdminProductFrame apf = new AdminProductFrame();
	AdminRentalFrame arf = new AdminRentalFrame();
	
	public AdminFrame(IndexFrame indexF) {
		super("[SOLAR���� ������ ���]");
		AdminUserFrame auf = new AdminUserFrame(indexF, this);
		tab.add("��ǰ", apf.adminProductPanel);
		tab.add("�뿩", arf.adminRentalPanel);
		tab.add("ȸ��", auf.adminUserPanel);
		
		add(tab);
		setSize(1400,700);		setLocation(500,100);		setResizable(false);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}