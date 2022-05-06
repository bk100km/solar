package product;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientProductDialog extends JDialog implements ActionListener {
	// IndexFrame login = new IndexFrame();

	JPanel pw = new JPanel(new GridLayout(10, 1));
	JPanel pc = new JPanel(new GridLayout(10, 1));
	JPanel pem = new JPanel(new GridLayout(10, 1));
	JPanel ps = new JPanel();

	JLabel lable_id = new JLabel("아이디    ");
	JLabel lable_pcode = new JLabel("제품 코드    ");
	JLabel lable_pcate = new JLabel("제품 분류    ");
	JLabel lable_pname = new JLabel("제 품 명    ");
	JLabel lable_ptype = new JLabel("제품 유형    ");
	JLabel lable_pprice = new JLabel("월 대여료    ");
	JLabel lable_pas = new JLabel("점검 주기    ");
	JLabel lable_rreserv = new JLabel("대여신청일    ");
	JLabel lable_rstart = new JLabel("대여시작일    ");
	JLabel lable_rday = new JLabel("대여 기간    ");

	JTextField tf_id = new JTextField();
	JTextField pcode = new JTextField();
	JTextField pcate = new JTextField();
	JTextField pname = new JTextField();
	JTextField ptype = new JTextField();
	JTextField pprice = new JTextField();
	JTextField pas = new JTextField();
	JTextField rreserv = new JTextField(new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis()));
	JTextField rstart = new JTextField("");
	JTextField rday = new JTextField("");

	JLabel em1 = new JLabel();
	JLabel em2 = new JLabel();
	JLabel em3 = new JLabel();
	JLabel em4 = new JLabel();
	JLabel em5 = new JLabel();
	JLabel em6 = new JLabel();
	JLabel em7 = new JLabel();
	JLabel em8 = new JLabel();
	JLabel em9 = new JLabel(" 예시) 2000-01-01  ");
	JLabel em10 = new JLabel(" 개월");

	JButton confirm;
	JButton reset = new JButton("취소");
	ClientProductFrame me;
	ProductDAO dao = new ProductDAO();

	public ClientProductDialog(ClientProductFrame me, String index, String id) {
		super(me, "대여신청");
		this.me = me;

		confirm = new JButton("대여하기");
		confirm.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 20));
		confirm.setPreferredSize(new Dimension(130, 30));
		reset.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 20));
		reset.setPreferredSize(new Dimension(130, 30));

		// text박스에 선택된 레코드의 정보 넣기
		int row = me.jt.getSelectedRow();

		// id.setText( login.tf_id.getText().trim() );
		tf_id.setText(id);
		pcode.setText(me.jt.getValueAt(row, 0).toString());
		pcate.setText(me.jt.getValueAt(row, 1).toString());
		pname.setText(me.jt.getValueAt(row, 2).toString());
		ptype.setText(me.jt.getValueAt(row, 3).toString());
		pprice.setText(me.jt.getValueAt(row, 4).toString());
		pas.setText(me.jt.getValueAt(row, 5).toString());
		// rreserv.setText( me.jt.getValueAt(row, 5).toString() );
		// rstart.setText( me.jt.getValueAt(row, 6).toString() );
		// rday.setText( me.jt.getValueAt(row, 7).toString() );

		tf_id.setEditable(false);
		pcode.setEditable(false);
		pcate.setEditable(false);
		pname.setEditable(false);
		ptype.setEditable(false);
		pprice.setEditable(false);
		pas.setEditable(false);
		rreserv.setEditable(false);

		pw.add(lable_id);
		pw.add(lable_pcode);
		pw.add(lable_pcate);
		pw.add(lable_pname);
		pw.add(lable_ptype);
		pw.add(lable_pprice);
		pw.add(lable_pas);
		pw.add(lable_rreserv);
		pw.add(lable_rstart);
		pw.add(lable_rday);
		pw.setPreferredSize(new Dimension(130, 60));
		lable_id.setHorizontalAlignment(JLabel.RIGHT);
		lable_pcode.setHorizontalAlignment(JLabel.RIGHT);
		lable_pcate.setHorizontalAlignment(JLabel.RIGHT);
		lable_pname.setHorizontalAlignment(JLabel.RIGHT);
		lable_ptype.setHorizontalAlignment(JLabel.RIGHT);
		lable_pprice.setHorizontalAlignment(JLabel.RIGHT);
		lable_pas.setHorizontalAlignment(JLabel.RIGHT);
		lable_rreserv.setHorizontalAlignment(JLabel.RIGHT);
		lable_rstart.setHorizontalAlignment(JLabel.RIGHT);
		lable_rday.setHorizontalAlignment(JLabel.RIGHT);
		lable_id.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		lable_pcode.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		lable_pcate.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		lable_pname.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		lable_ptype.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		lable_pprice.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		lable_pas.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		lable_rreserv.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		lable_rstart.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		lable_rday.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		// lable_id.setFont(getFont().deriveFont(15.0f));

		pc.add(tf_id);
		pc.add(pcode);
		pc.add(pcate);
		pc.add(pname);
		pc.add(ptype);
		pc.add(pprice);
		pc.add(pas);
		pc.add(rreserv);
		pc.add(rstart);
		pc.add(rday);
		pc.setPreferredSize(new Dimension(20, 20));
		tf_id.setHorizontalAlignment(JLabel.CENTER);
		pcode.setHorizontalAlignment(JLabel.CENTER);
		pcate.setHorizontalAlignment(JLabel.CENTER);
		pname.setHorizontalAlignment(JLabel.CENTER);
		ptype.setHorizontalAlignment(JLabel.CENTER);
		pprice.setHorizontalAlignment(JLabel.CENTER);
		pas.setHorizontalAlignment(JLabel.CENTER);
		rreserv.setHorizontalAlignment(JLabel.CENTER);
		rstart.setHorizontalAlignment(JLabel.CENTER);
		rday.setHorizontalAlignment(JLabel.CENTER);
		tf_id.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		pcode.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		pcate.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		pname.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		ptype.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		pprice.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		pas.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		rreserv.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		rstart.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		rday.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));

		pem.add(em1);
		pem.add(em2);
		pem.add(em3);
		pem.add(em4);
		pem.add(em5);
		pem.add(em6);
		pem.add(em7);
		pem.add(em8);
		pem.add(em9);
		pem.add(em10);
		em9.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 15));
		em10.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));

		ps.add(confirm);
		ps.add(reset);

		add(pw, "West");
		add(pc, "Center");
		add(pem, "East");
		add(ps, "South");
		setSize(500, 450);
		setVisible(true);		setLocation(1400,250);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		confirm.addActionListener(this);
		reset.addActionListener(this);
	}

	@Override
	   public void actionPerformed(ActionEvent e) {
	      String btnLabel = e.getActionCommand();
	      String rstart = this.rstart.getText();
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	      Date yesterday = new Date();
	      Calendar calendar = new GregorianCalendar();
	      calendar.add(Calendar.DATE, 0);
	      String today = sdf.format(calendar.getTime());
	      System.out.println(today);
	      Date rstartd = null;
	      if (btnLabel.equals("취소")) {
	         dispose();
	         return;
	      }

	      if (btnLabel.equals("대여하기")) {
	         if (rstart.equals("")) {
	            ClientProductDialog.messageBox(this, "날짜를 다시 입력하세요.");
	         } else {
	            try {
	               yesterday = new Date(sdf.parse(today).getTime());
	               rstartd = sdf.parse(rstart);
	            } catch (ParseException e1) {
	               e1.printStackTrace();
	            }
	         }
	         if (rstartd.before(yesterday)) {
	            System.out.println(rstartd);
	            ClientProductDialog.messageBox(this, "날짜를 다시 입력하세요.");
	         } else if (dao.userListInsert(this) > 0) {
	            messageBox(this, tf_id.getText() + "님 대여가 완료되었습니다.");
	            dispose();
	            // 모든레코드가져와서 DefaultTableModel에 올리기
	            dao.userSelectAll(me.dt);
	         }
	         if (me.dt.getRowCount() > 0) {
	            me.jt.setRowSelectionInterval(0, 0);// 첫번째 행 선택
	         } else {
	            messageBox(this, "대여되지 않았습니다.");
	         }
	      }
	   }

	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog((Component) obj, message);
	}
}