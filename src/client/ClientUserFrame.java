package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import index.ClientFrame;
import index.IndexFrame;

public class ClientUserFrame extends JFrame implements ActionListener {

	String id;
	ClientFrame clientF;
	IndexFrame indexF;
	
	JPanel BorderPan = new JPanel (new BorderLayout());
	JPanel WestImgPan = new JPanel (new FlowLayout());
	JPanel EastImgPan = new JPanel (new FlowLayout());
	
	JPanel NorthPan = new JPanel(new FlowLayout());
	JPanel WestPan = new JPanel(new GridLayout(10,1));
	JPanel centerPan = new JPanel(new GridLayout(10,1));
	JPanel southPan = new JPanel(new FlowLayout());
	
	JLabel ltitle= new JLabel("�� �� ��");
	JLabel lid= new JLabel("��  ��  �� :  ");
	JLabel lpw= new JLabel("��й�ȣ :  ");
	JLabel lcName= new JLabel("��        �� :  ");
	JLabel lbirth= new JLabel("������� :  ");
	JLabel laddr= new JLabel("��        �� :  ");
	JLabel ltel= new JLabel("��  ��  ó :  ");
	JLabel lmail= new JLabel("��  ��  �� :  ");
	JLabel ljDate= new JLabel("�������� :  ");
	
	JLabel infoId = new JLabel();
	JLabel infoPw = new JLabel();
	JLabel infoCName = new JLabel();
	JLabel infoBirth = new JLabel();
	JLabel infoAddr = new JLabel();
	JLabel infoTel = new JLabel();
	JLabel infoMail = new JLabel();
	JLabel infoJDate = new JLabel();
	
	

	JButton btnUpdate = new JButton("��     ��");
	JButton btnDelete = new JButton("Ż     ��");
	JButton btnLogout = new JButton("�α׾ƿ�");
	
	ImageIcon iconw = new ImageIcon(
			ClientUserFrame.class.getResource("/client/img/������w.jpg"));
	Image imgw = iconw.getImage();
	Image changeImgw = imgw.getScaledInstance(200, 600, Image.SCALE_SMOOTH);
	ImageIcon changeIconw = new ImageIcon(changeImgw);
	JLabel WestImg = new JLabel(changeIconw);
	
	ImageIcon icone = new ImageIcon(
			IndexFrame.class.getResource("/client/img/������e.jpg"));
	Image imge = icone.getImage();
	Image changeImge = imge.getScaledInstance(200, 600, Image.SCALE_SMOOTH);
	ImageIcon changeIcone = new ImageIcon(changeImge);
	JLabel EastImg = new JLabel(changeIcone);	
	
	public JPanel clientUserPanel = new JPanel(new BorderLayout());
	
	ClientDAO dao = new ClientDAO();

	
	public ClientUserFrame(IndexFrame indexF, ClientFrame clientF, String id) {
		this.id = id;
		this.clientF = clientF;
		this.indexF = indexF;
		
		NorthPan.add(ltitle);
		
		WestPan.add(lid);	
		WestPan.add(lpw);	
		WestPan.add(lcName);	
		WestPan.add(lbirth);
		WestPan.add(laddr);	
		WestPan.add(ltel);	
		WestPan.add(lmail);	
		WestPan.add(ljDate);
		
		centerPan.add(infoId);	
		centerPan.add(infoPw );	
		centerPan.add(infoCName);	
		centerPan.add(infoBirth);
		centerPan.add(infoAddr);	
		centerPan.add(infoTel);	
		centerPan.add(infoMail);	
		centerPan.add(infoJDate);
		
		southPan.setBackground(Color.lightGray);
		southPan.add(btnUpdate);	
		southPan.add(btnDelete);	
		southPan.add(btnLogout);
		
		WestPan.setPreferredSize(new Dimension(300, 580));
		centerPan.setPreferredSize(new Dimension(700, 580));
		lid.setPreferredSize(new Dimension(70, 72));
		lpw.setPreferredSize(new Dimension(70, 72));
		lcName.setPreferredSize(new Dimension(70, 72));
		lbirth.setPreferredSize(new Dimension(70, 72));
		laddr.setPreferredSize(new Dimension(70, 72));
		ltel.setPreferredSize(new Dimension(70, 72));
		lmail.setPreferredSize(new Dimension(70, 72));
		ljDate.setPreferredSize(new Dimension(70, 72));
		infoId.setPreferredSize(new Dimension(220, 72));
		infoPw .setPreferredSize(new Dimension(220, 72));
		infoCName.setPreferredSize(new Dimension(220, 72));
		infoBirth.setPreferredSize(new Dimension(220, 72));
		infoAddr.setPreferredSize(new Dimension(220, 72));
		infoTel.setPreferredSize(new Dimension(220, 72));
		infoMail.setPreferredSize(new Dimension(220, 72));
		infoJDate.setPreferredSize(new Dimension(220, 72));
		
		NorthPan.setFont(new Font("���ü", Font.BOLD, 35));
		WestPan.setFont(new Font("���ü", Font.BOLD, 20));
		centerPan.setFont(new Font("���ü", Font.BOLD, 20));
		ltitle.setFont(new Font("���ü", Font.BOLD, 35));
		lid.setFont(new Font("���ü", Font.BOLD, 20));
		lpw.setFont(new Font("���ü", Font.BOLD, 20));
		lcName.setFont(new Font("���ü", Font.BOLD, 20));
		lbirth.setFont(new Font("���ü", Font.BOLD, 20));
		laddr.setFont(new Font("���ü", Font.BOLD, 20));
		ltel.setFont(new Font("���ü", Font.BOLD, 20));
		lmail.setFont(new Font("���ü", Font.BOLD, 20));
		ljDate.setFont(new Font("���ü", Font.BOLD, 20));
		infoId.setFont(new Font("���ü", Font.BOLD, 20));
		infoPw .setFont(new Font("���ü", Font.BOLD, 20));
		infoCName.setFont(new Font("���ü", Font.BOLD, 20));
		infoBirth.setFont(new Font("���ü", Font.BOLD, 20));
		infoAddr.setFont(new Font("���ü", Font.BOLD, 20));
		infoTel.setFont(new Font("���ü", Font.BOLD, 20));
		infoMail.setFont(new Font("���ü", Font.BOLD, 20));
		infoJDate.setFont(new Font("���ü", Font.BOLD, 20));
		
		lid.setHorizontalAlignment(JLabel.RIGHT); 
		lpw.setHorizontalAlignment(JLabel.RIGHT); 
		lcName.setHorizontalAlignment(JLabel.RIGHT); 
		lbirth.setHorizontalAlignment(JLabel.RIGHT); 
		laddr.setHorizontalAlignment(JLabel.RIGHT); 
		ltel.setHorizontalAlignment(JLabel.RIGHT); 
		lmail.setHorizontalAlignment(JLabel.RIGHT); 
		ljDate.setHorizontalAlignment(JLabel.RIGHT); 
		infoId.setHorizontalAlignment(JLabel.LEFT); 
		infoPw.setHorizontalAlignment(JLabel.LEFT); 
		infoCName.setHorizontalAlignment(JLabel.LEFT); 
		infoBirth.setHorizontalAlignment(JLabel.LEFT); 
		infoAddr.setHorizontalAlignment(JLabel.LEFT); 
		infoTel.setHorizontalAlignment(JLabel.LEFT); 
		infoMail.setHorizontalAlignment(JLabel.LEFT); 
		infoJDate.setHorizontalAlignment(JLabel.LEFT); 
		
		BorderPan.add(NorthPan, "North");
		BorderPan.add(WestPan, "West");
		BorderPan.add(centerPan, "Center");
		
		clientUserPanel.add(WestImgPan, "West");	
		clientUserPanel.add(BorderPan, "Center");	
		clientUserPanel.add(EastImgPan, "East");
		clientUserPanel.add(southPan, "South");
		
		WestImgPan.add(WestImg);
		EastImgPan.add(EastImg);
		WestImgPan.setPreferredSize(new Dimension(220, 40));
		EastImgPan.setPreferredSize(new Dimension(220, 40));
		
		clientUserPanel.setSize (400,450);
		clientUserPanel.setFont(new Font("���ü", Font.BOLD, 15));

		
		dao.clientShow(this, id);
	
		eventUp();
	}
	
	public void eventUp() {
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
		btnLogout.addActionListener(this);		
	}

	public void actionPerformed(ActionEvent e) {		
		if (e.getSource() == btnUpdate) {
			
			dao.updateShowByClient(this,id);
			System.out.println("ȸ������ ���� â OPEN");
			
		} else if (e.getSource() == btnDelete) {
						
			String id2 = JOptionPane.showInputDialog(this,"Ż���� ���̵� �ٽ� �Է� :");
			if (id2 == null) {
				return;
			} else if (!id2.equals(id)){
				JOptionPane.showMessageDialog(this, "���̵� ��ġ���� �ʽ��ϴ�.");
			} else if (id2.equals(id)) {
				int warnning = JOptionPane.showConfirmDialog(this, "������ Ż���Ͻðڽ��ϴ�?", "Ż�� ���",
			               JOptionPane.YES_NO_OPTION);
				
			    if (warnning == 1) {return;}
			    else {	
			    	if (dao.deleteByClient(id) > 0) {
			    		JOptionPane.showMessageDialog(this, "Ż�� �Ϸ�");
			    		clientF.setVisible(false);   		
			    		indexF.setVisible(true);
			    		indexF.tf_id.setText("");			indexF.tf_pw.setText("");			    		
			    	} else {
			    		JOptionPane.showMessageDialog(this, "Ż�� ����");
			    	}
			    }
			}			
			
		} else if (e.getSource() == btnLogout){
			clientF.setVisible(false);
			indexF.setVisible(true);
			indexF.tf_id.setText("");			indexF.tf_pw.setText("");
		}
	}
}