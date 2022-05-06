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
	String [] title = { "아이디", "성명", "생년월일", "주소", "연락처", "이메일", "가입일" };
	DefaultTableModel dTable = new DefaultTableModel(data, title) {
		public boolean isCellEditable(int i, int c) {
            return false;
        }
	};
	JTable table = new JTable(dTable);
	DefaultTableCellRenderer dtcc = new DefaultTableCellRenderer();
	JScrollPane centerPan = new JScrollPane(table);
	
	String [] comboText = { "아이디", "성명", "주소", "연락처", "이메일" };
	JComboBox combo = new JComboBox(comboText);
	JTextField tf_search = new JTextField(20);
	JLabel empty1 = new JLabel("      ");
	JLabel empty2= new JLabel("                       ");
	JLabel empty3= new JLabel("                                                                               ");
	JLabel info = new JLabel("회원정보 :");
	JButton btnSearch = new JButton("검색");
	JButton btnSelectAll = new JButton("전체조회");
	JButton btnUpdate = new JButton("수정");
	JButton btnDelete = new JButton("삭제");
	JButton btnLogout = new JButton("로그아웃");
	JPanel southPan = new JPanel(new FlowLayout());
	
	public JPanel adminUserPanel = new JPanel(new BorderLayout());
	
	ClientDAO dao = new ClientDAO();
	
	public AdminUserFrame(IndexFrame indexF, AdminFrame AdminF) {
		this.indexF = indexF;
		this.AdminF = AdminF;
		
		table.getTableHeader().setReorderingAllowed(false);
	    table.getTableHeader().setResizingAllowed(false);
	    table.getColumn("아이디").setPreferredWidth(100);
	    table.getColumn("성명").setPreferredWidth(100);
	    table.getColumn("생년월일").setPreferredWidth(150);
	    table.getColumn("주소").setPreferredWidth(300);
	    table.getColumn("연락처").setPreferredWidth(150);
	    table.getColumn("이메일").setPreferredWidth(150);
	    table.getColumn("가입일").setPreferredWidth(150);
	    table.getColumn("생년월일").setCellRenderer(dtcc);
		table.getColumn("연락처").setCellRenderer(dtcc);
		table.getColumn("가입일").setCellRenderer(dtcc);
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
		System.out.println("검색조건 : " + comboName);
			if (tf_search.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "검색어를 입력해주세요.");
				tf_search.requestFocus();
			} else {
				dao.searchByAdmin(dTable, comboName, tf_search.getText());
				if(dTable.getRowCount()>0) table.setRowSelectionInterval(0, 0);
				tf_search.setText("");
			}	
	
		} else if (e.getSource() == btnSelectAll) {
			dao.selectAllByAdmin(dTable);
			if(dTable.getRowCount()>0) table.setRowSelectionInterval(0, 0);
			System.out.println("전체조회"); 
			
		} else if (e.getSource() == btnUpdate) {
			int row = table.getSelectedRow();
			if(row < 0) {
				JOptionPane.showMessageDialog(this, "수정할 데이터를 선택하세요.");
	            return;
			} else {
				String id;
				id = (String)(dTable.getValueAt(row,0));
				dao.updateShowByAdmin(this,id);
			}
			System.out.println("회원정보 수정 창 OPEN");
		} else if (e.getSource() == btnDelete) {
			int row = table.getSelectedRow();
			if(row < 0) {
				JOptionPane.showMessageDialog(this, "삭제할 데이터를 선택하세요.");
	            return;
			} else {
				String id;
				id = (String)(dTable.getValueAt(row,0));
				String id2 = JOptionPane.showInputDialog(this,"삭제할 아이디 다시 입력 :");
				if (id2 == null) {
					return;
				} else if (!id2.equals(id)){
					JOptionPane.showMessageDialog(this, "아이디가 일치하지 않습니다.");
				} else if (id2.equals(id)) {
					int warnning = JOptionPane.showConfirmDialog(this, "정말로 삭제하시겠습니다?", "삭제 경고",
				               JOptionPane.YES_NO_OPTION);
					
				    if (warnning != 0) {return;}			 
				    else {	
				    	if (dao.deleteByAdmin(id) > 0) {
				    		JOptionPane.showMessageDialog(this, "삭제 완료");
				    		dao.selectAllByAdmin(dTable);
				    		if (dTable.getRowCount() > 0) {table.setRowSelectionInterval(0, 0);}
				    	} else {
				    		JOptionPane.showMessageDialog(this, "삭제 실패");
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
