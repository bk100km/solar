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
	String[] name = {"제품 코드", "제품 분류", "제품명", "제품 유형", "월 대여료", "점검 주기"};
	
	DefaultTableModel dt = new DefaultTableModel(name,0) {
		public boolean isCellEditable(int i, int c) {
            return false;
        }
	};
	
	public JTable jt = new JTable(dt);
	JScrollPane jsp = new JScrollPane(jt);
	public JPanel p = new JPanel(new BorderLayout());
	public JPanel clientProductPanel = new JPanel(new BorderLayout());
	String[] comboName = {"제품 분류", "제품명", "제품 유형", "월 대여료"};
	
	JComboBox combo = new JComboBox(comboName);
	JTextField jtf = new JTextField(30);
	JButton all	= new JButton("전체조회");
	JButton search = new JButton("검색");
	JButton rental = new JButton("대여신청");
	JButton insert = new JButton("가입");
	ProductDAO dao = new ProductDAO();
	JLabel empty = new JLabel("    "); 
	JLabel empty1 = new JLabel("    "); 

	public ClientProductFrame(String id) {
		super("상품 목록");
		this.id = id;
		jt.setRowHeight(30);
		jt.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 15));
		combo.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 20));
		all.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 20));
		search.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 20));
		rental.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 20));
		insert.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 20));
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
		
		//모든 레코드를 검색하여 DefaultTableModel에 올리기
		dao.userSelectAll(dt);
		
		//첫 번째 행 선택
		if(dt.getRowCount()>0) jt.setRowSelectionInterval(0, 0);
		
		// DefaultTableCellHeaderRenderer 생성 (가운데 정렬을 위한)
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();

		// DefaultTableCellHeaderRenderer의 정렬을 가운데 정렬로 지정
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		// 정렬할 테이블의 ColumnModel을 가져옴
		TableColumnModel tcmSchedule = jt.getColumnModel();

		// 반복문을 이용하여 테이블을 가운데 정렬로 지정
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
		tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}
		
	}

//	public static void main(String[] args) {
//		new ProductMain();
//	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() ==search) {
			//JComboBox에 선택된 value 가져오기
			String fieldName = combo.getSelectedItem().toString();
			System.out.println("필드명 "+fieldName);
			if(jtf.getText().trim().equals("")) {
				ClientProductDialog.messageBox(this,"검색어 입력!");
				jtf.requestFocus();
				if(dt.getRowCount()>0) jt.setRowSelectionInterval(0, 0);
			}else {
				dao.getUserSearch(dt,fieldName,jtf.getText());
				jtf.setText("");
				if(dt.getRowCount()>0) jt.setRowSelectionInterval(0, 0);
			}
		} else if (e.getSource() == rental) {
			new ClientProductDialog(this, "대여신청", id);
		} else if(e.getSource() == all) {
			dao.userSelectAll(dt);
			System.out.println("전체조회");
			if(dt.getRowCount()>0) jt.setRowSelectionInterval(0, 0);
		} /*else if(e.getSource() == insert) {
			new ProductGUI(this, "대여하기");
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
