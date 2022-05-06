package rental;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class AdminRentalFrame extends JFrame implements ActionListener, KeyListener  {

	JButton search = new JButton("�˻�");
	JButton all = new JButton("��ü �˻�");
	JButton update = new JButton("����");
	JButton delete = new JButton("�뿩���");
	public JPanel adminRentalPanel = new JPanel(new BorderLayout());
	
	String data[][] = new String[0][14];
	String[] name = { "�뿩��ȣ", "���̵�", "����", "��ǰ �ڵ�", "��ǰ��", "�뿩��û��", "�뿩������", "�뿩������", "�뿩�Ⱓ", "�ܿ���", "�� �뿩��",
			"�� �뿩��", "����ó", "�ּ�" };
	DefaultTableModel dt = new DefaultTableModel(data, name) {
		public boolean isCellEditable(int i, int c) {
			return false;
		}
	};
	DefaultTableCellRenderer dtcc = new DefaultTableCellRenderer();
	DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
	String[] comboName = { "�뿩��ȣ", "���̵�", "��ǰ �ڵ�" };
	JTable jt = new JTable(dt);
	JComboBox combo = new JComboBox(comboName);
	JScrollPane jsp = new JScrollPane(jt);
	JTextField jtf = new JTextField(20);

	RentalDAO dao = new RentalDAO();

	public AdminRentalFrame() {
		JPanel s = new JPanel();

		s.add(combo);
		s.add(jtf);
		s.add(search);
		s.add(all);
		s.add(update);
		s.add(delete);
		s.setBackground(Color.lightGray);
		adminRentalPanel.add(jsp, "Center");
		adminRentalPanel.add(s, "South");

		jsp.setPreferredSize(new Dimension(1380, 570));
		adminRentalPanel.setPreferredSize(new Dimension(500, 38));
		jt.getColumn("�뿩��ȣ").setPreferredWidth(50);
		jt.getColumn("�뿩��ȣ").setCellRenderer(dtcc);
		jt.getColumn("���̵�").setPreferredWidth(70);
		jt.getColumn("���̵�").setCellRenderer(dtcc);
		jt.getColumn("����").setPreferredWidth(60);
		jt.getColumn("����").setCellRenderer(dtcc);
		jt.getColumn("��ǰ �ڵ�").setPreferredWidth(80);
		jt.getColumn("��ǰ �ڵ�").setCellRenderer(dtcc);
		jt.getColumn("��ǰ��").setPreferredWidth(120);
		jt.getColumn("��ǰ��").setCellRenderer(dtcc);
		jt.getColumn("�뿩��û��").setPreferredWidth(80);
		jt.getColumn("�뿩��û��").setCellRenderer(dtcc);
		jt.getColumn("�뿩������").setPreferredWidth(80);
		jt.getColumn("�뿩������").setCellRenderer(dtcc);
		jt.getColumn("�뿩������").setPreferredWidth(80);
		jt.getColumn("�뿩������").setCellRenderer(dtcc);
		jt.getColumn("�뿩�Ⱓ").setPreferredWidth(70);
		jt.getColumn("�뿩�Ⱓ").setCellRenderer(dtcr);
		jt.getColumn("�ܿ���").setPreferredWidth(60);
		jt.getColumn("�ܿ���").setCellRenderer(dtcr);
		jt.getColumn("�� �뿩��").setPreferredWidth(70);
		jt.getColumn("�� �뿩��").setCellRenderer(dtcr);
		jt.getColumn("�� �뿩��").setPreferredWidth(80);
		jt.getColumn("�� �뿩��").setCellRenderer(dtcr);
		jt.getColumn("����ó").setPreferredWidth(100);
		jt.getColumn("����ó").setCellRenderer(dtcc);
		jt.getColumn("�ּ�").setPreferredWidth(230);

		dtcr.setHorizontalAlignment(SwingConstants.RIGHT);
		dtcc.setHorizontalAlignment(SwingConstants.CENTER);

		setSize(1400, 700);
		setResizable(false);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		search.addActionListener(this);
		all.addActionListener(this);
		update.addActionListener(this);
		delete.addActionListener(this);
		jtf.addKeyListener(this);

		dao.SelectAll(dt);
		jt.getTableHeader().setReorderingAllowed(false);
		jt.getTableHeader().setResizingAllowed(false);
		if (dt.getRowCount() > 0)
			jt.setRowSelectionInterval(0, 0);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == update) {
			int row = jt.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(this, "���� ������ ����.");
				return;
			} else {
				String rcode;
				rcode = (String) (dt.getValueAt(row, 0));
				dao.updateShowByAdmin(this, rcode);
				dao.SelectAll(dt);
				jt.setRowSelectionInterval(row, row);
			}
		} else if (e.getSource() == all) {
			dao.SelectAll(dt);
			if (dt.getRowCount() > 0)
				jt.setRowSelectionInterval(0, 0);
		}

		else if (e.getSource() == delete) {
			int row = jt.getSelectedRow();
			Object obj = jt.getValueAt(row, 0);
			System.out.println("�뿩�ڵ� : " + obj);
			int warnning = JOptionPane.showConfirmDialog(this, "������ ����Ͻðڽ��ϱ�?", "���� ���", JOptionPane.YES_NO_OPTION);
			if (warnning != 0) { return; } 
			if (dao.rentalDelete(obj.toString()) > 0) {
				JOptionPane.showMessageDialog(this, "���ڵ� ����");
				dao.SelectAll(dt);
				if (dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);
			} else {
				JOptionPane.showMessageDialog(this, "���ڵ� �������� ����");
			}
		} else if (e.getSource() == search) {

			String fieldName = combo.getSelectedItem().toString();
			System.out.println(fieldName + "�˻�");

			if (jtf.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "�˻��� �Է�");
				jtf.requestFocus();
			} else
				dao.keySearch(dt, fieldName, jtf.getText());
			if (dt.getRowCount() > 0)
				jt.setRowSelectionInterval(0, 0);
			jtf.requestFocus();
			jtf.setText("");
		}
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
