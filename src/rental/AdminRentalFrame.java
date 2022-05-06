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

	JButton search = new JButton("검색");
	JButton all = new JButton("전체 검색");
	JButton update = new JButton("수정");
	JButton delete = new JButton("대여취소");
	public JPanel adminRentalPanel = new JPanel(new BorderLayout());
	
	String data[][] = new String[0][14];
	String[] name = { "대여번호", "아이디", "성명", "제품 코드", "제품명", "대여신청일", "대여시작일", "대여종료일", "대여기간", "잔여일", "월 대여료",
			"총 대여료", "연락처", "주소" };
	DefaultTableModel dt = new DefaultTableModel(data, name) {
		public boolean isCellEditable(int i, int c) {
			return false;
		}
	};
	DefaultTableCellRenderer dtcc = new DefaultTableCellRenderer();
	DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
	String[] comboName = { "대여번호", "아이디", "제품 코드" };
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
		jt.getColumn("대여번호").setPreferredWidth(50);
		jt.getColumn("대여번호").setCellRenderer(dtcc);
		jt.getColumn("아이디").setPreferredWidth(70);
		jt.getColumn("아이디").setCellRenderer(dtcc);
		jt.getColumn("성명").setPreferredWidth(60);
		jt.getColumn("성명").setCellRenderer(dtcc);
		jt.getColumn("제품 코드").setPreferredWidth(80);
		jt.getColumn("제품 코드").setCellRenderer(dtcc);
		jt.getColumn("제품명").setPreferredWidth(120);
		jt.getColumn("제품명").setCellRenderer(dtcc);
		jt.getColumn("대여신청일").setPreferredWidth(80);
		jt.getColumn("대여신청일").setCellRenderer(dtcc);
		jt.getColumn("대여시작일").setPreferredWidth(80);
		jt.getColumn("대여시작일").setCellRenderer(dtcc);
		jt.getColumn("대여종료일").setPreferredWidth(80);
		jt.getColumn("대여종료일").setCellRenderer(dtcc);
		jt.getColumn("대여기간").setPreferredWidth(70);
		jt.getColumn("대여기간").setCellRenderer(dtcr);
		jt.getColumn("잔여일").setPreferredWidth(60);
		jt.getColumn("잔여일").setCellRenderer(dtcr);
		jt.getColumn("월 대여료").setPreferredWidth(70);
		jt.getColumn("월 대여료").setCellRenderer(dtcr);
		jt.getColumn("총 대여료").setPreferredWidth(80);
		jt.getColumn("총 대여료").setCellRenderer(dtcr);
		jt.getColumn("연락처").setPreferredWidth(100);
		jt.getColumn("연락처").setCellRenderer(dtcc);
		jt.getColumn("주소").setPreferredWidth(230);

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
				JOptionPane.showMessageDialog(this, "수정 데이터 선택.");
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
			System.out.println("대여코드 : " + obj);
			int warnning = JOptionPane.showConfirmDialog(this, "정말로 취소하시겠습니까?", "삭제 경고", JOptionPane.YES_NO_OPTION);
			if (warnning != 0) { return; } 
			if (dao.rentalDelete(obj.toString()) > 0) {
				JOptionPane.showMessageDialog(this, "레코드 삭제");
				dao.SelectAll(dt);
				if (dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);
			} else {
				JOptionPane.showMessageDialog(this, "레코드 삭제되지 않음");
			}
		} else if (e.getSource() == search) {

			String fieldName = combo.getSelectedItem().toString();
			System.out.println(fieldName + "검색");

			if (jtf.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "검색어 입력");
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
