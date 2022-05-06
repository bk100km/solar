package rental;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class ClientRentalFrame extends JFrame implements ActionListener, MouseListener, KeyListener {

	String id;
	String[] name = { "예약번호", "제품 코드", "제품 분류", "제품명", "월 대여료", "대여기간", "총 대여료", "대여시작일", "대여종료일", "잔여일"};
	RentalDAO dao = new RentalDAO();
	DefaultTableCellRenderer dtcrc = new DefaultTableCellRenderer();
	DefaultTableCellRenderer dtcrr = new DefaultTableCellRenderer();
	public JPanel clientRentalPanel = new JPanel(new BorderLayout());
	DefaultTableModel dt = new DefaultTableModel(name, 0) {
		public boolean isCellEditable(int row, int col) {
			return false;
		}
	};

	JTable jt = new JTable(dt);
	JScrollPane jsp = new JScrollPane(jt);
	JPanel ps = new JPanel();
	JPanel pw = new JPanel();
	JPanel tp = new JPanel();
	String[] comboName = { "제품 분류", "제품명" };
	JLabel lable_Rcode = new JLabel("   예 약 번 호    ");
	JLabel lable_Pcode = new JLabel("   제 품 코 드    ");
	JLabel lable_Ptype = new JLabel("   제 품 분 류    ");
	JLabel lable_Pname = new JLabel("   제    품    명   ");
	JLabel lable_Pprice = new JLabel("    월 대 여 료    ");
	JLabel lable_Rstart = new JLabel("   대여시작일   ");
	JLabel lable_Rend = new JLabel("   대여종료일   ");
	JLabel lable_Rday = new JLabel("    대 여 기 간    ");
	JLabel lable_Rtotal = new JLabel("    총 대 여 료    ");
	JLabel lable_Month = new JLabel("   개월   ");
	JLabel label_empty = new JLabel("                                                                         ");
	JLabel label_empty2 = new JLabel("                                                                                  ");
	JLabel label_empty3 = new JLabel("  ");
	JLabel label_empty4 = new JLabel(" ");
	JTextField Rcode = new JTextField();
	JTextField Pcode = new JTextField();
	JTextField Ptype = new JTextField();
	JTextField Pname = new JTextField();
	JTextField Pprice = new JTextField();
	JTextField Rstart = new JTextField();
	JTextField Rend = new JTextField();
	JTextField Rday = new JTextField();
	JTextField Rtotal = new JTextField();

	JComboBox combo = new JComboBox(comboName);
	JTextField jtf = new JTextField(20);
	JButton searchButton = new JButton("검색");
	JButton selectAllButton = new JButton("전체보기");
	JButton updateButton = new JButton("수정");
	JButton deleteButton = new JButton("대여취소");
	JButton confirm;

	public ClientRentalFrame(String id) {
		super("");
		this.id = id;
		setResizable(false);
		pw.add(lable_Rcode);
		pw.add(Rcode);
		pw.add(lable_Pcode);
		pw.add(Pcode);
		pw.add(lable_Ptype);
		pw.add(Ptype);
		pw.add(lable_Pname);
		pw.add(Pname);
		pw.add(lable_Pprice);
		pw.add(Pprice);
		pw.add(lable_Rstart);
		pw.add(Rstart);
		pw.add(lable_Rend);
		pw.add(Rend);
		pw.add(lable_Rday);
		pw.add(Rday);
		pw.add(lable_Month);
		pw.add(lable_Rtotal);
		pw.add(Rtotal);
		ps.add(label_empty);
		pw.add(label_empty2);
		pw.add(label_empty3);
		pw.add(updateButton);
		pw.add(label_empty4);
		pw.add(deleteButton);
		ps.add(combo);
		ps.add(jtf);
		ps.add(searchButton);
		ps.add(selectAllButton);
		clientRentalPanel.add(pw, "West");
		clientRentalPanel.add(jsp, "Center");
		clientRentalPanel.add(ps, "South");

		pw.setLocation(800, 400);
		clientRentalPanel.setPreferredSize(new Dimension(500, 38));
		jsp.setPreferredSize(new Dimension(800, 450));
		pw.setPreferredSize(new Dimension(270, 450));
		ps.setPreferredSize(new Dimension(800, 60));
		pw.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
		ps.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		Rcode.setPreferredSize(new Dimension(160, 47));
		Pcode.setPreferredSize(new Dimension(160, 47));
		Pname.setPreferredSize(new Dimension(160, 47));
		Ptype.setPreferredSize(new Dimension(160, 47));
		Pprice.setPreferredSize(new Dimension(160, 47));
		Rtotal.setPreferredSize(new Dimension(160, 47));
		Rstart.setPreferredSize(new Dimension(160, 47));
		Rend.setPreferredSize(new Dimension(160, 47));
		Rday.setPreferredSize(new Dimension(112, 47));
		lable_Rcode.setPreferredSize(new Dimension(85, 47));
		lable_Pcode.setPreferredSize(new Dimension(85, 47));
		lable_Ptype.setPreferredSize(new Dimension(85, 47));
		lable_Pname.setPreferredSize(new Dimension(85, 47));
		lable_Pprice.setPreferredSize(new Dimension(85, 47));
		lable_Rstart.setPreferredSize(new Dimension(85, 47));
		lable_Rend.setPreferredSize(new Dimension(85, 47));
		lable_Rday.setPreferredSize(new Dimension(85, 47));
		lable_Rtotal.setPreferredSize(new Dimension(85, 47));
		lable_Month.setPreferredSize(new Dimension(44, 47));
		updateButton.setPreferredSize(new Dimension(110, 47));
		deleteButton.setPreferredSize(new Dimension(110, 47));
		searchButton.setPreferredSize(new Dimension(80, 30));
		selectAllButton.setPreferredSize(new Dimension(90, 30));
		combo.setPreferredSize(new Dimension(80, 30));
		jtf.setPreferredSize(new Dimension(350, 31));
		jt.getColumnModel().getColumn(0).setPreferredWidth(25);
		jt.getColumnModel().getColumn(1).setPreferredWidth(50);
		jt.getColumnModel().getColumn(2).setPreferredWidth(80);
		jt.getColumnModel().getColumn(3).setPreferredWidth(90);
		jt.getColumnModel().getColumn(4).setPreferredWidth(70);
		jt.getColumnModel().getColumn(5).setPreferredWidth(50);
		jt.getColumnModel().getColumn(6).setPreferredWidth(70);
		jt.getColumnModel().getColumn(7).setPreferredWidth(70);
		jt.getColumnModel().getColumn(8).setPreferredWidth(70);
		jt.getColumnModel().getColumn(9).setPreferredWidth(40);
		dtcrc.setHorizontalAlignment(SwingConstants.CENTER);
		dtcrr.setHorizontalAlignment(SwingConstants.RIGHT);
		TableColumnModel tcm = jt.getColumnModel();
		tcm.getColumn(0).setCellRenderer(dtcrc);
		tcm.getColumn(1).setCellRenderer(dtcrc);
		tcm.getColumn(2).setCellRenderer(dtcrc);
		tcm.getColumn(3).setCellRenderer(dtcrc);
		tcm.getColumn(4).setCellRenderer(dtcrr);
		tcm.getColumn(5).setCellRenderer(dtcrr);
		tcm.getColumn(6).setCellRenderer(dtcrr);
		tcm.getColumn(7).setCellRenderer(dtcrc);
		tcm.getColumn(8).setCellRenderer(dtcrc);
		tcm.getColumn(9).setCellRenderer(dtcrr);
		setSize(1150, 650);
		setVisible(false);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jt.getTableHeader().setReorderingAllowed(false);

		searchButton.addActionListener(this);
		updateButton.addActionListener(this);
		deleteButton.addActionListener(this);
		selectAllButton.addActionListener(this);
		jtf.addKeyListener(this);
		jt.addMouseListener(this);
		jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		dao.clientRentalSelectAll(dt, id);
		if (dt.getRowCount() > 0)
			jt.setRowSelectionInterval(0, 0);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchButton) {
			String fieldName = combo.getSelectedItem().toString();
			System.out.println("필드명" + fieldName);

			if (fieldName.trim().equals("제품 분류")) {
				dao.clientRentalSearch(dt, fieldName, jtf.getText(),id);
				if (dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);
			} else if (fieldName.trim().equals("제품명")) {
					dao.clientRentalSearch(dt, fieldName, jtf.getText(),id);
					if (dt.getRowCount() > 0)
						jt.setRowSelectionInterval(0, 0);
			} else {
				if (jtf.getText().trim().equals("")) {
					RentalDAO.messageBox(this, "검색어 입력!");
					jtf.requestFocus();
				} else {
					dao.clientRentalSearch(dt, fieldName, jtf.getText(), id);
					if (dt.getRowCount() > 0)
						jt.setRowSelectionInterval(0, 0);
				}
			}
		} else if (e.getSource() == updateButton) {
			int row = jt.getSelectedRow();
			int warnning = JOptionPane.showConfirmDialog(this, "정말로 수정하시겠습니까?", "경고", JOptionPane.YES_NO_OPTION);
			if (warnning != 0) {
				return;
			}
			if (dao.clientRentalUpdate(this) > 0) {
				messageBox(this, "수정 완료");
				dao.clientRentalSelectAll(dt, id);
				if (dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, row);
			} else {
				messageBox(this, "수정 실패");
			}
		} else if (e.getSource() == deleteButton) {
			int row = jt.getSelectedRow();
			System.out.println("선택 행 : " + row);
			Object obj = jt.getValueAt(row, 0);
			System.out.println("값 : " + obj);
			int warnning = JOptionPane.showConfirmDialog(this, "정말로 취소하시겠습니까?", "삭제 경고", JOptionPane.YES_NO_OPTION);
			if (warnning != 0) {
				return;
			} else if (dao.clientRentalDelete(obj.toString()) > 0) {
				RentalDAO.messageBox(this, "대여예약이 취소되었습니다.");
				dao.clientRentalSelectAll(dt, id);
				if (dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);
			} else {
				RentalDAO.messageBox(this, "예약취소 실패 !");
			}
		} else if (e.getSource() == selectAllButton) {
			dao.clientRentalSelectAll(dt, id);
			if (dt.getRowCount() > 0)
				jt.setRowSelectionInterval(0, 0);
		}
	}

	public void mouseClicked(MouseEvent me) {
		int row = jt.getSelectedRow();
		int column = jt.getSelectedColumn();
		System.out.println(row + "행, " + jt.getValueAt(row, column) + "선택했음");

		for (int i = 0; i < 10; i++) {
			String rowval = null;
			rowval = (String) dt.getValueAt(row, i);
			switch (i) {
			case 0:
				Rcode.setText("    " + rowval);
				break;
			case 1:
				Pcode.setText("    " + rowval);
				break;
			case 2:
				Ptype.setText("    " + rowval);
				break;
			case 3:
				Pname.setText("    " + rowval);
				break;
			case 4:
				Pprice.setText(" " + rowval);
				break;
			case 5:
				Rday.setText("    " + rowval.substring(0, rowval.length() - 2));
				break;
			case 6:
				Rtotal.setText(" " + rowval);
				break;
			case 7:
				Rstart.setText("    " + rowval);
				break;
			case 8:
				Rend.setText("    " + rowval);
				break;
			}
		}
		Rcode.setEnabled(false);
		Pcode.setEnabled(false);
		Pname.setEnabled(false);
		Ptype.setEnabled(false);
		Pprice.setEnabled(false);
		Rtotal.setEnabled(false);
		Rend.setEnabled(false);
	}

	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog((Component) obj, message);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ENTER) {
			Toolkit.getDefaultToolkit().beep();
			searchButton.doClick();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}