package product;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class ClientProductFrame extends JFrame implements ActionListener, KeyListener {
	String id;
	String[] name = {"��ǰ �ڵ�", "��ǰ �з�", "��ǰ��", "��ǰ ����", "�� �뿩��", "���� �ֱ�"};
	
	DefaultTableModel dt = new DefaultTableModel(name,0) {
		public boolean isCellEditable(int i, int c) {
            return false;
        }
	};
	
	public JTable jt = new JTable(dt);
	JScrollPane jsp = new JScrollPane(jt);
	public JPanel p = new JPanel(new BorderLayout());
	public JPanel clientProductPanel = new JPanel(new BorderLayout());
	String[] comboName = {"��ǰ �з�", "��ǰ��", "��ǰ ����", "�� �뿩��"};
	
	JComboBox combo = new JComboBox(comboName);
	JTextField jtf = new JTextField(30);
	JButton all	= new JButton("��ü��ȸ");
	JButton search = new JButton("�˻�");
	JButton rental = new JButton("�뿩��û");
	JButton insert = new JButton("����");
	ProductDAO dao = new ProductDAO();
	JLabel empty = new JLabel("    "); 
	JLabel empty1 = new JLabel("    "); 

	public ClientProductFrame(String id) {
		super("��ǰ ���");
		this.id = id;
		jt.setRowHeight(30);
		jt.setFont(new Font("���Ļ�浸��", Font.PLAIN, 15));
		combo.setFont(new Font("���Ļ�浸��", Font.PLAIN, 20));
		all.setFont(new Font("���Ļ�浸��", Font.PLAIN, 20));
		search.setFont(new Font("���Ļ�浸��", Font.PLAIN, 20));
		rental.setFont(new Font("���Ļ�浸��", Font.PLAIN, 20));
		insert.setFont(new Font("���Ļ�浸��", Font.PLAIN, 20));
		combo.setPreferredSize(new Dimension(150,30));

		p.setBackground(Color.LIGHT_GRAY);
		jt.getTableHeader().setBackground(Color.LIGHT_GRAY);
		p.add(combo); p.add(jtf); p.add(search); p.add(empty); p.add(all); p.add(empty1); p.add(rental);
		clientProductPanel.add(jsp,"Center"); 	clientProductPanel.add(p,"South"); 
		jtf.setPreferredSize(new Dimension(0,30));
		search.setPreferredSize(new Dimension(100,30));
		all.setPreferredSize(new Dimension(130,30));
		rental.setPreferredSize(new Dimension(130,30));
		//p.setPreferredSize(new Dimension(0,45));
		p.setLayout(new FlowLayout(FlowLayout.CENTER,3,7));
		
		jt.getTableHeader().setReorderingAllowed(false);
		jt.getTableHeader().setResizingAllowed(false);
		//jt.getTableHeader().setBackground(Color.BLACK);
		
		setSize(1400,700); //setVisible(true); 
		setResizable(false);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		search.addActionListener(this);
		rental.addActionListener(this);
		all.addActionListener(this);
		insert.addActionListener(this);
		jtf.addKeyListener(this);
		
		//��� ���ڵ带 �˻��Ͽ� DefaultTableModel�� �ø���
		dao.userSelectAll(dt);
		
		//ù ��° �� ����
		if(dt.getRowCount()>0) jt.setRowSelectionInterval(0, 0);
		
		// DefaultTableCellHeaderRenderer ���� (��� ������ ����)
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();

		// DefaultTableCellHeaderRenderer�� ������ ��� ���ķ� ����
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		// ������ ���̺��� ColumnModel�� ������
		TableColumnModel tcmSchedule = jt.getColumnModel();

		// �ݺ����� �̿��Ͽ� ���̺��� ��� ���ķ� ����
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
		tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}
		
	}

//	public static void main(String[] args) {
//		new ProductMain();
//	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() ==search) {
			//JComboBox�� ���õ� value ��������
			String fieldName = combo.getSelectedItem().toString();
			System.out.println("�ʵ�� "+fieldName);
			if(jtf.getText().trim().equals("")) {
				ClientProductDialog.messageBox(this,"�˻��� �Է�!");
				jtf.requestFocus();
				if(dt.getRowCount()>0) jt.setRowSelectionInterval(0, 0);
			}else {
				dao.getUserSearch(dt,fieldName,jtf.getText());
				jtf.setText("");
				if(dt.getRowCount()>0) jt.setRowSelectionInterval(0, 0);
			}
		} else if (e.getSource() == rental) {
			new ClientProductDialog(this, "�뿩��û", id);
		} else if(e.getSource() == all) {
			dao.userSelectAll(dt);
			System.out.println("��ü��ȸ");
			if(dt.getRowCount()>0) jt.setRowSelectionInterval(0, 0);
		} /*else if(e.getSource() == insert) {
			new ProductGUI(this, "�뿩�ϱ�");
		}*/
	}

@Override
public void keyPressed(KeyEvent e) {	
	int key = e.getKeyCode();
	if (key == KeyEvent.VK_ENTER) {
	Toolkit.getDefaultToolkit().beep();
	search.doClick();
	}
}

@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}
}
