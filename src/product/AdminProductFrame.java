package product;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class AdminProductFrame extends JFrame implements ActionListener, KeyListener {

	String[] name = {"제품 코드", "제품 분류", "제품명", "제품 유형", "월 대여료", "점검 주기"};
	
	DefaultTableModel dt = new DefaultTableModel(name,0) {
	      public boolean isCellEditable(int i, int c) {
	            return false;
	        }
	   };
	   
	JTable jt = new JTable(dt);
	JScrollPane jsp = new JScrollPane(jt);
	JPanel p = new JPanel(new BorderLayout());
	public JPanel adminProductPanel = new JPanel( new BorderLayout());
	
	String[] comboName = {"제품 코드", "제품 분류", "제품명", "제품 유형"};
	
	JComboBox combo = new JComboBox(comboName);
	JTextField jtf = new JTextField(40);
	JButton psearch = new JButton("검 색");
	JButton pall = new JButton("전체 조회");
	JButton pinsert = new JButton("추 가");
	JButton pupdate = new JButton("수 정");
	JButton pdelete = new JButton("삭 제");
	JLabel empty = new JLabel("                            ");
	//Font f18= new Font("한컴산뜻돋움", Font.BOLD, 18);
	//Font f15= new Font("한컴산뜻돋움", Font.BOLD, 15);
	
	ProductDAO dao = new ProductDAO();
	
	public AdminProductFrame() {
		
		
		p.setBackground(Color.lightGray);
		//p.setPreferredSize(new Dimension(1400,70));
		p.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
		p.add(combo); p.add(jtf);p.add(psearch); p.add(pall);
		p.add(empty);
		p.add(pinsert); p.add(pupdate); p.add(pdelete);
		adminProductPanel.add(jsp,"Center"); 		adminProductPanel.add(p,"South");
		
		/*jtf.setPreferredSize(new Dimension(0,30));
		combo.setPreferredSize(new Dimension(85,30));
		psearch.setPreferredSize(new Dimension(80,30));
		pall.setPreferredSize(new Dimension(120,30));
		pinsert.setPreferredSize(new Dimension(80,40));
		pupdate.setPreferredSize(new Dimension(80,40));
		pdelete.setPreferredSize(new Dimension(80,40));
		
		combo.setFont(f15);
		psearch.setFont(f15);
		pall.setFont(f15);
		pinsert.setFont(f18);
		pupdate.setFont(f18);
		pdelete.setFont(f18);
		
		jt.setRowHeight(30);
		jt.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 18));*/
		
		setSize(1400,700);
		
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
		
	    jt.getTableHeader().setReorderingAllowed(false);
	    jt.getTableHeader().setResizingAllowed(false);
		
		
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pall.addActionListener(this);
		psearch.addActionListener(this);
		pinsert.addActionListener(this);
		pupdate.addActionListener(this);
		pdelete.addActionListener(this);
		jtf.addKeyListener(this);
	
		dao.userSelectAllByAdmin(dt);
		

		if(dt.getRowCount()>0) jt.setRowSelectionInterval(0, 0);
		
	}	 

	
	
	public void actionPerformed(ActionEvent e) {
		 if(e.getSource() == pinsert) {
				new AdminProductDialog(this,"추가");
			}else if(e.getSource() == pupdate) {
				new AdminProductDialog(this,"수정");
				
			
			
			}else if(e.getSource() == pdelete) {
				int row = jt.getSelectedRow();
				System.out.println("선택 행 : "+row);
				Object obj = jt.getValueAt(row, 0);
				System.out.println("값 : "+obj);
				
				if(dao.userDelete(obj.toString())>0) {
					AdminProductDialog.messageBox(this,"제품 정보 삭제");
					dao.userSelectAllByAdmin(dt);
					if(dt.getRowCount()>0) jt.setRowSelectionInterval(0, 0);
				} else {
					AdminProductDialog.messageBox(this,"제품 정보 삭제 실패");
				}
				
			}else if (e.getSource() == psearch) {
	         String fieldName = combo.getSelectedItem().toString();
	         System.out.println("필드명 "+fieldName);
	         
	         if(jtf.getText().trim().equals("")) {
	            AdminProductDialog.messageBox(this,"검색어를 입력하세요.");
	            jtf.requestFocus();
	         } else {
	            dao.getUserSearchByAdmin(dt,fieldName,jtf.getText());
	            if(dt.getRowCount()>0) jt.setRowSelectionInterval(0, 0);
	            jtf.setText("");
	         }
				
			} if(e.getSource() == pall) {
	         dao.userSelectAllByAdmin(dt);
	         System.out.println("전체 조회");
	         if(dt.getRowCount()>0) jt.setRowSelectionInterval(0, 0);
	      } 
 }



	@Override
	public void keyPressed(KeyEvent e) {	
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ENTER) {
		Toolkit.getDefaultToolkit().beep();
		psearch.doClick();
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
