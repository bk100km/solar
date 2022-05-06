package product;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AdminProductDialog extends JDialog implements ActionListener {
	JPanel pw = new JPanel(new GridLayout(6,1));
	JPanel pc = new JPanel(new GridLayout(6,1));
	JPanel pa = new JPanel(new GridLayout(6,1));
	JPanel ps = new JPanel();
	JLabel lable_pcode = new JLabel("  ��ǰ �ڵ� ");
	JLabel lable_pcate = new JLabel("  ��ǰ �з� ");
	JLabel lable_pname = new JLabel("  ��  ǰ  �� ");
	JLabel lable_ptype = new JLabel("  ��ǰ ����  ");
	JLabel lable_pprice = new JLabel(" �� �뿩�� ");
	JLabel lable_pas = new JLabel("  ���� �ֱ� ");
	JTextField pcode = new JTextField();
	JTextField pcate = new JTextField();
	JTextField pname = new JTextField();
	JTextField ptype = new JTextField();
	JTextField pprice= new JTextField();
	JTextField pas = new JTextField();
	JLabel lable_pcode1 = new JLabel();
	JLabel lable_pcate2 = new JLabel();
	JLabel lable_pname3 = new JLabel();
	JLabel lable_ptype4 = new JLabel();
	JLabel lable_pprice5 = new JLabel("  ��");
	JLabel lable_pas6 = new JLabel("  ���� ");
	//JLabel lable_price5 = new JLabel("  �� ");
	JButton confirm;
	JButton reset = new JButton("���");
	AdminProductFrame me;
	//Font f20= new Font("���Ļ�浸��", Font.BOLD, 20);
	//Font f18= new Font("���Ļ�浸��", Font.PLAIN, 18);
	

	ProductDAO dao = new ProductDAO();
		
	public AdminProductDialog(AdminProductFrame me, String index) {
		
		this.me = me;
		if(index.equals("�߰�")) confirm = new JButton(index);
			else {
				confirm = new JButton("����");
				int row = me.jt.getSelectedRow();
				String sub = me.jt.getValueAt(row, 5).toString();
				pcode.setText(me.jt.getValueAt(row, 0).toString());
				pcate.setText(me.jt.getValueAt(row, 1).toString());
				pname.setText(me.jt.getValueAt(row, 2).toString());
				ptype.setText(me.jt.getValueAt(row, 3).toString());
				pprice.setText(me.jt.getValueAt(row, 4).toString().replaceAll("[^0-9]",""));
				pas.setText(sub.substring(0, sub.length()-2 ));
	
				pcode.setEditable(false);
				pcate.setEditable(false);
				pname.setEditable(false);
				ptype.setEditable(false);
			}
		
		pw.add(lable_pcode); pw.add(lable_pcate); pw.add(lable_pname);
		pw.add(lable_ptype); pw.add(lable_pprice); pw.add(lable_pas);
		pc.add(pcode); pc.add(pcate); pc.add(pname); pc.add(ptype); pc.add(pprice); pc.add(pas);  
		ps.add(confirm); ps.add(reset);
		pa.add(lable_pcode1); pa.add(lable_pcate2); pa.add(lable_pname3);
		pa.add(lable_ptype4); pa.add(lable_pprice5); pa.add(lable_pas6);
		add(pw,"West"); add(pc,"Center"); add(ps,"South"); add(pa,"East");
		setSize(300,300); 	setLocation(1400,250);
		setVisible(true);
		
		lable_pcode.setHorizontalAlignment(JLabel.RIGHT);
		lable_pcate.setHorizontalAlignment(JLabel.RIGHT);
		lable_pname.setHorizontalAlignment(JLabel.RIGHT);
		lable_ptype.setHorizontalAlignment(JLabel.RIGHT);
		lable_pprice.setHorizontalAlignment(JLabel.RIGHT);
		lable_pas.setHorizontalAlignment(JLabel.RIGHT);
		
		/*lable_pcode.setFont(f20);
		lable_pcate.setFont(f20);
		lable_pname.setFont(f20);
		lable_ptype.setFont(f20);
		lable_pprice.setFont(f20);
		lable_pas.setFont(f20);
		pcode.setFont(f18);
		pcate.setFont(f18);
		pname.setFont(f18);
		ptype.setFont(f18);
		pprice.setFont(f18);
		pas.setFont(f18);
		confirm.setFont(new Font("���Ļ�浸��",Font.BOLD, 15));
		reset.setFont(new Font("���Ļ�浸��",Font.BOLD, 15));
		
		confirm.setPreferredSize(new Dimension(80,30));
		reset.setPreferredSize(new Dimension(80,30));*/
		
		
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		confirm.addActionListener(this);
		reset.addActionListener(this);
		
	}
	

		
	@Override
	public void actionPerformed(ActionEvent e) {
		String btnLabel = e.getActionCommand();
		
		if(btnLabel.equals("�߰�")) {
			if(dao.userListInsert(this)>0) {
				messageBox(this,pcode.getText()+"  �߰� �Ϸ�");
				dispose();
				
				dao.userSelectAll(me.dt);
				
				if(me.dt.getRowCount()>0) 
					me.jt.setRowSelectionInterval(0, 0);
			} else {
				messageBox(this,"�߰� ����");
			}
		
		} else if(btnLabel.equals("����")) {
			int row = me.jt.getSelectedRow();
			if(dao.userUpdate(this)>0) {
				messageBox(this,"���� �Ϸ�");
				dispose();
				dao.userSelectAll(me.dt);
				if(me.dt.getRowCount()>0)
					me.jt.setRowSelectionInterval(row, row);
			
			} else {
				messageBox(this,"���� ����");
			}
			
		}else if(btnLabel.equals("���")) {
			dispose();
		
		}
	}
	
	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog((Component)obj, message);
	}
}
