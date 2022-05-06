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
		super("[SOLAR전자 관리자 모드]");
		AdminUserFrame auf = new AdminUserFrame(indexF, this);
		tab.add("제품", apf.adminProductPanel);
		tab.add("대여", arf.adminRentalPanel);
		tab.add("회원", auf.adminUserPanel);
		
		add(tab);
		setSize(1400,700);		setLocation(500,100);		setResizable(false);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}