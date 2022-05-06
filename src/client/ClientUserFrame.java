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
	
	JLabel ltitle= new JLabel("³» Á¤ º¸");
	JLabel lid= new JLabel("¾Æ  ÀÌ  µð :  ");
	JLabel lpw= new JLabel("ºñ¹Ð¹øÈ£ :  ");
	JLabel lcName= new JLabel("¼º        ¸í :  ");
	JLabel lbirth= new JLabel("»ý³â¿ùÀÏ :  ");
	JLabel laddr= new JLabel("ÁÖ        ¼Ò :  ");
	JLabel ltel= new JLabel("¿¬  ¶ô  Ã³ :  ");
	JLabel lmail= new JLabel("ÀÌ  ¸Þ  ÀÏ :  ");
	JLabel ljDate= new JLabel("°¡ÀÔÀÏÀÚ :  ");
	
	JLabel infoId = new JLabel();
	JLabel infoPw = new JLabel();
	JLabel infoCName = new JLabel();
	JLabel infoBirth = new JLabel();
	JLabel infoAddr = new JLabel();
	JLabel infoTel = new JLabel();
	JLabel infoMail = new JLabel();
	JLabel infoJDate = new JLabel();
	
	

	JButton btnUpdate = new JButton("¼ö     Á¤");
	JButton btnDelete = new JButton("Å»     Åð");
	JButton btnLogout = new JButton("·Î±×¾Æ¿ô");
	
	ImageIcon iconw = new ImageIcon(
			ClientUserFrame.class.getResource("/client/img/¿¡¾îÄÁw.jpg"));
	Image imgw = iconw.getImage();
	Image changeImgw = imgw.getScaledInstance(200, 600, Image.SCALE_SMOOTH);
	ImageIcon changeIconw = new ImageIcon(changeImgw);
	JLabel WestImg = new JLabel(changeIconw);
	
	ImageIcon icone = new ImageIcon(
			IndexFrame.class.getResource("/client/img/¿¡¾îÄÁe.jpg"));
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
		
		NorthPan.setFont(new Font("°íµñÃ¼", Font.BOLD, 35));
		WestPan.setFont(new Font("°íµñÃ¼", Font.BOLD, 20));
		centerPan.setFont(new Font("°íµñÃ¼", Font.BOLD, 20));
		ltitle.setFont(new Font("°íµñÃ¼", Font.BOLD, 35));
		lid.setFont(new Font("°íµñÃ¼", Font.BOLD, 20));
		lpw.setFont(new Font("°íµñÃ¼", Font.BOLD, 20));
		lcName.setFont(new Font("°íµñÃ¼", Font.BOLD, 20));
		lbirth.setFont(new Font("°íµñÃ¼", Font.BOLD, 20));
		laddr.setFont(new Font("°íµñÃ¼", Font.BOLD, 20));
		ltel.setFont(new Font("°íµñÃ¼", Font.BOLD, 20));
		lmail.setFont(new Font("°íµñÃ¼", Font.BOLD, 20));
		ljDate.setFont(new Font("°íµñÃ¼", Font.BOLD, 20));
		infoId.setFont(new Font("°íµñÃ¼", Font.BOLD, 20));
		infoPw .setFont(new Font("°íµñÃ¼", Font.BOLD, 20));
		infoCName.setFont(new Font("°íµñÃ¼", Font.BOLD, 20));
		infoBirth.setFont(new Font("°íµñÃ¼", Font.BOLD, 20));
		infoAddr.setFont(new Font("°íµñÃ¼", Font.BOLD, 20));
		infoTel.setFont(new Font("°íµñÃ¼", Font.BOLD, 20));
		infoMail.setFont(new Font("°íµñÃ¼", Font.BOLD, 20));
		infoJDate.setFont(new Font("°íµñÃ¼", Font.BOLD, 20));
		
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
		clientUserPanel.setFont(new Font("°íµñÃ¼", Font.BOLD, 15));

		
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
			System.out.println("È¸¿øÁ¤º¸ ¼öÁ¤ Ã¢ OPEN");
			
		} else if (e.getSource() == btnDelete) {
						
			String id2 = JOptionPane.showInputDialog(this,"Å»ÅðÇÒ ¾ÆÀÌµð ´Ù½Ã ÀÔ·Â :");
			if (id2 == null) {
				return;
			} else if (!id2.equals(id)){
				JOptionPane.showMessageDialog(this, "¾ÆÀÌµð°¡ ÀÏÄ¡ÇÏÁö ¾Ê½À´Ï´Ù.");
			} else if (id2.equals(id)) {
				int warnning = JOptionPane.showConfirmDialog(this, "Á¤¸»·Î Å»ÅðÇÏ½Ã°Ú½À´Ï´Ù?", "Å»Åð °æ°í",
			               JOptionPane.YES_NO_OPTION);
				
			    if (warnning == 1) {return;}
			    else {	
			    	if (dao.deleteByClient(id) > 0) {
			    		JOptionPane.showMessageDialog(this, "Å»Åð ¿Ï·á");
			    		clientF.setVisible(false);   		
			    		indexF.setVisible(true);
			    		indexF.tf_id.setText("");			indexF.tf_pw.setText("");			    		
			    	} else {
			    		JOptionPane.showMessageDialog(this, "Å»Åð ½ÇÆÐ");
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