package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import index.AdminFrame;
import index.IndexFrame;

public class AdminUserFrame extends JFrame implements ActionListener, KeyListener {
	IndexFrame indexF;
	AdminFrame AdminF;
	
	String data[][] = new String[0][7];	
	String [] title = { "���̵�", "����", "�������", "�ּ�", "����ó", "�̸���", "������" };
	DefaultTableModel dTable = new DefaultTableModel(data, title) {
		public boolean isCellEditable(int i, int c) {
            return false;
        }
	};
	JTable table = new JTable(dTable);
	DefaultTableCellRenderer dtcc = new DefaultTableCellRenderer();
	JScrollPane centerPan = new JScrollPane(table);
	
	String [] comboText = { "���̵�", "����", "�ּ�", "����ó", "�̸���" };
	JComboBox combo = new JComboBox(comboText);
	JTextField tf_search = new JTextField(20);
	JLabel empty1 = new JLabel("      ");
	JLabel empty2= new JLabel("                       ");
	JLabel empty3= new JLabel("                                                                               ");
	JLabel info = new JLabel("ȸ������ :");
	JButton btnSearch = new JButton("�˻�");
	JButton btnSelectAll = new JButton("��ü��ȸ");
	JButton btnUpdate = new JButton("����");
	JButton btnDelete = new JButton("����");
	JButton btnLogout = new JButton("�α׾ƿ�");
	JPanel southPan = new JPanel(new FlowLayout());
	
	public JPanel adminUserPanel = new JPanel(new BorderLayout());
	
	ClientDAO dao = new ClientDAO();
	
	public AdminUserFrame(IndexFrame indexF, AdminFrame AdminF) {
		this.indexF = indexF;
		this.AdminF = AdminF;
		
		table.getTableHeader().setReorderingAllowed(false);
	    table.getTableHeader().setResizingAllowed(false);
	    table.getColumn("���̵�").setPreferredWidth(100);
	    table.getColumn("����").setPreferredWidth(100);
	    table.getColumn("�������").setPreferredWidth(150);
	    table.getColumn("�ּ�").setPreferredWidth(300);
	    table.getColumn("����ó").setPreferredWidth(150);
	    table.getColumn("�̸���").setPreferredWidth(150);
	    table.getColumn("������").setPreferredWidth(150);
	    table.getColumn("�������").setCellRenderer(dtcc);
		table.getColumn("����ó").setCellRenderer(dtcc);
		table.getColumn("������").setCellRenderer(dtcc);
		dtcc.setHorizontalAlignment(SwingConstants.CENTER);
		
		southPan = new JPanel();
		southPan.add(combo);
		southPan.add(tf_search);
		southPan.add(btnSearch);
		southPan.add(empty1);
		southPan.add(btnSelectAll);	
		southPan.add(empty2);	
		southPan.add(info);	
		southPan.add(btnUpdate);	
		southPan.add(btnDelete);	
		southPan.add(empty3);	
		southPan.add(btnLogout);	
		southPan.setBackground(Color.lightGray);

		adminUserPanel.add(centerPan, "Center");
		adminUserPanel.add(southPan, "South");
		
		adminUserPanel.setSize(1400,700);				
//		setVisible(true);		setResizable(false);
//		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		dao.selectAllByAdmin(dTable);
		if(dTable.getRowCount()>0) table.setRowSelectionInterval(0, 0);
		eventUp();
	}

//	public static void main(String[] args) {
//		new AdminUserFrame();
//	}
	
	public void eventUp() {
		btnSearch.addActionListener(this);
		btnSelectAll.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
		btnLogout.addActionListener(this);
		tf_search.addKeyListener(this);
	}

	public void actionPerformed(ActionEvent e) {		
		if (e.getSource() == btnSearch) {
			
		String comboName = combo.getSelectedItem().toString();
		System.out.println("�˻����� : " + comboName);
			if (tf_search.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "�˻�� �Է����ּ���.");
				tf_search.requestFocus();
			} else {
				dao.searchByAdmin(dTable, comboName, tf_search.getText());
				if(dTable.getRowCount()>0) table.setRowSelectionInterval(0, 0);
				tf_search.setText("");
			}	
	
		} else if (e.getSource() == btnSelectAll) {
			dao.selectAllByAdmin(dTable);
			if(dTable.getRowCount()>0) table.setRowSelectionInterval(0, 0);
			System.out.println("��ü��ȸ"); 
			
		} else if (e.getSource() == btnUpdate) {
			int row = table.getSelectedRow();
			if(row < 0) {
				JOptionPane.showMessageDialog(this, "������ �����͸� �����ϼ���.");
	            return;
			} else {
				String id;
				id = (String)(dTable.getValueAt(row,0));
				dao.updateShowByAdmin(this,id);
			}
			System.out.println("ȸ������ ���� â OPEN");
		} else if (e.getSource() == btnDelete) {
			int row = table.getSelectedRow();
			if(row < 0) {
				JOptionPane.showMessageDialog(this, "������ �����͸� �����ϼ���.");
	            return;
			} else {
				String id;
				id = (String)(dTable.getValueAt(row,0));
				String id2 = JOptionPane.showInputDialog(this,"������ ���̵� �ٽ� �Է� :");
				if (id2 == null) {
					return;
				} else if (!id2.equals(id)){
					JOptionPane.showMessageDialog(this, "���̵� ��ġ���� �ʽ��ϴ�.");
				} else if (id2.equals(id)) {
					int warnning = JOptionPane.showConfirmDialog(this, "������ �����Ͻðڽ��ϴ�?", "���� ���",
				               JOptionPane.YES_NO_OPTION);
					
				    if (warnning != 0) {return;}			 
				    else {	
				    	if (dao.deleteByAdmin(id) > 0) {
				    		JOptionPane.showMessageDialog(this, "���� �Ϸ�");
				    		dao.selectAllByAdmin(dTable);
				    		if (dTable.getRowCount() > 0) {table.setRowSelectionInterval(0, 0);}
				    	} else {
				    		JOptionPane.showMessageDialog(this, "���� ����");
				    	}
				    }
				}			
			}
		} else if (e.getSource() == btnLogout) {
			AdminF.setVisible(false);
			indexF.setVisible(true);
			indexF.tf_id.setText("");			indexF.tf_pw.setText("");
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {	
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ENTER) {
		Toolkit.getDefaultToolkit().beep();
		btnSearch.doClick();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}
